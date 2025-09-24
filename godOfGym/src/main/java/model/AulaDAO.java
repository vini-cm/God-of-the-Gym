package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AulaDAO extends genericoDAO {
    
    public void salvar (Aula aula) throws SQLException{
        String insert = "insert into aulas (tipo,descrisao,vagas,data,professor) values (?,?,?,?,?)";
        salvar(insert, aula.getTipo(),aula.getDescricao(),aula.getVagas(),aula.getData(),aula.getProfessor());
    }
    
    public void editar (Aula aula) throws SQLException{
        String update = "update aulas set tipo=?, descrisao=?,vagas=?,data=?,professor=? where id=?";
        editar(update,aula.getId(),aula.getTipo(),aula.getDescricao(),aula.getVagas(),aula.getData(),aula.getProfessor(),aula.getId());
    }
    
    public void deletar (int id) throws SQLException{
        String deletar = "delete aula where id=?";
        deletar(deletar, id, id);
    }
    
    public ObservableList<Aula> listarAulas() throws SQLException{
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas";
        PreparedStatement ptsm = conectarConn().prepareStatement(sql);
        ResultSet rs = ptsm.executeQuery();
        while (rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setTipo(rs.getString("Tipo"));
            aula.setDescricao(rs.getString("Descricao"));
            aula.setVagas(rs.getInt("Vagas"));
            aula.setProfessor(rs.getInt("Professor"));
            aula.setData(rs.getString("Data"));
            lista.add(aula);
        }
        rs.close();
        ptsm.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Aula> selecionarAula(int id) throws SQLException{
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas where id=?";
        PreparedStatement ptsm = conectarConn().prepareStatement(sql);
        ptsm.setInt(1, id);
        ResultSet rs = ptsm.executeQuery();
        while (rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setTipo(rs.getString("Tipo"));
            aula.setDescricao(rs.getString("Descricao"));
            aula.setVagas(rs.getInt("Vagas"));
            aula.setProfessor(rs.getInt("Professor"));
            aula.setData(rs.getString("Data"));
            lista.add(aula);
        }
        rs.close();
        ptsm.close();
        conectarConn().close();
        return lista;
    }
}
