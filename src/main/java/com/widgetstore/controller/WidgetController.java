package com.widgetstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.widgetstore.dto.WidgetRequestDTO;
import com.widgetstore.exception.WidgetNotFoundException;
import com.widgetstore.exception.WidgetValidationException;
import com.widgetstore.model.Widget;
import com.widgetstore.service.WidgetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	@GetMapping("/widgets")
	public ResponseEntity<List<Widget>> getAllWidgets(@RequestParam(name = "limit", defaultValue = "10", required = false) int limit) {
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
		validate(requestDTO);
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
		validate(requestDTO);
		Widget widget = service.updateWidget(requestDTO);
		return new ResponseEntity<Widget>(widget, HttpStatus.OK);
	}

	/**
	 * Check if the value of width and height is greater than zero
	 * 
	 * @param requestDTO
	 * @throws WidgetValidationException
	 */
	private void validate(WidgetRequestDTO requestDTO) throws WidgetValidationException {
		if(requestDTO.getWidth() < 1 || requestDTO.getHeight() < 1 ) {
			throw new WidgetValidationException("Value of Width and Height attributes should be greater than zer0");
		}
		
	}


}
