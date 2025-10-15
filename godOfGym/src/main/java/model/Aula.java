package model;

import java.time.LocalDateTime;

public class Aula {
   private int id;
   private String tipo;
   private LocalDateTime data;
   private String descricao;
   private int vagas;
   private String professor;

    public Aula() {
    }

    public Aula(String tipo, LocalDateTime data, String descricao, int vagas, String professor) {
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.vagas = vagas;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
   
   
}
