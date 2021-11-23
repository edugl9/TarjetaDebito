package com.example.TarjetaDebito.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTajeta;
    private Integer numTarjeta;
    private String marca;
    private String tipo;
    private String estado;
    private String limExtraccion;
    private String vencimiento;
    private Double saldo;

    private Integer edadUserTest;

}
