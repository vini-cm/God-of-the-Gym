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
import javafx.scene.control.TableColumn;
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
    
    @FXML
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
        var image = new Image(getClass().getResourceAsStream("src\\main\\java\\Imagens\\Imagem_AvatarPrincipal.png\""));
        ImageView imageView = new ImageView(image);
        btnHome.setGraphic(imageView);
        System.out.println(btnHome);
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
    
    @FXML
    private void carregarClientesTabela() throws SQLException{
        lista.setAll(selecionarClientesUsuario());
        if (!lista.isEmpty()){
            twCliente.getColumns().clear();
            
            TableColumn<Cliente, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u -> u.getValue().nomeProperty());
            colunaNome.setStyle("-fx-aligment: CENTER;");
            
            TableColumn<Cliente, String> colunaSobrenome = new TableColumn<>("Sobrenome");
            colunaSobrenome.setCellValueFactory(u -> u.getValue().sobrenomeProperty());
            
            TableColumn<Cliente, String> colunaTelefone = new TableColumn<>("Telefone");
            colunaTelefone.setCellValueFactory(u -> u.getValue().telefoneProperty());
            
            TableColumn<Cliente, String> colunaCPF = new TableColumn<>("CPF");
            colunaCPF.setCellValueFactory(u -> u.getValue().cpfProperty());
            
             TableColumn<Cliente, String> colunaPlano = new TableColumn<>("Plano");
            colunaPlano.setCellValueFactory(u -> u.getValue().tipoProperty());
            
            twCliente.getColumns().addAll(colunaNome,colunaSobrenome, colunaTelefone,colunaCPF, colunaPlano);
            
            FilteredList<Cliente> listaFiltrada = new FilteredList<>(lista, p -> true);
            tfPesquisa.textProperty().addListener((obs,oldVal,newVal) -> {
             listaFiltrada.setPredicate(Cliente -> {
                 if (newVal == null || newVal.isEmpty()){
                     return true;
                 }
                 String filtro = newVal.toLowerCase();
                 return Cliente.getNome().toLowerCase().contains(filtro)
                        || Cliente.getSobrenome().toLowerCase().contains(filtro)
                        || Cliente.getCPF().toLowerCase().contains(filtro)
                        || Cliente.getIdPlano().toLowerCase().contains(filtro);
             });
            });
            
            SortedList<Cliente> listaOrdenada = new SortedList<>(listaFiltrada);
            listaOrdenada.comparatorProperty().bind(twCliente.comparatorProperty());
            twCliente.setItems(listaOrdenada);
        }
    }

    @FXML
    private Cliente[] selecionarClientesUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}