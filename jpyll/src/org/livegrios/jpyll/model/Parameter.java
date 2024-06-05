package org.livegrios.jpyll.model;

public class Parameter
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
    
    public Parameter(){}

    public Parameter(String name, Type type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
    
    
}
