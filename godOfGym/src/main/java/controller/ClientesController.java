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
import model.Cliente;
import model.UserDAO;
import model.Usuario;

public class ClientesController {

    Stage stageClientes;
    Cliente cliente;

    @FXML
    ObservableList<Usuario> lista = FXCollections.observableArrayList();

    @FXML
    private Button bntAdicionar;

    @FXML
    private Button btnHome;

    @FXML
    private TableView<Usuario> tabela;

    @FXML
    private TextField tfPesquisa;

    public void setStage(Stage stage) throws SQLException {
        this.stageClientes = stage;
        ajustarTabela();
    }

    @FXML
    void voltarHome(ActionEvent event) throws IOException {
        URL url = new File("src/main/java/view/home.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage home = new Stage();
        HomeController hc = loader.getController();
        hc.setStage(home);
        Scene scene = new Scene(root);
        home.setScene(scene);
        home.show();
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        home.getIcons().add(icon);
        home.setTitle("HOME PAGE");
        stageClientes.close();
    }

    @FXML
    void abrirAddCliente(ActionEvent event) throws IOException, SQLException {
        URL url = new File("src/main/java/view/addCliente.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaAddClientes = new Stage();
        AddClienteController acc = loader.getController();
        acc.setStage(telaAddClientes,this);
        Scene scene = new Scene(root);
        telaAddClientes.setScene(scene);
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        telaAddClientes.getIcons().add(icon);
        telaAddClientes.setTitle("ADICIONAR CLIENTE");
        telaAddClientes.show();
    }

    public void ajustarTabela() throws SQLException {
        carregarTabela();
    }

    @FXML
    public void carregarTabela() throws SQLException {
        lista.setAll(listarClientes());
        if (!lista.isEmpty()) {
            tabela.getColumns().clear();
            TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getNome() + " " + u.getValue().getSobrenome()));

            TableColumn<Usuario, String> colunaTelefone = new TableColumn<>("Telefone");
            colunaTelefone.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getTelefone()));

            TableColumn<Usuario, String> colunaCPF = new TableColumn<>("CPF");
            colunaCPF.setCellValueFactory(u -> new SimpleStringProperty(u.getValue().getCPF()));

            tabela.getColumns().addAll(colunaNome, colunaTelefone, colunaCPF);
            tabela.getColumns().forEach(e -> {
                e.setPrefWidth(218);
            });

            //tabela.setItems(lista);
            FilteredList<Usuario> listaFiltrada = new FilteredList<>(lista, p -> true);
            tfPesquisa.textProperty().addListener((obs, oldVal, newVal) -> {
                listaFiltrada.setPredicate(Usuario -> {
                    if (newVal == null || newVal.isEmpty()) {
                        return true;
                    }
                    String filtro = newVal.toLowerCase();
                    return Usuario.getNome().toLowerCase().contains(filtro)
                            || Usuario.getSobrenome().toLowerCase().contains(filtro)
                            || Usuario.getCPF().toLowerCase().contains(filtro);
                });
            });

            SortedList<Usuario> listaOrdenada = new SortedList<>(listaFiltrada);
            listaOrdenada.comparatorProperty().bind(tabela.comparatorProperty());
            tabela.setItems(listaOrdenada);

            tabela.setRowFactory(e -> { //setRow customiza a linha
                TableRow<Usuario> linha = new TableRow<>();
                linha.setOnMouseClicked(event -> {
                    if (!linha.isEmpty() && event.getClickCount() == 2) {
                        Usuario usuarioSelecionado = linha.getItem(); //get item porque a gente setou os item logo acima
                        try {
                            acessarPerfil(usuarioSelecionado);
                        } catch (IOException | SQLException ex) {
                            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
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
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        telaPerfil.getIcons().add(icon);
        telaPerfil.setTitle("PERFIL CLIENTE");
        telaPerfil.show();
        stageClientes.close();
    }
}
