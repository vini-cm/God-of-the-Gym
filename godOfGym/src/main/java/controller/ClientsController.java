package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.UserDAO;
import model.Usuario;

public class ClientsController {

    Stage stageClients;
    Cliente cliente;
    
    @FXML
    ObservableList<Usuario>lista = FXCollections.observableArrayList();
    
    @FXML
    private Button bntAdicionar;
    
    @FXML
    private Button btnHome;
    
    @FXML
    private TableView<Usuario> tabela;
    
    @FXML
    private TextField tfPesquisa;
    
    public void setStage(Stage stage) throws SQLException{
        this.stageClients = stage;
        ajustarTabela();
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
    
    public void ajustarTabela() throws SQLException{
        carregarTabela();
    }
    
    @FXML
    public void carregarTabela() throws SQLException{
        lista.setAll(listarClientes());
        System.out.println("clientes encontrados:" + lista.size());
        if(!lista.isEmpty()){
            tabela.getColumns().clear();
            TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
            colunaNome.setCellValueFactory(u ->  new SimpleStringProperty(u.getValue().getNome()));
            
            TableColumn<Usuario, String> colunaSobrenome = new TableColumn<>("Sobrenome");
            colunaSobrenome.setCellValueFactory(u ->  new SimpleStringProperty(u.getValue().getSobrenome()));
            
            TableColumn<Usuario, String> colunaTelefone = new TableColumn<>("Telefone");
            colunaTelefone.setCellValueFactory(u ->  new SimpleStringProperty(u.getValue().getTelefone()));
            
            TableColumn<Usuario, String> colunaCPF = new TableColumn<>("CPF");
            colunaCPF.setCellValueFactory(u ->  new SimpleStringProperty(u.getValue().getCPF()));
                
            tabela.getColumns().addAll(colunaNome,colunaSobrenome,colunaTelefone,colunaCPF);
            tabela.getColumns().forEach(e -> {e.setPrefWidth(135); });

            tabela.setItems(lista);
        }
    }
    
    private ObservableList<Usuario> listarClientes() throws SQLException{
        UserDAO dao = new UserDAO();
        return dao.PesquisarUsuariosPorTipo("cliente");
    }
}
