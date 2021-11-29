package com.example.TarjetaDebito.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tarjetas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTarjeta;
    private Integer numeroCuenta;// relacion al usuario
    private Integer numTarjeta; // nuevo numero sea nueva/reposicion
    private String marca;
    private String tipo;
    private String estado;
    private Integer limExtraccion; // limite que se puede extraer dinero, max 10,000
    private String vencimiento;
    private Double saldo; // que se le asigne o tome el saldo relacionado a la cuenta, solo si se relaciona a cuenta

    // variables de prueba temporales
    private Integer edadUserTest;
    private String auntenticado;

}
