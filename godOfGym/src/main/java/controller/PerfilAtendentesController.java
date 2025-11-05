
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Atendente;
import model.AtendenteDAO;
import model.Instrutor;
import model.UserDAO;
import model.Usuario;
import util.Alerta;

public class PerfilAtendentesController  {
  
    Stage stage;
    Usuario user;
    Atendente atendente;
    AtendenteDAO dao = new AtendenteDAO();
    UserDAO userDAO = new UserDAO();

    @FXML
    private Button btnInstrutor;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lbCPF;

    @FXML
    private Label lbEntrada;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbSaida;

    @FXML
    private Label lbTelefone;
    
    @FXML
    private Label lbIdade;
    
    @FXML
    private Label lbGenero;
    
    @FXML
    private Label lbSalario;

    @FXML
    void excluirInstrutor(ActionEvent event) throws SQLException, IOException {
        Optional<ButtonType> resultado = Alerta.mostrarConfirmacao("EXCLUSÃO", 
               "VOCE DESEJA EXCLUIR ATENDENTE " + user.getNome() + "?");
       if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            dao.deletar(user.getCPF());
            userDAO.deletar(user.getCPF());
            URL url = new File("src/main/java/view/atendentes.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage atendente = new Stage();
            AtendentesController ac = loader.getController();
            ac.setStage(atendente);
            Scene scene = new Scene(root);
            atendente.setScene(scene);
            atendente.show();
            stage.close();
        }
    }
    
    @FXML
    void voltarInstrutor(ActionEvent event) throws SQLException, IOException {
        URL url = new File("src/main/java/view/Instrutores.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage instrutor = new Stage();
        InstrutoresController ic = loader.getController();
        ic.setStage(instrutor);
        Scene scene = new Scene(root);
        instrutor.setScene(scene);
        instrutor.show();
        stage.close();
    }
    
    @FXML
    void editarAtendente(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/editarAtendente.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage editar = new Stage();
        EditarAtendenteController ic = loader.getController();
        ic.setStage(editar,atendente);
        Scene scene = new Scene(root);
        editar.setScene(scene);
        editar.show();
    }
    
    public void setStage(Stage stage, Usuario user) throws SQLException {
        this.stage = stage;
        this.user = user;
        this.atendente = dao.selecionarAtendente(user.getCPF());
        configurarTela();
    }
    
    public void configurarTela() throws SQLException{
        lbNome.setText(user.getNome() + " " + user.getSobrenome());
        lbCPF.setText(user.getCPF());
        lbTelefone.setText(user.getTelefone());
        lbIdade.setText((String.valueOf(calcularIdade(user.getDataNascimento()))));
        if (user.getGenero().equals("f")){
            lbGenero.setText("feminino");
        } else {
            lbGenero.setText("masculino");
        }
        lbEntrada.setText(String.valueOf(atendente.getEntrada()));
        lbSaida.setText(String.valueOf(atendente.getSaida()));
        lbSalario.setText(String.valueOf(atendente.getSalario()));
        
    }
    
    public int calcularIdade(String nascimento){
        LocalDate aniversario = LocalDate.parse(nascimento);
        LocalDate hoje = LocalDate.now();
        return Period.between(aniversario, hoje).getYears();
    }
    
}
