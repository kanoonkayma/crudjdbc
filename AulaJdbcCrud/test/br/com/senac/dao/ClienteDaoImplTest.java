/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Usuario;
import gerador.Gerador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    //@Test
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

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarClienteBD();
        
        cliente.setNome(Gerador.gerarNome());
        
        clienteDAO.alterar(cliente);
        
        Cliente clienteAlt = clienteDAO.pesquisarPorId(cliente.getId());
        
        assertEquals(cliente.getNome(), clienteAlt.getNome());
    }

    //@Test
    public void testRemover() throws Exception {
       System.out.println("remover");
        buscarClienteBD();
        
        clienteDAO.remover(cliente.getId());
         
        Cliente clienteDel = clienteDAO.pesquisarPorId(cliente.getId());
        
        assertNull(clienteDel);
    }

    //@Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        
        
    }

   //@Test
    public void testPesquisarTodos() throws Exception {
        System.out.println("pesquisarTodos");

    }

    //@Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
 
    }
    
    public Cliente buscarClienteBD() throws Exception{
        String sql = "SELECT * FROM cliente";
        
        Connection conexao = FabricaConexa.abrirConexao();
        PreparedStatement prepareStatement = conexao.prepareStatement(sql);
        ResultSet resultado = prepareStatement.executeQuery();
        
        if(resultado.next()){
            
                cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setRg(resultado.getString("rg"));
                cliente.setSalario(resultado.getDouble("salario"));
            } else {
            testSalvar();
        }
        return cliente;
    }
    
}
