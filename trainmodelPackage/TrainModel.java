
package trainmodelPackage;

import java.lang.Math;
import java.util.*;
import java.util.logging.*;

public class TrainModel
{
	private final static Logger L = Logger.getLogger("train log");  
	Level logLevel = Level.ALL;
	private static final int GET_NEXT = 0;
	private static final int GET_PREV = 1;
	private static final int RED_LINE = 1;
	private static final int GREEN_LINE = 2;
	private Car trainCar;
	public Antenna antenna;
	//private MBO theMBO;
	public TrainController trainController;
        //public Info info;
	//Physical Limits on Train Movement
	private float speedLimit; //physical limit in kilometers/hour
	private float accelerationLimit;  //physical limit in kilometers/hour
	private float decelerationLimit;  //physical limit in kilometers/hour
	//Attributes
	private int trainID;
	private float speed; //the current speed in kilometers/hour
	private float power; //in watts
	private float powerMax;  //physical limit, in watts
	private float acceleration; //in meter/second^2
	private double gradient; //in percentage, from -1 to 0 to 1 where 0 is flat and 1 is vertical  
	private float totalMass; //in tons
	//private int numberOfCars;
	private int totalPassengers;
	private double distanceTravelled; //in kilometers, distance within the current block
	private float trackFriction;
	private boolean brake;
	private boolean emergencyBrake;
	private boolean engineFailure = false;
	private boolean brakeFailure = false;
	private Block currentBlock;
	private int blockNumber;
	private double blockLength;
	private int direction = 0; //0 -> getNext, 1 -> getPrevious 
	private final int firstRedBlock = 9;
	private final int firstGreenBlock = 62;
	private String stationName;
	private HashMap<Integer, Block> theLine;
	private int trainLineNum; //1 -> red, 2 -> green
	public float temperature;
        private double CTC_Authority=0;
        private double CTC_Speed_Limit=0;
        //private static double MBO_Authority=0;
        //private static double MBO_Speed_Limit=0;
        
	public TrainModel(int trainID)
	{
            //Called by CTC, who passes the train ID
            this.trainController = new TrainController();
            this.trainController.setTrain(this);
            this.trainID = trainID;
            this.trainCar = new Car();
            this.antenna = new Antenna();
            this.init();
	}

	private void init()
	{
            //initializes all necessary values
            this.speed = 0;
            this.trainID = 0;
            this.speedLimit = 70;
            this.totalMass = (float) 40.9;
            this.gradient = 0.0;
            this.brake = false;
            this.emergencyBrake = false;
            this.accelerationLimit = (float) 0.65;
            this.decelerationLimit = (float) -0.65;
            this.power = 0;
            this.powerMax = 120.0f;
            this.distanceTravelled = 0;
            this.temperature = (float) 70.0;
            this.trackFriction = (float) 0.15;
            //this.info = new Info();

            L.setLevel(logLevel);
	}

	public TrainModel()
	{
            //default constructor (for testing)
            this.speed = 0;
            this.trainID = 0;
            this.speedLimit = 70;
            this.totalMass = (float) 40.9;
            this.gradient = 0.0;
            this.temperature = (float) 70.0;
            this.brake = false;
            this.emergencyBrake = false;
            this.accelerationLimit = (float) 0.65;
            this.decelerationLimit = (float) -0.65;
            this.power = 0;
            this.distanceTravelled = 0;
            this.trainCar = new Car();
            this.antenna = new Antenna();
            this.init();
	}

	public void update(int timeStep)
	{
            //called every clock iteration
            //updates the physics for the train (many times within one 
            //tick of the global clock)
            int numCalcTimes = 35;
            double ts = (double) timeStep/numCalcTimes;
            /*Info tempInfo = this.currentBlock.getInfo();
            System.out.print(this.currentBlock.blockID+ " "+tempInfo);
            if(tempInfo != null)
            {
                this.info = tempInfo;
            }*/
            for (int ii = 0; ii < numCalcTimes; ii++)
            {
                this.trainController.tick(ts);
                this.updatePhyicsState((float) ts);
            }
            //L.info(this + "\n " + this.trainController + "\n" + this.currentBlock);
            L.info(this.trainController.toString());
            //System.out.println(this);
            //System.out.println(this.trainController);
            //System.out.println(this.distanceTravelled);
            //System.out.println("update is called2");
	}

