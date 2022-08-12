/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;
import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author kayma.silva
 */
public class ClienteDaoImpl implements ClienteDAO {

    //preparar a conexao
    private Connection conexao;
    //preparar a instrução pra mandar pro banco
    private PreparedStatement prepareStatement;
    //resultado da consulta
    private ResultSet resultado;
    
    /**
     *
     * @param cliente
     * @throws SQLDataException
     */
    @Override
    public void salvar(Cliente cliente) throws SQLDataException {
        String sql = "INSERT INTO cliente (nome, cpf, rg, salario) VALUES(?, ?, ?, ?)";
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            prepareStatement.setString(1, cliente.getNome());
            prepareStatement.setString(2, cliente.getCpf());
            prepareStatement.setString(3, cliente.getRg());
            prepareStatement.setDouble(4, cliente.getSalario());
            
            prepareStatement.executeUpdate();
            
            resultado = prepareStatement.getGeneratedKeys();
            resultado.next();
            cliente.setId(resultado.getInt(1));
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário!" + e.getMessage());
            //throw new Exception(e);
        }
    }

    @Override
    public void alterar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE idusuario = ?";
        
        try {
            conexao = FabricaConexa.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql);
            
            prepareStatement.setString(1, usuario.getNome());
            prepareStatement.setString(2, usuario.getLogin());
            prepareStatement.setString(3, usuario.getSenha());
            
            prepareStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário!" + e.getMessage());
        }
    }

    @Override
    public void remover(Integer id) throws Exception {
    }

    @Override
    public Usuario pesquisarPorId(Integer id) throws Exception {
        return null;
    }

    @Override
    public List<Usuario> pesquisarTodos() throws Exception {
        return null;
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome) throws Exception {
        return null;
    }

    
    
}
