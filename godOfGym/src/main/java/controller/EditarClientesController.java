/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import util.Alerta;

public class EditarClientesController {
    
    private Stage dialogStage;
    private Cliente clienteEmEdicao;
    private boolean isSaved = false; 
    private ClienteDAO clienteDAO = new ClienteDAO();
    

    @FXML 
    private TextField tfNome;
    @FXML 
    private TextField tfSobrenome;

    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setCliente(Cliente cliente) {
        this.clienteEmEdicao = cliente;
        
        tfNome.setText(cliente.getNome());
        tfSobrenome.setText(cliente.getSobrenome());
    }

    public boolean isSaved() {
        return isSaved;
    }
    
    @FXML
    void handleSalvar() {
        clienteEmEdicao.setNome(tfNome.getText());
        clienteEmEdicao.setSobrenome(tfSobrenome.getText());
        try {
            clienteDAO.editar(clienteEmEdicao);

            this.isSaved = true; 
            dialogStage.close();
            
        } catch (SQLException e) {
            Alerta.exibirAlertaErro("Erro de Banco de Dados", "Falha ao salvar as alterações do cliente");
        }
    }
    
    @FXML
    void handleCancelar() {
        // Just close the window without saving
        dialogStage.close();
    }
}
