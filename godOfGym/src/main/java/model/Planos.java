
package model;

public class Planos {
    private int idPlano;
    private String nome;
    private String tipo;
    private String preco;

    public Planos() {
    }

    public Planos(String nome, String tipo, String preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return  nome;
    }
    
    
}
