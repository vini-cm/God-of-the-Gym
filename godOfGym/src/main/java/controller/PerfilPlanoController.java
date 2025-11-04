package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Planos;
import model.PlanosDAO;
import util.Alerta;

public class PerfilPlanoController {

    Stage stage;
    Planos plano;
    PlanosDAO dao = new PlanosDAO();

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnSubstituir;
    
    @FXML
    private ChoiceBox<Planos> cbPlanos;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbTipo;

    @FXML
    private Label lbValor;
    
    @FXML
    private Label lbSubstituir;


    public void setStage(Stage stage, Planos plano) {
        this.stage = stage;
        this.plano = plano;
        ajustarTela();
    }
    
    void ajustarTela(){
        lbNome.setText(plano.getNome());
        lbTipo.setText(plano.getTipo());
        lbValor.setText(String.valueOf(plano.getPreco()));
    }
    
    @FXML
    void editarPlano(ActionEvent event) {
        
    }

    @FXML
    void excluirPlano(ActionEvent event) throws SQLException {
        ObservableList<Planos> lista = dao.selecionarPlanos();
        lista.remove(plano);
        cbPlanos.setItems(lista);
        lbSubstituir.setVisible(true);
        cbPlanos.setVisible(true);
        btnSubstituir.setVisible(true);
    }
    
    @FXML
    void substituirPlanos(ActionEvent event) throws SQLException, IOException {
        Optional<ButtonType> resultado = Alerta.mostrarConfirmacao("EXCLUSÃO", 
               "VOCE DESEJA EXCLUIR PLANOS " + plano.getNome().toUpperCase() + 
               "E SUBSTITUIR POR:"+cbPlanos.getValue().getNome().toUpperCase()+"?");
       if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            //dao.deletar(plano,cbPlanos.getValue().getIdPlano());
            URL url = new File("src/main/java/view/planos.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage planos = new Stage();
            PlanosController pc = loader.getController();
            pc.setStage(planos);
            Scene scene = new Scene(root);
            planos.setScene(scene);
            planos.show();
            stage.close();
        }
    }

    @FXML
    void voltarPlanos(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/planos.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage planos = new Stage();
      PlanosController pc = loader.getController();
      pc.setStage(planos);
      Scene scene = new Scene(root);
      planos.setScene(scene);
      planos.show();
      stage.close();
    }

}
