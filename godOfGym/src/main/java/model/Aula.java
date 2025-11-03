
package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Aula {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private String professor;
    private int vagas;
    private LocalDate data;
    private LocalTime comeco;
    private LocalTime fim;

    public Aula() {
    }

    public Aula(String nome, String descricao, String tipo, String professor, int vagas, LocalDate data, LocalTime comeco, LocalTime fim) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.professor = professor;
        this.vagas = vagas;
        this.data = data;
        this.comeco = comeco;
        this.fim = fim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getComeco() {
        return comeco;
    }

    public void setComeco(LocalTime comeco) {
        this.comeco = comeco;
    }

    public LocalTime getFim() {
        return fim;
    }

    public void setFim(LocalTime fim) {
        this.fim = fim;
    }
    
    
}
