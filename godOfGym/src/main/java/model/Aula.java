
package model;

import java.time.LocalDateTime;

public class Aula {
    private int id;
    private String nome;
    private String descricao;
    private String tipo;
    private String professor;
    private int vagas;
    private LocalDateTime data;

    public Aula() {
    }

    public Aula(String nome, String descricao, String tipo, String professor, int vagas, LocalDateTime data) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.professor = professor;
        this.vagas = vagas;
        this.data = data;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    
}