	public void setLine(HashMap<Integer, Block> theMap, int line)
	{
            //Called by CTC to set the train line
            this.trainLineNum = line;
            this.theLine = theMap;
            //make sure the line is either Red (1) or Green (2)
            //Change this if more lines are added in the future
            assert (trainLineNum == RED_LINE || trainLineNum == GREEN_LINE);
            if (trainLineNum == RED_LINE)
            {
                this.currentBlock = this.theLine.get(firstRedBlock);
                this.blockNumber = firstRedBlock;
            } 
            else if (trainLineNum == GREEN_LINE)
            {
                this.currentBlock = this.theLine.get(firstGreenBlock);
                this.blockNumber = firstGreenBlock;
            } 
            else
            {
                //default train line
                this.trainLineNum = RED_LINE;
            }
            this.blockLength = this.currentBlock.getBlockLength();
            this.gradient = this.currentBlock.getGrade();
            this.currentBlock.changeOccupancy(true);
	}

        //The section below contains all of the 
        //accessors for the data of the train model
        //----------V-ACCESSORS-V--------------
	public float getSpeedMax()
	{
            return this.speedLimit;
	}
        
        public float getAcceleration()
	{
            return acceleration;
	}
        
        public float getAccelerationMax()
	{
            return this.accelerationLimit;
	}
        
        public float getDecelerationLimit()
	{
            return this.decelerationLimit;
	}

	public float getTotalMass()
	{
            return totalMass;
	}

        public int getPassengers()
	{
            return this.totalPassengers;
	}

        public double getGradient()
	{
            if (this.currentBlock != null)
	    {
                //gradient is defined in the block between -100 and 100
                //so we divide by 100 to make it between -1 and 1
                //for calculation purposes
	        return this.currentBlock.getGrade() / 100;
	    } 
            else
	    {
	        return 0.0;
	    }
	}

	public float getPowerMax()
	{
            return powerMax;
	}

	public float getPower()
	{
            return power;
	}

	public float getSpeed()
	{
            return speed;
	}

	public float getTemperature()
	{
            return this.trainCar.getTemperature();
	}

	public int getTrainID()
	{
            return trainID;
	}

	public Block getCurrentBlock()
	{
            return this.currentBlock;
	}

	public int getCurBlock()
	{
            return this.blockNumber;
	}

	public int getLine()
	{
            return trainLineNum;
	}

	public boolean isLightCondition()
	{
            return trainCar.isLightCondition();
	}

	public boolean isDoorCondition()
	{
            return this.trainCar.isDoorCondition();
	}

	public boolean isEngineFailure()
	{
            return this.engineFailure;
	}
        
	public boolean isBrakeFailure()
	{
            return this.brakeFailure;
	}
        
        public String getStation()
        {
            return this.stationName;
        }
        
        public float getTrackFriction()
	{
            return trackFriction;
	}
        
	public double getDistanceTravelled()
	{
            return this.distanceTravelled;
	}

	public boolean isBrake()
	{
            return this.brake;
	}

	public boolean isEmergencyBrake()
	{
            return this.emergencyBrake;
	}
        
	public double getAuthorityFromMBO()
	{
            //return the authority set by MBO
            //-1 is returned if no MBO signal is available 
            if(this.antenna.isSignalAvailable())
            {
                //System.out.print(", MBO Authority="+this.antenna.getAuthority() );
                return this.antenna.getAuthority();
            }
            else 
            {
                return -1; 
            }
	}

	public double getSpeedLimitFromMBO()
	{       
            //return the speedlimit set by MBO
            //-1 is returned if no MBO signal is available 
            if(this.antenna.isSignalAvailable())
            {
                return this.antenna.getSpeed();
            }
            else
            {
                return -1;
            }
	}
/*
	public double getAuthorityFromCTC()
	{       
                //get and return authority if there is one
                //if not, negative one is returned to signify 
                //that no authority was given
                try{
                   this.CTC_Authority= this.info.ctcAuthority;
                   return this.CTC_Authority;
                }
                catch(NullPointerException e){
                    return -1;
                }
	}

	public double getSpeedLimitFromCTC()
	{
                //get and return speedlimit if there is one
                //if not, negative one is returned to signify 
                //that no speedlimit was given
                try{
                    this.CTC_Speed_Limit= this.info.ctcSpeed;
                    return this.CTC_Speed_Limit;
                }
                catch(NullPointerException e){
                    return -1;
                }
	}
*/
       /* public Info getInfoBlock()
        {
            //used to send the info block to train controller
            //so it can get CTC speed and authority
            getInfoFromCTC();
            return this.info;
        }
        */
        public void getInfoFromCTC()
	{
            //this updates the info block to reflect CTC's changes
            //if the block is null, just use the current block
            /*Info tempInfo = this.currentBlock.getInfo();
            if(tempInfo != null)
            {
                this.info = tempInfo;
            }*/
	}
        //---------^-ACCESSORS-^-------------
        
