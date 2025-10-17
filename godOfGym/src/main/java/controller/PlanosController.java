package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PlanosController{

Stage stagePlanos;

    @FXML
    private Button bntAdicionar;
    
    @FXML
   private Button btnHome;
    
    public void setStage(Stage stage){
        this.stagePlanos = stage;
        
    }
    
    @FXML
    void voltarHome(ActionEvent event) throws IOException{
      URL url = new File ("src/main/java/view/home.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage home = new Stage();
      HomeController hc = loader.getController();
      hc.setStage(home);
      Scene scene = new Scene(root);
      home.setScene(scene);
      home.show();
      stagePlanos.close();
    }

    @FXML
    void abrirAddPlano(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/addPlano.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAddPlano = new Stage();
      AddPlanoController acc = loader.getController();
      acc.setStage(telaAddPlano);
      Scene scene = new Scene(root);
      telaAddPlano.setScene(scene);
      telaAddPlano.show();
    }

       
    
}
