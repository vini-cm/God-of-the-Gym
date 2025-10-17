package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAtendenteController {

    Stage stage;
    
    String genero;
    
    LocalTime entrada;
    
    LocalTime saida;
    
     @FXML
    private DatePicker DataNascimento;

    @FXML
    private Button btnCadastro;

    @FXML
    private RadioButton rdFeminino;

    @FXML
    private RadioButton rdMasculino;

    @FXML
    private TextField tfCPF;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfEntrada;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSaida;

    @FXML
    private TextField tfSalario;

    @FXML
    private TextField tfSenha;

    @FXML
    private TextField tfSobrenome;

    @FXML
    private TextField tfTelefone;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    @FXML
    void Cadastrar(ActionEvent event) {
        if (rdFeminino.isSelected()) {
            genero = "f";
        } else if (rdMasculino.isSelected()) {
            genero = "m";
        }
        if(!tfNome.getText().isEmpty() && tfNome.getText() != null
                && !tfSobrenome.getText().isEmpty() && tfSobrenome.getText() != null
                && !tfCPF.getText().isEmpty() && tfCPF.getText() != null
                && !tfEmail.getText().isEmpty() && tfEmail.getText() != null
                && !tfEntrada.getText().isEmpty() && tfEntrada.getText() != null
                && !tfSaida.getText().isEmpty() && tfSaida.getText() != null
                && !tfSalario.getText().isEmpty() && tfSalario.getText() != null
                && !tfSenha.getText().isEmpty() && tfSenha.getText() != null
                && !tfTelefone.getText().isBlank() && tfTelefone.getText() != null){
            entrada = LocalTime.parse(tfEntrada.getText());
            saida = LocalTime.parse(tfSaida.getText());
        }
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
        rdFeminino.setOnAction(e -> HandleRadioButton(rdFeminino, rdMasculino));
        rdMasculino.setOnAction(e -> HandleRadioButton(rdMasculino, rdFeminino));
        tfEntrada.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfEntrada.setStyle("-fx-border-color:red");
                } else {
                    tfEntrada.setStyle("");
                }
            }
        });

        tfSaida.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfSaida.setStyle("-fx-border-color:red");
                } else {
                    tfSaida.setStyle("");
                }
            }
        });
    }
    
     private void HandleRadioButton(RadioButton select, RadioButton... others) {
        for (RadioButton button : others) {
            if (button != select) {
                button.setSelected(false);
            }
        }
    }

    private boolean formatoHorario(String hora) {
        Pattern padrao = Pattern.compile("(^[01]?[0-9]|2[0-3]):([0-5]?[0-9])$");
        Matcher matcher = padrao.matcher(hora);
        return matcher.matches();
    }
}
