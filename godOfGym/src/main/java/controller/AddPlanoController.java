package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    
}
