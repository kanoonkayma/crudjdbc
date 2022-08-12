/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Usuario;
import gerador.Gerador;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kayma.silva
 */
public class ClienteDaoImplTest {
    
    Cliente cliente;
    ClienteDAO clienteDAO;
    
    public ClienteDaoImplTest() {
        clienteDAO = new ClienteDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        cliente = new Cliente(
                Gerador.gerarNome(),
                Gerador.gerarCpf(),
                Gerador.gerarNumero(6),
                Double.valueOf(Gerador.gerarNumero(12)));
        clienteDAO.salvar(cliente);
        assertNotNull(cliente.getId());
    }

    //@Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        Usuario usuario = null;
        ClienteDaoImpl instance = new ClienteDaoImpl();
        instance.alterar(usuario);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testRemover() throws Exception {
        System.out.println("remover");
        Integer id = null;
        ClienteDaoImpl instance = new ClienteDaoImpl();
        instance.remover(id);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        Integer id = null;
        ClienteDaoImpl instance = new ClienteDaoImpl();
        Usuario expResult = null;
        Usuario result = instance.pesquisarPorId(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

   //@Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");
        ClienteDaoImpl instance = new ClienteDaoImpl();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.pesquisarTodos();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        String nome = "";
        ClienteDaoImpl instance = new ClienteDaoImpl();
        List<Usuario> expResult = null;
        List<Usuario> result = instance.pesquisarPorNome(nome);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
