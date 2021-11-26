package com.example.TarjetaDebito.controller;

import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                return ResponseEntity.ok("Fallida");
            }
        }
        tarjetaService.nuevaSolicitudTarjeta(tarjeta);
        return ResponseEntity.ok("Finalizada");
    }

    // Detalle de tarjeta debito
//    @GetMapping("/{idTarjeta}")
//    public Optional<Tarjeta> getTarjetaPorUsuario(@PathVariable("idTarjeta") Integer idTarjeta){
//        return tarjetaService.tarjetaPorId(idTarjeta);
//    }
//----------------------------  Ó   ---------------------------------------------------------
    @GetMapping("/{idtarjeta}")
    public List<String> getTarjetaPorIdTarjeta(@PathVariable("idtarjeta") Integer idtarjeta){
        return tarjetaService.tarjetaPorIdTarjeta(idtarjeta);
    }

    // Activacion de tarjeta en "pendiente activacion"
    @PutMapping("/activacion")
    public ResponseEntity<String> activacionTarjeta(@RequestBody Tarjeta tarjeta){
        if ( tarjetaService.tarjetaPorId(tarjeta.getIdTarjeta()).isEmpty() || tarjeta.getSaldo()<10000 || tarjeta.getEstado().contains("activada")==false ){
            return ResponseEntity.ok("Fallida");
        } /*else if ( tarjeta.getEstado().contains("activada") ){
            return ResponseEntity.ok("Su tarjeta ya se encuentra activada");
        } */
        tarjetaService.activacionTarjeta(tarjeta);
        return ResponseEntity.ok("Finalizada");
    }

    // Gestion limites extraccion
    @PutMapping("/limiteExtraccion/{numTarjeta}/{limExtraccion}")
    public ResponseEntity<String> nuevoLimite(@PathVariable Integer numTarjeta, @PathVariable Integer limExtraccion){
        tarjetaService.nuevoLimExtraccion(numTarjeta,limExtraccion);
        String mensaje = "Nuevo limite de extraccion de " + numTarjeta + " es " + limExtraccion;
        return ResponseEntity.ok(mensaje);
    }

}
