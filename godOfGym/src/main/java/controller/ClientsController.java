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

public class ClientsController {

    Stage stageClients;
    
    @FXML
    private Button bntAdicionar;
    
    @FXML
   private Button btnHome;
    
    public void setStage(Stage stage){
        this.stageClients = stage;
       
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
      stageClients.close();
    }
    
    @FXML
    void abrirAddCliente (ActionEvent event) throws IOException, SQLException{
      URL url = new File ("src/main/java/view/addCliente.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAddClientes = new Stage();
      AddClienteController acc = loader.getController();
      acc.setStage(telaAddClientes);
      Scene scene = new Scene(root);
      telaAddClientes.setScene(scene);
      telaAddClientes.show();
    }
}
