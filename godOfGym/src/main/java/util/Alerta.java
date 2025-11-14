package util;

import java.io.File;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Alerta {
  private static Scene cenaAlerta;
  
    public static void setCena(Scene scene){
       cenaAlerta = scene;
    }
  
    public static void aplicarEstilo(Alert alerta){
        DialogPane dp = alerta.getDialogPane();
        dp.getStylesheets().add(Alerta.class.getResource("/css/alertas.css").toExternalForm());
    }
 
    public static void mostrarAlerta(Alert.AlertType alertat, String titulo, String mgs, Image img) {
        Alert alerta = new Alert(alertat);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mgs);
        aplicarEstilo(alerta);
        ImageView imgV = new ImageView(img);
        Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
        stage.getIcons().add(img);
        alerta.setGraphic(imgV);
        alerta.showAndWait();
    }
   
   public static void mostrarErro(String titulo, String mensagem){
       File file = new File("src/main/resources/imagens/alerta/erro.png");
       Image img = new Image(file.toURI().toString());
       mostrarAlerta(Alert.AlertType.ERROR, titulo, mensagem,img);
    }
    
    public static void mostrarInformacao(String titulo, String mensagem){
        File file = new File("src/main/resources/imagens/alerta/info.png");
        Image img = new Image(file.toURI().toString());
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, mensagem,img);
    }
    
    public static void mostrarAviso(String titulo, String mensagem){
        File file = new File("src/main/resources/imagens/alerta/aviso.png");
        Image img = new Image(file.toURI().toString());
        mostrarAlerta(Alert.AlertType.WARNING, titulo, mensagem,img);
    }
    
    public static Optional<ButtonType> mostrarConfirmacao(String titulo,String mensagem){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        File arquivo = new File("src/main/resources/imagens/alerta/conf.png");
        Image img = new Image(arquivo.toURI().toString());
        ImageView imgV = new ImageView(img);
        alerta.setGraphic(imgV);
        aplicarEstilo(alerta);
        return alerta.showAndWait();
    }
        
}
