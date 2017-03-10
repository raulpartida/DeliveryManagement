package com.example.rulop_000.deliverym.Controlador;

import com.example.rulop_000.deliverym.Entidad.LoginRepartidor;
import com.example.rulop_000.deliverym.Entidad.PerfilRepartidor;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rulop_000 on 02/05/2016.
 */
public class LoginWebService {
    private static final String URL ="http://172.20.10.3/DeliveryManagementWebService/Login";

    public PerfilRepartidor getRepartidor(LoginRepartidor logRep){
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("user","pass");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json"));
        httpHeaders.setAuthorization(httpAuthentication);

        HttpEntity<LoginRepartidor> entity = new HttpEntity<>(logRep, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        PerfilRepartidor repartidor= new PerfilRepartidor(0,"","","",0,"");
        try {
            repartidor = restTemplate.exchange(URL, HttpMethod.POST, entity, PerfilRepartidor.class).getBody();
        }catch (Exception e ){

        }
        return repartidor;
    }
}
