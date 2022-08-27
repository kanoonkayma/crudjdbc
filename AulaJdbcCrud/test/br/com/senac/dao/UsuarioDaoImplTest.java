/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import gerador.Gerador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kayma.silva
 */
public class UsuarioDaoImplTest {
    
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    
    public UsuarioDaoImplTest() {
        
        //injeção de dependencia
        usuarioDAO = new UsuarioDaoImpl();
    }

    //@Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        
        usuario = new Usuario(null, 
                Gerador.gerarNome(), 
                Gerador.gerarLogin(), 
                Gerador.gerarSenha(5), 
                null
        );
        usuarioDAO.salvar(usuario);
        
        //assertNotNull(usuario.getId());
    }

    //@Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarUsuarioBD();
        
        usuario.setNome(Gerador.gerarNome());
        usuarioDAO.alterar(usuario);

        Usuario usuarioAlt = usuarioDAO.pesquisarPorId(usuario.getId());
        
        assertEquals(usuario.getNome(), usuarioAlt.getNome());
        
    }

    //@Test
    public void testRemover() throws Exception {
        System.out.println("remover");
        buscarUsuarioBD();
        
        usuarioDAO.remover(usuario.getId());
         
        Usuario usuarioDel = usuarioDAO.pesquisarPorId(usuario.getId());
        
        assertNull(usuarioDel);
    }

    //@Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        buscarUsuarioBD();
        
        Usuario usuarioPesquisado = usuarioDAO.pesquisarPorId(usuario.getId());
        assertNotNull(usuarioPesquisado);
    }

    //@Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        buscarUsuarioBD();
        
        List <Usuario> usuarioTodos = usuarioDAO.pesquisarTodos();
        
        assertTrue(!usuarioTodos.isEmpty());
        //assertFalse
        
        
    }

    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarUsuarioBD();
                
        List <Usuario> usuarioNome = usuarioDAO.pesquisarPorNome(usuario.getNome());
        
        assertTrue(!usuarioNome.isEmpty());
        

    }
    
    public Usuario buscarUsuarioBD() throws Exception{
        String sql = "SELECT * FROM usuario";
        
        Connection conexao = FabricaConexa.abrirConexao();
        PreparedStatement prepareStatement = conexao.prepareStatement(sql);
        ResultSet resultado = prepareStatement.executeQuery();
        
        if(resultado.next()){
            
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setUltimoAcesso(resultado.getDate("ultimo_acesso"));
            } else {
            testSalvar();
        }
        return usuario;
    }
}
