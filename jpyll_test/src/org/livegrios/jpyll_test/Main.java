package org.livegrios.jpyll_test;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.livegrios.jpyll.JPythonLinker;
import org.livegrios.jpyll.model.PythonEnvironment;

/**
 *
 * @author LiveGrios
 */
public class Main extends Application
{
    @FXML AnchorPane pnlRoot;
    @FXML BorderPane pnlOutputContainer;
    @FXML TextField txtFilePath;
    @FXML TextField txtFilePathPyEnv;
    @FXML TextArea txtaOutput;
    @FXML Button btnSearchFilePyEnv;
    @FXML Button btnSearchFile;
    @FXML Button btnRunScriptFile;
    
    FileChooser fileChooser;
    
    FXMLLoader fxmll;
    Scene scene;
    Stage window;
    
    StringBuilder sbOutput;
    JPythonLinker jpyl;
    PythonEnvironment pyenv;
    
    public Main()
    {
        super();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        fxmll = new FXMLLoader(Main.class.getResource("main.fxml"));
        fxmll.setController(this);
        fxmll.load();
        
        scene = new Scene(fxmll.getRoot());
        
        initComponents();
        addListeners();
        
        window = primaryStage;
        window.setScene(scene);
        window.setTitle("Java-Python Linker Test");
        window.show();
    }
    
    private void initComponents()
    {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));        
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        sbOutput = new StringBuilder();
        
        jpyl = new JPythonLinker();
    }
    
    private void addListeners()
    {
        btnSearchFilePyEnv.setOnAction(evt -> { loadEnvironmentFile(); });
    }
    
    private void loadEnvironmentFile()
    {
        File f = null;
        try
        {            
            f = fileChooser.showOpenDialog(window);
            if (f != null)
            {
                pyenv = PythonEnvironment.fromFile(f.getAbsolutePath());
                jpyl.setPythonEnvironment(pyenv);
                txtFilePathPyEnv.setText(f.getAbsolutePath());
                txtaOutput.setText("Python Environment Configuration File was loaded succesfully.\n");
            }
                
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            txtaOutput.setText(sbOutput.append(e.toString() + "\n").toString());
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
