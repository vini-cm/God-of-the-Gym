package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlanosDAO extends genericoDAO{
    
    public void salvar(Planos plano) throws SQLException{
        String insert = "insert into planos (nome,tipo,preco) values (?,?,?)";
        salvar(insert,plano.getNome(),plano.getTipo(),plano.getPreco());
    }
    
    public void editar(Planos plano) throws SQLException{
        String update = "update planos" + "set nome=? tipo=?, set preco=?" + "where idPlano=?";
        editar(update,plano.getNome(), plano.getTipo(),plano.getPreco(),plano.getIdPlano());
    }
    
    /*public void deletar(int id) throws SQLException{
        String delete = "delete from planos where idPlano = ?";
        deletar(delete, id);
    }*/
    
    public ObservableList<Planos> selecionarPlanos() throws SQLException{
        ObservableList<Planos> lista = FXCollections.observableArrayList();
        String sql = "select * from planos";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Planos plano = new Planos();
            plano.setIdPlano(rs.getInt("id"));
            plano.setNome(rs.getString("nome"));
            plano.setTipo(rs.getString("tipo"));
            plano.setPreco(rs.getFloat("preco"));
            
            lista.add(plano);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Planos> selecionarPlano(String tipo) throws SQLException{
        ObservableList<Planos> lista = FXCollections.observableArrayList();
        String sql = "select * from planos where tipo = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, tipo);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Planos plano = new Planos();
            plano.setIdPlano(rs.getInt("id"));
            plano.setNome(rs.getString("nome"));
            plano.setTipo(rs.getString("tipo"));
            plano.setPreco(rs.getFloat("preco"));
            
            lista.add(plano);
        }
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}
