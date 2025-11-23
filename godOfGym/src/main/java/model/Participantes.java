
package model;

public class Participantes {
    private int id;
    private int id_aula;
    private String cpf_participante;

    public Participantes() {
    }
    
    public Participantes(int id_aula, String cpf_participante) {
        this.id_aula = id_aula;
        this.cpf_participante = cpf_participante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String getCpf_participante() {
        return cpf_participante;
    }

    public void setCpf_participante(String cpf_participante) {
        this.cpf_participante = cpf_participante;
    }
    
    
}
