package controller;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {
    
    @FXML
    private ImageView Imagem;
    
    @FXML
    private Button bntEntrar;

    @FXML
    private PasswordField password;

    @FXML
    private TextField tfNome;

    
    Stage stageLogin = new Stage();
    
    public void setStage (Stage stage){
        this.stageLogin = stage;
    }  
    
}
