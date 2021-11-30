package com.example.TarjetaDebito.controller;

import com.example.TarjetaDebito.entity.Compra;
import com.example.TarjetaDebito.entity.Cuenta;
import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    // Solicitud de tarjeta debito
    @PostMapping
    public ResponseEntity<String> nuevaSolicitudTarjeta(@RequestBody Tarjeta tarjeta) {
        // añadir auntenticacion de usuario
        Cuenta cuenta = tarjetaService.buscarCuenta(tarjeta.getNumeroCuenta());
        if (cuenta != null) {
            List<Tarjeta> tarjetaList = tarjetaService.getTarjetas();
            for (Tarjeta tarjetas : tarjetaList) {
                if (tarjetas.getNumTarjeta().equals(tarjeta.getNumTarjeta()) || tarjeta.getEdadUserTest() < 18 || tarjeta.getAuntenticado().contains("si") == false) {
                    return ResponseEntity.ok("Fallida");
                }
            }
            tarjetaService.nuevaSolicitudTarjeta(tarjeta);
            return ResponseEntity.ok("Finalizada");
        }else {
            return ResponseEntity.ok("Fallida");
        }
    }

    // Detalle de tarjeta debito
//    @GetMapping("/{idTarjeta}")
//    public Optional<Tarjeta> getTarjetaPorUsuario(@PathVariable("idTarjeta") Integer idTarjeta){
//        return tarjetaService.tarjetaPorId(idTarjeta);
//    }
//----------------------------  Ó   ---------------------------------------------------------
    @GetMapping("/{idtarjeta}")
    public List<String> getTarjetaPorIdTarjeta(@PathVariable("idtarjeta") Integer idtarjeta) {
        return tarjetaService.tarjetaPorIdTarjeta(idtarjeta);
    }

    // Activacion de tarjeta en "pendiente activacion"
    @PutMapping("/activacion")
    public ResponseEntity<String> activacionTarjeta(@RequestBody Tarjeta tarjeta) {
        if (tarjetaService.tarjetaPorId(tarjeta.getIdTarjeta()).isEmpty() || tarjeta.getSaldo() < 10000 || tarjeta.getEstado().contains("activada") == false) {
            return ResponseEntity.ok("Fallida");
        }
        tarjetaService.activacionTarjeta(tarjeta);
        return ResponseEntity.ok("Finalizada");
    }

    // Gestion limites extraccion
    @PutMapping("/limiteExtraccion/{idTarjeta}/{numTarjeta}/{limExtraccion}")
    public ResponseEntity<String> nuevoLimite(@PathVariable Integer idTarjeta, @PathVariable Integer numTarjeta, @PathVariable Integer limExtraccion) {
        if (tarjetaService.tarjetaPorId(idTarjeta).isEmpty()) {
            return ResponseEntity.ok("Fallida");
        }
        tarjetaService.nuevoLimExtraccion(idTarjeta, numTarjeta, limExtraccion);
        String mensaje = "Nuevo limite de extraccion de " + numTarjeta + " es " + limExtraccion;
        return ResponseEntity.ok(mensaje);
    }

    // Bloqueo de tarjeta
    @PutMapping("/bloqueoTarjeta/{idTarjeta}/{numTarjeta}/{estado}")
    public ResponseEntity<String> bloqueoTarjeta(@PathVariable Integer idTarjeta, @PathVariable Integer numTarjeta, @PathVariable String estado) {
        if (tarjetaService.tarjetaPorId(idTarjeta).isEmpty() || numTarjeta == null || estado.contains("bloqueada") == false) {
            return ResponseEntity.ok("Fallida");
        }
        tarjetaService.bloqueoTarjeta(idTarjeta, numTarjeta, estado);
        return ResponseEntity.ok("Finalizada");
    }

    // Consulta compras
    @GetMapping("/compras/{numTarjeta}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<String> getCompras(@PathVariable Integer numTarjeta, @PathVariable Calendar fechaDesde, @PathVariable Calendar fechaHasta){
        return tarjetaService.getCompras(numTarjeta,fechaDesde,fechaHasta);
    }

}