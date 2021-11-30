package com.example.TarjetaDebito.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Compras")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idcompra;
    private Integer numTarjeta;
    private Integer idCuenta;
    private Calendar fecha;
    private Double importe;

}
