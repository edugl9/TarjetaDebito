package com.example.TarjetaDebito.service;

import com.example.TarjetaDebito.entity.Compra;
import com.example.TarjetaDebito.entity.Cuenta;
import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.repository.TarjetaRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepositoryDao tarjetaRepositoryDao;

    @Autowired
    RestTemplate restTemplate = new RestTemplate();


    public void nuevaSolicitudTarjeta(Tarjeta tarjeta) {

        tarjetaRepositoryDao.save(tarjeta);
    }

    public List<Tarjeta> getTarjetas() {
        return (List<Tarjeta>) tarjetaRepositoryDao.findAll();
    }

    public Optional<Tarjeta> tarjetaPorId(Integer idTajeta) {
        return tarjetaRepositoryDao.findById(idTajeta);
    }

    public List<String> tarjetaPorIdTarjeta(Integer id) {
        return tarjetaRepositoryDao.findTarjetaporIdTarjeta(id);
    }

    public Tarjeta activacionTarjeta(Tarjeta tarjeta) {
        return tarjetaRepositoryDao.save(tarjeta);
    }
//    public void activacionTarjeta(Integer idTarjeta, Integer numTarjeta, String estado, Integer saldo) {
//        tarjetaRepositoryDao.activarTarjeta(estado,saldo,idTarjeta,numTarjeta);
//    }

    public void nuevoLimExtraccion(Integer idTarjeta, Integer numTarjeta, Integer limExtraccion) {
        tarjetaRepositoryDao.nuevoLimite(limExtraccion, idTarjeta, numTarjeta);
    }


    public void bloqueoTarjeta(Integer idTarjeta, Integer numTarjeta, String estado) {
        tarjetaRepositoryDao.bloquearTarjeta(estado, idTarjeta, numTarjeta);
    }

    public List<Cuenta> getCuentas() {
        ResponseEntity<Cuenta[]> cuentaResponseEntity = restTemplate.getForEntity("http://localhost:8081/cuenta/listacuentas", Cuenta[].class);
        Cuenta[] cuenta = cuentaResponseEntity.getBody();
        List<Cuenta> cuentas = Arrays.asList(cuenta);
        return cuentas;
    }

    public Cuenta buscarCuenta(Integer numeroCuenta) {
        List<Cuenta> cuentas = getCuentas();
        Cuenta cuenta = new Cuenta();
        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                return c;
            }
        }
        return cuenta;
    }


    public ResponseEntity<String> getCompras(Integer numTarjeta, Calendar fechaDesde, Calendar fechaHasta) {
        return (ResponseEntity<String>) tarjetaRepositoryDao.consultaComprasFecha(numTarjeta, fechaDesde,fechaHasta);
    }
}