        //the section below contains all of 
        //the setters for the train model
        //----------v-SETTERS-v--------------
	public void setSpeedMax(float speedMax)
	{
            this.speedLimit = speedMax;
	}

	public void setAcceleration(float acceleration)
	{
            this.acceleration = acceleration;
	}

	public void setTotalMass(float totalMass)
	{
            this.totalMass = totalMass;
	}

	public void setPassengers(int passengers)
	{
            //the overall weight is affected by the passenger weight
            //so the weight must be recalculated
            int passDiff = passengers - this.totalPassengers;
            float weightDiff = (float) (passDiff * (0.0005));
            this.totalMass += weightDiff;
            this.totalPassengers = passengers;
	}
/*
	public void setPowerMax(float powerMax)
	{
            this.powerMax = powerMax;
	}
*/
	public void setPower(float power)
	{
            this.power = power;
	}

	public void setTrainID(int trainID)
	{
            this.trainID = trainID;
	}

	public void setTemperature(float temperature)
	{
            this.trainCar.setTemperature(temperature);
	}

	public void setDoorCondition(boolean doorCondition)
	{
            this.trainCar.setDoorCondition(doorCondition);
	}

	public void setLightCondition(boolean lightCondition)
	{
            trainCar.setLightCondition(lightCondition);
	}

	public void setEngineFailure(boolean isFailed)
	{
            this.engineFailure = isFailed;
	}

        public void setBrakeFailure(boolean isFailed)
	{
            this.brakeFailure = isFailed;
	}
        
	public void setTrackFriction(float trackFriction)
	{
            this.trackFriction = trackFriction;
	}
        
        //this method only for train model UI
        public void setGradient(double grad)
        {
            this.gradient = grad;
        }
        
	public void brake(boolean theBrake)
	{
            //if no brake failure, turn on the brake
            if(!isBrakeFailure())
            {
		this.brake = theBrake;
            }
            else
            {
                this.brake = false;
            }
	}

	public void setEmergencyBrake(boolean eBrake)
	{
            this.emergencyBrake = eBrake;
	}
        //----------^-SETTERS-^--------------
        	
        //this method updates the speed of the train
	public void updatePhyicsState(float sampleTimeInterval)
	{
            //check for failures
            if(isBrakeFailure()) 
            {
                this.setPower(0);
                this.brake(false);
            }
            if(isEngineFailure())
            {
                this.setPower(0);
            }
            //calculate acceleration
            float force = (float) (power / (speed / 3.6 + 0.1));
            double gg = getGradient();
            float gradient_deceleration = (float) (gg / Math.sqrt(gg * gg + 1) * 9.8);
            acceleration = force / totalMass - gradient_deceleration - getTrackFriction();
            acceleration = Math.min(acceleration, getAccelerationMax());
            //adjust for brake and limits
            if (acceleration < 0)
            {
                acceleration = Math.max(acceleration, getDecelerationLimit());
            }
            if (isBrake() && !isBrakeFailure())
            {
                acceleration -= 0.5;
            }
            if (isEmergencyBrake() || isEngineFailure() || isBrakeFailure())
            {
                acceleration -= 2.7;
            }
            //calculate new speed 
            speed += acceleration * sampleTimeInterval * 3.6 / 1000;
            // 0 <= speed <= speed limit
            speed = Math.min(getSpeedMax(), Math.max(0, speed));
            //update distance travelled
            this.distanceTravelled += speed * sampleTimeInterval / 1000.0f;
            //check if the train is in a new block
            checkForNewBlock();
	}

	private void checkForNewBlock()
	{
            //if we have travelled outside the block, we have to switch blocks
            if ((this.distanceTravelled) >= this.blockLength)
            {
                if (this.currentBlock == null)
                {
                    return;
                }
                //account for distance which the train may have 
                //travelled over the block length
                this.distanceTravelled = this.distanceTravelled - this.blockLength;
                this.currentBlock.changeOccupancy(false);
                //HERE WE ARE SWITCHING BLOCKS-----------------------
                this.getNextBlock();
                //---------------------------------------------------
                this.currentBlock.changeOccupancy(true);
                //if there is a transponder, get the station name
                if (this.currentBlock.hasTransponder())
                {
                    //this.stationName = this.currentBlock.getTransponder().getNextStation();
                } else
                {
                    //set to null to indicate no station in block
                    this.stationName = null;
                }
                this.gradient = this.currentBlock.getGrade();
                this.blockNumber = this.currentBlock.getBlockID();
            }
        }
        
