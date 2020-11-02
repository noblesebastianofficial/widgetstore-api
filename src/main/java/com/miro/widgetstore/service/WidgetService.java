package com.miro.widgetstore.service;

import java.util.List;

import com.miro.widgetstore.model.Widget;
import com.miro.widgetstore.dto.WidgetRequestDTO;
import com.miro.widgetstore.exception.WidgetNotFoundException;

public interface WidgetService {

	/**
	 * Get widget by id
	 * 
	 * @param id
	 * @return widget details
	 * @throws WidgetNotFoundException 
	 */
	public Widget getWidgetById(Integer id) throws WidgetNotFoundException;

	/**
	 * Create a new widget
	 * 
	 * @param
	 * @return saved widget details
	 */
	public Widget createWidget(WidgetRequestDTO requestDTO);

	/**
	 * Update widget
	 * 
	 * @param requestDTO
	 * @return updated widget
	 * @throws WidgetNotFoundException 
	 */
	public Widget updateWidget(WidgetRequestDTO requestDTO) throws WidgetNotFoundException;

	/**
	 * Get all widgets
	 * 
	 * @param limit - default 10 if no limit is specified
	 * @return
	 */
	public List<Widget> getAllWidgets(int limit);

	/**
	 * Delete widget by Id
	 * 
	 * @param id
	 */
	public String deleteWidget(Integer id);

}
