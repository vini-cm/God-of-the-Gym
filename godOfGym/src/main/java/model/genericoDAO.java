package model;

import dal.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class genericoDAO {
    private Connection connection;
    
    protected genericoDAO(){
        this.connection = Conn.conectar();
    }
    
    protected Connection conectarConn(){
        this.connection = Conn.conectar();
        if (connection == null){
            System.out.println("erro com Conn");
            System.exit(0);
        }
        return connection;
    }
    
    public void salvar (String insertsql, Object... parametros) throws SQLException{
        PreparedStatement stmt = conectarConn().prepareStatement(insertsql);
        for ( int i = 0; i < parametros.length; i++){
            stmt.setObject(i + 1, parametros[i]);
        }
        stmt.execute();
        stmt.close();
        connection.close();
    }
    
     protected void editar (String editarsql,Object cpf, Object... parametros) throws SQLException{
         PreparedStatement stmt = conectarConn().prepareStatement(editarsql);
        for ( int i = 0; i < parametros.length; i++){
            stmt.setObject(i + 1, parametros[i]);
        }
        stmt.setObject(parametros.length + 1, cpf);
        stmt.execute();
        stmt.close();
        connection.close();
     }
     
      protected void deletar (String deletarsql,Object cpf, Object... parametros) throws SQLException{
        PreparedStatement stmt = conectarConn().prepareStatement(deletarsql);
        for ( int i = 0; i < parametros.length; i++){
            stmt.setObject(i + 1, parametros[i]);
        }
        stmt.setObject(parametros.length + 1, cpf);
        stmt.execute();
        stmt.close();
        connection.close();
    }
}
