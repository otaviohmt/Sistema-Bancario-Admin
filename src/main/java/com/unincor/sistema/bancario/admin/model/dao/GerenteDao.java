package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenteDao {
    
    public void inserirGerente(Gerente gerente) {
        String sql = "INSERT INTO gerentes(nome, cpf, data_nascimento, email, telefone, senha_hash, agencia)" +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";  
        try(Connection con = MySQL.connect();PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, gerente.getNome());
            ps.setString(2, gerente.getCpf());
            ps.setDate(3, Date.valueOf(gerente.getDataNascimento()));
            ps.setString(4, gerente.getEmail());
            ps.setString(5, gerente.getTelefone());
            ps.setString(6, gerente.getSenhaHash());
            ps.setLong(7, gerente.getAgencia().getIdAgencia());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public List<Gerente> buscarTodosGerentes(){
        List<Gerente> gerentes = new ArrayList<>();
        String sql = "SELECT * FROM gerentes";
        try(Connection con = MySQL.connect();PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                var gerente = construirGerenteSql(rs);
                gerentes.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gerentes;
    }
    
    public Gerente buscarGerentePorCpf(String cpf){
        String sql = "SELECT * FROM gerentes WHERE cpf = ?";
        try(Connection con = MySQL.connect();PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return construirGerenteSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
    public Gerente construirGerenteSql (ResultSet rs) throws SQLException {
        Gerente gerente = new Gerente();
                gerente.setIdGerente(rs.getLong("id_funcionario"));
                gerente.setNome(rs.getString("nome"));
                gerente.setCpf(rs.getString("cpf"));
                gerente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                gerente.setEmail(rs.getString("email"));
                gerente.setTelefone(rs.getString("telefone"));
                gerente.setSenhaHash(rs.getString("senha_hash"));
                
        Agencia agencia = new Agencia();
                agencia.setIdAgencia(rs.getLong("agencia"));

                gerente.setAgencia(agencia);
                
                return gerente;
    }
}
