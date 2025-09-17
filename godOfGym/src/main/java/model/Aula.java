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
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(){
        this.tipo = tipo;
    }
    
    public LocalTime getData(){
        return data;
    }
    
    public void setData(){
        this.data = data;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(){
        this.descricao = descricao;
    }
    
    public int getVagas(){
        return vagas;
    }
    
    public void setVagas(){
        this.vagas = vagas;
    }
    
    public int getIdAula(){
        return idAula;
    }
    
    public void setIdAula(){
        this.idAula = idAula;
    }
}
