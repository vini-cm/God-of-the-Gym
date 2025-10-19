
package model;


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
    private int id_usuario;

    public Cliente() {
    }

    public Cliente(String CPF, String idPlano, float peso, float altura, float porcentagem, float imc, String experiencia, String medicamentos, String limitacoes, int id_usuario) {
        this.CPF = CPF;
        this.idPlano = idPlano;
        this.peso = peso;
        this.altura = altura;
        this.porcentagem = porcentagem;
        this.imc = imc;
        this.experiencia = experiencia;
        this.medicamentos = medicamentos;
        this.limitacoes = limitacoes;
        this.id_usuario = id_usuario;
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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
}