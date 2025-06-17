
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.ClienteDao;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.time.LocalDate;
import java.util.List;

public class ClienteService {
    
    private final ClienteDao clienteDao = new ClienteDao();
    
    public void salvarCliente(Cliente cliente) throws CadastroException {
        if(cliente.getCpf() == null || cliente.getCpf().isBlank()){
            throw new CadastroException("CPF não encontrado!");
        }
        
        if (clienteDao.buscarClientePorCpf(cliente.getCpf()) != null) {
            throw new CadastroException("CPF já cadastrado!");
        }
        
        if(cliente.getNome()== null || cliente.getNome().isBlank()){
            throw new CadastroException("Nome não pode ficar vazio");
        }
            
        if(cliente.getEmail()== null || cliente.getEmail().isBlank()){
            throw new CadastroException("Email não pode ficar vazio");
        }
        
        if(cliente.getTelefone()== null || cliente.getTelefone().isBlank()){
            throw new CadastroException("Telefone não pode ficar vazio");
        }
        
        clienteDao.inserirCliente(cliente);
    }
    
    public List<Cliente> buscarClientes(){
        return clienteDao.buscarTodosClientes();
    }
    
    public static void main(String[] args) {
        ClienteService clinteService = new ClienteService();
        
        Cliente cliente = new Cliente(Long.MIN_VALUE, "Mateus", "12345678900", LocalDate.MIN,
                "toremo@gmail.com", "35998877665", "toremovacilao");
    }
}
