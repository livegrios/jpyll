package org.livegrios.jpyll;

import org.livegrios.jpyll.model.PythonEnvironment;

/**
 *
 * @author LiveGrios
 */
public interface PythonListener
{
    public void setOnReady();
    public void setOnMessage(String message);
    public void setOnFinish();
}
