
package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.AulaDAO;
import model.Instrutor;
import model.InstrutorDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;


public class PerfilInstrutorController {
    
    Stage stage;
    Usuario user;
    Instrutor instrutor;
    InstrutorDAO dao = new InstrutorDAO();
    UserDAO userDAO = new UserDAO();
    AulaDAO aulaDAO = new AulaDAO();

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
    private Label lbFormacao;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbTelefone;
    
    @FXML
    private Label lbIdade;
    
    @FXML
    private Label lbGenero;
    
    @FXML
    private Label lbSalario;
    
    @FXML
    private Label lbEmail;
    
    @FXML
    private Label lbHorario;

    @FXML
    void excluirInstrutor(ActionEvent event) throws SQLException, IOException {
        Optional<ButtonType> resultado = Alerta.mostrarConfirmacao("EXCLUSÃO", 
               "VOCE DESEJA EXCLUIR O INSTRUTOR " + user.getNome() + "?");
       if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            aulaDAO.deletarAulaPorProfessor(user.getCPF());
            dao.deletar(user.getCPF());
            userDAO.deletar(user.getCPF());
            URL url = new File("src/main/java/view/Instrutores.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage instrutor = new Stage();
            InstrutoresController ic = loader.getController();
            ic.setStage(instrutor);
            Scene scene = new Scene(root);
            instrutor.setScene(scene);
            File fileIcon = new File("src/main/resources/imagens/icon.png");
            Image icon = new Image(fileIcon.toURI().toString());
            instrutor.getIcons().add(icon);
            instrutor.setTitle("INSTRUTORES");
            instrutor.show();
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
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        instrutor.getIcons().add(icon);
        instrutor.setTitle("INSTRUTORES");
        instrutor.show();
        stage.close();
    }
    
     @FXML
    void editarInstrutor(ActionEvent event) throws IOException, SQLException {
        URL url = new File("src/main/java/view/editarInstrutor.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage editar = new Stage();
        EditarInstrutorController eic = loader.getController();
        eic.setStage(editar, instrutor,this);
        Scene scene = new Scene(root);
        editar.setScene(scene);
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        editar.getIcons().add(icon);
        editar.setTitle("adicionar atendente");
        editar.show();
    }
    
    public void setStage(Stage stage, Usuario user) throws SQLException {
        this.stage = stage;
        this.user = user;
        this.instrutor = dao.selecionarInstrutor(user.getCPF());
        configurarTela();
    }
    
    public void configurarTela() throws SQLException{
        user = userDAO.selecionarUsuario(user.getCPF());
        lbNome.setText(user.getNome() + " " + user.getSobrenome());
        lbCPF.setText(user.getCPF());
        lbTelefone.setText(user.getTelefone());
        lbEmail.setText(user.getEmail());
        lbIdade.setText((String.valueOf(calcularIdade(user.getDataNascimento()))));
        if (user.getGenero().equals("f")){
            lbGenero.setText("feminino");
        } else {
            lbGenero.setText("masculino");
        }
        lbHorario.setText(instrutor.getEntrada().toString() + " - " + instrutor.getSaida().toString());
        lbFormacao.setOnMouseClicked(e -> {
            Alerta.mostrarInformacao("Formação", instrutor.getFormacao());
        });
        lbSalario.setText(String.valueOf(instrutor.getSalario()));
        
    }
    
    public int calcularIdade(Date nascimento){
        LocalDate aniversario = nascimento.toLocalDate();
        LocalDate hoje = LocalDate.now();
        return Period.between(aniversario, hoje).getYears();
    }
}
