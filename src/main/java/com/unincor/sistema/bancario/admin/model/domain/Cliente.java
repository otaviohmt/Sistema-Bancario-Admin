package com.unincor.sistema.bancario.admin.model.domain;

import java.time.LocalDate;

public class Cliente extends Pessoa{
    
    private Long idCliente;

    public Cliente() {
    }

    public Cliente(Long idCliente, String nome, String cpf, LocalDate dataNascimento, String email, String telefone, String senhaHash) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
    }

    public Long getId() {
        return idCliente;
    }
    
    public void setId(Long id) {
        this.idCliente = id;
    }

}
