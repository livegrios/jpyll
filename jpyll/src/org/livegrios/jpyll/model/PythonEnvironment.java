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
 *  Artifact:   PythonEnvironment.java
 *  Version :   0.1
 *  Date    :   2024-06-05 12:00:00
 *  Author  :   Miguel Angel Gil Rios
 *  Email   :   angel.grios@gmail.com
 */
package org.livegrios.jpyll.model;

import com.google.gson.Gson;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 * This class abstracts the information related with a Python Environment.
 * 
 * It includes the Python Environment path and the binaries path.
 * @author LiveGrios
 */
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
