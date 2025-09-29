package controller;

import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Aula;
import model.AulaDAO;
import model.PlanosDAO;
import util.Alerta;

public class AulasController {

    Stage stageAddCliente;
    Aula aula = new Aula(LocalTime.parse(tfHorario.getText()), tfTipo.getText(), LocalDate.parse(tfData.getText()), tfDescricao.getText(), parseInt(tfVagas.getText()));
    AulaDAO dao = new AulaDAO();

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField tfHorario;

    @FXML
    private TextField tfTipo;

    @FXML
    private TextField tfData;

    @FXML
    private TextField tfDescricao;

    @FXML
    private TextField tfVagas;
    
    @FXML
    private TextField tfNome;
    
    @FXML
    void CadastrarAula(ActionEvent event) throws SQLException {
        if (!tfNome.getText().isEmpty() && tfNome.getText() != null && !tfHorario.getText().isEmpty() && tfHorario.getText() != null
                && !tfTipo.getText().isEmpty() && tfTipo.getText() != null && !tfData.getText().isEmpty() && tfData.getText() != null
                && !tfDescricao.getText().isEmpty() && tfDescricao.getText() != null && tfVagas.getText().isEmpty() && tfVagas.getText() != null && !cbPlano.getValue().isEmpty() && cbPlano.getValue() != null) {
            
            if (dao.selecionarAula(tfNome.getText()).isEmpty()){
            aula = new Aula(LocalTime.parse(tfHorario.getText()), tfTipo.getText(), LocalDate.parse(tfData.getText()),tfDescricao.getText(),parseInt(tfVagas.getText()));
            dao.salvar(aula);
            auladao.salvar(aula);
            }
            else {
                Alerta.mostrarErro("ERROR", "ESSA AULA JÁ EXISTE!");
            }
            } else { 
            Alerta.mostrarErro("ERROR", "Preencha todas as informações!");
        }
    }
}
