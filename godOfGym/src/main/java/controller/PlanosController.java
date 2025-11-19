package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Planos;
import model.PlanosDAO;

public class PlanosController{

    Stage stagePlanos;
    
    @FXML
    ObservableList<Planos> Lista = FXCollections.observableArrayList();

    @FXML
    private Button bntAdicionar;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private TableView<Planos> tabela;
    
    @FXML
    private TextField tfPesquisa;
    
    public void setStage(Stage stage) throws SQLException{
        this.stagePlanos = stage;
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
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      home.getIcons().add(icon);
      home.setTitle("HOME PAGE");
      home.show();
      stagePlanos.close();
    }

    @FXML
    void abrirAddPlano(ActionEvent event) throws IOException {
      URL url = new File ("src/main/java/view/addPlano.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAddPlano = new Stage();
      AddPlanoController acc = loader.getController();
      acc.setStage(telaAddPlano,this);
      Scene scene = new Scene(root);
      File fileIcon = new File("src/main/resources/imagens/icon.png");
      Image icon = new Image(fileIcon.toURI().toString());
      telaAddPlano.getIcons().add(icon);
      telaAddPlano.setTitle("ADICIONAR PLANO");
      telaAddPlano.setScene(scene);
      telaAddPlano.show();
    }

    public void carregarTabela() throws SQLException {
        Lista.setAll(listarPlanos());
        if (!Lista.isEmpty()) {
            tabela.getColumns().clear();
            TableColumn<Planos, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getNome()));

            TableColumn<Planos, String> colunaTipo = new TableColumn<>("Tipo");
            colunaTipo.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getTipo()));
            
            TableColumn<Planos, String> colunaPreco = new TableColumn<>("Preço");
            colunaPreco.setCellValueFactory(u -> new SimpleStringProperty(String.valueOf(u.getValue().getPreco())));


            tabela.getColumns().addAll(colunaNome,colunaTipo,colunaPreco);
            tabela.getColumns().forEach(e -> {
                e.setPrefWidth(218);
            });

            FilteredList<Planos> listaFiltrada = new FilteredList<>(Lista, p -> true);
            tfPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrada.setPredicate(Planos -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String filtro = newVal.toLowerCase();
                    return Planos.getNome().toLowerCase().contains(filtro)
                            || Planos.getTipo().toLowerCase().contains(filtro)
                            || String.valueOf(Planos.getPreco()).contains(filtro);
                });
            });

            SortedList<Planos> listaOrdenada = new SortedList<>(listaFiltrada);
            listaOrdenada.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(listaOrdenada);

            tabela.setRowFactory(e -> {
                TableRow<Planos> linha = new TableRow<>();
                linha.setOnMouseClicked(event -> {
                    if (!linha.isEmpty() && event.getClickCount() == 2) {
                        Planos planoSelecionado = linha.getItem(); 
                        try {
                            acessarPlano(planoSelecionado);
                        } catch (IOException | SQLException ex) {
                            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return linha;
            });
        }
    }

    private ObservableList<Planos> listarPlanos() throws SQLException {
        PlanosDAO dao = new PlanosDAO();
        return dao.selecionarPlanos();
    }
    
    void acessarPlano(Planos plano) throws IOException, SQLException {
        URL url = new File("src/main/java/view/perfilPlano.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage tela = new Stage();
        PerfilPlanoController ppc = loader.getController();
        ppc.setStage(tela, plano);
        Scene scene = new Scene(root);
        tela.setScene(scene);
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        tela.getIcons().add(icon);
        tela.setTitle("PERFIL PLANO");
        tela.show();
        stagePlanos.close();
    }  
    
}
