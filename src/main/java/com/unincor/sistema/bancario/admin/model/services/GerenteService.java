
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.AgenciaDao;
import com.unincor.sistema.bancario.admin.model.dao.GerenteDao;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.time.LocalDate;
import java.util.List;

public class GerenteService {
    
    private final GerenteDao gerenteDao = new GerenteDao();
    
    public void salvarGerente(Gerente gerente) throws CadastroException {
        if(gerente == null) {
            throw new CadastroException("Gerente informado inválido!");
        }
        
        if(gerente.getNome() == null || gerente.getNome().isBlank()) {
            throw new CadastroException("O nome não foi informado!");
        }
        
        if(gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroException("Cpf não foi informado!");
        }
        
        if(gerenteDao.buscarGerentePorCpf(gerente.getCpf()) !=  null) {
            throw new CadastroException("Cpf já cadastrado!");
        }
        
        if(gerenteDao.buscarGerentePorEmail(gerente.getEmail()) !=  null) {
            throw new CadastroException("E-mail já cadastrado!");
        }
        
        gerenteDao.inserirGerente(gerente);
    }
    
    public static void main(String[] args) {
        try {
            var gerente = new Gerente();
            gerente.setNome("Fernando");
            gerente.setCpf("65498711");
            gerente.setDataNascimento(LocalDate.now());
            gerente.setEmail("fernadao@tbnao.eo.bryan.com.br");
            gerente.setSenhaHash("98765432das32d16");
            gerente.setTelefone("35988220819");
            var agencia = new AgenciaDao().buscarAgenciaPorId(2l);
            gerente.setAgencia(agencia);
            
            GerenteService gerenteService = new GerenteService();
            gerenteService.salvarGerente(gerente);
        } catch (CadastroException ex) {
            System.out.println(ex.getMessage());
        }
    }
}