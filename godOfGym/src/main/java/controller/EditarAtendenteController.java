
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Atendente;
import util.Visibilidade;

public class EditarAtendenteController {
    
    Stage stage;
    Atendente atendente;
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private CheckBox cEmail;

    @FXML
    private CheckBox cGenero;

    @FXML
    private CheckBox cHorario;

    @FXML
    private CheckBox cNascimento;

    @FXML
    private CheckBox cNome;

    @FXML
    private CheckBox cSalario;

    @FXML
    private CheckBox cSenha;

    @FXML
    private CheckBox cSobrenome;

    @FXML
    private CheckBox cTelefone;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private RadioButton rFeminino;

    @FXML
    private RadioButton rMasculino;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFinal;

    @FXML
    private TextField tfInicio;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSalario;

    @FXML
    private TextField tfSobrenome;

    @FXML
    private TextField tfTelefone;
    
    public void setStage(Stage stage, Atendente atendente){
        this.stage = stage;
        this.atendente = atendente;
    }

    @FXML
    void editarAtendente(ActionEvent event) {

    }
    
    public void configuracaoVisibilidade(){
        Visibilidade.visibilidadeTexto(cNome, tfNome);
        Visibilidade.visibilidadeTexto(cSobrenome, tfSobrenome);
        Visibilidade.visibilidadeTexto(cSenha, pfSenha);
        Visibilidade.visibilidadeGenero(cGenero, rFeminino, rFeminino);
        Visibilidade.visibilidadeHorario(cHorario, tfInicio, tfFinal);
        Visibilidade.visibilidadeTexto(cSalario, tfSalario);
        Visibilidade.visibilidadeTexto(cEmail, tfEmail);
        Visibilidade.visibilidadeTexto(cTelefone, tfTelefone);
        Visibilidade.visibilidadeDatePicker(cNascimento, dpNascimento);
    }
}
