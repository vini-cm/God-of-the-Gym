package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
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
    private ChoiceBox<String> cbPlano;

    public void setStage(Stage stage) throws SQLException {
        this.stageAddCliente = stage;
        rdFeminino.setOnAction(e -> HandleRadioButton(rdFeminino, rdMasculino));
        rdMasculino.setOnAction(e -> HandleRadioButton(rdMasculino, rdFeminino));
        for (int i = 0; i < planos.selecionarPlanos().size();i++){
            cbPlano.getItems().add(planos.selecionarPlanos().get(i).getTipo());
        }
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
                && !tfSenha.getText().isEmpty() && tfSenha.getText() != null && genero != null && !cbPlano.getValue().isEmpty() && cbPlano.getValue() != null) {
            
            if (dao.selecionarUsuario(tfCPF.getText()).isEmpty()){
            user = new Usuario(tfCPF.getText(), tfNome.getText(),tfSobrenome.getText(),dpNascimento.getValue().toString(),tfSenha.getText(),tfEmail.getText(),genero);
            cliente = new Cliente(tfCPF.getText(), cbPlano.getValue());
            dao.salvar(user);
            clientedao.salvar(cliente);
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

}
