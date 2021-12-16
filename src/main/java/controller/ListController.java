package controller;

import start.App;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import model.Item;
import model.dao.DaoFactory;
import model.dao.ItemDaoJDBC;
import javafx.fxml.Initializable;

public class ListController implements Initializable {

    @FXML private TableView<Item> tabelaItens;    
    @FXML private TableColumn<Item,Image> colunaImagem;
    @FXML private TableColumn<Item,String> colunaDescricao;
    @FXML private TableColumn<Item,String> colunaStatus;
    @FXML private TableColumn<Item,String> colunaColecao;
    @FXML private TableColumn<Item,LocalDate> colunaDataAquisicao;
    
    private List<Item> listaItem;
    private ObservableList<Item> observableListItem;
 
    
    @FXML
    private void switchToPrimary() throws IOException {        
        App.setRoot("index"); 
    }
    @FXML
    private void switchToAdd() throws IOException {
        App.setRoot("add");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarItens();
    }
    
    public void carregarItens() {
        
        //Ele tá pegando a quantidade certa dos itens, o problema tá sendo na hora de exibir
        //Você pode testar que a quantidade de itens que você colocar vai aparecer na tabela clicável, mas não visível
        //A gente pode usar uma propriedade Callback tbm, tem sobre no StackOverflow
        colunaImagem.setCellValueFactory(new PropertyValueFactory<>("Imagem"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("Descricao"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colunaColecao.setCellValueFactory(new PropertyValueFactory<>("Colecao"));
        colunaDataAquisicao.setCellValueFactory(new PropertyValueFactory<>("DataAquisicao"));
        
        try {
            ItemDaoJDBC dao = DaoFactory.novoItemDao();
            listaItem = dao.listar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        observableListItem = FXCollections.observableArrayList(listaItem);
        tabelaItens.setItems(observableListItem);
    }
    
}