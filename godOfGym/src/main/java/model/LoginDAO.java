package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends genericoDAO {

    public Boolean bancoOnline() {
        Connection conn = conectarConn();
        if (conn != null) {
            try {
                conectarConn().close();
            } catch (SQLException e) {
            }
            return false;
        }
        return true;
    }

    public Usuario autenticar(String Nome, String Senha) throws SQLException {
        String sql = "SELECT * from usuarios Where Nome = ? and Senha = MD5(?)";
        Usuario user = null;
        Connection conn = conectarConn();
        if (conn != null) {
            PreparedStatement stmt = conectarConn().prepareStatement(sql);
            stmt.setString(1, Nome);
            stmt.setString(2, Senha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new Usuario();
                user.setIdUsuario(rs.getInt("IdUsuario"));
                user.setCPF(rs.getString("CPF"));
                user.setNome(rs.getString("Nome"));
                user.setSobrenome(rs.getString("Sobrenome"));
                user.setDataNascimento(rs.getString("dataNascimento"));
                user.setEmail(rs.getString("Email"));
                user.setSenha(rs.getString("Senha"));
                user.setGenero(rs.getString("genero"));
            }
            rs.close();
            stmt.close();
            conectarConn().close();
            return user;
        } else {
            System.out.println(" não Conectado com Conn");
            return null;
        }

    }
}
