
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.UserDAO;
import model.Usuario;


public class ParticipantesController {

   Stage stage;
   int id_aula;
   UserDAO uDAO = new UserDAO();
   
    @FXML
    private ImageView btnPesquisa;

    @FXML
    private ChoiceBox<Usuario> cbClientes;

    @FXML
    private ListView<String> lista;

    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private Button btnCadastrar;

    public void setStage(Stage stage, int id_aula) {
        this.stage = stage;
        this.id_aula = id_aula;
        
    }
   
    @FXML
    void cadastrarParticipantes(ActionEvent event) {

    }
}
