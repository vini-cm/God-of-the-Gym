package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO extends genericoDAO{
    public void salvar(Usuario user) throws SQLException{
        String insert = "insert into usuarios (CPF, nome, sobrenome, dataNascimento, senha, email, genero, telefone, tipo) values (?,?,?,?,MD5(?),?,?,?,?)";
        salvar(insert,user.getCPF(),user.getNome(),user.getSobrenome(),user.getDataNascimento(),user.getSenha(),user.getEmail(),user.getGenero(), user.getTelefone(), user.getTipo());
    }
    
    public void editar (Usuario user) throws SQLException{
        String update = "UPDATE usuarios" + "SET nome = ?, sobrenome = ?, dataNascimento = ?, senha = ?, email = ?, genero = ?, telefone = ?" + "WHERE CPF = ?";
        editar(update, user.getNome(),user.getSobrenome(),user.getDataNascimento(),user.getSenha(),user.getEmail(),user.getGenero(),user.getCPF(), user.getTelefone());
    }
    
    public void deletar(String CPF) throws SQLException{
        String delete = "DELETE from usuarios where CPF = ?";
        deletar(delete,CPF);
    }
    
    public ObservableList<Usuario> selecionarUsuarios() throws SQLException {
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        String sql = "SELECT * from usuarios";
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
    
    public ObservableList<Usuario> selecionarUsuario(String CPF) throws SQLException {
        ObservableList<Usuario> usuario = FXCollections.observableArrayList();
        String sql = "SELECT * from usuarios Where CPF = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Usuario user = new Usuario();
            user.setCPF(rs.getString("CPF"));
            user.setNome(rs.getString("Nome"));
            user.setSobrenome(rs.getString("Sobrenome"));
            user.setDataNascimento(rs.getString("dataNascimento"));
            user.setEmail(rs.getString("Email"));
            user.setSenha(rs.getString("Senha"));
            user.setGenero(rs.getString("genero"));
            user.setTelefone(rs.getString("telefone"));
            user.setTipo(rs.getString("tipo"));
            usuario.add(user);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return usuario;
    }
}
