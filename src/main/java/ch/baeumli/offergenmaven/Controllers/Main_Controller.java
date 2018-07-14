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
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author lbaum
 */
public class Main_Controller implements Initializable {
    
    @FXML private AnchorPane toolbarPane;
    @FXML private AnchorPane paneMain;
    @FXML private Button btnMinimize;
    @FXML private Button btnExit;
    @FXML private Button btnMaximize;
    @FXML private ToggleButton menuFile;
    @FXML private ToggleButton menuOptions;
    @FXML private ToggleButton menuSend;
    @FXML private TextArea txtPreview;
    @FXML private AnchorPane paneTop;
    @FXML private ToggleGroup menuGroup;
    @FXML private AnchorPane previewPane;

    private static double xOffset;
    private static double yOffset;
    
    @FXML
    public void btnExitClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void btnMaximizeClick(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        if (stage.isMaximized()) {
            stage.setMaximized(false);
        } else {
            stage.setMaximized(true);
        }
    }

    @FXML
    public void btnMinimizeClick(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML
    public void menuFileClick(ActionEvent event) {
        try {
            toolbarPane.getChildren().clear();
            AnchorPane loadedPane = FXMLLoader.load(getClass().getResource("/fxml/ToolbarFile_View.fxml"));
            toolbarPane.getChildren().add(loadedPane);

        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }
    
    @FXML
    public void menuOptionsClick(ActionEvent event) {
        try {
            toolbarPane.getChildren().clear();
            AnchorPane loadedPane = FXMLLoader.load(getClass().getResource("/fxml/ToolbarOptions_View.fxml")); 
            toolbarPane.getChildren().add(loadedPane);
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @FXML
    public void menuSendClick(ActionEvent event) {
        try {
            toolbarPane.getChildren().clear();
            AnchorPane loadedPane = FXMLLoader.load(getClass().getResource("/fxml/ToolbarSend_View.fxml"));
            toolbarPane.getChildren().add(loadedPane);
        } catch (IOException ex) {
            Logger.getLogger(Main_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void paneTopDragged(MouseEvent event) {
        Stage stage = (Stage) paneMain.getScene().getWindow();
        
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);

    }

    @FXML
    public void paneTopPressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        menuFile.fire();
        
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