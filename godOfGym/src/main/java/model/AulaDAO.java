
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AulaDAO extends genericoDAO {
    
    public void salvar(Aula aula) throws SQLException{
        String insert = "insert into aulas (nome,tipo,descricao,vagas,cpf_professor,data,comeco,fim) values (?,?,?,?,?,?,?,?)";
        salvar(insert, aula.getNome(), aula.getTipo(),aula.getDescricao(),aula.getVagas(),aula.getProfessor(),
                aula.getData(),aula.getComeco(),aula.getFim());
    }
    
    public void editar(Aula aula) throws SQLException{
        String update = "update aulas set nome=+'"+aula.getNome()+"', tipo='"+aula.getTipo()+
                "', descricao='"+aula.getDescricao()+"', vagas ="+aula.getVagas()+", cpf_professor='"+aula.getProfessor()+
                "',data='"+aula.getData().toString()+"',comeco='"+aula.getComeco().toString()+"',fim='"+aula.getFim().toString()+"' where id=?";
        editar(update,aula.getId());
    }
    
    public void diminuirVaga(Aula aula) throws SQLException{
        String diminuir = "update aula set vaga = ? where id = ?";
        editar(diminuir, aula.getVagas()-1,aula.getId());
    }
    
    public void deletar(int id) throws SQLException{
        String delete = "delete from aulas where id = ?";
        deletar(delete, id);
    }
    
    public void deletarAulaPorProfessor(String cpf) throws SQLException{
        String delete = "delete from aulas where cpf_professor=?";
        deletar(delete,cpf);
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
            aula.setData(LocalDate.parse(rs.getString("data")));
            aula.setComeco(LocalTime.parse(rs.getString("comeco")));
            aula.setFim(LocalTime.parse(rs.getString("fim")));
            lista.add(aula);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public Aula selecionarAula(String nome) throws SQLException{
        String sql = "select * from aulas where nome=?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, nome);
        try(ResultSet rs = stmt.executeQuery()){
        if (rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setNome(rs.getString("nome"));
            aula.setTipo(rs.getString("Tipo"));
            aula.setDescricao(rs.getString("Descricao"));
            aula.setProfessor(rs.getString("cpf_professor"));
            aula.setVagas(rs.getInt("Vagas"));
            aula.setData(LocalDate.parse(rs.getString("data")));
            aula.setComeco(LocalTime.parse(rs.getString("comeco")));
            aula.setFim(LocalTime.parse(rs.getString("fim")));
            return aula;
        } else {
        return null; }
        }
    }
    
    public Aula selecionarAulaPorId(int id) throws SQLException{
        String sql = "select * from aulas where id=?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setInt(1, id);
        try(ResultSet rs = stmt.executeQuery()){
        if (rs.next()){
            Aula aula = new Aula();
            aula.setId(rs.getInt("id"));
            aula.setNome(rs.getString("nome"));
            aula.setTipo(rs.getString("Tipo"));
            aula.setDescricao(rs.getString("Descricao"));
            aula.setProfessor(rs.getString("cpf_professor"));
            aula.setVagas(rs.getInt("Vagas"));
            aula.setData(LocalDate.parse(rs.getString("data")));
            aula.setComeco(LocalTime.parse(rs.getString("comeco")));
            aula.setFim(LocalTime.parse(rs.getString("fim")));
            return aula;
        } else {
        return null; }
        }
    }
}
