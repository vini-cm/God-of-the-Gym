
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Aula;
import model.AulaDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;


public class PerfilAulaController {

    Stage stage; 
    Aula aula;
    Usuario instrutor;
    UserDAO dao = new UserDAO();
    AulaDAO aDao = new AulaDAO();
    
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
    void editarAula(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/editarAula.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage aulas = new Stage();
      EditarAulaController eac = loader.getController();
      eac.setStage(aulas,aula);
      Scene scene = new Scene(root);
      aulas.setScene(scene);
      aulas.show();
      stage.close();
    }

    @FXML
    void excluirAula(ActionEvent event) throws SQLException, IOException {
        Optional<ButtonType> resultado = Alerta.mostrarConfirmacao("EXCLUSÃO", 
               "VOCE DESEJA EXCLUIR AULA " + aula.getNome() + "?");
       if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            aDao.deletar(aula.getId());
            URL url = new File("src/main/java/view/aulas.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage aula = new Stage();
            AulasController ac = loader.getController();
            ac.setStage(aula);
            Scene scene = new Scene(root);
            aula.setScene(scene);
            File fileIcon = new File("src/main/resources/imagens/icon.png");
            Image icon = new Image(fileIcon.toURI().toString());
            aula.getIcons().add(icon);
            aula.setTitle("adicionar atendente");
            aula.show();
            stage.close();
        }
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
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      aulas.getIcons().add(icon);
      aulas.setTitle("adicionar atendente");
      aulas.show();
      stage.close();
    }
    
    
}
