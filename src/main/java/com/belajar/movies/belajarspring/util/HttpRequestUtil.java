package com.belajar.movies.belajarspring.util;

import com.belajar.movies.belajarspring.util.exception.BadRequestException;
import com.belajar.movies.belajarspring.views.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class HttpRequestUtil {

    public static RestResponse sendGetRequest(String url){
        RestResponse restResponse = new RestResponse();
        OkHttpClient httpClient = new OkHttpClient();
        String statusCode ="500";
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("key", "5345f2df16e151ab11dffd976ff7e6d5")
                    .build();

            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new BadRequestException(ErrorCode.ERROR.INTERNAL_SERVER_ERROR);

            // Get response body

            restResponse.setResponse(response.body().string());
            restResponse.setHttpCode(String.valueOf(response.code()));
            log.info("ini bodynya : "+response.body().string() +" || codeRest :"+response.code());

        }catch (Exception es){
            log.error(es.getMessage());
        }
        return restResponse;
    }
}
