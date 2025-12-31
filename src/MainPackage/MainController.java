/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainPackage;

import UsingApi.ConvertisseurJsonXmlUsingJsonApi;
import UsingApi.ConvertisseurXmlJsonUsingJsonApi;
import WithoutApi.ConvertisseurXmlJsonWithoutApi;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author eloua
 */
public class MainController {

    @FXML
    private Button btnChooseFile;
    @FXML
    private TextArea xmlTexte;
    @FXML
    private TextArea jsonTexte;
    @FXML
    private Button btnToJson;
    @FXML
    private Button btnToXml;    
    private File f;
    private int choix;
    @FXML
    private CheckBox usingApi;
    @FXML
    private Button btnSaveXml;
    @FXML
    private Button btnSaveJson;
    @FXML
    private Label applicationName;
    @FXML
    private Button btnRefresh;


    @FXML
    private void handelChooseFileBtn(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();

        // Optionnel : définir un titre pour la fenêtre
        fileChooser.setTitle("Ouvrir un fichier");

        // Optionnel : définir le répertoire initial
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

//        // Optionnel : filtrer les types de fichiers
//        fileChooser.getExtensionFilters().addAll(
//            new FileChooser.ExtensionFilter("Xml or Json File", "*.json"),
//            new FileChooser.ExtensionFilter("Xml or Json File", "*.xml"));

        this.f = fileChooser.showOpenDialog(btnChooseFile.getScene().getWindow());
        afterChoosingFile();
    }

    private void afterChoosingFile() throws FileNotFoundException, IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(this.f));
        List<String> lines = bfr.readAllLines();
        Iterator<String> ite = lines.iterator();
        String texte="";
        while(ite.hasNext()){
            texte+=ite.next()+"\n";
        }
        if(this.f.getAbsolutePath().endsWith(".json")){
            this.jsonTexte.setText(texte);
        }
        if(this.f.getAbsolutePath().endsWith(".xml")){
            this.xmlTexte.setText(texte);
        }
    }


    @FXML
    private void handelToJsonBtn(ActionEvent event) throws IOException {
        if(this.usingApi.isSelected()){
            String xmltexte = this.xmlTexte.getText();
            ConvertisseurXmlJsonUsingJsonApi cxjwa = new ConvertisseurXmlJsonUsingJsonApi(xmltexte);
            this.jsonTexte.setText(cxjwa.toJson());
        }
        else{
            String xmltexte = this.xmlTexte.getText();
            ConvertisseurXmlJsonWithoutApi cxjwa = new ConvertisseurXmlJsonWithoutApi(xmltexte);
            this.jsonTexte.setText(cxjwa.toJson(xmltexte));
        }
        this.choix=0;
    }

    
    
    @FXML
    private void handelToXmlBtn(ActionEvent event) throws IOException {
        String jsonexte = this.jsonTexte.getText();
        ConvertisseurJsonXmlUsingJsonApi cxjwa = new ConvertisseurJsonXmlUsingJsonApi(jsonexte);
        this.xmlTexte.setText(cxjwa.toXml());
        this.choix=1;
    }

    private void saveTexteDeDand(File f, String jsonTexte) throws FileNotFoundException, IOException {
        FileOutputStream fos= new FileOutputStream(f);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeBytes(jsonTexte);
    }

    @FXML
    private void handelbtnSaveXml(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(btnChooseFile.getScene().getWindow());
        saveTexteDeDand(f,this.xmlTexte.getText());
        
    }

    @FXML
    private void handelbtnSaveJson(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(btnChooseFile.getScene().getWindow());
        saveTexteDeDand(f,this.jsonTexte.getText());
    }

    @FXML
    private void handelbtnRefreshBtn(ActionEvent event) {
        this.jsonTexte.setText("");
        this.xmlTexte.setText("");
        this.f = null;
    }
    
}
