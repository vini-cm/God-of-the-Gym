package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Cliente;
import util.Alerta;

public class ClientsController {

    Stage stageClients;
    Cliente cliente;
    private ObservableList<Cliente> lista = FXCollections.observableArrayList();
    
    @FXML
    private Button bntAdicionar;
    
    @FXML
   private Button btnHome;
    
    @FXML
    private Button btnCadastrar;
    
    @FXML
    private TableView<Cliente> twCliente;
    
    @FXML
    private TextField tfPesquisa;
    
    public void setStage(Stage stage){
        this.stageClients = stage;
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
      stageClients.close();
    }
    
    @FXML
    void abrirAddCliente (ActionEvent event) throws IOException, SQLException{
      URL url = new File ("src/main/java/view/addCliente.fxml").toURI().toURL();
      FXMLLoader loader = new FXMLLoader(url);
      Parent root = loader.load();
      Stage telaAddClientes = new Stage();
      AddClienteController acc = loader.getController();
      acc.setStage(telaAddClientes);
      Scene scene = new Scene(root);
      telaAddClientes.setScene(scene);
      telaAddClientes.show();
    }
    
    private void carregarClientesTabela() throws SQLException{
        lista.setAll(selecionarClientes());
        if (!lista.isEmpty()){
            twCliente.getColumns().clear();
            
            TableColumn<Cliente, Number> colunaID = new TableColumn<>("ID");
            colunaID.setCellValueFactory(u -> u.getValue().idProperty());
            colunaID.setPrefWidth(40);
            
            TableColumn<Cliente, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> u.getValue().nomeProperty());
            colunaNome.setStyle("-fx-aligment: CENTER;");
            
            TableColumn<Cliente, String> colunaSobrenome = new TableColumn<>("Sobrenome");
            colunaSobrenome.setCellValueFactory(u -> u.getValue().sobrenomeProperty());
            
            TableColumn<Cliente, String> colunaEmail = new TableColumn<>("Email");
            colunaEmail.setCellValueFactory(u -> u.getValue().emailProperty());
            
            TableColumn<Cliente, String> colunaCargo = new TableColumn<>("Cargo");
            colunaCargo.setCellValueFactory(u -> u.getValue().cargoProperty());
            
            TableColumn<Cliente, String> colunaUsername = new TableColumn<>("Username");
            colunaUsername.setCellValueFactory(u -> u.getValue().usernameProperty());
            
            TableColumn<Cliente, String> colunaTelefone = new TableColumn<>("Telefone");
            colunaTelefone.setCellValueFactory(u -> u.getValue().telefoneProperty());
            
            TableColumn<Cliente, String> colunaAniversario = new TableColumn<>("Aniversario");
            colunaAniversario.setCellValueFactory(u -> u.getValue().aniversarioProperty());
            
            twCliente.getColumns().addAll(colunaID,colunaNome,colunaSobrenome, colunaEmail, colunaCargo, colunaUsername,colunaTelefone,
                    colunaAniversario);
            
            FilteredList<Cliente> listaFiltrada = new FilteredList<>(lista, p -> true);
            tfPesquisa.textProperty().addListener((obs,oldVal,newVal) -> {
             listaFiltrada.setPredicate(user -> {
                 if (newVal == null || newVal.isEmpty()){
                     return true;
                 }
                 String filtro = newVal.toLowerCase();
                 return user.getNome().toLowerCase().contains(filtro)
                        || user.getSobrenome().toLowerCase().contains(filtro)
                        || user.getCPF().toLowerCase().contains(filtro)
                        || Cliente.getIdPlano().toLowerCase().contains(filtro);
             });
            });
            
            SortedList<Cliente> listaOrdenada = new SortedList<>(listaFiltrada);
            listaOrdenada.comparatorProperty().bind(twCliente.comparatorProperty());
            twCliente.setItems(listaOrdenada);
}

    private Cliente[] selecionarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
