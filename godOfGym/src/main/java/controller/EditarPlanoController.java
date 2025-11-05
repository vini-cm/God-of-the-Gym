package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Planos;
import model.PlanosDAO;
import util.Visibilidade;

public class EditarPlanoController  {
    
    Stage stage;
    Planos plano;
    PlanosDAO dao = new PlanosDAO();

    @FXML
    private Button btnEditar;

    @FXML
    private CheckBox cNome;

    @FXML
    private CheckBox cTipo;

    @FXML
    private CheckBox cValor;

    @FXML
    private ChoiceBox<String> cbTipo;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfValor;
    
    public void setStage(Stage stage, Planos plano){
        this.stage = stage;
        this.plano = plano;
        cbTipo.getItems().addAll("Semanal","Mensal","Bimestral","Trimestral","Quadrimestal","Semestral","Anual");
        configurarTela();
    }

    @FXML
    void editarPlano(ActionEvent event) throws SQLException {
        if(cNome.isSelected()){
            plano.setNome(tfNome.getText());
        }
        if (cTipo.isSelected()){
            plano.setTipo(cbTipo.getValue());
        }
        if (cValor.isSelected()){
            plano.setPreco(Float.parseFloat(tfValor.getText()));
        }
        dao.editar(plano);
    }
    
    public void configurarTela(){
        Visibilidade.visibilidadeChoiceBox(cTipo, cbTipo);
        Visibilidade.visibilidadeTexto(cNome, tfNome);
        Visibilidade.visibilidadeTexto(cValor, tfValor);
    }
    
}
