/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package trainmodelPackage;

/**
 *
 * @author Zach
 */
public class Car {
    
    private float length; //in meters
    private float width; //in meters
    private float height; //in meters
    private float temperature; //in degree farenheight
    
    private Lights theLights;
    private Doors theDoors;
    
    public Car()
    {
        this.length = (float) 32.2;
        this.width = (float) 2.65;
        this.height = (float) 3.42;
        this.temperature = (float) 70.0;
        
        this.theLights = new Lights();
        this.theDoors = new Doors();
    }
    
    public boolean isDoorCondition()
    {
    	return this.theDoors.isDoorCondition();
    }

    public void setDoorCondition(boolean doorCondition)
    {
        this.theDoors.setDoorCondition(doorCondition);
    }

    public boolean isLightCondition()
    {
    	return this.theLights.isLightCondition();
    }

    public void setLightCondition(boolean lightCondition)
    {
        this.theLights.setLightCondition(lightCondition);
    }
        
    public float getTemperature()
    {
    	return temperature;
    }

    public void setTemperature(float temperature)
    {
    	this.temperature = temperature;
    }
        
}
