package com.example.TarjetaDebito.service;

import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.repository.TarjetaRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepositoryDao tarjetaRepositoryDao;

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

    public void nuevoLimExtraccion(Integer idTarjeta,Integer numTarjeta, Integer limExtraccion) {
        tarjetaRepositoryDao.nuevoLimite(limExtraccion, idTarjeta, numTarjeta);
    }


    public void bloqueoTarjeta(Integer idTarjeta, Integer numTarjeta, String estado) {
        tarjetaRepositoryDao.bloquearTarjeta(estado,idTarjeta,numTarjeta);
    }

}
