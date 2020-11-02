package com.widgetstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Noble Sebastian
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponseDTO {
	private int status;
	private String message;
}
