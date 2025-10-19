package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
    
    private int idUsuario;
    private String CPF;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String senha;
    private String email;
    private String genero;
    private String telefone;
    private String tipo;


    public Usuario() {
    }

    public Usuario(int idUsuario, String CPF, String nome, String sobrenome, String dataNascimento, String senha, String email, String genero, String tipo) {
        this.idUsuario = idUsuario;
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.genero = genero;
        this.tipo = tipo;
    }

    public Usuario(String CPF, String nome, String sobrenome, String dataNascimento, String senha, String email, String genero, String telefone,String tipo) {
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
    
    private transient StringProperty nomeProperty;
    
    public StringProperty nomeProperty(){
        if (nomeProperty == null){
            nomeProperty = new SimpleStringProperty(nome);
        }
        return nomeProperty;
    }
    
    private transient StringProperty sobrenomeProperty;
    
    public StringProperty sobrenomeProperty(){
       if (sobrenomeProperty == null){
           sobrenomeProperty = new SimpleStringProperty(sobrenome);
       }
       return sobrenomeProperty;
    }
    
    private transient StringProperty cpfProperty;
    
    public StringProperty cpfProperty(){
       if (cpfProperty == null){
           cpfProperty = new SimpleStringProperty(CPF);
       }
       return cpfProperty;
    }
    
    private transient StringProperty telefoneProperty;
    
    public StringProperty telefoneProperty(){
       if (telefoneProperty == null){
           telefoneProperty = new SimpleStringProperty(telefone);
       }
       return telefoneProperty;
    }
    
}
