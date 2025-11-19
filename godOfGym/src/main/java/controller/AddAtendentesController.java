package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Atendente;
import model.AtendenteDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;
import util.Formatar;

public class AddAtendentesController {

    Stage stage;
    AtendenteDAO dao = new AtendenteDAO();
    Atendente atendente = new Atendente();
    AtendentesController controller;
    Usuario user = new Usuario();
    UserDAO userDAO = new UserDAO();
    String genero;
    LocalTime entrada;
    LocalTime saida;

    @FXML
    private Button btnCadastrar;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private RadioButton rdFeminino;

    @FXML
    private RadioButton rdMasculino;

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
    
    

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    
    public void setStage(Stage stage, AtendentesController controller) throws SQLException {
        this.stage = stage;
        this.controller = controller;
        rdFeminino.setOnAction(e -> HandleRadioButton(rdFeminino, rdMasculino));
        rdMasculino.setOnAction(e -> HandleRadioButton(rdMasculino, rdFeminino));
        formatar();
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
            Date nascimento = Date.valueOf(dpNascimento.getValue());
            entrada = LocalTime.parse(tfEntrada.getText(), formatter);
            saida = LocalTime.parse(tfSaida.getText(), formatter);
            
                try{
                user = new Usuario(tfCPF.getText(), tfNome.getText(), tfSobrenome.getText(), nascimento,
                        tfSenha.getText(), tfEmail.getText(), genero, tfTelefone.getText(), "atendente");
                userDAO.salvar(user);
                    atendente = new Atendente(tfCPF.getText(), tfSalario.getText(),
                        entrada, saida);
                    dao.salvar(atendente);
                    if(controller != null){
                        controller.carregarTabela();
                    }
                    Alerta.mostrarInformacao("SUCESS", "ATENDENTE ADICIONAOD COM SUCESSO!");
                    stage.close();
                } catch(SQLException e){
                    Alerta.mostrarErro("ERROR", e.getMessage());
                }
                
        }
    }
    
    public void formatar(){
        Formatar.formatarCPF(tfCPF);
        Formatar.formatarTelefone(tfTelefone);
        Formatar.formatarDinheiro(tfSalario);
        Formatar.formatarHorario(tfEntrada);
        Formatar.formatarHorario(tfSaida);
        Formatar.formatarEmail(tfEmail);
    }

    private void HandleRadioButton(RadioButton select, RadioButton... others) {
        for (RadioButton button : others) {
            if (button != select) {
                button.setSelected(false);
            }
        }
    }
    
    
    }
