package org.livegrios.jpyll_test;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
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
import org.livegrios.jpyll.PythonListener;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

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
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        sbOutput = new StringBuilder();       
    }
    
    private void addListeners()
    {
        btnSearchFilePyEnv.setOnAction(evt -> { loadEnvironmentFile(); });
        btnSearchFile.setOnAction(evt -> { loadScriptConfigurationFile(); });
        btnRunScriptFile.setOnAction(evt -> { runFile(); });
    }
    
    private void loadEnvironmentFile()
    {
        File f = null;
        try
        {            
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
            f = fileChooser.showOpenDialog(window);
            if (f != null)
            {
                txtFilePathPyEnv.setText(f.getAbsolutePath());
            }
                
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            txtaOutput.setText(sbOutput.append(e.toString() + "\n").toString());
        }
    }
    
    private void loadScriptConfigurationFile()
    {
        File f = null;
        try
        {            
            fileChooser.getExtensionFilters().clear();            
            f = fileChooser.showOpenDialog(window);
            if (f != null)
            {
                txtFilePath.setText(f.getAbsolutePath());                
            }
                
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            txtaOutput.setText(sbOutput.append(e.toString() + "\n").toString());
        }
    }
    
    private void addMessage(String message)
    {
////////        Platform.runLater(() -> {
////////            txtaOutput.setText(sbOutput.append(message).toString());
////////            //txtaOutput.setScrollTop(Double.MAX_VALUE);
////////        });
        try
        {
            FXUtilities.runAndWait(() ->{
                txtaOutput.setText(sbOutput.append(message).toString());
                txtaOutput.setScrollTop(Double.MAX_VALUE);
            });
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Controlled Exception.");
        }
    }
    
    private void runFile()
    {   
        try
        {
            PythonEnvironment pyenv = PythonEnvironment.fromFile(txtFilePathPyEnv.getText());            
            PythonScript ps = PythonScript.fromFile(txtFilePath.getText());
            PythonListener listener = new PythonListener()
            {
                @Override
                public void onReady()
                {
                    addMessage("Python Environment Ready!\n");
                }

                @Override
                public void onMessage(String message)
                {
                    //System.out.println(message);
                    addMessage(message + "\n");
                }

                @Override
                public void onFinish()
                {
                    addMessage("Python Process Ended.\n");
                }
                
                @Override
                public void onException(Exception ex)
                {
                    addMessage(ex.toString() + "\n");
                }
            };
            JPythonLinker jpyl = new JPythonLinker();            
            
            addMessage("Python Environment established.\n");
            addMessage("Python Script Configuration File was loaded succesfully.\n");            
            addMessage("Python Linker created OK.\n");            
            jpyl.runScript(pyenv, ps, listener);
        } 
        catch(com.google.gson.JsonSyntaxException jse)
        {
            addMessage(jse.toString() + "\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            addMessage("No Python Script File was Selected.\n");
        }
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}
