package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO extends genericoDAO{
    public void salvar(Usuario user) throws SQLException{
        String insert = "insert int usuarios (CPF, nome, sobrenome, dataNascimento, senha, email, genero) values (?,?,?,?,?,?,?)";
        salvar(insert,user.getCPF(),user.getNome(),user.getSobrenome(),user.getDataNascimento(),user.getSenha(),user.getEmail(),user.getGenero());
    }
    
    public void editar (Usuario user) throws SQLException{
        String update = "UPDATE usuario" + "SET nome = ?, sobrenome = ?, dataNascimento = ?, senha = ?, email = ?, genero = ?" + "WHERE CPF = ?";
        editar(update, user.getNome(),user.getSobrenome(),user.getDataNascimento(),user.getSenha(),user.getEmail(),user.getGenero(),user.getCPF());
    }
    
    public void deletar(long CPF) throws SQLException{
        String delete = "DELETE from usuario where CPF = ?";
        deletar(delete,CPF);
    }
    
    public ObservableList<Usuario> selecionarUsuarios() throws SQLException {
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        String sql = "SELECT * from usuario";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Usuario user = new Usuario();
            user.setCPF(rs.getString("CPF"));
            user.setNome(rs.getString("Nome"));
            user.setSobrenome(rs.getString("Sobrenome"));
            user.setDataNascimento(rs.getString("dataNascimento"));
            user.setEmail(rs.getString("Email"));
            user.setSenha(rs.getString("Senha"));
            user.setGenero(rs.getString("genero").charAt(0));
            
            lista.add(user);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Usuario> selecionarUsuario(int CPF) throws SQLException {
        ObservableList<Usuario> usuario = FXCollections.observableArrayList();
        String sql = "SELECT * from usuario Where CPF = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setInt(1, CPF);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Usuario user = new Usuario();
            user.setCPF(rs.getString("CPF"));
            user.setNome(rs.getString("Nome"));
            user.setSobrenome(rs.getString("Sobrenome"));
            user.setDataNascimento(rs.getString("dataNascimento"));
            user.setEmail(rs.getString("Email"));
            user.setSenha(rs.getString("Senha"));
            user.setGenero(rs.getString("genero").charAt(0));
            
            usuario.add(user);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return usuario;
    }
}
