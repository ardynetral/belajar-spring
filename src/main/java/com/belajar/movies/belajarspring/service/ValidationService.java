package com.belajar.movies.belajarspring.service;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;

/**
 * Created by yandi on 9/14/17.
 */
public interface ValidationService {
    Map<String, List<Map<String, String>>> errorMessage(BindingResult bindingResult);
}
