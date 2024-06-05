package org.livegrios.jpyll.model;

import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


public class PythonScript
{
    String shortName;
    String description;
    String filePath;
    String action;
    List<Parameter> parameters;
    
    public PythonScript()
    {
        parameters = new ArrayList<>();
    }
    
    public PythonScript(String filePath, String description, String action)
    {
        this.filePath = filePath;
        this.description = description;
        this.action = action;
        this.parameters = new ArrayList<>();
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public List<Parameter> getParameters()
    {
        return parameters;
    }
    
    public void clearParameters()
    {
        parameters.clear();
    }
    
    public void addParameter(Parameter... p)
    {
        for (Parameter par : p)
            parameters.add(par);
    }
    
    public static PythonScript fromFile(String filePath) throws Exception
    {
        String content = FileUtils.readFileToString(new File(filePath), "UTF-8");
        return fromJSON(content);
    }
    
    public static PythonScript fromJSON(String json) throws Exception
    {
        return new Gson().fromJson(json, PythonScript.class);
    }
}
