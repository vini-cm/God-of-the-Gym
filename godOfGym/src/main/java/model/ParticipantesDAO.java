
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParticipantesDAO extends genericoDAO{
    
    public void salvar(Participantes p) throws SQLException{
        String insert = "insert into aula_aluno(id_aula,id_aluno) values (?,?)";
        salvar(insert,p.getId_aula(),p.getCpf_participante());
    }
    
    
    public void deletar(Participantes p) throws SQLException{
        String delete = "delete from aula_aluno where id_aluno =?";
        deletar(delete,p.getCpf_participante());
    }
    
    public Participantes pesquisarParticipante(String cpf) throws SQLException{
        String sql = "SELECT * FROM aula_aluno WHERE id_aluno = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, cpf);
        try(ResultSet rs = stmt.executeQuery()){
        if(rs.next()){
            Participantes participante = new Participantes();
            participante.setId(rs.getInt("id"));
            participante.setId_aula(rs.getInt("id_aula"));
            participante.setCpf_participante(rs.getString("id_aluno"));
            return participante;
        }else {
            return null;
        }
        }  
    }
    
    public ObservableList<Usuario> listarParticipantes(int id) throws SQLException{
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        String sql = "SELECT u.* FROM usuarios u INNER JOIN aula_aluno a ON u.cpf = a.id_aluno WHERE a.id_aula = ?;";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Usuario user = new Usuario();
            user.setCPF(rs.getString("CPF"));
            user.setNome(rs.getString("Nome"));
            user.setSobrenome(rs.getString("Sobrenome"));
            user.setDataNascimento(rs.getDate("dataNascimento"));
            user.setEmail(rs.getString("Email"));
            user.setSenha(rs.getString("Senha"));
            user.setGenero(rs.getString("genero"));
            user.setTelefone(rs.getString("telefone"));
            user.setTipo(rs.getString("tipo"));
            lista.add(user);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Usuario> listarClientes(int id) throws SQLException{
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        String sql = "SELECT u.* FROM usuarios u LEFT JOIN aula_aluno a ON u.cpf = a.id_aluno AND a.id_aula = ? "
                     + "WHERE a.id_aluno IS NULL AND u.tipo = 'cliente'";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Usuario user = new Usuario();
            user.setCPF(rs.getString("CPF"));
            user.setNome(rs.getString("Nome"));
            user.setSobrenome(rs.getString("Sobrenome"));
            user.setDataNascimento(rs.getDate("dataNascimento"));
            user.setEmail(rs.getString("Email"));
            user.setSenha(rs.getString("Senha"));
            user.setGenero(rs.getString("genero"));
            user.setTelefone(rs.getString("telefone"));
            user.setTipo(rs.getString("tipo"));
            lista.add(user);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}
