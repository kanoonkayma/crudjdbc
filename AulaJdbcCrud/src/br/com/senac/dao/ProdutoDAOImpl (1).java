package senac.aula.dao.impl;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senac.aula.Produto;
import senac.aula.dao.ConnectionManager;
import senac.aula.dao.ProdutoDAO;

/**
 *
 * @author kayma.silva
 */
public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public Produto inserirProduto(Produto produto) throws Exception {

        Connection conexao = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultado = null;// recuperar informacao do bd

        try {

            String sql = "INSERT INTO produto (nome, preco, vencimento) VALUES(?, ?, ?)";
            conexao = ConnectionManager.abrirConexao();
            prepareStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            prepareStatement.setString(1, produto.getNome());
            prepareStatement.setDouble(2, produto.getPreco());
            prepareStatement.setDate(3, Date.valueOf(produto.getVencimento()));

            prepareStatement.execute();

            resultado = prepareStatement.getGeneratedKeys();
            resultado.next();
            produto.setId(resultado.getLong(1));

        } catch (SQLException ex) {
            throw new Exception(ex);
        } finally {
            ConnectionManager.fecharConexao(conexao);

        }

        return produto;
    }

    @Override
    public void validarConexao() throws SQLException {
        Connection conexao = ConnectionManager.abrirConexao();
        System.out.println("Conectou no banco");
        ConnectionManager.fecharConexao(conexao);
    }

    /**
     *
     * @param produto
     * @return
     * @throws Exception
     */
    @Override
    public Produto criar(Produto produto) throws Exception {
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            conexao = ConnectionManager.abrirConexao();
            String sql = "INSERT INTO produto (nome, preco, vencimento) VALUES(?, ?, ?)";
            statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setDate(3, Date.valueOf(produto.getVencimento()));

            statement.executeUpdate();

            resultado = statement.getGeneratedKeys();

            if (resultado.next()) {
                produto.setId(resultado.getLong(1));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar produto :" + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement, resultado);
        }
        return produto;

    }

    @Override
    public Produto buscar(long id) throws Exception {

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Produto produto = null;

        try {

            conexao = ConnectionManager.abrirConexao();
            String sql = "SELECT * FROM produto WHERE idproduto = ?";
            statement = conexao.prepareStatement(sql);
            statement.setLong(1, id);

            resultado = statement.executeQuery();

            if (resultado.next()) {

                produto = new Produto();
                produto.setId(id);
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setVencimento(resultado.getDate("vencimento").toLocalDate());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar o produto :" + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement, resultado);
        }

        return produto;
    }

    @Override
    public void criarTabela() throws Exception {

        Connection conexao = null;
        Statement statement = null;

        try {
            conexao = ConnectionManager.abrirConexao();
            statement = conexao.createStatement();
            statement.executeUpdate(getSqlCriarTabelaProduto());
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela :" + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement);
        }

    }

    //criei um metodo pra facilitar a criação da tabela
    private String getSqlCriarTabelaProduto() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS produto ( ");
        sql.append("id int NOT NULL AUTO_INCREMENT, ");
        sql.append("nome varchar(45) NOT NULL, ");
        sql.append("preco decimal(10,2) NOT NULL DEFAULT '0.00', ");
        sql.append("vencimento date NOT NULL, ");
        sql.append("PRIMARY KEY (id) ");
        sql.append(")");
        return sql.toString();

    }

    @Override
    public Produto atualizar(Produto produto) throws Exception {
        
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
       
        try {

            conexao = ConnectionManager.abrirConexao();
            String sql = "UPDATE produto SET nome = ?, preco = ?, vencimento = ? WHERE idproduto = ?";
            statement = conexao.prepareStatement(sql);
            
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setDate(3, Date.valueOf(produto.getVencimento()));
            statement.setLong(4, produto.getId());
            
            statement.executeUpdate();
                
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o produto :" + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement, resultado);
        }

        return produto;
     
    }

    @Override
    public List<Produto> pesquisarTodos(long id) throws Exception {
       Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        List<Produto> produtos = new ArrayList();

        try {
            conexao = ConnectionManager.abrirConexao();
            String sql = "SELECT * FROM produto ORDER BY nome ASC";
            statement = conexao.prepareStatement(sql);

            resultado = statement.executeQuery();

            while (resultado.next()) {
                Produto produto = new Produto();
                produto.setId(resultado.getLong("id"));
                produto.setNome(resultado.getString("nome"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setVencimento(resultado.getDate("vencimento").toLocalDate());
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement, resultado);
        }
        return produtos;
    }

    @Override
    public void removerRegistros() throws Exception {

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            conexao = ConnectionManager.abrirConexao();
            String sql = "DELETE produto FROM produto";
            statement = conexao.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement, resultado);
        }
        
    }

    @Override
    public void remover(long id) throws Exception {

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            conexao = ConnectionManager.abrirConexao();
            String sql = "DELETE produto FROM produto WHERE idproduto = ?";
            statement = conexao.prepareStatement(sql);

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            throw new Exception(e);
        } finally {
            ConnectionManager.fecharConexao(conexao, statement, resultado);
        }

}}
