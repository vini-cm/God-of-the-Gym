package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class Aula {
    private int idAula;
    private String tipo;
    private LocalDate data;
    private String descricao;
    private LocalTime horario;
    private int vagas;

    public Aula(LocalTime parse, String text, LocalDate parse1, String text1, int parseInt) {
    }


    public Aula(LocalTime horario, String tipo, LocalDate data, String descricao, int vagas, int idAula) {
        this.horario = horario;
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.vagas = vagas;
        this.idAula = idAula;
    }
    public LocalTime getHorario() {
        return horario;
    }
    
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
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