package trainmodelPackage;
/**
 *
 * @author Junyang huang
 */
public class TrainController {

    TrainModel train;
    public double setPointSpeed;
    public double speedLimit;
    public double authority;

	//refer the coe1186 train control pdf
	
	//four constant, need to tune up manually
    private final double INITIAL_INTEGRAL = 0.0f;		
    private final double INITIAL_ERROR = 0.0f;	
	
    private final double PROPORTIONAL_GAIN = 0.3f;	
    private final double INTEGRAL_GAIN = 0.5f;	
	
    private double previous_integral = INITIAL_INTEGRAL;		
    private double previous_error = INITIAL_ERROR;				

	//will be removed after system integration
    public final boolean STAND_ALONE_TESTING = true;
    
    public TrainController() {
        this.authority = 1000;
        this.setPointSpeed = 100;
        this.speedLimit = 100;
    }

	/*
	 * set train
	 */
    public void setTrain(TrainModel train) {
        this.train = train;
    }

	/*
	 * get speed limit
	 */
    public double getSpeedLimit() {
        return speedLimit;
    }

	/*
	 * set speed limit
	 */
    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

	/*
	 * get set point speed
	 */
    public double getSetPointSpeed() {
        return setPointSpeed;
    }

	/*
	 * set setpoint speed
	 */
    public void setSetPointSpeed(double setPointSpeed) {
        if (setPointSpeed > getSpeedLimit()) {
            this.setPointSpeed = getSpeedLimit();
        } else {
            this.setPointSpeed = setPointSpeed;
        }
    }

	/*
	 * set air condition
	 * not too hot not too cold
	 */
    public void setTemperature(double temp) {
        if (temp < 80 && temp > 60) {
            train.setTemperature((float) temp);
        }
    }

	/*
	 * get authority 
	 */
    public double getAuthority() {
        return authority;
    }

	/*
	 * set authority
	 */
    public void setAuthority(double authority) {
        this.authority = authority;
    }



	/*
	 * calculate the next power by control theory
	 */
    private double calculateNextPower(double millSecond) 
    {
		//simply applied the formula from, page 4, train control pdf
        double temp_integral;  
        double velocityError;    
        double nextPower;
      
        velocityError = getSetPointSpeed() - train.getSpeed();

        temp_integral = previous_integral + (millSecond / 2f) * (velocityError + previous_error);
        nextPower = (INTEGRAL_GAIN * temp_integral)   + (PROPORTIONAL_GAIN * velocityError);

        if (nextPower > train.getPowerMax()) //the power of train has a physical limit
		{
            temp_integral = previous_integral;
            nextPower = train.getPowerMax();
        } else if (nextPower <= 0.0f) { //power can not be smaller 0.0
            temp_integral = previous_integral;
            nextPower = 0.0f;
        }
		
        previous_integral = temp_integral;
        previous_error = velocityError;

        return nextPower;
    }

    /**
     * check door 
	 * close door during running
     */
    private void checkDoor() {
        if (train.getSpeed() > 0) {
            train.setDoorCondition(false);
        } else if (train.getSpeed() >= 0) {
            train.setDoorCondition(true);
        }
    }
	
	public double getSlowDownDistance()
	{
		return train.getSpeed()*train.getSpeed()/3.6/3.6/train.getTrackFriction()/2;
	}


    private void checkApproachStation() 
	{
        double distToNextStation = getDistToNextStation();

        //if distance to next station <= distance needed for a stop, pull brake
        if ( getSlowDownDistance() > distToNextStation) 
		{
            train.setPower(0);		// turn down power
        }
    }

    /*
     * update authority from track and mbo signal
	 * and react
     */
    private void checkAuth()
	{
        //update authority
        double authorityFromTrack = 100;//train.getAuthorityFromCTC();
        double authorityFromMBO = train.getAuthorityFromMBO();
        double allowedAuthority = Math.min(authorityFromMBO, authorityFromTrack);
        this.setAuthority(allowedAuthority);

		if (STAND_ALONE_TESTING)
		{
			this.setAuthority(1000);
		}
		
		
		if ( getSlowDownDistance() > getAuthority())
		{
			train.setPower(0);
		}
	    if (authority <= 0) {
			train.setEmergencyBrake(true);
        }       
    }

    /*
     * update speed limit from track and mbo signal
     */
    private void checkSpeed() {
        //update speed limit
        double speedLimitFromTrack = 60;//train.getSpeedLimitFromCTC();
        double speedLimitFromMBO = train.getSpeedLimitFromMBO();
        double allowedSpeedLimit = Math.min(speedLimitFromMBO, speedLimitFromTrack);

        this.setSpeedLimit(allowedSpeedLimit);

        if (STAND_ALONE_TESTING)
        {
            this.setSpeedLimit(100);
        }       
    }

    /**
     * get distance to next station 
     */
    public double getDistToNextStation() {
        // distance between a next block to the block before station black
        double distBetween = 0;
        double distToNextStation = 0;

        if (STAND_ALONE_TESTING) {
            return 1000;
        }

        return distToNextStation;
    }

	/*
	 * get train station info from train model
	 * now is just pesudo method
	 */
    public String getNextStation() 
	{
        return "New York City";
    }

    /*
     * the clock function
	 * update controller state and monitor anything wrong
     */
    public void tick(double time_step) {
        //coor checking
        checkDoor();

        checkSpeed();
       
        if (train.isBrake() || train.isEmergencyBrake()) {
            train.setPower(0);
        } else {
             double setPointPower = calculateNextPower(time_step);
            train.setPower((float) setPointPower);
        }

        checkApproachStation();
        checkAuth();
    }

    @Override
    public String toString() {
        return "TrainController{" + " setPointSpeed=" + setPointSpeed + ", speedLimit=" + speedLimit + ", authority=" + authority + ", integralLast=" + previous_integral + ", errorLast=" + previous_error + '}';
    }

	
	
}
