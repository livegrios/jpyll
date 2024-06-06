# jpyll
### Java-Python Linker Library

The library is aimed to reduce the effort to integrate the Python environment to
the Java ecosystem.

With this library you will able to call your Python scripts from your Java apps
and retrieve all the messages that are produced by your Python program.

### Example 1 [Quick Guide]
#### 1. Your Python Environment
Consider that you have your standard Python environment installed at: ```C:\Python311```
#### 2. Your Python Script
For instance, consider that you have a Python script called  ```print_numbers.py```
which is intented to print the first 10 numbers starting at 1:
```python
# print_numbers.py

for i in range(1, 11):
    print(i)
```
And imagine that your python file is stored in ```C:/Users/Markdown/print_numbers.py```.

From you Java app, you can invoke the Python environment to execute your script and
print the outputs produced by Python in whis way:
```java
public class Test
{
    public static void main(String[] args)
    {
        try
        {
            PythonEnvironment pyenv = new PythonEnvironment("C:/Python311_Dist01",
                                                            "C:/Python311_Dist01/python.exe");
            PythonScript ps = new PythonScript("C:/Users/Markdown/print_numbers.py", "", "");
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
                    System.out.println(""Python Process Ended.");
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
        catch (Exception ex
        {
            ex.printStackTrace();
        }
    }
}
```
#### JSON Alternative
As an alternate way, jpyll is able to configure the Python environment and script
information from JSON files as follows.
##### Python Environment file 
Suppose that you have your configuration in path ```C:/Users/Markdown/Documents/python_env.json```
with the next content:
```json
{
    "pythonPath"    :  "C:/Python311_Dist01",
    "pythonBinPath" :  "C:/Python311_Dist01/python.exe"
}
``` 
You can create the ```PythonEnvironment``` object as: ```PythonEnvironment pyenv = PythonEnvironment.fromFile("C:/Users/Markdown/Documents/python_env.json");```
