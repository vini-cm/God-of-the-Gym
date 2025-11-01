
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AulaDAO extends genericoDAO {
    
    public void salvar(Aula aula) throws SQLException{
        String insert = "insert into aulas (nome,tipo,descricao,vagas,professor,data) values (?,?,?,?,?,?)";
        salvar(insert, aula.getNome(), aula.getTipo(),aula.getDescricao(),aula.getVagas(),aula.getProfessor(),aula.getData());
    }
    
    public void editar(Aula aula) throws SQLException{
        String update = "update aulas" + "set nome=? tipo=?, descricao=?, vagas =?, professor=?,data=?" + "where id=?";
        editar(update,aula.getNome(), aula.getTipo(),aula.getDescricao(),aula.getVagas(),aula.getProfessor(),aula.getData(), aula.getId());
    }
    
    public void deletar(int id) throws SQLException{
        String delete = "delete from aulas where id = ?";
        deletar(delete, id);
    }
    
    public ObservableList<Aula> selecionarAulas() throws SQLException{
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setNome(rs.getString("nome"));
            aula.setTipo(rs.getString("Tipo"));
            aula.setDescricao(rs.getString("Descricao"));
            aula.setProfessor(rs.getString("cpf_professor"));
            aula.setVagas(rs.getInt("Vagas"));
            aula.setData(LocalDateTime.parse(rs.getString("data")));
            lista.add(aula);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public Aula selecionarAula() throws SQLException{
        String sql = "select * from aulas";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        try(ResultSet rs = stmt.executeQuery()){
        if (rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setNome(rs.getString("nome"));
            aula.setTipo(rs.getString("Tipo"));
            aula.setDescricao(rs.getString("Descricao"));
            aula.setProfessor(rs.getString("cpf_professor"));
            aula.setVagas(rs.getInt("Vagas"));
            aula.setData(LocalDateTime.parse(rs.getString("data")));
            return aula;
        } else {
        return null; }
        }
    }
}
