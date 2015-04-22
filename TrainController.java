/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package traincontrollersubsystem;

/**
 *
 * @author Junyang
 */
public class TrainController
{

	TrainControllerModel TM;
	public float setPointSpeed;
	public float speedLimit;
	public float authority;
	float Kp = (float) 1.0; //proportional gain
	float Ki = (float) 1.0; //integral gain

	public TrainController()
	{
		this.TM = new TrainControllerModel();
		this.authority = 0;
		this.setPointSpeed = 0;
		this.speedLimit = 0;
	}

	public float getSpeedLimit()
	{
		return speedLimit;
	}

	public void setSpeedLimit(float speedLimit)
	{
		this.speedLimit = speedLimit;
	}

	public float getAuthority()
	{
		return authority;
	}

	void setAuthority(float authority)
	{
		this.authority = authority;
	}

	private void monitorDrivingCondition()
	{
		//update speed limit
		float speedLimitFromTrack = getTrain().getSpeedLimitFromTrack();
		float speedLimitFromMBO = getTrain().getSpeedLimitFromMBO();
		float allowedSpeedLimit = Math.min(speedLimitFromMBO, speedLimitFromTrack);

		assert allowedSpeedLimit >= 0;
		this.setSpeedLimit(allowedSpeedLimit);

		//update authority
		float authorityFromTrack = getTrain().getAuthorityFromTrack();
		float authorityFromMBO = getTrain().getAuthorityFromMBO();
		float allowedAuthority = Math.min(authorityFromMBO, authorityFromTrack);

		assert allowedAuthority >= 0;
		this.setAuthority(allowedAuthority);

		float setPower = calculatePower();
		getTrain().setPower(setPower);

	}

	private float calculatePower()
	{
		float v_error = setPointSpeed - getTrain().getSpeed();

		return 1;
	}

	public TrainModel getTrain()
	{
		return this.TM.getTrain();
	}

	/**
	 * station approach check
	 */
	private void checkIfApproachingstation()
	{

		float distance = getDistToNextStation();
		// update slown down distance
		//float brakeDownDist
	}

	/**
	 * get distance to next station
	 */
	private float getDistToNextStation()
	{
		return 100;
	}

	@Override
	public String toString()
	{
		return "TrainController{" + "setPointSpeed=" + setPointSpeed + ", speedLimit=" + speedLimit + ", authority=" + authority + ", Kp=" + Kp + ", Ki=" + Ki + '}';
	}

	/**
	 * very simple unit testing
	 */
	public void simplie_unit_test() throws InterruptedException
	{
		TrainController tc = new TrainController();
		System.out.println("simplie train controller unit test");
		System.out.println("train initial condition " + getTrain().toString());


		while (true)
		{
			tc.monitorDrivingCondition();
			System.out.println("speed: " + getTrain().getSpeed() + " power:" + getTrain().getPower());
			Thread.sleep(100);
		}
	}
}
