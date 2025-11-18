package controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import model.Planos;
import model.PlanosDAO;
import util.Alerta;
import util.Formatar;

public class AddPlanoController  {

   Stage stage = new Stage();
   Planos plano;
   PlanosDAO dao = new PlanosDAO();
   PlanosController controller;
   
   @FXML
   private Button btnAdicionar;
   
   @FXML 
   private ChoiceBox<String> cbTipo;
   
   @FXML
   private TextField tfPreco;
   
   @FXML
    private TextField tfNome;
   
      
   public void setStage(Stage stage, PlanosController controller){
       this.stage = stage;
       this.controller = controller;
       formatar();
       cbTipo.getItems().addAll("Semanal","Mensal","Bimestral","Trimestral","Quadrimestal","Semestral","Anual");
   }
 
   
   @FXML
   void adicionarPlano (ActionEvent event) throws SQLException{
        
       if (!cbTipo.getValue().isEmpty() && cbTipo.getValue() != null && 
               !tfPreco.getText().isEmpty() && tfPreco.getText() != null &&
               !tfNome.getText().isEmpty() && tfNome.getText() != null){
           plano = new Planos(tfNome.getText(),cbTipo.getValue(), Float.parseFloat(tfPreco.getText()));
           try{
           dao.salvar(plano);
           if(controller!=null){
               controller.carregarTabela();
           }
            Alerta.mostrarConfirmacao("PLANO ADICIONADO", "PLANO ADICIONADO COM SUCESSO");
            stage.close();
           } catch(SQLException e){
               Alerta.mostrarErro("ERROR", e.getMessage());
           }
       } else { 
            Alerta.mostrarErro("ERROR", "Preencha todas as informações!");
        }
   }
    
   void formatar(){
       Formatar.formatarDinheiro(tfPreco);
   }
}
