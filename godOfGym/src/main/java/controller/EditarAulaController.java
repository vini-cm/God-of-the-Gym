
package controller;

import java.sql.SQLException;
import java.time.LocalTime;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Aula;
import model.AulaDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;
import util.Formatar;
import util.Visibilidade;

public class EditarAulaController {
    
    Stage stage;
    Aula aula;
    PerfilAulaController controller;
    AulaDAO dao = new AulaDAO();
    UserDAO uDao = new UserDAO();
    
    @FXML
    private Button btnEditar;
    
    @FXML
    private TextArea taDescrisao;
    
    @FXML
    private ChoiceBox<Usuario> cbProfessor;

    @FXML
    private DatePicker dpData;

    @FXML
    private TextField tfComeco;

    @FXML
    private TextField tfFinal;
    
    @FXML
    private TextField tfNome;
    
    @FXML
    private TextField tfTipo;

    @FXML
    private CheckBox cNome;

    @FXML
    private CheckBox cbData;

    @FXML
    private CheckBox cbDescricao;

    @FXML
    private CheckBox cbHorario;
    
    @FXML
    private CheckBox cProfessor;
    
    @FXML
    private CheckBox cbTipo;

    public void setStage(Stage stage, Aula aula, PerfilAulaController controller) throws SQLException{
        this.stage = stage;
        this.aula = aula;
        this.controller = controller;
        ObservableList<Usuario> instrutores = uDao.PesquisarUsuariosPorTipo("instrutor");
        cbProfessor.setItems(instrutores);
        configurarVisibilidade();
    }
    
    @FXML
    void editarAula(ActionEvent event) throws SQLException {
        if(cNome.isSelected()){
            campos(tfNome.getText(),"nome");
        }
        if(cbDescricao.isSelected()){
            campos(taDescrisao.getText(),"descricao");
        }
        if(cbTipo.isSelected()){
            campos(tfTipo.getText(),"tipo");
        }
        if(cProfessor.isSelected()){
            aula.setProfessor(cbProfessor.getValue().getCPF());
        }
        if(cbData.isSelected()){
            aula.setData(dpData.getValue());
        }
        if(cbHorario.isSelected()){
            aula.setComeco(LocalTime.parse(tfComeco.getText()));
            aula.setFim(LocalTime.parse(tfFinal.getText()));
        }
        try{
        dao.editar(aula);
        if(controller!=null){
            controller.ajustarTela();
        }
        Alerta.mostrarInformacao("SUCESS", "AULA EDITADA COM SUCESSO!");
        stage.close();
        }catch(SQLException e){
            Alerta.mostrarErro("ERROR", e.getMessage());
        }
    }
    
    private void campos(String valor, String tipo){
        switch (tipo) {
            case "nome":
                aula.setNome(valor);
                break;
            case "descrisao":
                aula.setDescricao(valor);
                break;
            case "tipo":
                aula.setTipo(valor);
                break;
        }
    }
    
    private void configurarVisibilidade(){
        Visibilidade.visibilidadeTexto(cNome, tfNome);
        Visibilidade.visibilidadeTexto(cbTipo, tfTipo);
        Visibilidade.visibilidadeChoiceBox(cProfessor, cbProfessor);
        Visibilidade.visibilidadeTextArea(cbDescricao, taDescrisao);
        Visibilidade.visibilidadeHorario(cbHorario, tfComeco, tfFinal);
        Visibilidade.visibilidadeDatePicker(cbData, dpData);
    }
    
    void formatador(){
        Formatar.formatarHorario(tfComeco);
        Formatar.formatarHorario(tfFinal);
    }
}
