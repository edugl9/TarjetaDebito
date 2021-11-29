package com.example.TarjetaDebito.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private Integer idCuenta;
    private String tipoCuenta;
    private String usuario;
    private String monedaCuenta;
    private Double balance;
    private Integer numeroCuenta;
}

