package com.example.rulop_000.deliverym.Controlador;

import com.example.rulop_000.deliverym.Entidad.OlvidoContrasena;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rulop_000 on 28/07/2016.
 */
public class OlvidoContrasenaWebService {
    private static final String URL ="http://172.20.10.3/DeliveryManagementWebService/OlvidoContrasena";

    public OlvidoContrasena setContra(OlvidoContrasena olvidasteTuContrasena){
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("user","pass");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json"));
        httpHeaders.setAuthorization(httpAuthentication);

        HttpEntity<OlvidoContrasena> entity = new HttpEntity<>(olvidasteTuContrasena, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        OlvidoContrasena olvidasteTuContrasena1 = new OlvidoContrasena(0,0,"","");
        try {
            olvidasteTuContrasena1 = restTemplate.exchange(URL, HttpMethod.POST, entity, OlvidoContrasena.class).getBody();
        }catch (Exception e){

        }
            return olvidasteTuContrasena1;
    }
}
