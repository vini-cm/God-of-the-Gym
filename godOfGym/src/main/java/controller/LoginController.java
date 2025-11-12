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
                if(listaDados.get(2).equals("administrador") || listaDados.equals("atendente")){
                Alerta.mostrarInformacao("Informação", "Seja Bem-Vindo! " + listaDados.get(0) + " acesso liberado!" );
                } else {
                    Alerta.mostrarErro("ERROR", "Você não tem acesso a esse sistema");
                    System.exit(0);
                }
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
            listaDados.add(user.getTipo());
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
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaHome.getIcons().add(icon);
      telaHome.setTitle("HOME PAGE");
      telaHome.show();
      if (stageLogin != null) {
        stageLogin.close();
    }
    }
    
}
