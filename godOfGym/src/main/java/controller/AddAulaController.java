
package controller;

import java.sql.SQLException;
import java.time.LocalTime;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.UserDAO;
import model.Usuario;

public class AddAulaController  {

    Stage stage;
    UserDAO uDao = new UserDAO();
    LocalTime entrada;
    LocalTime saida;
    
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
    
    public void setStage(Stage stage) throws SQLException{
        this.stage = stage;
        ObservableList<Usuario> instrutores = uDao.PesquisarUsuariosPorTipo("instrutor");
        cbProfessor.setItems(instrutores);
    }
    
    
}
