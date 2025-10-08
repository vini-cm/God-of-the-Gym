package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.LoginDAO;
import model.Usuario;
import util.Alerta;

public class LoginController {
    private Usuario user;
    private ArrayList<String> listaDados;
    private final LoginDAO dao = new LoginDAO();
    
    @FXML
    private ImageView Imagem;
    
    @FXML
    private Button bntEntrar;

    @FXML
    private PasswordField password;

    @FXML
    private TextField tfNome;
    
    @FXML
    void onClickLogar(ActionEvent event) throws IOException, SQLException {
        processarLogin();
    }

    
    Stage stageLogin = new Stage();
    
    public void setStage (Stage stage){
        this.stageLogin = stage;
    }  
    
    public void verificarBanco(){
        if (dao.bancoOnline()){
            File arquivo = new File("src/main/resources/imagens/warning.png");
            Image img = new Image(arquivo.toURI().toString());
            Imagem.setImage(img);
        } else {
             File arquivo = new File("src/main/resources/imagens/avatar.png");
            Image img = new Image(arquivo.toURI().toString());
            Imagem.setImage(img);
        }
    }
    
    public void processarLogin() throws IOException, SQLException{
        if (dao.bancoOnline()){
            Alerta.mostrarErro("Erro!", "Banco de dados desconectado!");
            System.out.println("error");
        } else {
            autenticar(tfNome.getText(), password.getText());
            if (listaDados != null){
                Alerta.mostrarInformacao("Informação", "Seja Bem-Vindo! " + listaDados.get(0) + " acesso liberado!" );
                System.out.println("deu certo!");
                if (stageLogin != null){
                    stageLogin.close();
                }
                abrirHomePage();
            } else {
                Alerta.mostrarErro("Erro", "Usuario e senha invalidos!");
                System.out.println("informações erradas: " + tfNome.getText() + " " + password.getText());
            }
        }
    }
    
    public ArrayList autenticar (String nome, String senha) throws SQLException{
        user = dao.autenticar(nome, senha);
        if (user != null){
            listaDados = new ArrayList<>();
            listaDados.add(user.getNome());
            listaDados.add(user.getSenha());
            return listaDados;
        } else {
            Alerta.mostrarErro("error", "user não adicionado!");
            return null;
        }
    }
    
    public void abrirJanela(){
        bntEntrar.setDefaultButton(true);
        verificarBanco();
    }
    
    public void abrirHomePage() throws IOException{
      URL url = new File ("src/main/java/view/home.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaHome = new Stage();
      HomeController hc = loader.getController();
      hc.setStage(telaHome);
      Scene scene = new Scene(root);
      telaHome.setScene(scene);
      telaHome.show();
    }
    @FXML
    void openAtendentesPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/atendentes.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAtendentes = new Stage();
      AtendentesController ac = loader.getController();
      ac.setStage(telaAtendentes);
      Scene scene = new Scene(root);
      telaAtendentes.setScene(scene);
      telaAtendentes.show();
    }

    @FXML
    void openAulasPage(ActionEvent event) throws IOException { 
      URL url = new File ("src/main/java/view/aulas.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAulas = new Stage();
      AulasController ac = loader.getController();
      ac.setStage(telaAulas);
      Scene scene = new Scene(root);
      telaAulas.setScene(scene);
      telaAulas.show();
        
    }

    @FXML
    void openClientPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/clients.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaClients = new Stage();
      ClientsController cc = loader.getController();
      cc.setStage(telaClients);
      Scene scene = new Scene(root);
      telaClients.setScene(scene);
      telaClients.show();
    }

    @FXML
    void openInstrutoresPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/instrutores.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaInstrutores = new Stage();
      InstrutoresController ic = loader.getController();
      ic.setStage(telaInstrutores);
      Scene scene = new Scene(root);
      telaInstrutores.setScene(scene);
      telaInstrutores.show();
    }

    @FXML
    void openPlanosPage(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/planos.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaPlanos = new Stage();
      PlanosController pc = loader.getController();
      pc.setStage(telaPlanos);
      Scene scene = new Scene(root);
      telaPlanos.setScene(scene);
      telaPlanos.show();
    }
    
    
}
