package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import model.Planos;
import model.PlanosDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;

public class AddClienteController {
    Stage stageAddCliente;
    String genero;
    UserDAO dao = new UserDAO();
    Usuario user;
    ClienteDAO clientedao = new ClienteDAO();
    Cliente cliente;
    PlanosDAO planos = new PlanosDAO();

    @FXML
    private Button btnCadastro;

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
    private TextField tfSenha;

    @FXML
    private TextField tfSobrenome;
    
    @FXML
    private ChoiceBox<Planos> cbPlano;
    
     @FXML
    private CheckBox checkbLimitações;

    @FXML
    private CheckBox checkbMedicamentos;
    
    @FXML
    private TextField tfAltura;
    
    @FXML
    private TextField tfPeso;
    
    @FXML
    private TextField tfgordura;
    
    @FXML
    private TextField tfTelefone;
    
    @FXML
    private ChoiceBox<String> cbExperiencia;
    
    @FXML 
    private TextArea taMedicacoes;
    
    @FXML
    private TextArea taLimitacoes;
    
    private Pattern padraoPeso = Pattern.compile("\\d{0,2}(\\.\\d{0,1})?");
    private Pattern padraoAltura = Pattern.compile("\\d{0,1}(\\.\\d{0,2})?");
    
    float peso;
    float altura;
    float imc;
    float porcentagem;
    
    private final int maxCaracteres = 20;
    private final int maxCPF = 11;
    private final int maxEmail = 60;
    private final int maxSenha = 8;
    private final int maxTxtArea = 250 ;
        
    /////////////////////////////////////////////////////////////////////////
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
        taMedicacoes.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxTxtArea) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        taLimitacoes.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxTxtArea) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
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
        
        TextFormatter<String> PesoFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();

        // permite só números, ponto e vírgula
        if (!newText.matches("[0-9,%]*")) {
            return null; // rejeita caractere inválido
        }

        return change;
    });
        tfgordura.setTextFormatter(PesoFormatter);
        
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
    /////////////////////////////////////////////////////////////////////////

    public void setStage(Stage stage) throws SQLException {
        this.stageAddCliente = stage;
        aplicarPeso(tfPeso);
        aplicarAltura(tfAltura);
        taLimitacoes.setVisible(false);
        taMedicacoes.setVisible(false);
        rdFeminino.setOnAction(e -> HandleRadioButton(rdFeminino, rdMasculino));
        rdMasculino.setOnAction(e -> HandleRadioButton(rdMasculino, rdFeminino));
        cbPlano.setItems(planos.selecionarPlanos());
        cbExperiencia.getItems().addAll("Iniciante","Intermediario","Experiente","Profissional");
        checkbLimitações.setOnAction(e -> {
            boolean marcado = checkbLimitações.isSelected();
            taLimitacoes.setVisible(marcado);
        });
        
        checkbMedicamentos.setOnAction(e -> {
            boolean marcado = checkbMedicamentos.isSelected();
            taMedicacoes.setVisible(marcado);
        });
    }

    @FXML
    void Cadastrar(ActionEvent event) throws SQLException {
        if (rdFeminino.isSelected()) {
            genero = "f";
        } else if (rdMasculino.isSelected()) {
            genero = "m";
        }
        if (!tfCPF.getText().isEmpty() && tfCPF.getText() != null && !tfEmail.getText().isEmpty() && tfEmail.getText() != null
                && !tfNome.getText().isEmpty() && tfNome.getText() != null && !tfSobrenome.getText().isEmpty() && tfSobrenome.getText() != null
                && !tfSenha.getText().isEmpty() && tfSenha.getText() != null && genero != null && 
                !cbPlano.getValue().equals(null) && cbPlano.getValue() != null && !tfAltura.getText().isEmpty() && tfAltura.getText() != null &&
                !tfPeso.getText().isEmpty() && tfPeso.getText() != null && !tfTelefone.getText().isEmpty() && tfTelefone.getText() != null &&
                !tfgordura.getText().isEmpty() && tfgordura.getText() != null) {
            
            if (dao.selecionarUsuario(tfCPF.getText()) == null){
                Date nascimento = Date.valueOf(dpNascimento.getValue());
                altura = Float.parseFloat(tfAltura.getText());
                peso = Float.parseFloat(tfPeso.getText());
                porcentagem = Float.parseFloat(tfgordura.getText());
                imc = peso/(altura*altura);
            user = new Usuario(tfCPF.getText(), tfNome.getText(),tfSobrenome.getText(),nascimento,
                    tfSenha.getText(),tfEmail.getText(),genero,tfTelefone.getText(), "cliente");
            dao.salvar(user);
            
            if (dao.selecionarUsuario(tfCPF.getText()) != null) {
                    cliente = new Cliente(tfCPF.getText(), cbPlano.getValue().getIdPlano(),peso,altura,porcentagem,
                            imc,cbExperiencia.getValue(),taMedicacoes.getText(),taLimitacoes.getText());
                    clientedao.salvar(cliente);
                } else {
                    Alerta.mostrarErro("ERROR", "ERRO EM CADASTRAR INSTRUTOR");
                }
            
            if (clientedao.selecionarCliente(tfCPF.getText()) != null){
                Alerta.mostrarConfirmacao("SUCESSO", "PERFIL ADICIONADO COM SUCESSO");
                stageAddCliente.close();
            }
            }
            else {
                Alerta.mostrarErro("ERROR", "ESSE USUARIO JÁ EXISTE!");
            }
            } else { 
            Alerta.mostrarErro("ERROR", "Preencha todas as informações!");
        }
    }

    private void HandleRadioButton(RadioButton select, RadioButton... others) {
        for (RadioButton button : others) {
            if (button != select) {
                button.setSelected(false);
            }
        }
    }
    
    private void aplicarPeso(TextField tf){
        UnaryOperator<TextFormatter.Change> filtro = change -> {
            String texto = change.getControlNewText();
            
            if (texto.isEmpty()){
                return change;
            }
            
            Matcher matcher = padraoPeso.matcher(texto);
            if(matcher.matches()){
                return change;
            } else {
                return null;
            }
    };
        tf.setTextFormatter(new TextFormatter<>(filtro));
    }
    
    private void aplicarAltura(TextField tf){
        UnaryOperator<TextFormatter.Change> filtro = change -> {
            String texto = change.getControlNewText();
            
            if (texto.isEmpty()){
                return change;
            }
            
            if(texto.length()>3){
                texto = texto.substring(0, 3);
            }
            
            Matcher matcher = padraoAltura.matcher(texto);
            if(matcher.matches()){
                return change;
            } else {
                return null;
            }
    };
        tf.setTextFormatter(new TextFormatter<>(filtro));
    }
}
