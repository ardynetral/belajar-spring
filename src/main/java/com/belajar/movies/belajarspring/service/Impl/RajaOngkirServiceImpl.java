package com.belajar.movies.belajarspring.service.Impl;

import com.belajar.movies.belajarspring.controller.config.ConfigProperties;
import com.belajar.movies.belajarspring.service.RajaOngkirService;
import com.belajar.movies.belajarspring.util.HttpRequestUtil;
import com.belajar.movies.belajarspring.util.JsonUtil;
import com.belajar.movies.belajarspring.views.response.RajaOngkirResp;
import com.belajar.movies.belajarspring.views.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class RajaOngkirServiceImpl implements RajaOngkirService {

    @Autowired
    private ConfigProperties configProperties;

    @Override
    public RajaOngkirResp viewProvince(Long id){
        var rjResp = new RajaOngkirResp();
        try {
            RestResponse restResponse = HttpRequestUtil.sendGetRequest( configProperties.getUrlRajaOngkir()+"?id="+id);
            if (restResponse.getHttpCode().equalsIgnoreCase("200")){
                Map<String, Object> map = (Map<String, Object>) JsonUtil.parse(restResponse.getResponse(), HashMap.class);
                map = (Map<String, Object>) JsonUtil.parse(JsonUtil.toJson(map.get("rajaongkir")), HashMap.class);
                Map<String, Object> results = (Map<String, Object>) JsonUtil.parse(JsonUtil.toJson(map.get("results")), HashMap.class);
                rjResp.setProvinceId(String.valueOf(results.get("province_id")));
                rjResp.setProvinceName(String.valueOf(results.get("province")));
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return rjResp;
    }

}
