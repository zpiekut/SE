/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package traincontrollersubsystem;

/**
 *
 * @author Junyang Huang
 */
public class TrainControllerModel
{
	private TrainModel train;

	public TrainControllerModel(TrainModel train)
	{
		this.train = train;
	}

	public TrainControllerModel()
	{
		this.train = new TrainModel(47, 100, 1000, 10000);
	}
	
	
	public TrainModel getTrain()
	{
		return train;
	}

	public void setTrain(TrainModel train)
	{
		this.train = train;
	}

	public float getSpeed()
	{
		return train.getSpeed();
	}

	public float getSpeedMax()
	{
		return train.getSpeedMax();
	}

	public float getAcceleration()
	{
		return train.getAcceleration();
	}

	public float getTotalMass()
	{
		return train.getTotalMass();
	}

	public double getGradient()
	{
		return train.getGradient();
	}

	public float getPowerMax()
	{
		return train.getPowerMax();
	}

	public float getPower()
	{
		return train.getPower();
	}

	public void setPower(float power)
	{
		train.setPower(power);
	}

	public int getTrainID()
	{
		return train.getTrainID();
	}

	public float getTemperature()
	{
		return train.getTemperature();
	}

	public void setTemperature(float temp)
	{
		train.setTemperature(temp);
	}

	public boolean isDoorCondition()
	{
		return train.isDoorCondition();
	}

	public void setDoorCondition(boolean doorCondition)
	{
		train.setDoorCondition(doorCondition);
	}

	public boolean isLightCondition()
	{
		return train.isLightCondition();
	}

	public void setLightCondition(boolean lightCondition)
	{
		train.setLightCondition(lightCondition);
	}
}
