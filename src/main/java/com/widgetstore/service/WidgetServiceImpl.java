package com.widgetstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widgetstore.dto.WidgetRequestDTO;
import com.widgetstore.exception.WidgetNotFoundException;
import com.widgetstore.model.Widget;
import com.widgetstore.repository.WidgetRepository;

@Service
public class WidgetServiceImpl implements WidgetService {

	WidgetRepository repository;

	@Autowired
	public WidgetServiceImpl(WidgetRepository repository) {
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see com.widgetstore.service.WidgetService#getWidgetById(java.lang.Integer)
	 */
	@Override
	@Transactional
	public Widget getWidgetById(Integer id) throws WidgetNotFoundException {
		return repository.findById(id).orElseThrow(() -> new WidgetNotFoundException(String.format("Widget with id %d not found.", id)));
	}

	/* (non-Javadoc)
	 * @see com.widgetstore.service.WidgetService#getAllWidgets(int)
	 */
	@Override
	public List<Widget> getAllWidgets(int limit) {
		return Optional.ofNullable(repository.getWidgets(limit)).orElse(Collections.emptyList());
	}


	/* (non-Javadoc)
	 * @see com.widgetstore.service.WidgetService#deleteWidget(java.lang.Integer)
	 */
	@Override
	public String deleteWidget(Integer id) {
		repository.deleteById(id);

		return String.format("Succesfully deleted widget with id: %d", id);
	}

	/* (non-Javadoc)
	 * @see com.widgetstore.service.WidgetService#createWidget(com.widgetstore.dto.WidgetRequestDTO)
	 */
	@Override
	@Transactional
	public Widget createWidget(WidgetRequestDTO requestDTO) {

		Widget newWidget = new Widget();
		newWidget.setHeight(requestDTO.getHeight());
		newWidget.setWidth(requestDTO.getWidth());
		newWidget.setXCoordinate(requestDTO.getxCoordinate());
		newWidget.setYCoordinate(requestDTO.getyCoordinate());
		if(requestDTO.getzCoordinate() == null) {
			int maxZCoord = repository.findMax();
			newWidget.setZCoordinate(maxZCoord + 1);
		}
		else {
			newWidget.setZCoordinate(requestDTO.getzCoordinate());
		}

		Widget widgetByZCoord = repository.findByZCoord(requestDTO.getzCoordinate());
		if(widgetByZCoord != null) {
			shiftZCoordinate(requestDTO);
		}

		return repository.save(newWidget);
	}

	/**
	 * Get all widgets greater than or equal to the zCoordinate of the new widget.
	 * Traverse through each widget and check if the zCoordinate is greater than or equal
	 * to the incremented z coordinate. This check is used to ensure we are only updating the widgets that needs to be shifted.
	 *
	 * @param requestDTO
	 */
	private void shiftZCoordinate(WidgetRequestDTO requestDTO) {
		List<Widget> allWidgets = repository.findAllZCoord(requestDTO.getzCoordinate());
		List<Widget> widgetsToBeShifted = new ArrayList<>();
		int zcoord = requestDTO.getzCoordinate();
		for(Widget widget: allWidgets) {
			if(isUpdateRequest(requestDTO, widget)) {
				continue;
			}
			zcoord = zcoord + 1;
			if(widget.getZCoordinate() >= zcoord ) {
				break;
			}
			else {
				widget.setZCoordinate(zcoord);
				applyLastModifiedDate(widget);
				widgetsToBeShifted.add(widget);
			}
		}

		repository.saveAll(widgetsToBeShifted);

	}

	/**
	 * @param requestDTO
	 * @param widget
	 * @return
	 */
	private boolean isUpdateRequest(WidgetRequestDTO requestDTO, Widget widget) {
		return requestDTO.getId() != null  && requestDTO.getId().equals(widget.getId());
	}

	/* (non-Javadoc)
	 * @see com.widgetstore.service.WidgetService#updateWidget(com.widgetstore.dto.WidgetRequestDTO)
	 */
	@Override
	@Transactional
	public Widget updateWidget(WidgetRequestDTO requestDTO) throws WidgetNotFoundException {
		Widget widget = repository.findById(requestDTO.getId()).orElse(null);

		if(widget == null) {
			throw new WidgetNotFoundException(String.format("Widget with id %d not found.", requestDTO.getId()));
		}
		else {
			if(isModified(requestDTO.getHeight(), widget.getHeight())) {
				widget.setHeight(requestDTO.getHeight());
			}
			if(isModified(requestDTO.getWidth(), widget.getWidth())) {
				widget.setWidth(requestDTO.getWidth());
			}
			if(isModified(requestDTO.getxCoordinate(), widget.getXCoordinate())) {
				widget.setXCoordinate(requestDTO.getxCoordinate());
			}
			if(isModified(requestDTO.getyCoordinate(), widget.getYCoordinate())) {
				widget.setYCoordinate(requestDTO.getyCoordinate());
			}

			if(isModified(requestDTO.getzCoordinate(), widget.getZCoordinate())) {
				Widget widgetByZCoord = repository.findByZCoord(requestDTO.getzCoordinate());
				if(widgetByZCoord != null) {
					shiftZCoordinate(requestDTO);
				}
				widget.setZCoordinate(requestDTO.getzCoordinate());
			}

		}
		applyLastModifiedDate(widget);
		return repository.save(widget);
	}

	/**
	 * @param widget
	 */
	private void applyLastModifiedDate(Widget widget) {
		LocalDateTime now = LocalDateTime.now();
		widget.setLastUpdateTimestamp(now);
	}


	/**
	 * Check if the value is modified
	 * @param newObj
	 * @param existingObj
	 * @return
	 */
	private boolean isModified(Integer newObj, Integer existingObj) {
		if(newObj == null) {
			return false;
		}

		return !existingObj.equals(newObj);
	}

}
