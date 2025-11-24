
package controller;

import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ParticipantesDAO;
import model.UserDAO;
import model.Usuario;


public class ParticipantesController {

   Stage stage;
   int id_aula;
   ParticipantesDAO pDAO = new ParticipantesDAO();
   
    @FXML
    private ImageView btnPesquisa;

    @FXML
    private ChoiceBox<Usuario> cbClientes;

    @FXML
    private ListView<Usuario> lista;

    @FXML
    private TextField tfPesquisa;
    
    @FXML
    private Button btnCadastrar;

    public void setStage(Stage stage, int id_aula) throws SQLException {
        this.stage = stage;
        this.id_aula = id_aula;
        ObservableList<Usuario> clientes = pDAO.listarClientes(id_aula);
        cbClientes.setItems(clientes);
        ObservableList<Usuario> participantes = pDAO.listarParticipantes(id_aula);
        lista.setItems(participantes);
    }
   
    @FXML
    void cadastrarParticipantes(ActionEvent event) {

    }
}
