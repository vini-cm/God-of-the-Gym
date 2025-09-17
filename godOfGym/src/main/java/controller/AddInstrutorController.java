
package controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;


public class AddInstrutorController {

 Stage stage;
 
   @FXML
    private Button bntCadastrar;

    @FXML
    private ChoiceBox<?> cbAssociado;

     @FXML
    private ComboBox<String> cbEntrada;

    @FXML
    private ChoiceBox<String> cbSaida;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private RadioButton rdFeminino;

    @FXML
    private RadioButton rdMasculino;

    @FXML
    private TextArea taFormacao;

    @FXML
    private TextField tfCPF;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSalario;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfSobrenome;
    

 
 public void setStage(Stage stage){
     this.stage = stage;
     }
     
}
