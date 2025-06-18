package com.unincor.sistema.bancario.admin.model.domain;

import java.time.LocalDate;

public class Funcionario extends Pessoa{
    
    private Long idFuncionario;
    
    private String turno;
    
    public Funcionario(){
    }
    
    public Funcionario (Long idFuncionario, String nome, String cpf, LocalDate dataNascimento, String email, String telefone, String senhaHash) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idFuncionario=" + idFuncionario + ", turno=" + turno + '}';
    }

}