        private float toDegrees(float radian)
	{
		return (float) (radian / Math.PI * 180);
	}
        
        private void getNextBlock()
        {
            //determines the next block based on track direction and switches
            //System.out.println("train line " + trainLineNum);
            if (this.trainLineNum == RED_LINE)
            {
                if (direction == GET_NEXT)
                {
                    //use getNext()
                    if (currentBlock.getBranchDirection() 
                            && blockNumber != 32
                            && blockNumber != 43)
                    {
                        //System.out.print("HERE!!!!!"+currentBlock.blockID);
                        currentBlock = currentBlock.getOptionalNextBlock();
                        blockNumber = currentBlock.getBlockID();
                        if (blockNumber == 66) 
                        {
                            direction = GET_PREV;
                        }
                    } 
                    else
                    {
                        //System.out.println("Called");
                        if (blockNumber == 66)
                        {
                            direction = GET_PREV;
                        }
                        currentBlock = currentBlock.getNextBlock();
                        blockNumber = currentBlock.getBlockID();
                    }
                } 
                else if (direction == GET_PREV)
                {
                    //use getPrevious()
                    if (currentBlock.getBranchDirection() 
                        && blockNumber != 9
                        && blockNumber != 27
                        && blockNumber != 38
                        && blockNumber != 52
                        && blockNumber != 15)
                    {
                        currentBlock = currentBlock.getOptionalNextBlock();
                        blockNumber = currentBlock.getBlockID();
                        if (blockNumber == 15) 
                        {
                            direction = GET_NEXT;
                        }
                    } 
                    else
                    {
                        if (blockNumber == 15) 
                        {
                            direction = GET_NEXT;
                        }
                        currentBlock = currentBlock.getPreviousBlock();
                        blockNumber = currentBlock.getBlockID();
                    }
                }
            } 
            else if (trainLineNum == GREEN_LINE)
            {
                if (direction == GET_NEXT)
                {
                    if (currentBlock.getBranchDirection())
                    {
                        currentBlock = currentBlock.getOptionalNextBlock();
                        blockNumber = currentBlock.getBlockID();
                        if (blockNumber == 100)
                        {
                            direction = GET_PREV;
                        }
                    }
                    else
                    {
                        if (blockNumber == 100 || blockNumber == 1)
                        {
                            direction = GET_PREV;
                        }
                        currentBlock = currentBlock.getNextBlock();
                        blockNumber = currentBlock.getBlockID();
                    }
                } 
                else if (direction == GET_PREV)
                {
                    if (currentBlock.getBranchDirection())
                    {
                        currentBlock = currentBlock.getOptionalNextBlock();
                        blockNumber = currentBlock.getBlockID();
                        if (blockNumber == 101)
                        {
                            direction = GET_NEXT;
                        }
                    } 
                    else
                    {
                        if (blockNumber == 77)
                        {
                            direction = GET_NEXT;
                            currentBlock = currentBlock.getOptionalNextBlock();
                        }
                        else
                        {
                            if (blockNumber == 27)
                            {
                                direction = GET_NEXT;
                            }
                            currentBlock = currentBlock.getPreviousBlock();
                            blockNumber = currentBlock.getBlockID();
                        }
                    }
                }
            }
	}


	/*@Override
	 public String toString()
	 {
	 return "TrainModel{" + "trainID=" + trainID + ", speed=" + speed  + ", power=" + power + ", powerMax=" + powerMax + ", acceleration=" + acceleration + ", temperature=" + temperature + ", totalMass=" + totalMass + ", gradient=" + gradient + ", doorCondition=" + doorCondition + ", lightCondition=" + lightCondition + '}';
	 }*/

	/*setAccelerationc public void simple_unit_test()
	 {
	 System.out.println("simplie train unit test");
	 TrainModel tr = new TrainModel(47, 100, 100, 100);
	 tr.setPower(100);

	 for (int ii = 0; ii < 100; ii++)
	 {][
	 System.out.println("train speed " + tr.getSpeed());
	 tr.updatePhyicsState((float) 1.0);
	 }
	 }*/
	@Override
	public String toString()
	{
		return "TrainModel{" + "speed=" + speed + ", power=" + power + ", acceleration=" + acceleration + ", gradient=" + gradient + ", totalMass=" + totalMass + "speedMax = " + speedLimit + " power max " + powerMax + '}';
	}
}
