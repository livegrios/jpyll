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
 *  Artifact:   Parameter.java
 *  Version :   0.1
 *  Date    :   2024-06-05 12:00:00
 *  Author  :   Miguel Angel Gil Rios
 *  Email   :   angel.grios@gmail.com
 */
package org.livegrios.jpyll.model;

/**
 * This class abstracts a Python script parameter information.
 * @author LiveGrios
 */
public class PythonParameter
{
    public static enum Type
    {
        Int,
        Float,
        String,
        Boolean
    }
        
    String name;
    Type type;
    Object value;
    
    /**
     * The default constructor.
     */
    public PythonParameter(){}

    /**
     * Create a <code>Parameter</code> object specifying its <i>name</i> and
     * <i>type</i>.
     * @param name  The name of the parameter.
     * @param type  The type of the parameter which is represented by an
     *              enumerated type which covers the basic data types such as
     *              integer (Type.Int), double precision (Type.Float), 
     *              string (Type.String) and boolean (Type.Boolean).
     */
    public PythonParameter(String name, Type type)
    {
        this.name = name;
        this.type = type;
    }
    
    /**
     * Create a <code>Parameter</code> object specifying its <i>name</i>,
     * <i>type</i> and <i>value</i>.
     * @param name  The name of the parameter.
     * @param type  The type of the parameter which is represented by an
     *              enumerated type which covers the basic data types such as
     *              integer (Type.Int), double precision (Type.Float), 
     *              string (Type.String) and boolean (Type.Boolean).
     * @param value The value of the parameter which can be of any common type
     *              such as numbers (integer and floating point), String and
     *              boolean.
     */
    public PythonParameter(String name, Type type, Object value)
    {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    /**
     * Return the parameter name.
     * @return The parameter name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the parameter name.
     * @param name The parameter name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Return the parameter type.
     * @return The paramater type.
     */
    public Type getType()
    {
        return type;
    }

    /**
     * Set the parameter type.
     * @param type 
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * Return the parameter value.
     * @return The paraneter value.
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Set the parameter value.
     * @param value The parameter value.
     */
    public void setValue(Object value)
    {
        this.value = value;
    }
    
    
}
