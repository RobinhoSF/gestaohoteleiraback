package dao;

import com.mysql.cj.xdevapi.Client;
import com.mysql.cj.xdevapi.Result;
import dao.connection.ConexaoMySQL;
import model.Cliente;
import model.Exemplo;
import model.Pessoa;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    public Pessoa selecionarMaxPessoa() {
        try {
            String sql = "SELECT MAX(id) FROM pessoa";
            Statement stmt = ConexaoMySQL.get().createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            if(resultado.next()) {
                Pessoa pessoa = new Pessoa(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade")
                );
                return pessoa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean inserir(Cliente cliente) {
        // Inserir na tabela pessoa
        try {
            String sql = "INSERT INTO pessoa (nome_completo, data_nascimento, documento, pais, estado, cidade) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setString(1, cliente.getNomeCompleto());
            preparacao.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            preparacao.setString(3, cliente.getDocumento());
            preparacao.setString(4, cliente.getPais());
            preparacao.setString(5, cliente.getEstado());
            preparacao.setString(6, cliente.getCidade());
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Consultar o id da pessoa inserida

        Pessoa pessoa = selecionarMaxPessoa();

        // Inserir na tabela cliente
        try {
            String sql = "INSERT INTO cliente (id_pessoa, fidelidade, observacao) VALUES (?, ?, ?)";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, pessoa.getId());
            preparacao.setBoolean(2, cliente.getFidelidade());
            preparacao.setString(3, cliente.getObservacao());
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Cliente> selecionar() {
        try {
            String sql = "SELECT * FROM cliente ORDER BY id";
            Statement stmt = ConexaoMySQL.get().createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            ArrayList<Cliente> lista = new ArrayList<>();
            while(resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean atualizar(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET fidelidade = ?, observacao = ? WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setBoolean(1, cliente.getFidelidade());
            preparacao.setString(2, cliente.getObservacao());
            preparacao.setLong(3, cliente.getId());
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deletar(Long id) {
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            int contLinhasAfetadas = preparacao.executeUpdate();
            return contLinhasAfetadas > 0;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente selecionarPorId(Long id) {
        try {
            String sql = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement preparacao = ConexaoMySQL.get().prepareStatement(sql);
            preparacao.setLong(1, id);
            ResultSet resultado = preparacao.executeQuery();

            if(resultado.next()) {
                Cliente cliente = new Cliente(
                        resultado.getLong("id"),
                        resultado.getString("nome_completo"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getString("documento"),
                        resultado.getString("pais"),
                        resultado.getString("estado"),
                        resultado.getString("cidade"),
                        resultado.getBoolean("fidelidade"),
                        resultado.getString("observacao")
                );
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
