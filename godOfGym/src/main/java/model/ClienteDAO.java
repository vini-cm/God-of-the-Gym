
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteDAO extends genericoDAO{
    public void salvar(Cliente cliente) throws SQLException{
        String insert = "insert into clientes (cpf,id_plano,peso,altura,porcentagem_gordura,imc,experiencia,medicamentos,limitacoes) values (?,?,?,?,?,?,?,?,?)";
        salvar(insert, cliente.getCPF(),cliente.getIdPlano(),cliente.getPeso(), cliente.getAltura(), cliente.getPorcentagem(), cliente.getImc(),
                cliente.getExperiencia(),cliente.getMedicamentos(),cliente.getLimitacoes());
    }
    
    public void editar (Cliente cliente) throws SQLException{
        String update = "update clientes id_plano =?,peso =?, altura =?, porcentagem_gordura=?,imc =?, experiencia =?,medicamentos =?,limitacoes =? where cpf = ?";
        editar(update, cliente.getIdPlano(),cliente.getPeso(), cliente.getAltura(), cliente.getPorcentagem(), cliente.getImc(),
                cliente.getExperiencia(),cliente.getMedicamentos(),cliente.getLimitacoes(), cliente.getCPF());
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
            cliente.setIdCliente(rs.getInt("id"));
            cliente.setCPF(rs.getString("cpf"));
            cliente.setIdPlano(rs.getString("id_plano"));
            cliente.setAltura(rs.getFloat("altura"));
            cliente.setPeso(rs.getFloat("peso"));
            cliente.setImc(rs.getFloat("imc"));
            cliente.setPorcentagem(rs.getFloat("porcentagem_gordura"));
            cliente.setExperiencia(rs.getString("experiencia"));
            cliente.setMedicamentos(rs.getString("medicamentos"));
            cliente.setLimitacoes(rs.getString("limitacoes"));
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
            cliente.setIdCliente(rs.getInt("id"));
            cliente.setCPF(rs.getString("cpf"));
            cliente.setIdPlano(rs.getString("id_plano"));
            cliente.setAltura(rs.getFloat("altura"));
            cliente.setPeso(rs.getFloat("peso"));
            cliente.setImc(rs.getFloat("imc"));
            cliente.setPorcentagem(rs.getFloat("porcentagem_gordura"));
            cliente.setExperiencia(rs.getString("experiencia"));
            cliente.setMedicamentos(rs.getString("medicamentos"));
            cliente.setLimitacoes(rs.getString("limitacoes"));
            lista.add(cliente);
        }
        
        rs.close();
        stmt.close();
        conectarConn().close();
        return lista;
    }
}
