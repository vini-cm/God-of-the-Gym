
package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import model.Aula;
import model.AulaDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;

public class AddAulaController  {

    Stage stage;
    UserDAO uDao = new UserDAO();
    AulaDAO dao = new AulaDAO();
    Aula aula = new Aula();
    AulasController controller;
    LocalDateTime data;
    LocalTime entrada;
    LocalTime saida;
    int vagas;
    
    @FXML
    private Button btnCadastrar;

    @FXML
    private ChoiceBox<Usuario> cbProfessor;

    @FXML
    private DatePicker dpDataAula;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfFim;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTipo;

    @FXML
    private TextField tfVagas;

    @FXML
    private TextField tfcomeco;
    
    private final int maxCaracteres = 20;
    private final int maxTxtArea = 250 ;


    public void initialize(){
        tfNome.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxCaracteres) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        tfTipo.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxCaracteres) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        tfVagas.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxCaracteres) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        taDescricao.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() <= maxTxtArea) {
                return change; //se tiver certo
            } else {
                return null;//se tiver errado
            }
        }));
        
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    
    public void setStage(Stage stage, AulasController controller) throws SQLException{
        this.stage = stage;
        this.controller = controller;
        ObservableList<Usuario> instrutores = uDao.PesquisarUsuariosPorTipo("instrutor");
        cbProfessor.setItems(instrutores);
        
        tfcomeco.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfcomeco.setStyle("-fx-border-color:yellow; -fx-text-fill:yellow");
                } else {
                    tfcomeco.setStyle("");
                }
            }
        });

        tfFim.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!formatoHorario(newValue)) {
                    tfFim.setStyle("-fx-border-color:yellow; -fx-text-fill:yellow");
                } else {
                    tfFim.setStyle("");
                }
            }
        });
    }
    
     @FXML
    void adicionarAula(ActionEvent event) throws SQLException {
        if (preenchidos(tfNome,tfTipo,tfVagas,tfcomeco,tfFim) && !taDescricao.getText().isBlank() && !taDescricao.getText().isEmpty()
                && cbProfessor.getValue() != null){
            entrada = LocalTime.parse(tfcomeco.getText());
            saida = LocalTime.parse(tfFim.getText());
            vagas = Integer.parseInt(tfVagas.getText());
            aula = new Aula(tfNome.getText(), taDescricao.getText(), tfTipo.getText(),cbProfessor.getValue().getCPF(),
                    vagas, dpDataAula.getValue(),entrada,saida);
            try{
            dao.salvar(aula);
            if(controller!=null){
                controller.carregarTabela();
            }
            Alerta.mostrarInformacao("SUCESS", "AULA ADCIONADA COM SUCESSO!");
            stage.close();
            }catch(SQLException e){
                Alerta.mostrarErro("ERROR", e.getMessage());
            }
            
        } else {
            Alerta.mostrarErro("ERROR", "PREENCHA TODAS AS INFORMAÇÕES");
        }
    }
    
    private boolean formatoHorario(String hora) {
        Pattern padrao = Pattern.compile("(^[0-1]?[0-9]|2[0-3]):([0-5]?[0-9])$");
        Matcher matcher = padrao.matcher(hora);
        return matcher.matches();
    }
    
    
    private boolean preenchidos(TextField... campos){
        for (TextField campo : campos){
          if (campo == null || campo.getText() == null || campo.getText().isBlank()) {
            return false;
        }
        }
        return true;
    }
}
