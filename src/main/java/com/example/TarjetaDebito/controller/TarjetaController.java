package com.example.TarjetaDebito.controller;

import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    // Solicitud de tarjeta debito
    @PostMapping
    public ResponseEntity<String> nuevaSolicitudTarjeta(@RequestBody Tarjeta tarjeta){
        // añadir auntenticacion de usuario
        List<Tarjeta> tarjetaList = tarjetaService.getTarjetas();
        for (Tarjeta tarjetas:tarjetaList) {
            if (tarjetas.getNumTarjeta().equals(tarjeta.getNumTarjeta()) || tarjeta.getEdadUserTest()<18 || tarjeta.getAuntenticado().contains("si")==false) {
                return ResponseEntity.ok("Solicitud Fallida");
            }
        }
        tarjetaService.nuevaSolicitudTarjeta(tarjeta);
        return ResponseEntity.ok("Solicitud Finalizada");
    }
//    // reposicion o renovacion de tarjeta debito
//    @PutMapping("/reposicion")
//    public ResponseEntity<String> reposicionTarjeta(@RequestBody Tarjeta tarjeta){
//        if (tarjetaService.tarjetaPorId(tarjeta.getIdTarjeta()).isEmpty() ){
//            return ResponseEntity.ok("Solicitud Fallida");
//        }
//        tarjetaService.reposicionTarjeta(tarjeta);
//        return ResponseEntity.ok("Solicitud Finalizada");
//    }

    // Detalle de tarjeta debito
    @GetMapping("/{idTarjeta}")
    public Optional<Tarjeta> getTarjetaPorUsuario(@PathVariable("idTarjeta") Integer idTarjeta){
        return tarjetaService.tarjetaPorId(idTarjeta);
    }

}
