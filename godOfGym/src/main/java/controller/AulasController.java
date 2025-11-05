package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Aula;
import model.AulaDAO;

public class AulasController {

    Stage stageAula;
    
    @FXML
    ObservableList<Aula> Lista = FXCollections.observableArrayList();

    @FXML
    private Button btnHome;
    
    @FXML
    private Button btnAddAula;
    
    @FXML
    private TableView<Aula> tabela;

    @FXML
    private TextField tfPesquisa;
    
    
    public void setStage(Stage stage) throws SQLException{
        this.stageAula = stage;
        carregarTabela();
    }
    
    @FXML
    void voltarHome(ActionEvent event) throws IOException{
      URL url = new File ("src/main/java/view/home.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage home = new Stage();
      HomeController hc = loader.getController();
      hc.setStage(home);
      Scene scene = new Scene(root);
      home.setScene(scene);
      home.show();
      stageAula.close();
    }
    
    @FXML
    void deletarAula(ActionEvent event) throws IOException,SQLException{
        
    }
    
    @FXML
    void adiconarAula(ActionEvent event) throws IOException, SQLException {
      URL url = new File ("src/main/java/view/addAula.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage adicionar = new Stage();
      AddAulaController aa = loader.getController();
      aa.setStage(adicionar);
      Scene scene = new Scene(root);
      adicionar.setScene(scene);
      adicionar.show();
    }
    
    public void carregarTabela() throws SQLException {
        AulaDAO dao = new AulaDAO();
        Lista.setAll(dao.selecionarAulas());
        if (!Lista.isEmpty()) {
            tabela.getColumns().clear();
            TableColumn<Aula, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getNome()));

            TableColumn<Aula, String> colunaTipo = new TableColumn<>("Tipo");
            colunaTipo.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getTipo()));
            
            TableColumn<Aula, String> colunaData = new TableColumn<>("Data");
            colunaData.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getData().toString()));
            
            TableColumn<Aula, String> colunaHorario = new TableColumn<>("Horario");
            colunaHorario.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getComeco().toString() + " " + 
                    u.getValue().getFim().toString()));

            tabela.getColumns().addAll(colunaNome,colunaTipo,colunaData,colunaHorario);
            tabela.getColumns().forEach(e -> {
                e.setPrefWidth(135);
            });

            FilteredList<Aula> listaFiltrada = new FilteredList<>(Lista, p -> true);
            tfPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrada.setPredicate(Aula -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String filtro = newVal.toLowerCase();
                    return Aula.getNome().toLowerCase().contains(filtro)
                            || Aula.getTipo().toLowerCase().contains(filtro)
                            || Aula.getData().toString().contains(filtro)
                            || Aula.getComeco().toString().contains(filtro)
                            || Aula.getFim().toString().contains(filtro);
                });
            });

            SortedList<Aula> listaOrdenada = new SortedList<>(listaFiltrada);
            listaOrdenada.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(listaOrdenada);

            tabela.setRowFactory(e -> {
                TableRow<Aula> linha = new TableRow<>();
                linha.setOnMouseClicked(event -> {
                    if (!linha.isEmpty() && event.getClickCount() == 2) {
                        Aula aulaSelecionada = linha.getItem(); 
                        try {
                            acessarAula(aulaSelecionada);
                        } catch (IOException | SQLException ex) {
                            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return linha;
            });
        }
    }

    private ObservableList<Aula> listarAulas() throws SQLException {
        AulaDAO dao = new AulaDAO();
        return dao.selecionarAulas();
    }
    
    void acessarAula(Aula aula) throws IOException, SQLException {
        URL url = new File("src/main/java/view/perfilAula.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaAula = new Stage();
        PerfilAulaController pac = loader.getController();
        pac.setStage(telaAula, aula);
        Scene scene = new Scene(root);
        telaAula.setScene(scene);
        telaAula.show();
        stageAula.close();
    }

}
