package model;

import java.time.LocalTime;

public class Aula {
    private int idAula;
    private String tipo;
    private LocalTime data;
    private String descricao;
    private int vagas;

    public Aula() {
    }


    public Aula(String tipo, LocalTime data, String descricao, int vagas, int idAula) {
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.vagas = vagas;
        this.idAula = idAula;
    }


    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

    public LocalTime getData() {
        return data;
    }
    
    public void setData(LocalTime data) {
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


    public int getIdAula() {
        return idAula;
    }
    
    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }
}