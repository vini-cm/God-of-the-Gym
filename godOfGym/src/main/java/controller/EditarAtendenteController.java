
package controller;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.AtendenteDAO;
import model.UserDAO;
import model.Usuario;
import util.Visibilidade;

public class EditarAtendenteController {
    
    Stage stage;
    AtendenteDAO dao = new AtendenteDAO();
    UserDAO uDAO = new UserDAO();
    Atendente atendente;
    Usuario user;
    String genero;
    
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
    
    public void setStage(Stage stage, Atendente atendente) throws SQLException{
        this.stage = stage;
        this.atendente = atendente;
        this.user = uDAO.selecionarUsuario(atendente.getCPF());
        configuracaoVisibilidade();
        
        rFeminino.setOnAction(e -> HandleRadioButton(rFeminino, rMasculino));
        rMasculino.setOnAction(e -> HandleRadioButton(rMasculino, rFeminino));
        tfInicio.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfInicio.setStyle("-fx-border-color:red");
                } else {
                    tfInicio.setStyle("");
                }
            }
        });

        tfFinal.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfFinal.setStyle("-fx-border-color:red");
                } else {
                    tfFinal.setStyle("");
                }
            }
        });
    }

    @FXML
    void editarAtendente(ActionEvent event) throws SQLException {
        if(cNome.isSelected()){
            campos(tfNome.getText(), "nome");
        }
        if(cSobrenome.isSelected()){
            campos(tfSobrenome.getText(), "sobrenome");
        }
        if(cSenha.isSelected()){
            campos(pfSenha.getText(), "senha");
        }
        if(cEmail.isSelected()){
            campos(tfEmail.getText(), "email");
        }
        if(cTelefone.isSelected()){
            campos(tfTelefone.getText(), "telefone");
        }
        if(cSalario.isSelected()){
            atendente.setSalario(Float.valueOf(tfSalario.getText()));
        }
        if(cNascimento.isSelected()){
            user.setDataNascimento(dpNascimento.getValue().toString());
        }
        if(cHorario.isSelected()){
            atendente.setEntrada(LocalTime.parse(tfInicio.getText()));
            atendente.setSaida(LocalTime.parse(tfFinal.getText()));
        }
        if(cGenero.isSelected()){
            user.setGenero(genero);
        }
        uDAO.editar(user);
        dao.editar(atendente);
    }
    
    void campos(String valor, String tipo){
        switch (tipo) {
            case "nome":
                user.setNome(valor);
                break;
            case "sobrenome":
                user.setSobrenome(valor);
                break;
            case "senha":
                user.setSenha(valor);
                break;
            case "email":
                user.setEmail(valor);
                break;
            case "telefone":
                user.setTelefone(valor);
        }
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
