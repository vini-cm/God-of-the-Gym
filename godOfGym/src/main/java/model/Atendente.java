
package model;

import java.time.LocalTime;

public class Atendente {
    private int id;
   private String CPF;
   private float salario;
   private LocalTime entrada;
   private LocalTime saida;

    public Atendente() {
    }

    public Atendente(String CPF, float salario, LocalTime entrada, LocalTime saida) {
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
   
   
}
