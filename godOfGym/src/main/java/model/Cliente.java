
package model;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Cliente {
    private int idCliente;
    private String CPF;
    private String idPlano;
    private float peso;
    private float altura;
    private float porcentagem;
    private float imc;
    private String experiencia;
    private String medicamentos;
    private String limitacoes;
     private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String senha;
    private String email;
    private String genero;
    private String telefone;
    private String tipo;

    public Cliente() {
    }

    public Cliente(int idCliente, String CPF, String idPlano, float peso, float altura, float porcentagem, float imc, String experiencia, String medicamentos, String limitacoes, String nome, String sobrenome, String dataNascimento, String senha, String email, String genero, String tipo,String telefone) {
        this.idCliente = idCliente;
        this.CPF = CPF;
        this.idPlano = idPlano;
        this.peso = peso;
        this.altura = altura;
        this.porcentagem = porcentagem;
        this.imc = imc;
        this.experiencia = experiencia;
        this.medicamentos = medicamentos;
        this.limitacoes = limitacoes;
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

    public Cliente(String CPF, String idPlano, float peso, float altura, float porcentagem, float imc, String experiencia, String medicamentos, String limitacoes) {
        this.CPF = CPF;
        this.idPlano = idPlano;
        this.peso = peso;
        this.altura = altura;
        this.porcentagem = porcentagem;
        this.imc = imc;
        this.experiencia = experiencia;
        this.medicamentos = medicamentos;
        this.limitacoes = limitacoes;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(String idPlano) {
        this.idPlano = idPlano;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getLimitacoes() {
        return limitacoes;
    }

    public void setLimitacoes(String limitacoes) {
        this.limitacoes = limitacoes;
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
    
    private transient StringProperty tipoProperty;
    
    public StringProperty tipoProperty(){
       if (tipoProperty == null){
           tipoProperty = new SimpleStringProperty(idPlano);
       }
       return tipoProperty;
    }

    void setUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
