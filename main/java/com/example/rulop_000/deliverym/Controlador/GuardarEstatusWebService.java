package com.example.rulop_000.deliverym.Controlador;

import com.example.rulop_000.deliverym.Entidad.GuardarEstatus;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class GuardarEstatusWebService {
    private static final String URL ="http://172.20.10.3/DeliveryManagementWebService/GuardarEstatusEntrega";

    public GuardarEstatus guardarEstatus(GuardarEstatus gEstatus)
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("user", "pass");
        requestHeaders.setAuthorization(httpAuthentication);
        HttpEntity<GuardarEstatus> requestEntity = new HttpEntity<>(gEstatus,requestHeaders);
        try {
            return restTemplate.exchange(URL, HttpMethod.POST, requestEntity, GuardarEstatus.class).getBody();
        }catch (Exception e){
            return null;
        }

    }
}
