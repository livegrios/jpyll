package org.livegrios.jpyll_test;

import java.io.File;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.livegrios.jpyll.JPythonLinker;
import org.livegrios.jpyll.PythonListener;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonParameter;
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
    
    @FXML BorderPane pnlPythonEnvironmentJSONContent;
    @FXML BorderPane pnlPythonScriptJSONContent;
    
    @FXML TextField txtPythonDirectory;
    @FXML TextField txtPythonBinPath;
    @FXML TextField txtScriptLocation;
    @FXML TextField txtScriptName;
    @FXML TextField txtScriptDescription;
    
    @FXML TextField txtParameterName;
    @FXML TextField txtParameterValue;
        
    @FXML TableView<PythonParameter> tblvScriptParameters;
    
    @FXML ComboBox cmbPythonParameterType;
    
    @FXML Button btnSavePythonEnvironment;
    @FXML Button btnBrowsePythonBinPath;
    @FXML Button btnBrowsePythonScriptLocation;
    @FXML Button btnSavePythonScript;    
    @FXML Button btnNewPythonParameter;
    @FXML Button btnAddPythonParameter;
    @FXML Button btnRemovePythonParameter;
    
    DirectoryChooser directoryChooser;
    FileChooser fileChooser;
    
    CodeArea codeAreaPythonEnvironment;
    CodeArea codeAreaPythonScript;
    
    ControllerPythonScript controllerPythonScript;
    
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
        
        directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        
        sbOutput = new StringBuilder();
        
        codeAreaPythonEnvironment = new CodeArea();
        codeAreaPythonScript      = new CodeArea();
        
        codeAreaPythonEnvironment.setEditable(false);
        codeAreaPythonScript.setEditable(false);
        
        pnlPythonEnvironmentJSONContent.setCenter(codeAreaPythonEnvironment);
        pnlPythonScriptJSONContent.setCenter(codeAreaPythonScript);
        
        controllerPythonScript = new ControllerPythonScript(this);
        
        cmbPythonParameterType.setItems(FXCollections.observableArrayList(PythonParameter.Type.values()));
        
    }
    
    private void addListeners()
    {
        btnSearchFilePyEnv.setOnAction(evt -> { loadEnvironmentFile(); });
        btnSearchFile.setOnAction(evt -> { loadScriptConfigurationFile(); });
        btnRunScriptFile.setOnAction(evt -> { runFile(); });
        btnBrowsePythonBinPath.setOnAction(evt -> { browsePythonBinPath(); });
        btnBrowsePythonScriptLocation.setOnAction( evt -> { browsePythonScript(); });
        
        btnAddPythonParameter.setOnAction( evt -> { controllerPythonScript.addNewParameter(); });
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
                fileChooser.setInitialDirectory(f.getParentFile());
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
                fileChooser.setInitialDirectory(f.getParentFile());
            }
                
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            txtaOutput.setText(sbOutput.append(e.toString() + "\n").toString());
        }
    }
    
    private void browsePythonBinPath()
    {        
        File f = null;
        String strf = null;
        String strb = null;
        
        try
        {            
            fileChooser.getExtensionFilters().clear();
            if (System.getProperty("os.name").toLowerCase().contains("windows"))
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Python Executable File (*.exe)", "*.exe"));
            
            f = fileChooser.showOpenDialog(window);
            if (f != null)
            {                
                strf = f.toURI().toString().substring(6);
                strb = f.getParentFile().toURI().toString().substring(6);                
                controllerPythonScript.setPythonEnvironmentPath(strb, strf);
                fileChooser.setInitialDirectory(f.getParentFile());                
            }
                
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            txtaOutput.setText(sbOutput.append(e.toString() + "\n").toString());
        }
    }
    
    private void browsePythonScript()
    {        
        File f = null;
        String strf = null;
        
        try
        {                        
            fileChooser.getExtensionFilters().clear();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Python Script File (*.py)", "*.py"));
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            f = fileChooser.showOpenDialog(window);
            if (f != null)
            {                
                strf = f.toURI().toString().substring(6);                               
                fileChooser.setInitialDirectory(f.getParentFile());
                txtScriptLocation.textProperty().set(strf);
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
