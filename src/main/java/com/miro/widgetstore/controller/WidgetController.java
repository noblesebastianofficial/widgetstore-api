package com.miro.widgetstore.controller;

import com.miro.widgetstore.model.Widget;
import com.miro.widgetstore.dto.WidgetRequestDTO;
import com.miro.widgetstore.exception.WidgetNotFoundException;
import com.miro.widgetstore.exception.WidgetValidationException;
import com.miro.widgetstore.service.WidgetService;
import com.miro.widgetstore.util.WidgetUtil;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author Noble Sebastian
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class WidgetController {

	@Autowired
	WidgetService service;

	/**
	 * Get all widgets
	 *
	 * @return list of widgets
	 */
	@SneakyThrows
	@GetMapping("/widgets")
	public ResponseEntity<List<Widget>> getAllWidgets(@RequestParam(name = "limit", defaultValue = "10", required = false) int limit) {
		WidgetUtil.validateMaxLimit(limit);
		List<Widget> widget = service.getAllWidgets(limit);
		return new ResponseEntity<List<Widget>>(widget, HttpStatus.OK);
	}

	/**
	 * Get widget by id
	 * 
	 * @return requested widget
	 * @throws WidgetNotFoundException 
	 */
	@GetMapping("/widgets/{id}")
	public ResponseEntity<Widget> getWidgetById(@PathVariable(value = "id") Integer id) throws WidgetNotFoundException {
		Widget widget = service.getWidgetById(id);
		return new ResponseEntity<Widget>(widget, HttpStatus.OK);
	}
	
	
	/**
	 * Delete a widget by its id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/widgets/{id}")
	public ResponseEntity<String> deleteWidgetById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<String>(service.deleteWidget(id), HttpStatus.OK);
	}

	/**
	 * Inserts or Updates Widget
	 * 
	 * @param requestDTO
	 * @return saved widget
	 * @throws WidgetNotFoundException 
	 * @throws WidgetValidationException 
	 */
	@ApiOperation(notes="Service for creating widget", value = "Create Widget")
	@PostMapping("/widgets")
	public ResponseEntity<Widget> createWidget(@RequestBody WidgetRequestDTO requestDTO) throws WidgetNotFoundException, WidgetValidationException {
		WidgetUtil.validate(requestDTO);
		Widget widget = service.createWidget(requestDTO);
		return new ResponseEntity<Widget>(widget, HttpStatus.OK);
	}

	/**
	 * Inserts or Updates Widget
	 *
	 * @param requestDTO
	 * @return saved widget
	 * @throws WidgetNotFoundException
	 * @throws WidgetValidationException
	 */
	@ApiOperation(notes="Service for Update widget", value = "Update Widget")
	@PutMapping("/widgets")
	public ResponseEntity<Widget> updateWidget(@RequestBody WidgetRequestDTO requestDTO) throws WidgetNotFoundException, WidgetValidationException {
		WidgetUtil.validate(requestDTO);
		Widget widget = service.updateWidget(requestDTO);
		return new ResponseEntity<Widget>(widget, HttpStatus.OK);
	}


}
