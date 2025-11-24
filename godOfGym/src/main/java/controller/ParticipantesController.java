
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Aula;
import model.AulaDAO;
import model.Participantes;
import model.ParticipantesDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;


public class ParticipantesController {

   Stage stage;
   int id_aula;
   ParticipantesDAO pDAO = new ParticipantesDAO();
   AulaDAO aDAO = new AulaDAO();
   Aula aula;
   
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
        aula = aDAO.selecionarAulaPorId(id_aula);
        ObservableList<Usuario> clientes = pDAO.listarClientes(id_aula);
        cbClientes.setItems(clientes);
        ObservableList<Usuario> participantes = pDAO.listarParticipantes(id_aula);
        lista.setItems(participantes);
    }
   
    @FXML
    void cadastrarParticipantes(ActionEvent event) {
        if(!cbClientes.getValue().equals(null)){
            try{
                Participantes p = new Participantes(id_aula, cbClientes.getValue().getCPF());
                pDAO.salvar(p);
                Alerta.mostrarInformacao("SUCESS", "cliente matriculado com sucesso");
            }catch(SQLException e){
                Alerta.mostrarErro("ERROR", e.getMessage());
            }
        }
    }
    
    @FXML
    void voltarAula(ActionEvent event) throws IOException, SQLException {
        URL url = new File("src/main/java/view/perfilAula.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaAula = new Stage();
        PerfilAulaController pac = loader.getController();
        pac.setStage(telaAula, aula);
        Scene scene = new Scene(root);
        telaAula.setScene(scene);
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        telaAula.getIcons().add(icon);
        telaAula.setTitle("adicionar atendente");
        telaAula.show();
        stage.close();
    }
}
