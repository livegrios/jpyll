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
 *  Artifact:   JPythonLinker.java
 *  Version :   0.1
 *  Date    :   2024-06-05 12:00:00
 *  Author  :   Miguel Angel Gil Rios
 *  Email   :   angel.grios@gmail.com
 */
package org.livegrios.jpyll;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

/**
 *  This class starts the execution of the Python Engine and the passed
 *  PythonScript object.
 */
public class JPythonLinker
{   
    /**
     * The JPythonLinker default constructor.
     */
    public JPythonLinker(){}
    
    /**
     * This method execute a Python script.
     * @param pyenv is the object with the Python environment information-
     * @param ps is the object with the Python script information.
     * @param listener  is the listener which will react to the distinct stages
     *                  of the Python execution environment and the messages
     *                  produced by the script.
     * @throws Exception 
     */
    public void runScript(PythonEnvironment pyenv, PythonScript ps, PythonListener listener) throws Exception
    {
        PythonProcess pp = PythonProcess.build(pyenv, ps, listener);
        pp.start();
    }
}
