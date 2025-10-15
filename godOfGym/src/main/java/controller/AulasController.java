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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AulasController {

   Stage stageAula;

    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnAula;

    
    public void setStage(Stage stage){
        this.stageAula = stage;
        Image image = new Image(getClass().getResourceAsStream("/imagens/voltar.png"));
        ImageView imageView = new ImageView(image);
        btnHome.setGraphic(imageView);
    }
    
    @FXML
    void  AdicionarAula(ActionEvent event) throws IOException, SQLException {
        URL url = new File("src/main/java/view/addAulas.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage adicionar = new Stage();
        AddAulasController ac = loader.getController();
        ac.setStage(adicionar);
        Scene scene = new Scene(root);
        adicionar.setScene(scene);
        adicionar.show();
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
      stageAula.close();
    }

}
