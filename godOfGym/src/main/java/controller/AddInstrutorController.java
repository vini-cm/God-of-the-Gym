
package controller;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    Usuario user = new Usuario();
    UserDAO userDAO = new UserDAO();
    String genero;
    LocalTime entrada;
    LocalTime saida;
 
   @FXML
    private Button bntCadastrar;

    @FXML
    private ChoiceBox<String> cbAssociado;

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
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
 
    public void setStage(Stage stage) throws SQLException{
     this.stage = stage;
     rdFeminino.setOnAction(e -> HandleRadioButton(rdFeminino, rdMasculino));
     rdMasculino.setOnAction(e -> HandleRadioButton(rdMasculino, rdFeminino));
     List<Usuario> usuarios = new UserDAO().selecionarUsuarios();
     List<String> associados = new ArrayList<>();
     for (Usuario u: usuarios){
         associados.add(u.getNome() + " " + u.getSobrenome());
     }
     associados.set(0, "Academia");
     
     cbAssociado.getItems().addAll(associados);
     tfEntrada.textProperty().addListener(new ChangeListener<String>(){
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!formatoHorario(newValue)){
                tfEntrada.setStyle("-fx-border-color:red");
            } else {
                tfEntrada.setStyle("");
            }
         } 
     });
     
     tfSaida.textProperty().addListener(new ChangeListener<String>(){
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!formatoHorario(newValue)){
                tfSaida.setStyle("-fx-border-color:red");
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
        if (!cbAssociado.getValue().isEmpty() && cbAssociado.getValue() != null && !tfNome.getText().isEmpty() && tfNome.getText() != null &&
                !tfSobrenome.getText().isEmpty() && tfSobrenome.getText() != null &&
                !tfCPF.getText().isEmpty() && tfCPF.getText() != null &&
                !tfEmail.getText().isEmpty() && tfEmail.getText() != null &&
                !tfEntrada.getText().isEmpty() && tfEntrada.getText() != null &&
                !tfSaida.getText().isEmpty() && tfSaida.getText() != null &&
                !tfSalario.getText().isEmpty() && tfSalario.getText() != null &&
                !tfSenha.getText().isEmpty() && tfSenha.getText() != null){
            entrada = LocalTime.parse(tfEntrada.getText(), formatter);
            saida = LocalTime.parse(tfSaida.getText(), formatter);
            if (dao.selecionarInstrutor(tfCPF.getText()).isEmpty()){
                user = new Usuario(tfCPF.getText(), tfNome.getText(),tfSobrenome.getText(),dpNascimento.getValue().toString(),tfSenha.getText(),tfEmail.getText(),genero);
                instrutor = new Instrutor(tfCPF.getText(),Float.parseFloat(tfSalario.getText()), taFormacao.getText(), cbAssociado.getValue(), entrada, saida);
                userDAO.salvar(user);
                dao.salvar(instrutor);
            } else {
                Alerta.mostrarErro("ERROR", "ESSE USUARIO JÁ EXISTE!");
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
    
    private boolean formatoHorario(String hora){
        Pattern padrao = Pattern.compile("(^[01]?[0-9]|2[0-3]):([0-5]?[0-9])$");
        Matcher matcher = padrao.matcher(hora);
        return matcher.matches();
    }
}
