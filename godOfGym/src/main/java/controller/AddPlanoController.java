package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
   private ChoiceBox<String> cbTipo;
   
   @FXML
   private TextField tfPreco;
   
   @FXML
    private TextField tfNome;
   
   public void setStage(Stage stage){
       this.stage = stage;
       cbTipo.getItems().addAll("Semanal","Mensal","Bimestral","Trimestral","Quadrimestal","Semestral","Anual");
   }
   
   @FXML
   void adicionarPlano (ActionEvent event) throws SQLException{
       if (!cbTipo.getValue().isEmpty() && cbTipo.getValue() != null && 
               !tfPreco.getText().isEmpty() && tfPreco.getText() != null &&
               !tfNome.getText().isEmpty() && tfNome.getText() != null){
           plano = new Planos(tfNome.getText(),cbTipo.getValue(), Float.parseFloat(tfPreco.getText()));
           dao.salvar(plano);
           if (dao.selecionarPlano(cbTipo.getValue()) != null){
               Alerta.mostrarConfirmacao("PLANO ADICIONADO", "plano adicionado com sucesso");
               stage.close();
           }
       } else { 
            Alerta.mostrarErro("ERROR", "Preencha todas as informações!");
        }
   }
    
}
