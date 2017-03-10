package com.example.rulop_000.deliverym.Controlador;

import com.example.rulop_000.deliverym.Entidad.Cliente;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rulop_000 on 29/07/2016.
 */
public class AgregarClienteWebService {
    private static final String URL ="http://172.20.10.3/DeliveryManagementWebService/InsertarClientePersona";

    public Cliente setClientePersona (Cliente cliente){
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("user","pass");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json"));
        httpHeaders.setAuthorization(httpAuthentication);

        HttpEntity<Cliente> entity = new HttpEntity<>(cliente, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        Cliente cliente2 = new Cliente("","","","","","",0,"",0,"","","",0) ;
        try{
            cliente2 = restTemplate.exchange(URL, HttpMethod.POST,entity,Cliente.class).getBody();
        }catch (Exception e){
        }
        return cliente2;
    }

}
