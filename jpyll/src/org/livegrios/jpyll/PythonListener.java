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
 *  Artifact:   PythonListener.java
 *  Version :   0.1
 *  Date    :   2024-06-05 12:00:00
 *  Author  :   Miguel Angel Gil Rios
 *  Email   :   angel.grios@gmail.com
 */

package org.livegrios.jpyll;

/**
 *  This interface listen for events which occurrs in the Python environment
 *  and retrieve the information produced by the running scripts.
 *  @author LiveGrios
 */
public interface PythonListener
{
    /**
     * This method is called when the Python process was started and the
     * Java Envirinment is ready to listen and retrieve messages from the
     * running script.
     */
    public void onReady();
    
    /**
     * This method is called when the running Python script produces messages
     * using the print() method.
     * @param message 
     */
    public void onMessage(String message);
    
    /**
     * This method is called when the running Python script
     * finishes their execution.
     * @param message 
     */
    public void onFinish();
    
    /**
     * This method is called when a Exception is produced in the Java Environment
     * until the Python process is being executed.
     * 
     * @param ex 
     */
    public void onException(Exception ex);
}
