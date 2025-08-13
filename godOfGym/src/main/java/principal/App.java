package principal;

import controller.LoginController;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("god of the gym!");
        URL url = new File("src/main/java/view/login.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaLogin = new Stage();
        LoginController lc = loader.getController();
        lc.setStage(stage);
        telaLogin.setOnShown(event ->{
        lc.abrirJanela();
        });
        Scene scene = new Scene(root);
        telaLogin.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("" + "/css/login.css").toExternalForm());
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        telaLogin.getIcons().add(icon);
        telaLogin.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
