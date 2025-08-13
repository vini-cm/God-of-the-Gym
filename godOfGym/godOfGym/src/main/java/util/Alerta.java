package util;

import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerta {
  private static Scene cenaAlerta;
  public static void setCena(Scene scene){
     cenaAlerta = scene;
 }
 
   public static void mostrarAlerta(Alert.AlertType alertat, String titulo, String mgs) {
        Alert alerta = new Alert(alertat);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mgs);
        alerta.showAndWait();
    }
   
   public static void mostrarErro(String titulo, String mensagem){
        mostrarAlerta(Alert.AlertType.ERROR, titulo, mensagem);
    }
    
    public static void mostrarInformacao(String titulo, String mensagem){
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, mensagem);
    }
    
    public static void mostrarAviso(String titulo, String mensagem){
        mostrarAlerta(Alert.AlertType.WARNING, titulo, mensagem);
    }
    
    public static Optional<ButtonType> mostrarConfirmacao(String titulo,
            String mensagem){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        return alerta.showAndWait();
    }
        
}
