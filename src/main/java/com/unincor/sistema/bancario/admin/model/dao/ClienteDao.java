package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDao {
    
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes(nome, cpf, data_nascimento, email, telefone, senha_hash)" +
        "VALUES (?, ?, ?, ?, ?, ?)";  
        try(Connection con = MySQL.connect();PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getSenhaHash());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Cliente> buscarTodosClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try(Connection con = MySQL.connect();PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                var cliente = construirClienteSql(rs);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    
     public Cliente buscarClientePorCpf(String cpf){
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try(Connection con = MySQL.connect();PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return construirClienteSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
    public Cliente construirClienteSql (ResultSet rs) throws SQLException {
        Cliente  cliente = new Cliente();
                cliente.setId(rs.getLong("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setSenhaHash(rs.getString("senha_hash"));
                return cliente;
    }
            
    public static void main(String[] args) {
        Cliente cliente = new Cliente(null, "Brayan", "8899222444", LocalDate.now(),
                "brayan@gmail.com", "112566663", "114444455666");
        ClienteDao clienteDao = new ClienteDao();
        var clientes = clienteDao.buscarTodosClientes();
        System.out.println(clientes);
        clientes.forEach(c -> System.out.println("Id: " + c.getId() + " Nome: " + c.getNome() + " CPF: " + c.getCpf() + 
                " Data nascimento: " + c.getDataNascimento() + " Email: " + c.getEmail() + " Tel: " + c.getTelefone() +
                " Senha hash: " + c.getSenhaHash()));
                
        var c = clienteDao.buscarClientePorCpf("123345699"); //PERGUNTAR PARA O DIOGENES COMO FAZER ISSO, ESTA ACUSANDO ERRO NO CPF(INT)
        System.out.println("Id: " + c.getId() + " Nome: " + c.getNome() + "CPF: " + c.getCpf());
    }
    
}
