
package controller;

import java.sql.Date;
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
import util.Alerta;
import util.Formatar;
import util.Visibilidade;

public class EditarAtendenteController {
    
    Stage stage;
    AtendenteDAO dao = new AtendenteDAO();
    UserDAO uDAO = new UserDAO();
    Atendente atendente;
    PerfilAtendentesController controller;
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
    
    public void setStage(Stage stage, Atendente atendente, PerfilAtendentesController controller) throws SQLException{
        this.stage = stage;
        this.atendente = atendente;
        this.controller = controller;
        this.user = uDAO.selecionarUsuario(atendente.getCPF());
        configuracaoVisibilidade();
        formatar();
        rFeminino.setOnAction(e -> HandleRadioButton(rFeminino, rMasculino));
        rMasculino.setOnAction(e -> HandleRadioButton(rMasculino, rFeminino));
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
            user.setDataNascimento(Date.valueOf(dpNascimento.getValue()));
        }
        if(cHorario.isSelected()){
            atendente.setEntrada(LocalTime.parse(tfInicio.getText()));
            atendente.setSaida(LocalTime.parse(tfFinal.getText()));
        }
        if(cGenero.isSelected()){
            user.setGenero(genero);
        }
        
        try{
        uDAO.editar(user);
        dao.editar(atendente);
        if(controller != null){
            controller.configurarTela();
        }
        Alerta.mostrarInformacao("SUCESS", "ATENDENTE EDITADO COM SUCESSO!");
        stage.close();
        }catch(SQLException e){
            Alerta.mostrarErro("ERROR", e.getMessage());
        }
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
        Visibilidade.visibilidadeGenero(cGenero, rFeminino, rMasculino);
        Visibilidade.visibilidadeHorario(cHorario, tfInicio, tfFinal);
        Visibilidade.visibilidadeTexto(cSalario, tfSalario);
        Visibilidade.visibilidadeTexto(cEmail, tfEmail);
        Visibilidade.visibilidadeTexto(cTelefone, tfTelefone);
        Visibilidade.visibilidadeDatePicker(cNascimento, dpNascimento);
    }
    
    public void formatar(){
        Formatar.formatarTelefone(tfTelefone);
        Formatar.formatarDinheiro(tfSalario);
        Formatar.formatarHorario(tfInicio);
        Formatar.formatarHorario(tfFinal);
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
