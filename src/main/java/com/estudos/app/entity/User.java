package com.estudos.app.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Data
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

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="cpf", nullable = false, unique = true)
    private String cpf;

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

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private UserType userType;

    public User(String nome, String email, String password, String cpf, LocalDate dataDeNascimento, int cep, String rua, int numRua, String complemento, UserType userType) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.cep = cep;
        this.rua = rua;
        this.numRua = numRua;
        this.complemento = complemento;
        this.userType = userType;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean isEnabled(){
        return true;
    }
}
