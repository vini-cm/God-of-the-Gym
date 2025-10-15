package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Aula;
import model.AulaDAO;
import model.UserDAO;
import model.Usuario;

public class AddAulasController  {
    
    Stage stage;
    AulaDAO dao = new AulaDAO();
    UserDAO UserDAO = new UserDAO();
 
    @FXML
    private DatePicker DataAula;

    @FXML
    private Button btnAdicionar;

    @FXML
    private ChoiceBox<String> cbProfessor;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfHorario;

    @FXML
    private TextField tfTipo;

    @FXML
    private TextField tfVagas;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
    
    LocalDateTime data;

    @FXML
    void Adicionar(ActionEvent event) throws SQLException {
        if(!tfHorario.getText().isEmpty() && tfHorario.getText() != null &&
           !tfTipo.getText().isEmpty() && tfTipo.getText() != null && 
           !tfVagas.getText().isEmpty() && tfVagas.getText() != null){
            LocalTime horario = LocalTime.parse(tfHorario.getText(), formatter);
            data = LocalDateTime.of(DataAula.getValue(), horario);
            Aula aula = new Aula(tfTipo.getText(), data, taDescricao.getText(),Integer.parseInt(tfVagas.getText()), cbProfessor.getValue());
            dao.salvar(aula);
            
        }
    }
    
    public void setStage(Stage stage) throws SQLException{
        this.stage = stage;
        tfHorario.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfHorario.setStyle("-fx-border-color:red");
                } else {
                    tfHorario.setStyle("");
                }
            }
        });
        
        tfVagas.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> obs, String antigo, String novo){
                if(!novo.matches("\\d*")){
                    tfVagas.setText(novo.replaceAll("[^\\d]",""));
                }
            }
        });
        
        List<Usuario> instrutores = UserDAO.selecionarUsuarios();
        ArrayList<String> nomes = new ArrayList<>();
        
        for(Usuario u : instrutores){
            if (!u.getTipo().equals("instrutor")){
               instrutores.remove(u);
            }
        }
        
        for(Usuario u : instrutores){
            if (u.getTipo().equals("instrutor")){
                nomes.add(u.getNome() + " " + u.getSobrenome());
            }
        }
        
        cbProfessor.getItems().addAll(nomes);
    }
    
    private boolean formatoHorario(String hora) {
        Pattern padrao = Pattern.compile("(^[01]?[0-9]|2[0-3]):([0-5]?[0-9])$");
        Matcher matcher = padrao.matcher(hora);
        return matcher.matches();
    }
    
}
