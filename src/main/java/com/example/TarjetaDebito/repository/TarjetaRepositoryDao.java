package com.example.TarjetaDebito.repository;

import com.example.TarjetaDebito.entity.Tarjeta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TarjetaRepositoryDao extends CrudRepository<Tarjeta, Integer> {
}
