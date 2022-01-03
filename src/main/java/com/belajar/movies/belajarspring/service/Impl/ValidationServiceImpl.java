package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.service.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;

/**
 * Created by yandi on 9/14/17.
 */
@Service("validationService")
public class ValidationServiceImpl implements ValidationService {
    @Override
    public Map<String, List<Map<String, String>>> errorMessage(BindingResult bindingResult) {
        Map<String, List<Map<String, String>>> errors = new HashMap<>();
        List<Map<String, String>> listErrorMessage = new ArrayList<>();
        for(ObjectError error: bindingResult.getAllErrors()) {
            Map<String, String> errorMessage = new HashMap<>();
            if(error instanceof FieldError) {
                errorMessage.put("field", ((FieldError)error).getField());
            }
            errorMessage.put("message", error.getDefaultMessage());
            listErrorMessage.add(errorMessage);
        }

        Collections.sort(listErrorMessage, fieldComparator);

        errors.put("errors", listErrorMessage);
        return errors;
    }

    private final static Comparator<Map<String, String>> fieldComparator = (val1, val2) -> {
        return val1.get("field").compareTo(val2.get("field"));
    };
}
