package org.livegrios.jpyll_test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.fxmisc.richtext.CodeArea;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonArgument;
import org.livegrios.jpyll.model.PythonScript;

/**
 *
 * @author LiveGrios
 */
public class ControllerPythonScript
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    Main main;    
    ObservableList<PythonArgument> parameters;
    TableAdapterPythonParameters tableAdapterPythonParameters;
    
    PythonEnvironment pyenv;
    PythonScript pyscript;
    
    public ControllerPythonScript(Main main)
    {
        this.main = main;
                
        this.parameters = FXCollections.observableArrayList();
        
        tableAdapterPythonParameters = new TableAdapterPythonParameters(main.tblvScriptParameters, parameters);
        tableAdapterPythonParameters.adapt();
        
        addListeners();
        
        clear();
    }
    
    private void addListeners()
    {
        main.txtPythonBinPath.textProperty().addListener((observable, oldValue, newValue) -> {
            pyenv.setPythonBinPath(main.txtPythonBinPath.getText());
            pyenv.setPythonPath(main.txtPythonDirectory.getText());
            main.codeAreaPythonEnvironment.clear();
            main.codeAreaPythonEnvironment.append(pyenv.toJSON(), Application.STYLESHEET_MODENA);
        });
        
        main.txtScriptLocation.textProperty().addListener((observable, oldValue, newValue) -> {
            pyscript.setFilePath(newValue);
            main.codeAreaPythonScript.clear();
            main.codeAreaPythonScript.append(pyscript.toJSON(), Application.STYLESHEET_MODENA);
        });
        
        main.txtScriptName.textProperty().addListener((observable, oldValue, newValue) -> {
            pyscript.setShortName(newValue);
            main.codeAreaPythonScript.clear();
            main.codeAreaPythonScript.append(pyscript.toJSON(), Application.STYLESHEET_MODENA);
        });
        
        main.txtScriptDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            pyscript.setDescription(newValue);
            main.codeAreaPythonScript.clear();
            main.codeAreaPythonScript.append(pyscript.toJSON(), Application.STYLESHEET_MODENA);
        });
    }
    
    public void clear()
    {
        pyenv = new PythonEnvironment("", "");
        pyscript = new PythonScript("", "", "");        
        main.txtScriptName.setText("");
        main.txtScriptDescription.setText("");
        parameters.clear();
        refreshCodeArea(main.codeAreaPythonEnvironment, pyenv);
        refreshCodeArea(main.codeAreaPythonScript, pyscript);
    }
    
    public void refreshCodeArea(CodeArea codeArea, Object entity)
    {
        codeArea.clear();
        codeArea.append(gson.toJson(entity), Application.STYLESHEET_MODENA);
    }
    
    public List<PythonArgument> getParameters()
    {
        return main.tblvScriptParameters.getItems();
    }
    
    public void addNewParameter()
    {
        String name = main.txtParameterName.getText().trim();
        int typeIndex = main.cmbPythonParameterType.getSelectionModel().getSelectedIndex();
        PythonArgument.Type type = null;
        String value = main.txtParameterValue.getText();
        PythonArgument pp = null;
        
        if (name.isEmpty())
            name = "Unamed";
        
        if (typeIndex < 0)
            typeIndex = 0;
        
        type = PythonArgument.Type.values()[typeIndex];
        
        pp = new PythonArgument(name, type, value);
        pyscript.addArgument(pp);
        parameters.add(pp);
        refreshCodeArea(main.codeAreaPythonScript, pyscript);
    }
    
    
    
    public void setPythonEnvironmentPath(String pythonDirectory, String pythonPath)
    {
        main.txtPythonDirectory.setText(pythonDirectory);
        main.txtPythonBinPath.textProperty().set(pythonPath);
    }
    
    public void setScriptLocation(String str)
    {
        main.txtScriptLocation.textProperty().set(str);
    }
    
    
}
