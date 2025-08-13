package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    
    
    
}
