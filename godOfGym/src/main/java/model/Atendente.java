
package model;

import java.time.LocalTime;

public class Atendente {
   private int id;
   private int id_usuario;
   private String CPF;
   private float salario;
   private LocalTime entrada;
   private LocalTime saida;
   
   public Atendente(){
   }

    public Atendente(int id_usuario, String CPF, float salario, LocalTime entrada, LocalTime saida) {
        this.id_usuario = id_usuario;
        this.CPF = CPF;
        this.salario = salario;
        this.entrada = entrada;
        this.saida = saida;
    }
   
    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }   

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}