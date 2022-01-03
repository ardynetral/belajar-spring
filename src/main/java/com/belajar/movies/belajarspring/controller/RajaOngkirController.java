package com.belajar.movies.belajarspring.controller;

import com.belajar.movies.belajarspring.global.Global;
import com.belajar.movies.belajarspring.global.Routes;
import com.belajar.movies.belajarspring.service.RajaOngkirService;
import com.belajar.movies.belajarspring.util.Response;
import com.belajar.movies.belajarspring.util.ResponseSuccess;
import com.belajar.movies.belajarspring.views.response.RajaOngkirResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.API_V1 + Routes.RAJAONGKIR)
public class RajaOngkirController {

    @Autowired
    private RajaOngkirService rajaOngkirService;
    private ResponseSuccess responseSuccess = new ResponseSuccess();

    @GetMapping(Routes.PROVINCE + Routes.ID)
    public ResponseEntity<Response> getProvince(@PathVariable Long id){
        RajaOngkirResp data = rajaOngkirService.viewProvince(id);
        responseSuccess.setData(data);
        responseSuccess.setMessage("success");
        return Global.resSuccess(responseSuccess);

    }
}
