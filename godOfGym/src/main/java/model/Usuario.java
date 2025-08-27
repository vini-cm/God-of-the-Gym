package model;

public class Usuario {
    
    private int idUsuario;
    private String CPF;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String senha;
    private String email;
    private String genero;


    public Usuario() {
    }

    public Usuario(int idUsuario, String CPF, String nome, String sobrenome, String dataNascimento, String senha, String email, String genero) {
        this.idUsuario = idUsuario;
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.genero = genero;
    }

    public Usuario(String CPF, String nome, String sobrenome, String dataNascimento, String senha, String email, String genero) {
        this.CPF = CPF;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
        this.email = email;
        this.genero = genero;
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
    
    

}
