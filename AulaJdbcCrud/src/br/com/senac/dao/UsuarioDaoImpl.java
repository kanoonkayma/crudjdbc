/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;
import br.com.senac.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kayma.silva
 */
public class UsuarioDaoImpl implements UsuarioDAO {

    //preparar a conexao
    private Connection conexao;
    //preparar a instrução pra mandar pro banco
    private PreparedStatement prepareStatement;
    //resultado da consulta
    private ResultSet resultado;
    
    @Override
    public void salvar(Usuario usuario) throws SQLDataException {
        String sql = "INSERT INTO usuario (nome, login, senha) VALUES(?, ?, ?)";
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql);
            
            prepareStatement.setString(1, usuario.getNome());
            prepareStatement.setString(2, usuario.getLogin());
            prepareStatement.setString(3, usuario.getSenha());
     
            prepareStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário!" + e.getMessage());
            //throw new Exception(e);
        }
    }

    @Override
    public void alterar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE id = ?";
        
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql);
            
            prepareStatement.setString(1, usuario.getNome());
            prepareStatement.setString(2, usuario.getLogin());
            prepareStatement.setString(3, usuario.getSenha());
            prepareStatement.setInt(4, usuario.getId());
            
            
            prepareStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar usuário!" + e.getMessage());
        }finally{
        
            FabricaConexa.fecharConexao(conexao, prepareStatement, resultado);
        }
    }

    @Override
    public void remover(Integer id) throws Exception {
        try{
        conexao = FabricaConexa.abrirConexao();
        prepareStatement = conexao.prepareStatement("DELETE FROM usuario WHERE id = ?");
        prepareStatement.setInt(1, id);
        
        prepareStatement.executeUpdate();
         
        }catch(SQLException e){
        System.out.println("Deletar não será possível" + e.getMessage());
        }
         
        
    }

    @Override
    public Usuario pesquisarPorId(Integer id) throws Exception {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        Usuario usuario = null;
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql);
            
            prepareStatement.setInt(1, id);
            //chamar a consulta 
            resultado = prepareStatement.executeQuery();
           
            //next->se existe um valor vindo
            if(resultado.next()){
            
                usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(resultado.getString("nome"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setUltimoAcesso(resultado.getDate("ultimo_acesso"));
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por id!" + e.getMessage());
            //throw new Exception(e);
        } finally {
            FabricaConexa.fecharConexao(conexao, prepareStatement, resultado);
        }
        
        return usuario;
    }

    @Override
    public List<Usuario> pesquisarTodos() throws Exception {
        
        String sql = "SELECT * FROM usuario ORDER BY nome ASC";
        //Usuario usuario;
        List<Usuario> usuarios = new ArrayList();
        
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql);
            
            resultado = prepareStatement.executeQuery();
            //
            while (resultado.next()){
            Usuario usuario = new Usuario();
            usuario.setId(resultado.getInt("id"));
            usuario.setNome(resultado.getString("nome"));
            usuario.setLogin(resultado.getString("login"));
            usuario.setSenha(resultado.getString("senha"));
            usuario.setUltimoAcesso(resultado.getDate("ultimo_acesso"));
            usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar todos!" + e.getMessage());
        } finally {
            FabricaConexa.fecharConexao(conexao, prepareStatement, resultado);
        }
        
        return usuarios;
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome) throws Exception {
        
        String sql = "SELECT * FROM usuario WHERE nome LIKE ?";
        List<Usuario> usuarios = new ArrayList();
        //Usuario usuario;
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql);
            prepareStatement.setString(1, "%" + nome + "%");
            
            resultado = prepareStatement.executeQuery();
            
            while (resultado.next()){
            Usuario usuario = new Usuario();
           
            usuario.setId(resultado.getInt("id"));
            usuario.setNome(resultado.getString("nome"));
            usuario.setLogin(resultado.getString("login"));
            usuario.setSenha(resultado.getString("senha"));
            usuario.setUltimoAcesso(resultado.getDate("ultimo_acesso"));
            usuarios.add(usuario);
            
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar por nome!" + e.getMessage());
        } finally{
        FabricaConexa.fecharConexao(conexao, prepareStatement, resultado);
        }
        
        return usuarios;
    }
    
}
