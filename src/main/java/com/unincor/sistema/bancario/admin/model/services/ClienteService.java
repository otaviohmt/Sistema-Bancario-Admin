
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.ClienteDao;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.time.LocalDate;
import java.util.List;

public class ClienteService {
    
    private final ClienteDao clienteDao = new ClienteDao();
    
    public void salvarCliente(Cliente cliente) throws CadastroException {
        if(cliente == null) {
            throw new CadastroException("Cliente informado inválido!");
        }
        
        if(cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new CadastroException("O nome não foi informado!");
        }
        
        if(cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new CadastroException("Cpf não foi informado!");
        }
        
        if(clienteDao.buscarClientePorCpf(cliente.getCpf()) !=  null) {
            throw new CadastroException("Cpf já cadastrado!");
        }
        
        if(clienteDao.buscarClientePorEmail(cliente.getEmail()) !=  null) {
            throw new CadastroException("E-mail já cadastrado!");
        }
        
        clienteDao.inserirCliente(cliente);
    }
    
    public List<Cliente> buscarClientes(){
        return clienteDao.buscarTodosClientes();
    }
    
    public static void main(String[] args) {
        try {
            var cliente = new Cliente();
            cliente.setNome("Fernando");
            cliente.setCpf("65498711");
            cliente.setDataNascimento(LocalDate.now());
            cliente.setEmail("fernadao@tbnao.eo.bryan.com.br");
            cliente.setSenhaHash("98765432das32d16");
            cliente.setTelefone("35988220819");
            
            ClienteService clienteService = new ClienteService();
            clienteService.salvarCliente(cliente);
        } catch (CadastroException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
