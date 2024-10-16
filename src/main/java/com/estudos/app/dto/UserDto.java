package com.estudos.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserDto (String nome,
         String email,
         int cpf,

           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
           String dataDeNascimento,

         int cep,
         String rua,
         int numRua,
         String complemento)
{
}
