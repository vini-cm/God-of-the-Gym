
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

public class InstrutoresController {

   Stage stageInstrutores;
   
   @FXML
    private Button btnAdicionar;
    
    public void setStage(Stage stage){
        this.stageInstrutores = stage;
    }
    
     @FXML
    void abrirAddInstrutor(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/addInstrutor.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAddInstrutor = new Stage();
      AddInstrutorController aic = loader.getController();
      aic.setStage(telaAddInstrutor);
      Scene scene = new Scene(root);
      telaAddInstrutor.setScene(scene);
      telaAddInstrutor.show();
    }
}
