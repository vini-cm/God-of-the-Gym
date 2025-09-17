package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AulaDAO extends genericoDAO{
    public void salvar(Aula aula) throws SQLException{
        String insert = "insert into aulas (Data, descricao, vagas) values (?,?)";
        salvar(insert, aula.getData(),aula.getDescricao(), aula.getVagas());
    }
    
    public void editar (Aula aula) throws SQLException{
        String update = "update aulas set Data = ?, descricao = ?, vagas = ? where idAula = ?";
        editar(update,aula.getData(),aula.getDescricao(), aula.getVagas(), aula.getIdAula());
     }
    
    public void deletar(String idAula) throws SQLException{
        String deletar = "delete aulas where idAula = ?";
        deletar(deletar, idAula);
    }
    //Arrumar
    public ObservableList<Aula> selecionarAulas() throws SQLException{
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Aula aula = new Aula();
            aula.setTipo(rs.getString("Tipo"));
            aula.setIdAula(rs.getInt("IdAula"));
            aula.setData(rs.getTime("Data").toLocalTime());
            aula.setVagas(rs.getInt("Vagas"));
            aula.setDescricao(rs.getString("Descricao"));
            lista.add(aula);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Aula> selecionarAula(String idAula) throws SQLException{
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas where IdAula = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, String.valueOf(idAula));
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Aula aula = new Aula();
            aula.setTipo(rs.getString("Tipo"));
            aula.setIdAula(rs.getInt("IdAula"));
            aula.setData(rs.getTime("Data").toLocalTime());
            aula.setVagas(rs.getInt("Vagas"));
            aula.setDescricao(rs.getString("Descricao"));
            lista.add(aula);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}