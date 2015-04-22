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
public class Antenna {
 
    //private int trainID;
    //private int blockNumber;
    //private double distance;
    private double authority1;
    private double authority2;
    private double speed1;
    private double speed2;
    private boolean signalAvailable;
    
    public Antenna()
    {
        this.signalAvailable = false;
    }
    
    public void setSignalAvailable(boolean isAvailable)
    {
        this.signalAvailable = isAvailable;
    }
    
   /* public void setTrainID(int trainID)
    {
        this.trainID = trainID;
    }*/
    
    public void setSpeed1(float speed)
    {
        this.speed1 = speed;
    }
    
    public void setSpeed2(float speed)
    {
        this.speed2 = speed;
    }
    
    public void setAuthority1(float auth)
    {
        this.authority1 = auth;
    }
    
    public void setAuthority2(float auth)
    {
        this.authority2 = auth;
    }
    /*
    public void setBlockNum(int blockNum)
    {
        this.blockNumber = blockNum;
    }
    
    public void setDistance(double distance)
    {
        this.distance = distance;
    }
    
    public int getTrainID()
    {
        return trainID;
    }
    */
    public double getSpeed()
    {
        double speed;
        if(this.speed1 == this.speed2)
        {
            speed = speed1;
        }
        else
        {
            speed = -1;
        }
        return speed;
    }
    
    public double getAuthority()
    {
        double authority;
        if(this.authority1 == this.authority2)
        {
            authority = this.authority1;
        }
        else 
        {
            authority = -1;
        }
        return authority;
    }
    
    public boolean isSignalAvailable()
    {
        return this.signalAvailable;
    }
    
    /*public int getBlockNum()
    {
        return blockNumber;
    }
    
    public double getDistance()
    {
        return distance;
    }*/
    
}
