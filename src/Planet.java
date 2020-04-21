
public class Planet extends Particle {

private double atmosphereRadius;
private double gravitationalConstant = 6.67384 * 1E-11;
private double atmosphereDensity;


public double getAtmosphereDensity(double height) {
	
	return 1;
	/*
	if (height <= radius+atmosphereRadius) {
		return 0.1*Math.pow(((Math.abs(height-radius))/atmosphereRadius),2);
	}else{
		return 0.0;
	}
	
	*/
}


public double getAtmosphereRadius() {
	return atmosphereRadius;
}

public void setAtmosphereRadius(double atmosphereRadius) {
	this.atmosphereRadius = atmosphereRadius;
}

public double getGravitationalConstant() {
	return gravitationalConstant;
}

public void setGravitationalConstant(double gravitationalConstant) {
	this.gravitationalConstant = gravitationalConstant;
}


	
}
