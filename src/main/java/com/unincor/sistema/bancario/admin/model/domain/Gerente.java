package com.unincor.sistema.bancario.admin.model.domain;

import java.time.LocalDate;

public class Gerente extends Pessoa{
    
    private Long idGerente;
    
    private Agencia agencia;

    public Gerente() {
    }

    public Gerente (Long idGerente, String nome, String cpf, LocalDate dataNascimento, String email, String telefone, String senhaHash) {
        this.idGerente = idGerente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
    }

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

}
