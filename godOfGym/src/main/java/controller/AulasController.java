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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Aula;
import model.UserDAO;
import model.Usuario;

public class AulasController {

    Stage stageAula;
    Aula aula;
    
    @FXML
    ObservableList<Aula> lista = FXCollections.observableArrayList();

   @FXML
    private Button bntAdicionar;

    @FXML
    private Button btnHome;

    @FXML
    private TableView<Usuario> tabela;

    @FXML
    private TextField tfPesquisa;
    
    public void setStage(Stage stage){
        this.stageAula = stage;
        Image image = new Image(getClass().getResourceAsStream("/imagens/voltar.png"));
        ImageView imageView = new ImageView(image);
        btnHome.setGraphic(imageView);
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
    
    public void carregarTabela() throws SQLException {
        lista.setAll(selecionarAulas());
        if (!lista.isEmpty()) {
            tabela.getColumns().clear();
            TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getNome()));

            TableColumn<Usuario, String> colunaSobrenome = new TableColumn<>("Sobrenome");
            colunaSobrenome.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getSobrenome()));

            TableColumn<Usuario, String> colunaTelefone = new TableColumn<>("Telefone");
            colunaTelefone.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getTelefone()));

            TableColumn<Usuario, String> colunaCPF = new TableColumn<>("CPF");
            colunaCPF.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getCPF()));

            tabela.getColumns().addAll(colunaNome, colunaSobrenome, colunaTelefone, colunaCPF);
            tabela.getColumns().forEach(e -> {
                e.setPrefWidth(135);
            });

            //tabela.setItems(lista);
            FilteredList<Aula> listaFiltrada = new FilteredList<>(lista, p -> true);
            tfPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrada.setPredicate(Aula -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String filtro = newVal.toLowerCase();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    return Aula.getNome().toLowerCase().contains(filtro)
                            || Aula.getTipo().toLowerCase().contains(filtro)
                            || String.valueOf(Aula.getValor()).toLowerCase().contains(filtro);
                });
            });

            SortedList<Aula> listaOrdenada = new SortedList<>(listaFiltrada);
            listaFiltrada.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(listaOrdenada);

            tabela.setRowFactory(e -> { //setRow customiza a linha
                TableRow<Usuario> linha = new TableRow<>();
                linha.setOnMouseClicked(event -> {
                    if (!linha.isEmpty() && event.getClickCount() == 2) {
                        Usuario usuarioSelecionado = linha.getItem(); //get item porque a gente setou os item logo acima
                        try {
                            acessarPerfil(usuarioSelecionado);
                        } catch (IOException | SQLException ex) {
                            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return linha;
            });
        }
    }

    private ObservableList<Usuario> listarClientes() throws SQLException {
        UserDAO dao = new UserDAO();
        return dao.PesquisarUsuariosPorTipo("cliente");
    }

    void acessarPerfil(Usuario user) throws IOException, SQLException {
        URL url = new File("src/main/java/view/perfilCliente.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaPerfil = new Stage();
        PerfilClienteController pcc = loader.getController();
        pcc.setStage(telaPerfil, user);
        Scene scene = new Scene(root);
        telaPerfil.setScene(scene);
        telaPerfil.show();
        stageClients.close();
    }

}