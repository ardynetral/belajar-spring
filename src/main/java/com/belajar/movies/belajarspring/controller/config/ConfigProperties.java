package com.belajar.movies.belajarspring.controller.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@Data
public class ConfigProperties {

    @Value("${spring.custom.config.urlRajaOngkir}")
    private String urlRajaOngkir;
}
