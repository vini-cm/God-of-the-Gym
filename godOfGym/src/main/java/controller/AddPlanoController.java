package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
   
   private final int maxCaracteres = 10;
 
   public void setStage(Stage stage){
       this.stage = stage;
       cbTipo.getItems().addAll("Semanal","Mensal","Bimestral","Trimestral","Quadrimestal","Semestral","Anual");
   }
   ///////////////////////////////////////////////////////////////////
   @FXML
    private void initialize() {
        tfNome.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxCaracteres) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        TextFormatter<String> precoFormatter = new TextFormatter<>(change -> {
        String newText = change.getControlNewText();

        // permite só números, ponto e vírgula
        if (!newText.matches("\\d*(\\.|,)?\\d{0,2}")) {
            return null; // rejeita caractere inválido
        }

        return change;
    });

    tfPreco.setTextFormatter(precoFormatter);
    }
    ///////////////////////////////////////////////////////////////////////
   
   @FXML
   void adicionarPlano (ActionEvent event) throws SQLException{
        
       if (!cbTipo.getValue().isEmpty() && cbTipo.getValue() != null && 
               !tfPreco.getText().isEmpty() && tfPreco.getText() != null &&
               !tfNome.getText().isEmpty() && tfNome.getText() != null){
           plano = new Planos(tfNome.getText(),cbTipo.getValue(), Float.parseFloat(tfPreco.getText()));
           dao.salvar(plano);
           if (dao.selecionarPlano(tfNome.getText()) != null){
               Alerta.mostrarConfirmacao("PLANO ADICIONADO", "plano adicionado com sucesso");
               stage.close();
           }
       } else { 
            Alerta.mostrarErro("ERROR", "Preencha todas as informações!");
        }
   }
    
}
