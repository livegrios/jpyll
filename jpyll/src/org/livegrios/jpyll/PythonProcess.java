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
 *  Artifact:   PythonProcess.java
 *  Version :   0.1
 *  Date    :   2024-06-05 12:00:00
 *  Author  :   Miguel Angel Gil Rios
 *  Email   :   angel.grios@gmail.com
 */
package org.livegrios.jpyll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.livegrios.jpyll.model.PythonParameter;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

/**
 *  This class is used to build and execute a Python process which will run
 *  a script. 
 * 
 *  Sinche this class is abstract, the static build(...) method must be used
 *  to create a new process.
 */
public abstract class PythonProcess implements Runnable
{
    ProcessBuilder processBuilder;
    Process process;
    String[] cmd;
    BufferedReader stdInput;
    BufferedReaderListener bufferedReaderListener;
    
    /**
     * Create a new Python Process which be executed in the future.
     * @param pyenv is the object which contains the Python interpreter location.
     * @param pythonScript  is the object containing the script information including
     *                      the parameters definition which will be passed from the
     *                      Java Environment.
     * @param listener  is the object which trigger actions on different execution
     *                  stages of the running Python Environment.
     * @return 
     */
    public static PythonProcess build(PythonEnvironment pyenv, PythonScript pythonScript, PythonListener listener)
    {
        PythonProcess pp = new PythonProcess()
        {
            @Override
            public void run()
            {
                cmd = new String[2 + pythonScript.getParameters().size()];
                int i = 2;
                try
                {
                    cmd[0] = pyenv.getPythonBinPath();
                    cmd[1] = pythonScript.getFilePath();
                    for (PythonParameter par : pythonScript.getParameters())
                        cmd[i++] =par.getValue().toString();
                    Runtime runtime = Runtime.getRuntime();
                    process = runtime.exec(cmd);
                    //process.wait();
                    stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    bufferedReaderListener = new BufferedReaderListener(listener, stdInput);
                    bufferedReaderListener.start();
                    //process.notify();
                } 
                catch (Exception e)
                {
                    //e.printStackTrace();
                    listener.onException(e);
                }
            }
        };
             
        return pp;
    }
    
    /**
     * Starts the execution of the Python Process.
     * 
     * The execution is launched in a new non-blocking Java Thread.
     */
    public void start()
    {
        Thread t = new Thread(this);
        t.start();
    }
    
    /**
     * This class is responsible for keep an indeterminate listening loop
     * until the Python Process is finished or canceled by the user.
     * 
     * Every time that a Python message is sended from the OS-native Python
     * environment, it is retrieved and passed out to the provided listener.
     */
    class BufferedReaderListener implements Runnable
    {
        BufferedReader br;
        boolean alive;
        String s;
        PythonListener listener;
        
        public BufferedReaderListener(PythonListener listener, BufferedReader br)
        {
            this.br = br;
            this.listener = listener;
        }
        
        public void start()
        {
            Thread t = new Thread(this);
            alive = true;
            t.start();
        }
        
        public void exit()
        {
            process.destroyForcibly();
            alive = false;
        }
        
        /**
         * This is the method focused into catch the incomming messages from
         * the Python Environment.
         */
        @Override
        public void run()
        {            
            listener.onReady();
            while(alive && process.isAlive())
            {
                try
                {
                    while ((s = br.readLine()) != null)
                    {
                        //System.out.println(s);
                        listener.onMessage(s);
                    }
                } 
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
            listener.onFinish();
        }
    }
}
