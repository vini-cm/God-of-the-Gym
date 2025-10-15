
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstrutorDAO extends genericoDAO{
  
    public void salvar(Instrutor instrutor) throws SQLException{
        String insert = "insert into instrutores(CPF,salario,formacao,associado,entrada,saida, id_usuario) values (?,?,?,?,?,?,?)";
        salvar(insert, instrutor.getCPF(),instrutor.getSalario(),instrutor.getFormacao(),instrutor.getAssociado(),
                instrutor.getEntrada(),instrutor.getSaida(), instrutor.getId_usuario());
    }
    
    public void editar(Instrutor instrutor) throws SQLException{
        String editar="update instrutor set salario = ?, formacao = ?, associado = ?, entrada = ?, saida = ? where CPF = ?";
        editar(editar, instrutor.getCPF(), instrutor.getSalario(),instrutor.getFormacao(),instrutor.getAssociado(), 
                instrutor.getEntrada(), instrutor.getSaida(),instrutor.getCPF());
    }
    
    public void deletar(String CPF) throws SQLException{
        String deletar = "delete instrutor where CPF = ?";
        deletar(deletar, CPF, CPF);
    }
    
    public ObservableList<Instrutor> selecionarInstrutores() throws SQLException{
        ObservableList<Instrutor> lista = FXCollections.observableArrayList();
        String sql = "select * from instrutores";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Instrutor instrutor = new Instrutor();
            instrutor.setId(rs.getInt("id"));
            instrutor.setCPF(rs.getString("CPF"));
            instrutor.setFormacao(rs.getString("formacao"));
            instrutor.setAssociado(rs.getString("associado"));
            instrutor.setEntrada(rs.getTime("saida").toLocalTime());
            instrutor.setSaida(rs.getTime("saida").toLocalTime());
            instrutor.setId_usuario(rs.getInt("id_usuario"));
            
            lista.add(instrutor);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Instrutor> selecionarInstrutor(String CPF) throws SQLException{
        ObservableList<Instrutor> lista = FXCollections.observableArrayList();
        String sql = "select * from instrutores where CPF = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Instrutor instrutor = new Instrutor();
            instrutor.setId(rs.getInt("id"));
            instrutor.setCPF(rs.getString("CPF"));
            instrutor.setFormacao(rs.getString("formacao"));
            instrutor.setAssociado(rs.getString("associado"));
            instrutor.setEntrada(rs.getTime("entrada").toLocalTime());
            instrutor.setSaida(rs.getTime("saida").toLocalTime());
            instrutor.setId_usuario(rs.getInt("id_usuario"));
            
            lista.add(instrutor);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
}
