package org.livegrios.jpyll.model;

import com.google.gson.Gson;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class PythonEnvironment
{
    String pythonPath;
    String pythonBinPath;
    
    public PythonEnvironment(){}

    public PythonEnvironment(String pythonPath, String pythonBinPath)
    {
        this.pythonPath = pythonPath;
        this.pythonBinPath = pythonBinPath;
    }
    
    public String getPythonPath()
    {
        return pythonPath;
    }

    public void setPythonPath(String pythonPath)
    {
        this.pythonPath = pythonPath;
    }

    public String getPythonBinPath()
    {
        return pythonBinPath;
    }

    public void setPythonBinPath(String pythonBinPath)
    {
        this.pythonBinPath = pythonBinPath;
    }
    
    public static PythonEnvironment fromFile(String filePath) throws Exception
    {
        String content = FileUtils.readFileToString(new File(filePath), "UTF-8");
        return fromJSON(content);
    }
    
    public static PythonEnvironment fromJSON(String json) throws Exception
    {
        return new Gson().fromJson(json, PythonEnvironment.class);
    }
}
