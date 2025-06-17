
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.GerenteDao;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.util.List;

public class GerenteService {
    
    private final GerenteDao gerenteDao = new GerenteDao();
    
    public void salvarGerente(Gerente gerente) throws CadastroException {
        if(gerente.getCpf() == null || gerente.getCpf().isBlank()){
            throw new CadastroException("CPF não encontrado!");
        }
        
        if (gerenteDao.buscarGerentePorCpf(gerente.getCpf()) != null) {
            throw new CadastroException("CPF já cadastrado!");
        }
        
        if(gerente.getNome()== null || gerente.getNome().isBlank()){
            throw new CadastroException("Nome não pode ficar vazio");
        }
            
        if(gerente.getEmail()== null || gerente.getEmail().isBlank()){
            throw new CadastroException("Email não pode ficar vazio");
        }
        
        if(gerente.getTelefone()== null || gerente.getTelefone().isBlank()){
            throw new CadastroException("Telefone não pode ficar vazio");
        }
        
        gerenteDao.inserirGerente(gerente);
    }
    
    public List<Gerente> buscarGerentes(){
        return gerenteDao.buscarTodosGerentes();
    }
}