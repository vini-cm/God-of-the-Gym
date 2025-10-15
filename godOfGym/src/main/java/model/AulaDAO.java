package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AulaDAO extends genericoDAO {

    public void salvar(Aula aula) throws SQLException {
        String sql = "insert into aulas(tipo,data,descricao,vagas,professor) values (?,?,?,?,?)";
        salvar(sql, aula.getTipo(), aula.getData(), aula.getDescricao(), aula.getVagas(), aula.getProfessor());
    }

    public void editar(Aula aula) throws SQLException {
        String sql = "update aulas set tipo = ?, data = ?, descricao = ?, vagas = ?, professor = ? where id = ?";
        editar(sql, aula.getId(), aula.getTipo(), aula.getData(), aula.getDescricao(), aula.getVagas(), aula.getProfessor(), aula.getId());
    }

    public void deletar(String id) throws SQLException {
        String sql = "delete aulas where id=?";
        deletar(sql, id, id);
    }

    public ObservableList<Aula> listarAulas() throws SQLException {
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setDescricao(rs.getString("descricao"));
            aula.setProfessor(rs.getString("professor"));
            aula.setTipo(rs.getString("tipo"));
            aula.setData(LocalDateTime.parse(rs.getString("data")));
            lista.add(aula);
        }

        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public Aula pesquisarAula(int id) throws SQLException{
        String sql = "select from aulas where id =?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setDescricao(rs.getString("descricao"));
            aula.setProfessor(rs.getString("professor"));
            aula.setTipo(rs.getString("tipo"));
            aula.setData(LocalDateTime.parse(rs.getString("data")));
            return aula;
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return null;
    }
}
