package model;

import java.time.LocalDateTime;

public class Aula {
    private String nome;
    private int idAula;
    private String tipo;
    private LocalDateTime data;
    private String descricao;
    private int vagas;
    private int id_instrutor;
    private String nome_instrutor;
    private Usuario usuario;
    private float preco;

    public Aula() {
    }


    public Aula( float preco, Usuario usuario, String tipo,int id_instrutor,String nome_instrutor, String nome, LocalDateTime data, String descricao, int vagas, int idAula) {
        this.tipo = tipo;
        this.preco = preco;
        this.usuario = usuario;
        this.nome_instrutor = nome_instrutor;
        this.id_instrutor = id_instrutor;
        this.data = data;
        this.descricao = descricao;
        this.vagas = vagas;
        this.idAula = idAula;
        this.nome = nome;
    }
    
    public float getPreco(){
        return preco;
    }
    
    public void setPreco(float preco){
        this.preco = preco;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public String getNomeInst(){
        return nome_instrutor;
    }
    
    public void setNomeInst(String nome_instrutor){
        this.nome_instrutor = nome_instrutor;
    }
    
    public int getIdInst(){
        return id_instrutor;
    }
    
    public void setIdInst(int id_instrutor){
        this.id_instrutor = id_instrutor;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
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


    public int getIdAula() {
        return idAula;
    }
    
    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

}