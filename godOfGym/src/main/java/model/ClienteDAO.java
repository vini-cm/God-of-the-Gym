
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteDAO extends genericoDAO{
    public void salvar(Cliente cliente) throws SQLException{
        String insert = "insert into clientes (CPF, idPlano) values (?,?)";
        salvar(insert, cliente.getCPF(),cliente.getIdPlano());
    }
    
    public void editar (Cliente cliente) throws SQLException{
        String update = "update clientes set cpf = ?, idPlano = ? where idCliente = ?";
        editar(update, cliente.getCPF(), cliente.getCPF(),cliente.getIdPlano(),cliente.getIdCliente());
    }
    
    public void deletar(String CPF) throws SQLException{
        String deletar = "delete clientes where cpf = ?";
        deletar(deletar,CPF,CPF);
    }
    
    public ObservableList<Cliente> selecionarClientes() throws SQLException{
        ObservableList<Cliente> lista = FXCollections.observableArrayList();
        String sql = "select * from clientes";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setCPF(rs.getString("CPF"));
            cliente.setDataAssinatura(rs.getDate("DataAssinatura"));
            cliente.setIdPlano(rs.getString("IdPlano"));
            
            lista.add(cliente);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
    
    public ObservableList<Cliente> selecionarCliente(String CPF) throws SQLException{
        ObservableList<Cliente> lista = FXCollections.observableArrayList();
        String sql = "select * from clientes where CPF = ?";
        PreparedStatement stmt = conectarConn().prepareStatement(sql);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setCPF(rs.getString("CPF"));
            cliente.setDataAssinatura(rs.getDate("DataAssinatura"));
            cliente.setIdPlano(rs.getString("IdPlano"));
            
            lista.add(cliente);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}
