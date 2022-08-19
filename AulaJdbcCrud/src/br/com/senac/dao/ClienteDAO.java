/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Usuario;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kayma.silva
 */

//todo método que esta na interface ele já public abstract
public interface ClienteDAO {
    
   //void validarConexao() throws SQLException; 
    
   void salvar(Cliente cliente) throws SQLDataException;
   
   void alterar(Cliente cliente) throws Exception;
   
   void remover(Integer id) throws Exception;
   
   Cliente pesquisarPorId(Integer id) throws Exception;
   
   List<Cliente> pesquisarTodos() throws Exception;
   
   List<Cliente> pesquisarPorNome(String nome) throws Exception;
}
