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
public class Doors {
    
    //open> true   closed -> false
    private boolean doorCondition;
    
    public Doors()
    {
        this.doorCondition = false;
    }
    
    public Doors(boolean doorCondition)
    {
        this.doorCondition = doorCondition;
    }
    
    public boolean isDoorCondition()
    {
    	return this.doorCondition;
    }

    public void setDoorCondition(boolean doorCondition)
    {
        this.doorCondition = doorCondition;
    }
    
}
