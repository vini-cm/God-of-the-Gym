package dal;

import java.sql.*;

public class Conn {

    private static final String URL = "jdbc:mysql://localhost:3306/godofthegym";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String SENHA = "admin";

    public static Connection conectar() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void desconectar(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }
}
