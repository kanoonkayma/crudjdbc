/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import gerador.Gerador;
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

    @Test
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
        Usuario usuario = null;
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        instance.alterar(usuario);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testRemover() throws Exception {
        System.out.println("remover");
        Integer id = null;
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        instance.remover(id);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        Integer id = null;
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        Usuario expResult = null;
        Usuario result = instance.pesquisarPorId(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.pesquisarTodos();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        String nome = "";
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.pesquisarPorNome(nome);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
