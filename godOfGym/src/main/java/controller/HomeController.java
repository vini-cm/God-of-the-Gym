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
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeController  {
    Stage HomeStage = new Stage();
    
    @FXML
    void openAtendentesPage(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/atendentes.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAtendentes = new Stage();
      AtendentesController ac = loader.getController();
      ac.setStage(telaAtendentes);
      Scene scene = new Scene(root);
      telaAtendentes.setScene(scene);
      telaAtendentes.setTitle("Tela Atendentes");
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaAtendentes.getIcons().add(icon);
      telaAtendentes.show();
      telaAtendentes.show();
      if (HomeStage != null){
         HomeStage.close();
      }
    }

    @FXML
    void openAulasPage(ActionEvent event) throws IOException, SQLException { 
      URL url = new File ("src/main/java/view/aulas.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAulas = new Stage();
      AulasController ac = loader.getController();
      ac.setStage(telaAulas);
      Scene scene = new Scene(root);
      telaAulas.setScene(scene);
      telaAulas.setTitle("Tela Aulas");
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaAulas.getIcons().add(icon);
      telaAulas.show();
      if (HomeStage != null){
         HomeStage.close();
      }
    }

    @FXML
    void openClientPage(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/clients.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaClients = new Stage();
      ClientsController cc = loader.getController();
      cc.setStage(telaClients);
      Scene scene = new Scene(root);
      telaClients.setScene(scene);
      telaClients.setTitle("Tela Clientes");
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaClients.getIcons().add(icon);
      telaClients.show();
      if (HomeStage != null){
         HomeStage.close();
      }
    }

    @FXML
    void openInstrutoresPage(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/instrutores.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaInstrutores = new Stage();
      InstrutoresController ic = loader.getController();
      ic.setStage(telaInstrutores);
      Scene scene = new Scene(root);
      telaInstrutores.setScene(scene);
      telaInstrutores.setTitle("Tela Instrutores");
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaInstrutores.getIcons().add(icon);
      telaInstrutores.show();
      if (HomeStage != null){
         HomeStage.close();
      }
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
      telaPlanos.setTitle("Tela Planos");
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaPlanos.getIcons().add(icon);
      telaPlanos.show();
      if (HomeStage != null){
         HomeStage.close();
      }
    }
    
    public void setStage(Stage stage){
        this.HomeStage = stage;
    }
    
}
