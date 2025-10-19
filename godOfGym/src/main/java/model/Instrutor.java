package model;

import java.time.LocalTime;

public class Instrutor {
   private int id;
   private String CPF;
   private float salario;
   private String formacao;
   private LocalTime entrada;
   private LocalTime saida;
   private int id_usuario;

    public Instrutor() {
    }

    public Instrutor(String CPF, float salario, String formacao, LocalTime entrada, LocalTime saida, int id_usuario) {
        this.id = id;
        this.CPF = CPF;
        this.salario = salario;
        this.formacao = formacao;
        this.entrada = entrada;
        this.saida = saida;
        this.id_usuario = id_usuario;
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

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
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