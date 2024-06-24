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
import org.livegrios.jpyll.JPYLLCommons;

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
    List<PythonArgument> arguments;
    /**
     * The default constructor.
     */
    public PythonScript()
    {
        arguments = new ArrayList<>();
    }
    
    /**
     * Create a <code>PythonScript</code> instance specifying the script 
     * file path, the description and the action name.
     * @param filePath      The script file path.
     * @param description   The script description.
     * @param action        The action to be executed. At this point, this
     *                      parameter has no effects but it is considered
     *                      for future releases of the library.
     */
    public PythonScript(String filePath, String description, String action)
    {
        this.filePath = filePath;
        this.description = description;
        this.action = action;
        this.arguments = new ArrayList<>();
    }

    /**
     * Return the script short name.
     * The short name is a given name to the script.
     * @return 
     */
    public String getShortName()
    {
        return shortName;
    }

    /**
     * Set the script short name.
     * @param shortName The script short name.
     */
    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    /**
     * Return the script description.
     * @return The script description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the script description.
     * @param description The script description.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Return the script file full-path.
     * @return The file full-path.
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * Set the script file full-path.
     * @param filePath 
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Return the script action.
     * @return The script action.
     */
    public String getAction()
    {
        return action;
    }

    /**
     * Set the script action.
     * @param action The script action.
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * Return the list of arguments needed by the script.
     * @return The list of arguments.
     */
    public List<PythonArgument> getArguments()
    {
        return arguments;
    }
    
    /**
     * Remove all the preiously defined script arguments.
     */
    public void clearArguments()
    {
        arguments.clear();
    }
    
    /**
     * Add arguments to the script.
     * @param p The script <code>Argument</code> to be added.
     */
    public void addArgument(PythonArgument... p)
    {
        for (PythonArgument par : p)
            arguments.add(par);
    }
    
    public String toJSON()
    {
        return JPYLLCommons.gson.toJson(this);
    }
    
    /**
     * Create a PythonScript instance from a JSON definition file.
     * @param filePath  The full path pf the PythonScript definition file 
     *                  in JSON format.
     * @return  The <code>PythonScript</code> object.
     * @throws Exception 
     */
    public static PythonScript fromFile(String filePath) throws Exception
    {
        String content = FileUtils.readFileToString(new File(filePath), "UTF-8");
        return fromJSON(content);
    }
    
    /**
     * Create a <code>PythonScript</code> object from a JSON content.
     * The JSON content have the next structure:
     * <pre>
     *      {
     *          "shortName"     : "Example",
     *          "description"   : "Script which prints the first N int numbers specified by the max_value parameter.",
     *          "filePath"      : "C:/Users/Markdown/Documents/print_numbers_v2.py",
     *          "action"        : "",
     *          "parameters"    : [
     *                              {
     *                                      "name" : "max_value",
     *                                      "type" : "Int",
     *                                      "value": "50000"
     *                              }
     *                            ]
     *       }
     * </pre>
     * @param json
     * @return
     * @throws Exception 
     */
    public static PythonScript fromJSON(String json) throws Exception
    {
        return new Gson().fromJson(json, PythonScript.class);
    }
}
