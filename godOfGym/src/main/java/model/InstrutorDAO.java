
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstrutorDAO extends genericoDAO{
  
    public void salvar(Instrutor instrutor) throws SQLException{
        String insert = "insert into instrutores(CPF,salario,formacao,entrada,saida) values (?,?,?,?,?)";
        salvar(insert, instrutor.getCPF(),instrutor.getSalario(),instrutor.getFormacao(),
                instrutor.getEntrada(),instrutor.getSaida());
    }
    
    public void editar(Instrutor instrutor) throws SQLException{
        String editar="update instrutores set salario = "+ instrutor.getSalario() +", formacao = '"+ instrutor.getFormacao() +
                "', entrada = '"+ instrutor.getEntrada().toString() +"', saida = '"+ instrutor.getSaida().toString() +"' where CPF = ?";
        editar(editar,instrutor.getCPF());
    }
    
    public void deletar(String CPF) throws SQLException{
        String deletar = "delete from instrutores where CPF = ?";
        deletar(deletar,CPF);
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
            instrutor.setEntrada(rs.getTime("saida").toLocalTime());
            instrutor.setSaida(rs.getTime("saida").toLocalTime());
            instrutor.setSalario(rs.getFloat("salario"));
            lista.add(instrutor);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public Instrutor selecionarInstrutor(String CPF) throws SQLException{
        String sql = "select * from instrutores where CPF = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()){
            Instrutor instrutor = new Instrutor();
            instrutor.setId(rs.getInt("id"));
            instrutor.setCPF(rs.getString("CPF"));
            instrutor.setFormacao(rs.getString("formacao"));
            instrutor.setEntrada(rs.getTime("entrada").toLocalTime());
            instrutor.setSaida(rs.getTime("saida").toLocalTime());
            instrutor.setSalario(rs.getFloat("salario"));
            rs.close();
            stmt.close();
            conectarConn().close();
            return instrutor;
        } else {  
            rs.close();
            stmt.close();
            conectarConn().close();
            return null;
        }
    }
    
}