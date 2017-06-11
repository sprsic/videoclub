package com.sprsic.util;

import com.sprsic.model.common.FieldErrorModel;
import com.sprsic.model.common.FormErrorModel;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public final class ResourceUtil {

    private ResourceUtil() {
    }

    /**
     * Handle all filed errors
     *
     * @param formErrorModel
     * @param bindingResult
     */

    public static void fieldErrors(FormErrorModel formErrorModel, BindingResult bindingResult) {

        for (ObjectError objectError : bindingResult.getAllErrors()) {
            ObjectError error = objectError;
            if (error instanceof FieldError) {
                formErrorModel.getFieldErrors().add(new FieldErrorModel(((FieldError) error).getField(), (error).getCode()));
            }
        }
    }

}
