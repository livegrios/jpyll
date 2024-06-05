package org.livegrios.jpyll;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.livegrios.jpyll.model.Parameter;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

/**
 *
 * @author LiveGrios
 */
public abstract class PythonProcess implements Runnable
{
    ProcessBuilder processBuilder;
    Process process;
    String[] cmd;
    BufferedReader stdInput;
    BufferedReaderListener runningListener;
    
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
                    cmd[0] = pyenv.getPythonPath();
                    cmd[1] = pythonScript.getFilePath();
                    for (Parameter par : pythonScript.getParameters())
                        cmd[i++] = par.getValue().toString();

                    Runtime runtime = Runtime.getRuntime();
                    Process process = runtime.exec(cmd);
                    stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    runningListener = new BufferedReaderListener(listener, stdInput);
                    runningListener.start();
                } 
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
             
        return pp;
    }
    
    public void start()
    {
        Thread t = new Thread(this);
        t.start();
    }
    
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
            alive = false;
        }
        
        @Override
        public void run()
        {            
            listener.setOnReady();
            while(alive)
            {
                try
                {
                    while ((s = br.readLine()) != null)
                    {
                        listener.setOnMessage(s);
                    }
                } 
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
            listener.setOnFinish();
        }
    }
}
