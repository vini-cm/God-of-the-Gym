package model;

import java.time.LocalTime;

public class Atendentes {
   private int id;
   private String CPF;
   private float salario;

    public Atendentes() {
    }

    public Atendentes(int id, String CPF, float salario) {
        this.CPF = CPF;
        this.salario = salario;
        this.id = id;
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
}