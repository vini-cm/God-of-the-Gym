
package model;

import java.sql.Date;


public class Cliente {
    private int idCliente;
    private String CPF;
    private Date dataAssinatura;
    private String idPlano;

    public Cliente() {
    }

    public Cliente(String CPF, String idPlano) {
        this.CPF = CPF;
        this.idPlano = idPlano;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public String getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(String idPlano) {
        this.idPlano = idPlano;
    }
    
    
    
}
