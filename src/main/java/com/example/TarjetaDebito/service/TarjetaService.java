package com.example.TarjetaDebito.service;

import com.example.TarjetaDebito.entity.Tarjeta;
import com.example.TarjetaDebito.repository.TarjetaRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Tarjeta reposicionTarjeta(Tarjeta tarjeta) {
        return tarjetaRepositoryDao.save(tarjeta);
    }
}
