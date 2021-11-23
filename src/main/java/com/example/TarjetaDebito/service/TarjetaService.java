package com.example.TarjetaDebito.service;

import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.repository.TarjetaRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TarjetaService {
    @Autowired
    private TarjetaRepositoryDao tarjetaRepositoryDao;

    public void nuevaSolicitudTarjeta(Tarjeta tarjeta) {
        tarjetaRepositoryDao.save(tarjeta);
    }

    public List<Tarjeta> getTarjetas() {
        return (List<Tarjeta>) tarjetaRepositoryDao.findAll();
    }
}
