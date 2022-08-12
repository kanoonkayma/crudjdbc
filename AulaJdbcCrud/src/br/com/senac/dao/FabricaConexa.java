/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kayma.silva
 */
public class FabricaConexa {
    
    public static Connection abrirConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager
                 .getConnection("jdbc:mysql://localhost:3306/crudjdbc?useTimezone=true&serverTimezone=UTC", "root", "Senac2021");  // ou Senac2021
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("Erro de Conexão");
        } 
      return null;
    }
    
      public static void fecharConexao(Connection conexao, Statement prepareStatement, ResultSet resultado) throws SQLException {

        if (conexao != null) { //Objects.nonNull(conexao)
            conexao.close();
        }
        if (prepareStatement != null) { //Objects.nonNull(conexao)
            prepareStatement.close();
        }
        if (resultado != null) { //Objects.nonNull(conexao)
            resultado.close();
        }
    }

}
