
package model;

public class Planos {
    private int idPlano;
    private String tipo;
    private float preco;

    public Planos() {
    }

    public Planos(String tipo, float preco) {
        this.tipo = tipo;
        this.preco = preco;
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    
}
