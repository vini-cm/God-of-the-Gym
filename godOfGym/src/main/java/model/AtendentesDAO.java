
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtendentesDAO extends genericoDAO{
  
    public void salvar(Atendentes atendente) throws SQLException{
        String insert = "insert into recepcionista(CPF,salario,Entrada,Saida) values (?,?)";
        salvar(insert, atendente.getCPF(),atendente.getSalario(), atendente.getEntrada(), atendente.getSaida());
    }
    
    public void editar(Atendentes atendente) throws SQLException{
        String editar="update recepcionista set salario = ?, entrada = ?, saida = ? where CPF = ?";
        editar(editar, atendente.getCPF(), atendente.getSalario(), atendente.getEntrada(), atendente.getSaida());
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
            atendentes.setSalario(rs.getFloat("salario"));
            atendentes.setEntrada(rs.getTime("entrada").toLocalTime());
            atendentes.setSaida(rs.getTime("saida").toLocalTime());
            
            
            lista.add(atendentes);
        }   
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}
