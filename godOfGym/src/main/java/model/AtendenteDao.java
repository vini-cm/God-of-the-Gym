package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtendenteDao extends genericoDAO {

    public void salvar(Atendente atendente) throws SQLException {
        String insert = "insert into atendentes(cpf, salario, entrada, saida) values(?,?,?,?)";
        salvar(insert, atendente.getCPF(), atendente.getSalario(), atendente.getEntrada(), atendente.getSaida());
    }

    public void editar(Atendente atendente) throws SQLException {
        String editar = "update atendentes set salario = ?, entrada = ?, saida = ? where cpf = ?";
        editar(editar, atendente.getCPF(), atendente.getSalario(),
                atendente.getEntrada(), atendente.getSaida(), atendente.getCPF());
    }

    public void deletar(String CPF) throws SQLException {
        String deletar = "delete from atendentes where cpf = ?";
        deletar(deletar, CPF);
    }

    public ObservableList<Atendente> selecionarAtendentes() throws SQLException {
        ObservableList<Atendente> lista = FXCollections.observableArrayList();
        String sql = "select * from atendentes";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Atendente atendente = new Atendente();
            atendente.setId(rs.getInt("id"));
            atendente.setCPF(rs.getString("CPF"));
            atendente.setEntrada(rs.getTime("saida").toLocalTime());
            atendente.setSaida(rs.getTime("saida").toLocalTime());
            atendente.setSalario(rs.getFloat("salario"));
            lista.add(atendente);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }

    public Atendente selecionarAtendente(String CPF) throws SQLException {
        String sql = "select * from atendentes where cpf = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Atendente atendente = new Atendente();
                atendente.setId(rs.getInt("id"));
                atendente.setCPF(rs.getString("CPF"));
                atendente.setEntrada(rs.getTime("entrada").toLocalTime());
                atendente.setSaida(rs.getTime("saida").toLocalTime());
                atendente.setSalario(rs.getFloat("salario"));
                return atendente;
            } else {
                return null;
            }
        }
    }
}
