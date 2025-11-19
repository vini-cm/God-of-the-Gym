
package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Instrutor;
import model.InstrutorDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;
import util.Formatar;
import util.Visibilidade;


public class EditarInstrutorController  {
    
    Stage stage;
    InstrutorDAO dao = new InstrutorDAO();
    PerfilInstrutorController controller;
    UserDAO uDAO = new UserDAO();
    Instrutor instrutor;
    Usuario user;
    String genero;

    @FXML
    private Button btnEditar;

    @FXML
    private CheckBox cEmail;

    @FXML
    private CheckBox cFormacao;

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
    private TextArea taFormacao;

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
    
    public void setStage(Stage stage, Instrutor i, PerfilInstrutorController controller) throws SQLException{
        this.stage = stage;
        this.instrutor = i;
        this.user = uDAO.selecionarUsuario(i.getCPF());
        this.controller = controller;
        configuracaoVisibilidade();
        formatar();
        rFeminino.setOnAction(e -> HandleRadioButton(rFeminino, rMasculino));
        rMasculino.setOnAction(e -> HandleRadioButton(rMasculino, rFeminino));
    }

    @FXML
    void editarInstrutor(ActionEvent event) throws SQLException {
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
        if(cFormacao.isSelected()){
            campos(taFormacao.getText(),"formacao");
        }
        if(cTelefone.isSelected()){
            campos(tfTelefone.getText(), "telefone");
        }
        if(cSalario.isSelected()){
            instrutor.setSalario(Float.valueOf(tfSalario.getText()));
        }
        if(cNascimento.isSelected()){
            user.setDataNascimento(Date.valueOf(dpNascimento.getValue()));
        }
        if(cHorario.isSelected()){
            instrutor.setEntrada(LocalTime.parse(tfInicio.getText()));
            instrutor.setSaida(LocalTime.parse(tfFinal.getText()));
        }
        if(cGenero.isSelected()){
            user.setGenero(genero);
        }
        try{
        uDAO.editar(user);
        dao.editar(instrutor);
        if(controller != null){
            controller.configurarTela();
        }
        Alerta.mostrarInformacao("SUCESS", "INSTRUTOR EDITADO COM SUCESSO!");
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
                break;
            case "formacao":
                instrutor.setFormacao(valor);
                break;
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
        Visibilidade.visibilidadeTextArea(cFormacao, taFormacao);
    }
    
    private void HandleRadioButton(RadioButton select, RadioButton... others) {
        for (RadioButton button : others) {
            if (button != select) {
                button.setSelected(false);
            }
        }
    }
    
    public void formatar(){
        Formatar.apenasLetras(tfNome);
        Formatar.apenasLetras(tfSobrenome);
        Formatar.formatarTelefone(tfTelefone);
        Formatar.formatarDinheiro(tfSalario);
        Formatar.formatarHorario(tfInicio);
        Formatar.formatarHorario(tfFinal);
        Formatar.formatarEmail(tfEmail);
    }
}
