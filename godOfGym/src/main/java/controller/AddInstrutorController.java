package controller;

import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import model.Instrutor;
import model.InstrutorDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;

public class AddInstrutorController {

    Stage stage;
    InstrutorDAO dao = new InstrutorDAO();
    Instrutor instrutor = new Instrutor();
    InstrutoresController controller;
    Usuario user = new Usuario();
    UserDAO userDAO = new UserDAO();
    String genero;
    LocalTime entrada;
    LocalTime saida;

    @FXML
    private Button bntCadastrar;

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

    @FXML
    private TextField tfEntrada;

    @FXML
    private TextField tfSaida;

    @FXML
    private TextField tfTelefone;
    
    private final int maxCaracteres = 20;
    private final int maxCPF = 11;
    private final int maxEmail = 60;
    private final int maxSenha = 8;
    private final int maxTxtArea = 250 ;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    
    public void initialize() {
        tfNome.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxCaracteres) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        tfSobrenome.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxCaracteres) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        tfEmail.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxEmail) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        tfSenha.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxSenha) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        taFormacao.setTextFormatter(new TextFormatter<String>(change ->{
            if(change.getControlNewText().length() <= maxTxtArea){
                return change;
            }
            else{
                return null;
            }
        }));
        TextFormatter<String> TelefoneFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();

        // permite só números, ponto e vírgula
        if (!newText.matches("[0-9()+\\-\\s]*")) {
            return null; // rejeita caractere inválido
        }

        return change;
    });

        tfTelefone.setTextFormatter(TelefoneFormatter);
        
        TextFormatter<String> precoFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();

        // permite só números, ponto e vírgula
        if (!newText.matches("\\d*(\\.|,)?\\d{0,2}")) {
            return null; // rejeita caractere inválido
        }

        return change;
    });

        tfSalario.setTextFormatter(precoFormatter);
        
                TextFormatter<String> CPFFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();

        // permite só números, ponto e vírgula
        if (!newText.matches("\\d{0," + maxCPF + "}")) {
            return null; // rejeita caractere inválido
        }

        return change;
    });
        tfCPF.setTextFormatter(CPFFormatter);
    }

    public void setStage(Stage stage, InstrutoresController controller) throws SQLException {
        this.stage = stage;
        this.controller = controller;
        rdFeminino.setOnAction(e -> HandleRadioButton(rdFeminino, rdMasculino));
        rdMasculino.setOnAction(e -> HandleRadioButton(rdMasculino, rdFeminino));
        tfEntrada.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfEntrada.setStyle("-fx-border-color:yellow; -fx-text-fill:yellow");
                } else {
                    tfEntrada.setStyle("");
                }
            }
        });

        tfSaida.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfSaida.setStyle("-fx-border-color:yellow; -fx-text-fill:yellow");
                } else {
                    tfSaida.setStyle("");
                }
            }
        });
    }

    @FXML
    void Cadastrar(ActionEvent event) throws SQLException {
       
        
        if (rdFeminino.isSelected()) {
            genero = "f";
        } else if (rdMasculino.isSelected()) {
            genero = "m";
        }
        if (!tfNome.getText().isEmpty() && tfNome.getText() != null
                && !tfSobrenome.getText().isEmpty() && tfSobrenome.getText() != null
                && !tfCPF.getText().isEmpty() && tfCPF.getText() != null
                && !tfEmail.getText().isEmpty() && tfEmail.getText() != null
                && !tfEntrada.getText().isEmpty() && tfEntrada.getText() != null
                && !tfSaida.getText().isEmpty() && tfSaida.getText() != null
                && !tfSalario.getText().isEmpty() && tfSalario.getText() != null
                && !tfSenha.getText().isEmpty() && tfSenha.getText() != null
                && !tfTelefone.getText().isBlank() && tfTelefone.getText() != null) {
            entrada = LocalTime.parse(tfEntrada.getText(),formatter);
            saida = LocalTime.parse(tfSaida.getText(), formatter);
                Date nascimento = Date.valueOf(dpNascimento.getValue());
                user = new Usuario(tfCPF.getText(), tfNome.getText(), tfSobrenome.getText(), 
                nascimento, tfSenha.getText(), tfEmail.getText(), genero, tfTelefone.getText(), "instrutor");
                instrutor = new Instrutor(tfCPF.getText(), Float.parseFloat(tfSalario.getText()), 
                taFormacao.getText(), entrada, saida);  
                try{
                    userDAO.salvar(user);
                    dao.salvar(instrutor);
                    if(controller != null){
                        controller.listarInstrutores();
                        controller.carregarTabela();
                    }
                    Alerta.mostrarInformacao("SUCESS", "INSTRUTOR ADICIONADO COM SUCESSO!");
                    stage.close();
                }catch(SQLException e){
                    Alerta.mostrarErro("ERROR", e.getMessage());
                }
                  
        }
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
