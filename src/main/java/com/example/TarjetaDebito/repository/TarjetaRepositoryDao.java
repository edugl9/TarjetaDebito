package com.example.TarjetaDebito.repository;

import com.example.TarjetaDebito.entity.Compra;
import com.example.TarjetaDebito.entity.Tarjeta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Transactional
public interface TarjetaRepositoryDao extends CrudRepository<Tarjeta, Integer> {

    // funciona pero sin mostrar columnas
    @Query(value = "Select t.numTarjeta, t.vencimiento, t.estado, t.limExtraccion from Tarjeta t where t.idTarjeta=:idTarjeta")
    public List<String> findTarjetaporIdTarjeta(Integer idTarjeta);

//    // arroja error 500
//    @Query(value = "Select num_tarjeta, vencimiento, estado, lim_Extraccion from tarjetas where id_tarjeta=:idTarjeta;", nativeQuery = true)
//    public List<String> findTarjetaporIdTarjeta(@Param("idTarjeta") Integer idTarjeta);

    @Query(value = "Select * from tarjetas where id_tarjeta=:idTarjeta", nativeQuery = true)
    List<Tarjeta> findTarjetaById(@Param("idTarjeta") Integer idTajeta);

//    @Modifying
//    @Query(value = "UPDATE tarjetas set estado=:estado, saldo=:saldo where id_tarjeta=:idTarjeta and num_tarjeta=:numTarjeta", nativeQuery = true)
//    public void activarTarjeta(String estado, Integer saldo, Integer idTarjeta, Integer numTarjeta);

    @Modifying
    @Query(value = "update tarjetas set lim_extraccion=:limExtraccion where id_tarjeta=:idTarjeta and num_tarjeta=:numTarjeta", nativeQuery = true)
    public void nuevoLimite(Integer limExtraccion, Integer idTarjeta, Integer numTarjeta);

    @Modifying
    @Query(value = "update tarjetas set estado=:estado where id_tarjeta=:idTarjeta and num_tarjeta=:numTarjeta", nativeQuery = true)
    public void bloquearTarjeta(String estado, Integer idTarjeta, Integer numTarjeta);

    @Query(value = "Select num_tarjeta, fecha, importe from compras where num_tarjeta=:numTarjeta and fecha Between fecha=:fechaDesde and fecha=:fechaHasta", nativeQuery = true)
    List<Compra> consultaComprasFecha(@Param("numTarjeta") Integer numTarjeta, @Param("fechaDesde") Calendar fechaDesde, @Param("fechaHasta") Calendar fechaHasta);



    List<Tarjeta> findAllByNumeroCuenta(Integer numeroCuenta);
}
