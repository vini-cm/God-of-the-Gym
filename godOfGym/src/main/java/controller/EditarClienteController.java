package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.Planos;
import model.UserDAO;
import model.Usuario;
import util.Visibilidade;


public class EditarClienteController {
    
    Stage stage;
    Cliente cliente;
    Usuario user;
    UserDAO uDAO = new UserDAO();
    
    @FXML
    private Button btnEditar;

    @FXML
    private CheckBox cAltura;

    @FXML
    private CheckBox cEmail;

    @FXML
    private CheckBox cGenero;

    @FXML
    private CheckBox cGordura;

    @FXML
    private CheckBox cLimitacões;

    @FXML
    private CheckBox cMedicamentos;

    @FXML
    private CheckBox cNascimento;

    @FXML
    private CheckBox cNome;

    @FXML
    private CheckBox cPeso;

    @FXML
    private CheckBox cPlano;

    @FXML
    private CheckBox cSenha;

    @FXML
    private CheckBox cSobrenome;

    @FXML
    private CheckBox cTelefone;

    @FXML
    private ChoiceBox<Planos> cbPlano;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private RadioButton rFemino;

    @FXML
    private RadioButton rMasculino;

    @FXML
    private TextArea taLimitacoes;

    @FXML
    private TextArea taMedicamentos;

    @FXML
    private TextField tfAltura;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfGordura;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPeso;

    @FXML
    private TextField tfSobrenome;

    @FXML
    private TextField tfTelefone;
    
    public void setStage(Stage stage, Cliente cliente) throws SQLException{
        this.stage = stage;
        this.cliente = cliente;
        user = uDAO.selecionarUsuario(cliente.getCPF());
        configurarVisibilidade();
    }
    
    public void configurarVisibilidade(){
        Visibilidade.visibilidadeTexto(cNome, tfNome);
        Visibilidade.visibilidadeTexto(cSobrenome, tfSobrenome);
        Visibilidade.visibilidadeTexto(cEmail, tfEmail);
        Visibilidade.visibilidadeTexto(cTelefone, tfTelefone);
        Visibilidade.visibilidadeTexto(cPeso, tfPeso);
        Visibilidade.visibilidadeTexto(cAltura, tfAltura);
    }

    @FXML
    void editarCliente(ActionEvent event) {
        
    }

 }   
