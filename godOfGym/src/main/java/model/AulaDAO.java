package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AulaDAO extends genericoDAO{
    public void salvar(Aula aula) throws SQLException{
        String insert = "insert into aulas (Nome, Data, Descricao, Vagas, Professor, Id_Professor) values (?,?,?,?,?,?,?)";
        salvar(insert,aula.getNome(), aula.getData(),aula.getDescricao(), aula.getVagas(),aula.getNomeInst(), aula.getIdInst());
    }
    
    public void editar (Aula aula) throws SQLException{
        String update = "update aulas set Nome = ?, Data = ?, Descricao = ?, Vagas = ?, Professor = ?, Id_Professor = ? where idAula = ?";
        editar(update, aula.getNome(), aula.getData(),aula.getDescricao(), aula.getVagas(), aula.getIdAula(), aula.getNomeInst(), aula.getIdInst());
     }
    
    public void deletar(String idAula) throws SQLException{
        String deletar = "delete aulas where idAula = ?";
        deletar(deletar, idAula);
    }
    
    public ObservableList<Aula> selecionarAulas() throws SQLException{
        ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select * from aulas";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Aula aula = new Aula();
            aula.setTipo(rs.getString("Tipo"));
            aula.setNome(rs.getString("Nome"));
            aula.setNomeInst(rs.getString("Professor"));
            aula.setIdInst(rs.getInt("Id_Professor"));
            aula.setIdAula(rs.getInt("IdAula"));
            aula.setData(rs.getTimestamp("Data").toLocalDateTime());
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
            aula.setNome(rs.getString("Nome"));
            aula.setNomeInst(rs.getString("Professor"));
            aula.setIdInst(rs.getInt("Id_Professor"));
            aula.setIdAula(rs.getInt("IdAula"));
            aula.setData(rs.getTimestamp("Data").toLocalDateTime());
            aula.setVagas(rs.getInt("Vagas"));
            aula.setDescricao(rs.getString("Descricao"));
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    public ObservableList<Aula> selecionarProfessor(int id_instrutor) throws SQLException {
    ObservableList<Aula> lista = FXCollections.observableArrayList();
        String sql = "select a.*,i.*,u.*" + 
                "from aulas a" +
                "INNER JOIN instrutores i On a.id_instrutor = i.id" +
                "INNER JOIN usuarios u On i.cpf = u.cpf" +
                "where id_instrutor = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setInt(1, id_instrutor);
        ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        Usuario user = new Usuario();
        user.setIdUsuario(rs.getInt("id"));
            user.setCPF(rs.getString("CPF"));
            user.setNome(rs.getString("Nome"));
            user.setSobrenome(rs.getString("Sobrenome"));
            user.setDataNascimento(rs.getString("dataNascimento"));
            user.setEmail(rs.getString("Email"));
            user.setSenha(rs.getString("Senha"));
            user.setGenero(rs.getString("genero"));
            user.setTelefone(rs.getString("telefone"));
            user.setTipo(rs.getString("tipo"));
        
        Aula aula = new Aula();
        aula.setUsuario(user);
        aula.setTipo(rs.getString("Tipo"));
        aula.setNome(rs.getString("Nome"));
        aula.setNomeInst(rs.getString("Professor"));
        aula.setIdInst(rs.getInt("Id_Professor"));
        aula.setIdAula(rs.getInt("IdAula"));
        aula.setData(rs.getTimestamp("Data").toLocalDateTime());
        aula.setVagas(rs.getInt("Vagas"));
        aula.setDescricao(rs.getString("Descricao"));
        
        lista.add(aula);
    }
        return lista;
    }
}