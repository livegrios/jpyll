package org.livegrios.jpyll_test;

import org.livegrios.jpyll.JPythonLinker;
import org.livegrios.jpyll.PythonListener;
import org.livegrios.jpyll.model.PythonArgument;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

/**
 *
 * @author LiveGrios
 */
public class TestConsoleFromJSON
{
    public static void main(String[] args)
    {
        try
        {
            PythonEnvironment pyenv = PythonEnvironment.fromFile("C:/Users/Markdown/python_env.json");
            PythonScript ps = PythonScript.fromFile("C:/Users/Markdown/script02.json");
            PythonListener listener = new PythonListener()
            {
                @Override
                public void onReady()
                {
                    System.out.println("Python Environment Ready!");
                }

                @Override
                public void onMessage(String message)
                {
                    System.out.println(message);                    
                }

                @Override
                public void onFinish()
                {
                    System.out.println("Python Process Ended.");
                }
                
                @Override
                public void onException(Exception e)
                {
                    e.printStackTrace();
                }
            };
            
            JPythonLinker jpyl = new JPythonLinker();
            jpyl.runScript(pyenv, ps, listener);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
