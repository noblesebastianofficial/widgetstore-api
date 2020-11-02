package com.miro.widgetstore.util;

import com.miro.widgetstore.dto.WidgetRequestDTO;
import com.miro.widgetstore.exception.WidgetValidationException;

/**
 * Util class to check the conditions
 * @author Noble Sebastian
 * @version 1.0
 */
public class WidgetUtil {
    /**
     * Throws validation exception the pagination limit exceeds the maximum
     * limit:500
     *
     * @param limit
     * @throws WidgetValidationException
     */
    public static void validateMaxLimit(int limit) throws WidgetValidationException {
        if(limit > 500) {
            throw new WidgetValidationException("Page limit for listing widgets should not exceed 500");
        }
    }
    /**
     * Check if the value of width and height is greater than zero
     *
     * @param requestDTO
     * @throws WidgetValidationException
     */
    public static void validate(WidgetRequestDTO requestDTO) throws WidgetValidationException {
        if(requestDTO.getWidth() < 1 || requestDTO.getHeight() < 1 ) {
            throw new WidgetValidationException("Value of Width and Height attributes should be greater than zer0");
        }

    }
}
