/*
    Copyright 2024 Miguel-Angel Gil-Rios [angel.grios@gmail.com]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

*/

/**
 *  Artifact:   PythonScript.java
 *  Version :   0.1
 *  Date    :   2024-06-05 12:00:00
 *  Author  :   Miguel Angel Gil Rios
 *  Email   :   angel.grios@gmail.com
 */
package org.livegrios.jpyll.model;

import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * This class abstracts the information related with a Python Script since it is
 * needed to be passed to a Python Script for their execution.
 * @author LiveGrios
 */
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
