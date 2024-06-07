package org.livegrios.jpyll_test;

import org.livegrios.jpyll.JPythonLinker;
import org.livegrios.jpyll.PythonListener;
import org.livegrios.jpyll.model.Parameter;
import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

/**
 *
 * @author LiveGrios
 */
public class TestConsole
{
    public static void main(String[] args)
    {
        try
        {
            PythonEnvironment pyenv = new PythonEnvironment("C:/Users/Markdown/AppData/Local/Programs/Python/Python311",
                                                            "C:/Users/Markdown/AppData/Local/Programs/Python/Python311/python.exe");
            PythonScript ps = new PythonScript("C:/Users/Markdown/Documents/NPROY17/UTL_Research/jpyll/python_samples/print_numbers_v2.py", "", "");
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
            ps.addParameter(new Parameter("max_value", Parameter.Type.Int, "50000"));
            JPythonLinker jpyl = new JPythonLinker();
            jpyl.runScript(pyenv, ps, listener);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
