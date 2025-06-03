
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.AgenciaDao;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgenciaService {
    
    private final AgenciaDao agenciaDao = new AgenciaDao();
    
    public void salvarAgencia(Agencia agencia) throws CadastroException {
        if(agencia.getCodigoAgencia() == null || agencia.getCodigoAgencia().isBlank()){
            throw new CadastroException("Agência não possui um código de agência");
        }
        
        if (agenciaDao.buscarAgenciaPorCodigoAgencia(agencia.getCodigoAgencia()) != null) {
            throw new CadastroException("Código de agência já está cadastrado!");
        }
        
        if(agencia.getCidade()== null || agencia.getCidade().isBlank()){
            throw new CadastroException("Cidade não pode ficar vazio");
        }
            
        if(agencia.getUf()== null || agencia.getUf().isBlank()){
            throw new CadastroException("UF não pode ficar vazio");
        }
        
        agenciaDao.inserirAgencia(agencia);
    }
    
    public static void main(String[] args) {
        AgenciaService agenciaService = new AgenciaService();
        
        Agencia agencia = new Agencia (null, null, "Tres Coracoes", "MG",
                "Rei Pele", "468798", "37410000");
        
        try {
            agenciaService.salvarAgencia(agencia);
        } catch (CadastroException ex) {
            Logger.getLogger(AgenciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
