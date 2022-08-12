/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kayma.silva
 */

//todo método que esta na interface ele já public abstract
public interface UsuarioDAO {
    
   //void validarConexao() throws SQLException; 
    
   void salvar(Usuario usuario) throws SQLDataException;
   
   void alterar(Usuario usuario) throws Exception;
   
   void remover(Integer id) throws Exception;
   
   Usuario pesquisarPorId(Integer id) throws Exception;
   
   List<Usuario> pesquisarTodos() throws Exception;
   
   List<Usuario> pesquisarPorNome(String nome) throws Exception;
}
