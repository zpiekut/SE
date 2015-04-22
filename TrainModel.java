/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package traincontrollersubsystem;

import java.lang.Math;

public class TrainModel
{
	//Physical Limits on Train Movement
	private float speedLimit = 70; //physical limit in kilometers/hour
	private float accelerationLimit;  //physical limit in kilometers/hour
	private float decelerationLimit;  //physical limit in kilometers/hour

	//Attributes
	private int trainID;
	private float speed; //the current speed in kilometers/hour
	private float temperature; //in degree farenheight
	private float power; //in watts
	private float powerMax;  //physical limit, in watts
	private float acceleration; //in meter/second^2
	private double gradient; //in percentage, from -1 to 0 to 1 where 0 is flat and 1 is vertical  
	//on -> true   off -> false
	private boolean doorCondition;
	//on -> true  off -> false
	private boolean lightCondition;
	private float totalMass; //in kilograms
	private int numberOfCars; 
	private int totalPassengers;
	private float distanceTraveled;
	private float trackFriction;
	private boolean brake;
	private boolean emergencyBrake;

	//private LinkedBlock lb;
	//private Block currentBlock;
	private int blockNumber;
	private int trainLine; //1 = red, 2 = green
	private final int firstRedBlock = 9;
	private final int firstGreenBlock = 62;

	public TrainModel(int trainID, float speedMax, float powerMax, float totalMass, int theTrainLine)
	{
		this.trainLine = theTrainLine;
		this.trainID = trainID;
		this.speedLimit = speedMax;
		this.powerMax = powerMax;
		this.totalMass = totalMass;
		this.gradient = 0.0;
		this.temperature = (float) 70.0;
		this.brake = false;
		this.emergencyBrake = false;
		this.accelerationLimit = (float) 0.65;

		/*
		lb = new LinkedBlock();

		if(this.trainLine == 1) 
		{
			currentBlock = lb.getBlockInfo(firstRedBlock);
		}
		else if(this.trainLine == 2)
		{
			currentBlock = lb.getBlockInfo(firstGreenBlock);
		}
		else
		{
			//error, invalid train line 
		}		
		blockNumber =  currentBlock.getBlockNumber();
		lb.updateTrainLocation(blockNumber);*/
	}

	public TrainModel()
	{
		this.speed = 0;
		this.doorCondition = true;
		this.lightCondition = true;
		this.trainID = 0;

		this.speedLimit = 70;
		this.totalMass = 40.9;
		this.gradient = 0.0;
		this.temperature = (float) 70.0;
		this.brake = false;
		this.emergencyBrake = false;
		this.accelerationLimit = (float) 0.65;
	}

	public float getSpeedMax()
	{
		return this.speedLimit;
	}

	public void setSpeedMax(float speedMax)
	{
		this.speedLimit = speedMax;
	}

	public float getAcceleration()
	{
		return acceleration;
	}

	public void setAcceleration(float acceleration)
	{
		this.acceleration = acceleration;
	}

	public float getAccelerationMax()
	{
		return this.accelerationLimit;
	}

	public float getTotalMass()
	{
		return totalMass;
	}

	public void setTotalMass(float totalMass)
	{
		this.totalMass = totalMass;
	}

	public double getGradient()
	{
		return gradient;
	}

	public void setGradient(float gradient)
	{
		this.gradient = gradient;
	}

	public float getPowerMax()
	{
		return powerMax;
	}

	public void setPowerMax(float powerMax)
	{
		this.powerMax = powerMax;
	}

	public float getPower()
	{
		return power;
	}

	public void setPower(float power)
	{
		this.power = power;
	}

	public int getTrainID()
	{
		return trainID;
	}

	public void setTrainID(int trainID)
	{
		this.trainID = trainID;
	}

	public float getSpeed()
	{
		return speed;
	}

	public float getTemperature()
	{
		return temperature;
	}

	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	}

	public boolean isDoorCondition()
	{
		return doorCondition;
	}

	public void setDoorCondition(boolean doorCondition)
	{
		this.doorCondition = doorCondition;
	}

	public boolean isLightCondition()
	{
		return lightCondition;
	}

	public void setLightCondition(boolean lightCondition)
	{
		this.lightCondition = lightCondition;
	}

	public void updatePhyicsState(float sampleTimeInterval)
	{
		float natural_friction = (float) 0.2;
		float force = (float) (power / (speed/3.6 + 0.1));
		double gg = getGradient();
		float gradient_deceleration = (float) (gg/Math.sqrt(gg*gg + 1)* 9.8);
		acceleration  = force/totalMass - gradient_deceleration - natural_friction ;
		acceleration = Math.min(acceleration, getAccelerationMax());
		//System.out.println(acceleration);
		
		if (isBrake())
		{
			acceleration -= 0.5;
		}
		if(isEmergencyBrake())
		{
			acceleration -= 2.7;
		}
		
		speed += acceleration * sampleTimeInterval*3.6/1000;

		//speed can be bigger than 0.0
		//smaller than speed limit
		speed = Math.min(getSpeedMax(),Math.max(0, speed));
	}

	public boolean isBrake()
	{
		return this.brake;
	}

	public boolean isEmergencyBrake()
	{
		return this.emergencyBrake;
	}

	private float toDegrees(float radian)
	{
		return (float) (radian / Math.PI * 180);
	}

	public void brake()
	{
	}

	private void emergencyBrake()
	{
	}

	public boolean isMBOSignalAvailable()
	{
		return false;
	}

	public boolean isTrackSignalAvailable()
	{
		return false;
	}

	public float getAuthorityFromTrack()
	{
		isTrackSignalAvailable();
		return 0;
	}

	public float getAuthorityFromMBO()
	{
		isMBOSignalAvailable();
		return 0;
	}

	public float getSpeedLimitFromMBO()
	{
		isMBOSignalAvailable();
		return 0;
	}

	public float getSpeedLimitFromTrack()
	{
		isTrackSignalAvailable();
		return 0;
	}

	public float[] getGPSPosition()
	{
		float[] GPS = {10,10};
		return GPS;
	}

	public float getTrackFriction()
	{
		return trackFriction;
	}

	public void setTrackFriction(float trackFriction)
	{
		this.trackFriction = trackFriction;
	}

	@Override
	public String toString()
	{
		return "TrainModel{" + "trainID=" + trainID + ", speed=" + speed  + ", power=" + power + ", powerMax=" + powerMax + ", acceleration=" + acceleration + ", temperature=" + temperature + ", totalMass=" + totalMass + ", gradient=" + gradient + ", doorCondition=" + doorCondition + ", lightCondition=" + lightCondition + '}';
	}

	/*setAccelerationc public void simple_unit_test()
	{
		System.out.println("simplie train unit test");
		TrainModel tr = new TrainModel(47, 100, 100, 100);
		tr.setPower(100);

		for (int ii = 0; ii < 100; ii++)
		{
			System.out.println("train speed " + tr.getSpeed());
			tr.updatePhyicsState((float) 1.0);
		}
	}*/
}
