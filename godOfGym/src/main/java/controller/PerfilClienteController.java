package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import model.Planos;
import model.PlanosDAO;
import model.UserDAO;
import model.Usuario;
import util.Alerta;


public class PerfilClienteController {

    Stage stage;
    Usuario user;
    ClienteDAO dao = new ClienteDAO();
    UserDAO userDAO = new UserDAO();
    PlanosDAO planosDAO = new PlanosDAO();
    Cliente c;
    
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Label lbAltura;

    @FXML
    private Label lbCPF;

    @FXML
    private Label lbGenero;

    @FXML
    private Label lbGordura;

    @FXML
    private Label lbIMC;

    @FXML
    private Label lbIdade;

    @FXML
    private Label lbLimitacoes;

    @FXML
    private Label lbMedicamentos;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbPlano;
    
    @FXML
    private Label lbPeso;

    @FXML
    private Label lbTelefone;
    
    @FXML
    private Label lbEmail;
    
    @FXML
    Button btnClientes;
    
    @FXML
    void voltarClientes(ActionEvent event) throws IOException, SQLException {
        URL url = new File("src/main/java/view/clients.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage cliente = new Stage();
        ClientesController cc = loader.getController();
        cc.setStage(cliente);
        Scene scene = new Scene(root);
        cliente.setScene(scene);
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        cliente.getIcons().add(icon);
        cliente.setTitle("CLIENTES");
        cliente.show();
        stage.close();
    }

    public void setStage(Stage stage, Usuario user) throws SQLException {
        this.stage = stage;
        this.user = user;
        c = dao.selecionarCliente(user.getCPF());
        configurarTela();
    }
    
    public void configurarTela() throws SQLException{
        user = userDAO.selecionarUsuario(user.getCPF());
        Planos plano = planosDAO.selecionarPlanoPorId(c.getIdPlano());
        lbNome.setText(user.getNome() + " " + user.getSobrenome());
        lbCPF.setText(user.getCPF());
        lbTelefone.setText(user.getTelefone());
        lbPlano.setText(plano.getNome());
        lbAltura.setText(String.valueOf(c.getAltura()));
        lbIMC.setText(String.valueOf(c.getImc()));
        lbEmail.setText(user.getEmail());
        if (user.getGenero().equals("f")){
            lbGenero.setText("feminino");
        } else {
            lbGenero.setText("masculino");
        }
        lbIdade.setText((String.valueOf(calcularIdade(user.getDataNascimento()))));
        lbGordura.setText(String.valueOf(c.getPorcentagem()));
        lbPeso.setText(String.valueOf(c.getPeso()));
        lbMedicamentos.setOnMouseClicked(e -> {
            if(c.getMedicamentos().equals(" ")){
                Alerta.mostrarAviso("Medicamentos", "esse cliente não faz uso de medicamentos");
            } else {
            Alerta.mostrarInformacao("Medicamentos", c.getMedicamentos());
            }
        });
        lbLimitacoes.setOnMouseClicked(e -> {
            if(c.getLimitacoes().equals(" ")){
                Alerta.mostrarAviso("Limitações", "esse cliente não tem nenhuma limitação");
            } else {
            Alerta.mostrarInformacao("Limitações", c.getLimitacoes());
            }
        });
    }

   
    public int calcularIdade(Date nascimento){
        LocalDate aniversario = nascimento.toLocalDate();
        LocalDate hoje = LocalDate.now();
        return Period.between(aniversario, hoje).getYears();
    }
    
    @FXML
    void excluirCliente(ActionEvent event) throws SQLException, IOException {
       Optional<ButtonType> resultado = Alerta.mostrarConfirmacao("EXCLUSÃO", 
               "VOCE DESEJA EXCLUIR O CLIENTE " + user.getNome() + "?");
       if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            dao.deletar(user.getCPF());
            userDAO.deletar(user.getCPF());
            URL url = new File("src/main/java/view/clients.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage cliente = new Stage();
            ClientesController cc = loader.getController();
            cc.setStage(cliente);
            Scene scene = new Scene(root);
            File fileIcon = new File("src/main/resources/imagens/icon.png");
            Image icon = new Image(fileIcon.toURI().toString());
            cliente.getIcons().add(icon);
            cliente.setTitle("adicionar atendente");
            cliente.setScene(scene);
            cliente.show();
            stage.close();
        }
    }
    
    @FXML
    void editarCliente(ActionEvent event) throws IOException, SQLException {
        URL url = new File("src/main/java/view/editarCliente.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage cliente = new Stage();
        EditarClienteController ecc = loader.getController();
        ecc.setStage(cliente, c,this);
        Scene scene = new Scene(root);
        cliente.setScene(scene);
        File fileIcon = new File("src/main/resources/imagens/icon.png");
        Image icon = new Image(fileIcon.toURI().toString());
        cliente.getIcons().add(icon);
        cliente.setTitle("EDITAR CLIENTE");
        cliente.show();
    }
}
