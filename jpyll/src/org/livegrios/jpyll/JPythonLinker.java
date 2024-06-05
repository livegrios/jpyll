package org.livegrios.jpyll;

import org.livegrios.jpyll.model.PythonEnvironment;
import org.livegrios.jpyll.model.PythonScript;

/**
 *
 * @author LiveGrios
 */
public class JPythonLinker
{
    PythonEnvironment pyenv;
    
    public JPythonLinker(){}
    
    public void setPythonEnvironment(PythonEnvironment pyenv)
    {
        this.pyenv = this.pyenv;
    }
    
    public PythonEnvironment getPythonEnvironment()
    {
        return pyenv;
    }
    
    public void runScript(PythonScript ps, PythonListener listener) throws Exception
    {
        
    }
}
