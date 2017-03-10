package com.example.rulop_000.deliverym.Controlador;

import com.example.rulop_000.deliverym.Entidad.Mercancia;
import com.example.rulop_000.deliverym.Entidad.MercanciaWS;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class MercanciaWebService {
    private static final String URL ="http://172.20.10.3/DeliveryManagementWebService/MercanciaEntrega";

    public ArrayList<Mercancia> getMercancia(MercanciaWS mercanciaWS){
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("user","pass");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json"));
        httpHeaders.setAuthorization(httpAuthentication);

        HttpEntity<MercanciaWS> entity = new HttpEntity<>(mercanciaWS, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<Mercancia> mercancias = new ArrayList<Mercancia>();
        try {
            mercancias = new ArrayList<>(Arrays.asList(restTemplate.exchange(URL, HttpMethod.POST, entity, Mercancia[].class).getBody()));
        }catch (Exception e){

        }
        return mercancias;
    }
}
