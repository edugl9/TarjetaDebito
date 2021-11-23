package com.example.TarjetaDebito.controller;

import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<String> nuevaSolicitudTarjeta(@RequestBody Tarjeta tarjeta){
        // añadir auntenticacion de usuario
        List<Tarjeta> tarjetaList = tarjetaService.getTarjetas();
        for (Tarjeta tarjetas:tarjetaList) {
            if (tarjetas.getNumTarjeta().equals(tarjeta.getNumTarjeta()) || tarjeta.getEdadUserTest()<18) {
                return ResponseEntity.ok("Fallo de solicitud");
            }
        }
        tarjetaService.nuevaSolicitudTarjeta(tarjeta);
        return ResponseEntity.ok("Operacion exitosa");
    }
}
