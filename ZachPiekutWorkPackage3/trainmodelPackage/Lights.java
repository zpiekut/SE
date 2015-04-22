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
public class Lights {
    
    //on -> true  off -> false
    private boolean lightCondition;

    public Lights(boolean lightCondition)
    {
        this.lightCondition = lightCondition;
    }
    
    public Lights()
    {
        this.lightCondition = false;
    }
    
    public boolean isLightCondition()
    {
    	return this.lightCondition;
    }

    public void setLightCondition(boolean lightCondition)
    {
        this.lightCondition = lightCondition;
    }
    
}

