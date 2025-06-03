package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgenciaDao {
    
    public void inserirAgencia(Agencia agencia) {
        String sql = "INSERT INTO AGENCIAS(codigo_agencia, cidade, uf, logradouro, numero, cep) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, agencia.getCodigoAgencia());
            ps.setString(2, agencia.getCidade());
            ps.setString(3, agencia.getUf());
            ps.setString(4, agencia.getLogradouro());
            ps.setString(5, agencia.getNumero());
            ps.setString(6, agencia.getCep());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Agencia> listarTodasAgencias() {
        String sql = "SELECT * FROM Agencias";
        List<Agencia> agencias = new ArrayList<>();
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                agencias.add(construirAgenciaSql(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agencias;
    }
    
    public Agencia buscarAgenciaPorId(Long id) {
        String sql = "SELECT * FROM Agencias where id_agencia = ?";
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return construirAgenciaSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Agencia buscarAgenciaPorCodigoAgencia(String codigoAgencia) {
        String sql = "SELECT * FROM Agencias where codigo_agencia = ?";
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigoAgencia);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return construirAgenciaSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Agencia construirAgenciaSql(ResultSet rs) throws SQLException {
        Agencia agencia = new Agencia();
        agencia.setIdAgencia(rs.getLong("id_agencia"));
        agencia.setCodigoAgencia(rs.getString("codigo_agencia"));
        agencia.setCidade(rs.getString("cidade"));
        agencia.setUf(rs.getString("uf"));
        agencia.setLogradouro(rs.getString("logradouro"));
        agencia.setNumero("numero");
        agencia.setCep(rs.getString("cep"));
        return agencia;
    }
    
    public static void main(String[] args) {
        AgenciaDao agenciaDao = new AgenciaDao();
        System.out.println("Teste de inserir agencia: ");
        Agencia agencia = new Agencia(null, "1235", "Três Corações",
                "MG", "Av. Rei Pelé", "589", "37410000");
        agenciaDao.inserirAgencia(agencia);
        
        System.out.println("Teste loop buscar todas agencias: ");
        List<Agencia> agencias = agenciaDao.listarTodasAgencias();
        agencias.forEach(ag -> System.out.print("Codigo: " + ag.getCodigoAgencia()));
        
        System.out.println("Teste buscar agencia por id: ");
        Agencia ag = agenciaDao.buscarAgenciaPorId(1l);
        System.out.println("Codigo: " + ag.getCodigoAgencia());
    }
}
