
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtendenteDao extends genericoDAO {
    public void salvar(Atendentes atendente)throws SQLException{
            String insert = "insert into atendentes(nome, cpf, salario, entrada, saida) values(?,?,?,?,?)";
            salvar(insert, atendente.getCPF(), atendente.getEntrada(), atendente.getId(), atendente.getSaida(),atendente.getSalario());
    }
     public void editar(Atendentes atendente) throws SQLException{
        String editar="update atendentes set salario = ?, entrada = ?, saida = ? where cpf = ?";
        editar(editar, atendente.getCPF(), atendente.getSalario(), 
                atendente.getEntrada(), atendente.getSaida(),atendente.getCPF());
    }
     public void deletar(String CPF) throws SQLException{
        String deletar = "delete atentendes where cpf = ?";
        deletar(deletar, CPF, CPF);
    }
     public ObservableList<Atendentes> selecionarAtendentes() throws SQLException{
        ObservableList<Atendentes> lista = FXCollections.observableArrayList();
        String sql = "select * from atendentes";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Atendentes atendente = new Atendentes();
            atendente.setId(rs.getInt("id"));
            atendente.setCPF(rs.getString("CPF"));
            atendente.setEntrada(rs.getTime("saida").toLocalTime());
            atendente.setSaida(rs.getTime("saida").toLocalTime());
            
            lista.add(atendente);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
     public ObservableList<Atendentes> selecionarAtendente(String CPF) throws SQLException{
        ObservableList<Atendentes> lista = FXCollections.observableArrayList();
        String sql = "select * from instrutores where cpf = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Atendentes atendente = new Atendentes();
            atendente.setId(rs.getInt("id"));
            atendente.setCPF(rs.getString("CPF"));
            atendente.setEntrada(rs.getTime("entrada").toLocalTime());
            atendente.setSaida(rs.getTime("saida").toLocalTime());
            
            lista.add(atendente);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}

