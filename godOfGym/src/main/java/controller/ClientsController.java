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
import javafx.stage.Stage;

public class ClientsController {

    Stage stageClients;
    
    @FXML
    private Button bntAdicionar;
    
    public void setStage(Stage stage){
        this.stageClients = stage;
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
