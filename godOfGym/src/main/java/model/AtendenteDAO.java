
package model;

import java.sql.SQLException;

public class AtendenteDAO extends genericoDAO{
   
    public void salvar(Atendente a) throws SQLException{
        String sql = "insert into atendentes(cpf,salario,entrada,saida) values (?,?,?,?)";
        salvar(sql, a.getCPF(),a.getSalario(),a.getEntrada(),a.getSaida());
    }
    
    public void editar(Atendente a) throws SQLException{
        String sql = " atendentes set salario = ?, entrada = ?, saida = ? where CPF = ?";
        editar(sql, a.getCPF(), a.getSalario(), a.getEntrada(), a.getSaida());
    }
    
    public void deletar(String CPF) throws SQLException{
        String deletar = "delete atendentes where CPF = ?";
        deletar(deletar, CPF, CPF);
    }
}
