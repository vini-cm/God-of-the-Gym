
package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

public class AddAulaController  {

    Stage stage;
    UserDAO uDao = new UserDAO();
    AulaDAO dao = new AulaDAO();
    Aula aula = new Aula();
    AulasController controller;
    LocalDateTime data;
    LocalTime entrada;
    LocalTime saida;
    int vagas;
    
    @FXML
    private Button btnCadastrar;

    @FXML
    private ChoiceBox<Usuario> cbProfessor;

    @FXML
    private DatePicker dpDataAula;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfFim;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTipo;

    @FXML
    private TextField tfVagas;

    @FXML
    private TextField tfcomeco;
    
    
    public void setStage(Stage stage, AulasController controller) throws SQLException{
        this.stage = stage;
        this.controller = controller;
        ObservableList<Usuario> instrutores = uDao.PesquisarUsuariosPorTipo("instrutor");
        cbProfessor.setItems(instrutores);
        
    }
    
     @FXML
    void adicionarAula(ActionEvent event) throws SQLException {
        if (preenchidos(tfNome,tfTipo,tfVagas,tfcomeco,tfFim) && !taDescricao.getText().isBlank() && !taDescricao.getText().isEmpty()
                && cbProfessor.getValue() != null){
            entrada = LocalTime.parse(tfcomeco.getText());
            saida = LocalTime.parse(tfFim.getText());
            vagas = Integer.parseInt(tfVagas.getText());
            aula = new Aula(tfNome.getText(), taDescricao.getText(), tfTipo.getText(),cbProfessor.getValue().getCPF(),
                    vagas, dpDataAula.getValue(),entrada,saida);
            try{
            dao.salvar(aula);
            if(controller!=null){
                controller.carregarTabela();
            }
            Alerta.mostrarInformacao("SUCESS", "AULA ADCIONADA COM SUCESSO!");
            stage.close();
            }catch(SQLException e){
                Alerta.mostrarErro("ERROR", e.getMessage());
            }
            
        } else {
            Alerta.mostrarErro("ERROR", "PREENCHA TODAS AS INFORMAÇÕES");
        }
    }
    
    void formatador(){
        Formatar.formatarHorario(tfcomeco);
        Formatar.formatarHorario(tfFim);
        Formatar.apenasNumero(tfVagas);
    }
    
    private boolean preenchidos(TextField... campos){
        for (TextField campo : campos){
          if (campo == null || campo.getText() == null || campo.getText().isBlank()) {
            return false;
        }
        }
        return true;
    }
}
