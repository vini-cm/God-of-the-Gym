package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Planos;
import model.PlanosDAO;
import util.Alerta;

public class AddPlanoController  {

   Stage stage = new Stage();
   Planos plano;
   PlanosDAO dao = new PlanosDAO();
   
   @FXML
   private Button btnAdicionar;
   
   @FXML
   private TextField tfTipo;
   
   @FXML
   private TextField tfPreco;
   
   @FXML
   void adicionarPlano (ActionEvent event) throws SQLException{
       if (!tfTipo.getText().isEmpty() && tfTipo.getText() != null && !tfPreco.getText().isEmpty() && tfPreco.getText() != null){
           plano = new Planos(tfTipo.getText(), Float.parseFloat(tfPreco.getText()));
           dao.salvar(plano);
       } else { 
            Alerta.mostrarErro("ERROR", "Preencha todas as informações!");
        }
   }
           
   public void setStage(Stage stage){
       this.stage = stage;
   }
    @FXML
    void openAtendentesPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/atendentes.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAtendentes = new Stage();
      AtendentesController ac = loader.getController();
      ac.setStage(telaAtendentes);
      Scene scene = new Scene(root);
      telaAtendentes.setScene(scene);
      telaAtendentes.show();
    }

    @FXML
    void openAulasPage(ActionEvent event) throws IOException { 
      URL url = new File ("src/main/java/view/aulas.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAulas = new Stage();
      AulasController ac = loader.getController();
      ac.setStage(telaAulas);
      Scene scene = new Scene(root);
      telaAulas.setScene(scene);
      telaAulas.show();
        
    }

    @FXML
    void openClientPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/clients.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaClients = new Stage();
      ClientsController cc = loader.getController();
      cc.setStage(telaClients);
      Scene scene = new Scene(root);
      telaClients.setScene(scene);
      telaClients.show();
    }

    @FXML
    void openInstrutoresPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/instrutores.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaInstrutores = new Stage();
      InstrutoresController ic = loader.getController();
      ic.setStage(telaInstrutores);
      Scene scene = new Scene(root);
      telaInstrutores.setScene(scene);
      telaInstrutores.show();
    }

    @FXML
    void openPlanosPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/planos.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaPlanos = new Stage();
      PlanosController pc = loader.getController();
      pc.setStage(telaPlanos);
      Scene scene = new Scene(root);
      telaPlanos.setScene(scene);
      telaPlanos.show();
    }
}
