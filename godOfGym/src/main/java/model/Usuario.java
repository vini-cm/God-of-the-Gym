package model;

import java.sql.Date;

public class Usuario {
    
    private String CPF;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String senha;
    private String email;
    private String genero;
    private String telefone;
    private String tipo;


    public Usuario() {
    }

    public Usuario(String CPF, String nome, String sobrenome, Date dataNascimento, String senha, String email, String genero, String telefone,String tipo) {
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.genero = genero;
        this.telefone = telefone;
        this.tipo = tipo;
    }
    
    

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  nome + " " + sobrenome;
    }
    
    
}
