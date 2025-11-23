
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
        String delete = "delete from planos where id_aluno =?";
        deletar(delete,p.getCpf_participante());
    }
    
    public ObservableList<Participantes> listarParticipantes(int id) throws SQLException{
        ObservableList<Participantes> lista = FXCollections.observableArrayList();
        String sql = "select * from aula_alunos where id_aula = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setInt(0, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Participantes p = new Participantes();
            p.setId(rs.getInt("id"));
            p.setId_aula(rs.getInt("id_aula"));
            p.setCpf_participante(rs.getString("id_aluno"));
            lista.add(p);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}
