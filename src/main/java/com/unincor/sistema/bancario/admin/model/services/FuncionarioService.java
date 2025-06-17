
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.FuncionarioDao;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.util.List;

public class FuncionarioService {
    
    private final FuncionarioDao funcionarioDao = new FuncionarioDao();
    
    public void salvarFuncionario(Funcionario funcionario) throws CadastroException {
        if(funcionario.getCpf() == null || funcionario.getCpf().isBlank()){
            throw new CadastroException("CPF não encontrado!");
        }
        
        if (funcionarioDao.buscarFuncionarioPorCpf(funcionario.getCpf()) != null) {
            throw new CadastroException("CPF já cadastrado!");
        }
        
        if(funcionario.getNome()== null || funcionario.getNome().isBlank()){
            throw new CadastroException("Nome não pode ficar vazio");
        }
            
        if(funcionario.getEmail()== null || funcionario.getEmail().isBlank()){
            throw new CadastroException("Email não pode ficar vazio");
        }
        
        if(funcionario.getTelefone()== null || funcionario.getTelefone().isBlank()){
            throw new CadastroException("Telefone não pode ficar vazio");
        }
        
        funcionarioDao.inserirFuncionario(funcionario);
    }
    
    public List<Funcionario> buscarFuncionarios(){
        return funcionarioDao.buscarTodosFuncionarios();
    }
}
