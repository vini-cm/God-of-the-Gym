
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Aula;
import model.UserDAO;
import model.Usuario;


public class PerfilAulaController {

    Stage stage; 
    Aula aula;
    Usuario instrutor;
    UserDAO dao = new UserDAO();
    
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private Label lbData;

    @FXML
    private Label lbDescricao;

    @FXML
    private Label lbHorario;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbProfessor;

    @FXML
    private Label lbTipo;

    @FXML
    private Label lbVagas;
    
    public void setStage(Stage stage, Aula aula) throws SQLException{
        this.stage = stage;
        this.aula = aula;
        ajustarTela();
    }
    
    public void ajustarTela() throws SQLException{
        instrutor = dao.selecionarUsuario(aula.getProfessor());
        lbNome.setText(aula.getNome().toUpperCase());
        lbTipo.setText(aula.getTipo());
        lbDescricao.setText(aula.getDescricao());
        lbProfessor.setText(instrutor.getNome());
        lbData.setText(aula.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        lbHorario.setText(aula.getComeco().toString() + "-" + aula.getFim().toString());
        lbVagas.setText(String.valueOf(aula.getVagas()));
    }

    @FXML
    void editarAula(ActionEvent event) {

    }

    @FXML
    void excluirAula(ActionEvent event) {

    }
    
    @FXML
    void voltarAulas(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/aulas.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage aulas = new Stage();
      AulasController ac = loader.getController();
      ac.setStage(aulas);
      Scene scene = new Scene(root);
      aulas.setScene(scene);
      aulas.show();
      stage.close();
    }
    
}
