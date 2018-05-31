/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.baeumli.offergenmaven.Controllers;

import ch.baeumli.offergenmaven.Database;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author lbaum
 */
public class Main_Controller implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private AnchorPane toolbarPane; 
    @FXML
    private Button btnMinimize;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnMaximize;
    @FXML
    private ToggleButton menuFile;
    @FXML
    private ToggleButton menuOptions;
    @FXML
    private ToggleButton menuSend;
    @FXML
    private TextArea txtPreview;

    
    
    @FXML
    void btnExitClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void btnMaximizeClick(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    void btnMinimizeClick(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    void menuFileClick(ActionEvent event) {
        try {
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ToolbarFile_View.fxml")); 
            toolbarPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        if (menuFile.isSelected()) {
            menuOptions.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff;");
            menuSend.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff;");
            menuFile.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #3c3c3c;");
        }
        
    }
    
    @FXML
    void menuOptionsClick(ActionEvent event) {
        try {
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ToolbarOptions_View.fxml")); 
            toolbarPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        if (menuOptions.isSelected()) {
            menuFile.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff;");
            menuSend.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff;");
            menuOptions.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #3c3c3c;");
        }
    }

    
    
    @FXML
    void menuSendClick(ActionEvent event) {
        try {
            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ToolbarSend_View.fxml"));
            toolbarPane.getChildren().add(newLoadedPane);
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (menuSend.isSelected()) {
            menuFile.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff;");
            menuOptions.setStyle("-fx-background-color: #3c3c3c; -fx-text-fill: #ffffff;");
            menuSend.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #3c3c3c;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        
        Database db = Database.getInstance();
        if (db.establishConnection() != null) {
            System.out.println("Successfully connected to db");
        }
  
        txtPreview.setText("Monsieur, Nous avons bien reçu votre commande du 30 mai et nous vous en remercions vivement."
                + "\n" + "\n"
                + "Nous vous proposons 10 tablettes modèle 0815 au prix de 800 CHF par tablette.\n"
                + "De plus, nous vous offre une remise spéciale de 5% pour toute commande supérieure à 5000 CHF.\n"
                + "Nous vous demandons de faire le paiement dans les 30 joursà notre compte de chèque postal.\n"
                + "Nous allons faire la livraison par camion après la réception de votre paiement."
                + "\n" + "\n"
                + "En vous remerciant d'avance de votre commande, nous vous prions d'agréer, Monsieur, nos distinguées.");   
    }      
}

