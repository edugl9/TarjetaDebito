package com.example.TarjetaDebito.repository;

import com.example.TarjetaDebito.entity.Tarjeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TarjetaRepositoryDao extends CrudRepository<Tarjeta, Integer> {

    // funciona pero sin mostrar columnas
    @Query(value = "Select t.numTarjeta, t.vencimiento, t.estado, t.limExtraccion from Tarjeta t where t.idTarjeta=:idTarjeta")
    public List<String> findTarjetaporIdTarjeta(Integer idTarjeta);

    // arroja error 500
//    @Query(value = "Select t.numTarjeta, t.vencimiento, t.estado, t.limExtraccion from Tarjeta t where t.idTarjeta=:idTarjeta", nativeQuery = true)
//    public List<String> findTarjetaporIdTarjeta(@Param("idTarjeta") Integer idTarjeta);

    @Query(value = "Select * from tarjetas where id_tarjeta=:idTarjeta", nativeQuery = true)
    List<Tarjeta> findTarjetaById(@Param("idTarjeta") Integer idTajeta);

    @Query(value = "Update tarjetas set estado=:estado where id_tarjeta=:idTarjeta and num_tarjeta=:numTarjeta", nativeQuery = true)
    List<Tarjeta> activarTarjeta(String estado, Integer idTarjeta, Integer numTarjeta);
}
