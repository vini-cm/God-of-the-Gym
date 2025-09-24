package model;

import java.time.LocalDateTime;



public class Aula {
    private int id;
    private String tipo;
    private String descricao;
    private int vagas;
    private String data;
    private int professor;

    public Aula() {
    }

    public Aula(String tipo, String descricao, int vagas, String data, int professor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.vagas = vagas;
        this.data = data;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }
    
    
    
}
