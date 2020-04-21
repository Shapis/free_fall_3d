
public class Particle {

	private double mass; //  KG



	private double vX; // Meters per sec.
	private double vY;
	private double vZ;

	private double xX; // Meters.
	private double xY;
	private double xZ;

	private double spinX; // Rad per sec.
	private double spinY;
	private double spinZ;

	private double radius; // Meters

	public Particle(){

	}

	public Particle(double mass , double radius, double vX, double vY, double vZ, double xX, double xY, double xZ){
		this.mass = mass;
		this.radius = radius;
		this.vX = vX;
		this.vY = vY;
		this.vZ = vZ;
		this.xX = xX;
		this.xY = xY;
		this.xZ = xZ;
	}

	public  Particle(double mass , double radius, double vX, double vY, double vZ, double xX, double xY, double xZ, double spinX, double spinY, double spinZ  ){

		this.mass = mass;
		this.radius = radius;
		this.vX = vX;
		this.vY = vY;
		this.vZ = vZ;
		this.xX = xX;
		this.xY = xY;
		this.xZ = xZ;
		this.spinX = spinX;
		this.spinY = spinY;
		this.spinZ = spinZ;

	}
	
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	

	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getvX() {
		return vX;
	}
	public void setvX(double vX) {
		this.vX = vX;
	}
	public double getvY() {
		return vY;
	}
	public void setvY(double vY) {
		this.vY = vY;
	}
	public double getvZ() {
		return vZ;
	}
	public void setvZ(double vZ) {
		this.vZ = vZ;
	}
	public double getxX() {
		return xX;
	}
	public void setxX(double xX) {
		this.xX = xX;
	}
	public double getxY() {
		return xY;
	}
	public void setxY(double xY) {
		this.xY = xY;
	}
	public double getxZ() {
		return xZ;
	}
	public void setxZ(double xZ) {
		this.xZ = xZ;
	}
	public double getSpinX() {
		return spinX;
	}
	public void setSpinX(double spinX) {
		this.spinX = spinX;
	}
	public double getSpinY() {
		return spinY;
	}
	public void setSpinY(double spinY) {
		this.spinY = spinY;
	}
	public double getSpinZ() {
		return spinZ;
	}
	public void setSpinZ(double spinZ) {
		this.spinZ = spinZ;
	}





}
