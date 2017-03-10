package com.example.rulop_000.deliverym.Controlador;

import com.example.rulop_000.deliverym.Entidad.Pedidos;
import com.example.rulop_000.deliverym.Entidad.PedidosWS;

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
public class PedidosDireccionesWebService {
    private static final String URL ="http://172.20.10.3/DeliveryManagementWebService/Direcciones";

    public ArrayList<Pedidos> getPedidos(PedidosWS pedidosWS){
        HttpAuthentication httpAuthentication = new HttpBasicAuthentication("user","pass");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json"));
        httpHeaders.setAuthorization(httpAuthentication);

        HttpEntity<PedidosWS> entity = new HttpEntity<>(pedidosWS, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();
        try {
            pedidos = new ArrayList<>(Arrays.asList(restTemplate.exchange(URL, HttpMethod.POST, entity, Pedidos[].class).getBody()));
        }catch (Exception E){

        }
        return pedidos;
    }
}
