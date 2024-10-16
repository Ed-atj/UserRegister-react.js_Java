package com.estudos.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    nome, email, cpf, datadenasc, cep, rua, número, complemento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="cpf", nullable = false, unique = true)
    private int cpf;

    @Column(name="data_nascimento", nullable = false)
    private LocalDate dataDeNascimento;

    @Column(name="cep", nullable = false)
    private int cep;

    @Column(name="rua", nullable = false)
    private String rua;

    @Column(name="numero_rua", nullable = false)
    private int numRua;

    @Column(name="complemento", nullable = false)
    private String complemento;

    public User(String nome, String email, int cpf, LocalDate dataDeNascimento, int cep, String rua, int numRua, String complemento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.cep = cep;
        this.rua = rua;
        this.numRua = numRua;
        this.complemento = complemento;
    }

}
