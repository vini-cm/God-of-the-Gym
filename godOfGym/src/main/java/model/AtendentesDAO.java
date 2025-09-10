
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtendentesDAO extends genericoDAO{
  
    public void salvar(Atendentes atendente) throws SQLException{
        String insert = "insert into recepcionista(CPF,salario) values (?,?)";
        salvar(insert, atendente.getCPF(),atendente.getSalario());
    }
    
    public void editar(Atendentes atendente) throws SQLException{
        String editar="update recepcionista set salario = ? where CPF = ?";
        editar(editar, atendente.getCPF(), atendente.getSalario());
    }
    
    public void deletar(String CPF) throws SQLException{
        String deletar = "delete recepcionista where CPF = ?";
        deletar(deletar, CPF, CPF);
    }
    
    public ObservableList<Atendentes> selecionarAtendentes() throws SQLException{
        ObservableList<Atendentes> lista = FXCollections.observableArrayList();
        String sql = "select * from ";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Atendentes atendentes = new Atendentes();
            atendentes.setId(rs.getInt("id"));
            atendentes.setCPF(rs.getString("CPF"));
            
            lista.add(atendentes);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Instrutor> selecionarInstrutor(String CPF) throws SQLException{
        ObservableList<Instrutor> lista = FXCollections.observableArrayList();
        String sql = "select * from isntrutores where CPF = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Instrutor instrutor = new Instrutor();
            instrutor.setId(rs.getInt("id"));
            instrutor.setCPF(rs.getString("CPF"));
            instrutor.setFormacao(rs.getString("formacao"));
            instrutor.setAssociado(rs.getString("associado"));
            instrutor.setEntrada(rs.getTime("saida").toLocalTime());
            instrutor.setSaida(rs.getTime("saida").toLocalTime());
            
            lista.add(instrutor);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
}
