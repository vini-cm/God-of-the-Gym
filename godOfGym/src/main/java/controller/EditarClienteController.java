package controller;

import java.sql.Date;
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
import model.ClienteDAO;
import model.Planos;
import model.PlanosDAO;
import model.UserDAO;
import model.Usuario;
import util.Visibilidade;


public class EditarClienteController {
    
    Stage stage;
    Cliente cliente;
    Usuario user;
    UserDAO uDAO = new UserDAO();
    ClienteDAO cDAO = new ClienteDAO();
    PlanosDAO pDAO = new PlanosDAO();
    String genero;
    
    @FXML
    private Button btnEditar;

    @FXML
    private CheckBox cAltura;

    @FXML
    private CheckBox cEmail;
    
    @FXML
    private CheckBox cExperiencia;

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
    private ChoiceBox<String> cbExperiencia;

    @FXML
    private DatePicker dpNascimento;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private RadioButton rFeminino;

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
        cbExperiencia.getItems().addAll("Iniciante","Intermediario","Experiente","Profissional");
        rFeminino.setOnAction(e -> HandleRadioButton(rFeminino, rMasculino));
        rMasculino.setOnAction(e -> HandleRadioButton(rMasculino, rFeminino));
        cbPlano.setItems(pDAO.selecionarPlanos());
        
    }
    
    @FXML
    void editarCliente(ActionEvent event) throws SQLException {
        if (rFeminino.isSelected()) {
            genero = "f";
        } else if (rMasculino.isSelected()) {
            genero = "m";
        }
        
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
        if(cMedicamentos.isSelected()){
            campos(taMedicamentos.getText(),"medicamentos");
        }
        if(cLimitacões.isSelected()){
            campos(taLimitacoes.getText(),"limitacoes");
        }
        if(cNascimento.isSelected()){
            user.setDataNascimento(Date.valueOf(dpNascimento.getValue()));
        }
        if(cGenero.isSelected()){
            user.setGenero(genero);
        }
        if(cPeso.isSelected()){
            cliente.setPeso(Float.valueOf(tfPeso.getText()));
        }
        if(cAltura.isSelected()){
            cliente.setAltura(Float.valueOf(tfAltura.getText()));
        }
        if(cGordura.isSelected()){
            cliente.setPorcentagem(Float.valueOf(tfGordura.getText()));
        }
        if(cPlano.isSelected()) {
            cliente.setIdPlano(cbPlano.getValue().getIdPlano());
        }
        if(cExperiencia.isSelected()){
            cliente.setExperiencia(cbExperiencia.getValue());
        }
        
        uDAO.editar(user);
        cDAO.editar(cliente);
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
                break;
            case "medicamentos":
                cliente.setMedicamentos(valor);
                break;
            case "limitacoes":
                cliente.setLimitacoes(valor);
                break;
        }
    }
    
    public void configurarVisibilidade(){
        Visibilidade.visibilidadeTexto(cNome, tfNome);
        Visibilidade.visibilidadeTexto(cSobrenome, tfSobrenome);
        Visibilidade.visibilidadeTexto(cSenha, pfSenha);
        Visibilidade.visibilidadeGenero(cGenero, rFeminino, rMasculino);
        Visibilidade.visibilidadeTexto(cEmail, tfEmail);
        Visibilidade.visibilidadeTexto(cTelefone, tfTelefone);
        Visibilidade.visibilidadeDatePicker(cNascimento, dpNascimento);
        Visibilidade.visibilidadeTexto(cPeso, tfPeso);
        Visibilidade.visibilidadeTexto(cAltura, tfAltura);
        Visibilidade.visibilidadeTexto(cGordura, tfGordura);
        Visibilidade.visibilidadeTextArea(cMedicamentos, taMedicamentos);
        Visibilidade.visibilidadeTextArea(cLimitacões, taLimitacoes);
        Visibilidade.visibilidadeChoiceBox(cPlano, cbPlano);
        Visibilidade.visibilidadeChoiceBox(cExperiencia, cbExperiencia);
    }
    
    private void HandleRadioButton(RadioButton select, RadioButton... others) {
        for (RadioButton button : others) {
            if (button != select) {
                button.setSelected(false);
            }
        }
    }


 }   
