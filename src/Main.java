import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		

		
		// Minhas constantes.
		double massaDaTerra = 5.98*1E24;
		double raioDaTerra = 6.38*1E6;
		double deltaTime = 0.1;
		// Fim das minhas constantes.

		double totalTime = 6000;

		
			Particle myParticle = new Particle(1,0.11,8387.5,0,0,0,0,raioDaTerra+120000,0,0,0);
			//Particle myParticle = new Particle(1,0.11,25,0,7,0,0,raioDaTerra+0.11,10,10,10);
			Planet earth = new Planet();
			earth.setMass(massaDaTerra);
			earth.setRadius(raioDaTerra);
			earth.setAtmosphereRadius(100000);
			earth.setvX(0);
			earth.setvY(0);
			earth.setvZ(0);
			earth.setxX(0);
			earth.setxY(0);
			earth.setxZ(0);
			
			
			
			salvarArquivo(executeAlgorithm(myParticle,earth,deltaTime,totalTime), "F:\\dataoutput\\meuspontos1.txt");
			
	}

	private static ArrayList<Double> executeAlgorithm(Particle myParticle, Planet myPlanet, double deltaTime, double totalTime ) {
		ArrayList<Double> returnList = new ArrayList<Double>();
		double distance = Math.sqrt((Math.pow(myParticle.getxX()-myPlanet.getxX(),2)+Math.pow(myParticle.getxY()-myPlanet.getxY(),2)+Math.pow(myParticle.getxZ()-myPlanet.getxZ(),2)));
		double currentTime = 0;

		
		while(currentTime<=totalTime+deltaTime&&(distance>=(myPlanet.getRadius())+myParticle.getRadius())) {
			double velocityModule = Math.sqrt((Math.pow(myParticle.getvX()-myPlanet.getvX(),2)+Math.pow(myParticle.getvY()-myPlanet.getvY(),2)+Math.pow(myParticle.getvZ()-myPlanet.getvZ(),2)));
			
			
			returnList.add(currentTime);
			returnList.add(myParticle.getxX());
			returnList.add(myParticle.getxY());
			returnList.add(myParticle.getxZ());
			returnList.add(distance-myPlanet.getRadius()-myParticle.getRadius());
			//returnList.add(((Math.pow(velocityModule,2)*myParticle.getMass())/2)+(9.8*myParticle.getMass()*(distance-myPlanet.getRadius())));
			//returnList.add(Math.sqrt(Math.pow(myParticle.getxX()-0,2)+Math.pow(myParticle.getxY()-0,2)+Math.pow(myParticle.getxZ()-myPlanet.getRadius()-myParticle.getRadius(),2)));
			returnList.add(velocityModule);
			
			myParticle = applyForces(myParticle, myPlanet, deltaTime);
			distance = Math.sqrt((Math.pow(myParticle.getxX()-myPlanet.getxX(),2)+Math.pow(myParticle.getxY()-myPlanet.getxY(),2)+Math.pow(myParticle.getxZ()-myPlanet.getxZ(),2)));
			currentTime += deltaTime;
		}
		

		
		return returnList;
	}


	private static Particle applyForces(Particle myParticle, Planet myPlanet, double deltaTime){
		ArrayList<ArrayList<Double>> tempList = new ArrayList<ArrayList<Double>>();

		double xNetForce = 0;
		double yNetForce = 0;
		double zNetForce = 0;



		tempList.add(forceGravitational(myParticle, myPlanet));
		//tempList.add(forceDrag(myParticle,myPlanet,1,0.1));
		//tempList.add(forceMagnus(myParticle, myPlanet));
		//tempList.add(forceSpring(myParticle, myPlanet, 0, 0 , myPlanet.getRadius()+1000, 1));
		//tempList.add(forcePendulum(myParticle, myPlanet, 0, 0, myPlanet.getRadius()+100));
		
		
		for (ArrayList<Double> i: tempList){
		xNetForce = xNetForce + i.get(0);
		yNetForce = yNetForce + i.get(1);
		zNetForce = zNetForce + i.get(2);
		}




		myParticle.setvX(myParticle.getvX() + (deltaTime*(xNetForce/myParticle.getMass())));
		myParticle.setvY(myParticle.getvY() + (deltaTime*(yNetForce/myParticle.getMass())));
		myParticle.setvZ(myParticle.getvZ() + (deltaTime*(zNetForce/myParticle.getMass())));

		myParticle.setxX(myParticle.getxX() +  deltaTime*myParticle.getvX());
		myParticle.setxY(myParticle.getxY() +  deltaTime*myParticle.getvY());
		myParticle.setxZ(myParticle.getxZ() +  deltaTime*myParticle.getvZ());

		return myParticle;
	}


	private static ArrayList<Double> forceGravitational(Particle myParticle, Planet myPlanet){
		ArrayList<Double> returnList = new ArrayList<Double>();
		double temp;
		double forceX, forceY, forceZ, distance;
		distance = Math.sqrt((Math.pow(myParticle.getxX()-myPlanet.getxX(),2)+Math.pow(myParticle.getxY()-myPlanet.getxY(),2)+Math.pow(myParticle.getxZ()-myPlanet.getxZ(),2)));

		temp =(-myPlanet.getGravitationalConstant() * myPlanet.getMass()*myParticle.getMass() )/ Math.pow(distance,2);

		//System.out.println((distance-myPlanet.getRadius()));


		forceX = (myParticle.getxX()-myPlanet.getxX())/distance;
		forceY = (myParticle.getxY()-myPlanet.getxY())/distance;
		forceZ = (myParticle.getxZ()-myPlanet.getxZ())/distance;
		forceX = forceX*temp;
		forceY = forceY*temp;
		forceZ = forceZ*temp;


		returnList.add(forceX);
		returnList.add(forceY);
		returnList.add(forceZ);
		
		return returnList;
	}








	private static ArrayList<Double> forceDrag(Particle myParticle, Planet myPlanet, double b, double dragCoefficient) {
		ArrayList<Double> returnList = new ArrayList<Double>();
		double velocityModule = Math.sqrt((Math.pow(myParticle.getvX()-myPlanet.getvX(),2)+Math.pow(myParticle.getvY()-myPlanet.getvY(),2)+Math.pow(myParticle.getvZ()-myPlanet.getvZ(),2)));
		double forceX, forceY, forceZ;
		double distance = Math.sqrt((Math.pow(myParticle.getxX()-myPlanet.getxX(),2)+Math.pow(myParticle.getxY()-myPlanet.getxY(),2)+Math.pow(myParticle.getxZ()-myPlanet.getxZ(),2)));
		//double temp = 0;
		//double gravity =(-myPlanet.getGravitationalConstant() * myPlanet.getMass()*myParticle.getMass() )/ Math.pow(distance,2);
		
		
		if (distance <= myPlanet.getRadius()+myPlanet.getAtmosphereRadius()){


			

			

		double temp = 0.5 * 0.02 * Math.pow(velocityModule,2) * dragCoefficient * (3.141592*Math.pow(myParticle.getRadius(),2));
		if (velocityModule == 0) {
			forceX = 0;
			forceY = 0;
			forceZ = 0;
		}else {
        forceX = -(myParticle.getvX()-myPlanet.getvX())/velocityModule;
		forceY = -(myParticle.getvY()-myPlanet.getvY())/velocityModule;
		forceZ = -(myParticle.getvZ()-myPlanet.getvZ())/velocityModule;
		}
		forceX = forceX*temp;
		forceY = forceY*temp;
		forceZ = forceZ*temp;

		returnList.add(forceX);
		returnList.add(forceY);
		returnList.add(forceZ);



		}else{
			returnList.add(0.0);
			returnList.add(0.0);
			returnList.add(0.0);
		}
        return returnList;
	}


	 private static ArrayList<Double> forceMagnus(Particle myParticle, Planet myPlanet) {
		 	ArrayList<Double> returnList = new ArrayList<Double>();
		 	double temp = 0;
		 	double lift = 1;
		 	double height = Math.sqrt((Math.pow(myParticle.getxX()-myPlanet.getxX(),2)+Math.pow(myParticle.getxY()-myPlanet.getxY(),2)+Math.pow(myParticle.getxZ()-myPlanet.getxZ(),2)));
			double forceX, forceY, forceZ;
			
			temp = 0.5 * lift * myPlanet.getAtmosphereDensity(height) * (3.141592*Math.pow(myParticle.getRadius(),2)); 
			

            forceX = temp*((myParticle.getSpinY()*myParticle.getvZ())-(myParticle.getSpinZ()*myParticle.getvY()));
            forceY = temp*((myParticle.getSpinZ()*myParticle.getvX())-(myParticle.getSpinX()*myParticle.getvZ()));
            forceZ = temp*((myParticle.getSpinX()*myParticle.getvY())-(myParticle.getSpinY()*myParticle.getvX()));

//System.out.println(height);
//System.out.println(myPlanet.getAtmosphereDensity(height));



			returnList.add(forceX);
            returnList.add(forceY);
            returnList.add(forceZ);


		 return returnList;
	 }


	 private static ArrayList<Double> forceSpring(Particle myParticle, Planet myPlanet, double springX, double springY, double springZ, double k) {
		 ArrayList<Double> returnList = new ArrayList<Double>();
		 double forceX, forceY, forceZ;
		 double distance = Math.sqrt((Math.pow(myParticle.getxX()-springX,2)+Math.pow(myParticle.getxY()-springY,2)+Math.pow(myParticle.getxZ()-springZ,2)));
		 double x = Math.sqrt(Math.pow(myParticle.getxX()-springX,2)+Math.pow(myParticle.getxY()-springY,2)+Math.pow(myParticle.getxZ()-springZ,2));
		 double temp = -k*x;
		 
		 
		 
		 if (distance != 0) {
			 	forceX = (myParticle.getxX()-springX)/distance;
				forceY = (myParticle.getxY()-springY)/distance;
				forceZ = (myParticle.getxZ()-springZ)/distance;
			 	} else {
			 		forceX = 0;
			 		forceY = 0;
			 		forceZ = 0;
			 	}
			 	
		 	forceX = forceX*temp;
			forceY = forceY*temp;
			forceZ = forceZ*temp;
		 
			returnList.add(forceX);
			returnList.add(forceY);
			returnList.add(forceZ);
		 
		 return returnList;
		 
		 
	 }
	 
	 private static ArrayList<Double> forcePendulum(Particle myParticle, Planet myPlanet, double pendulumX, double pendulumY, double pendulumZ){
		 ArrayList<Double> returnList = new ArrayList<Double>();
		 double forceX, forceY, forceZ;
		 double distance = Math.sqrt((Math.pow(myParticle.getxX()-myPlanet.getxX(),2)+Math.pow(myParticle.getxY()-myPlanet.getxY(),2)+Math.pow(myParticle.getxZ()-myPlanet.getxZ(),2)));
		 double gravity =(-myPlanet.getGravitationalConstant() * myPlanet.getMass()*myParticle.getMass() )/ Math.pow(distance,2);
		 double velocityModule = Math.sqrt((Math.pow(myParticle.getvX()-myPlanet.getvX(),2)+Math.pow(myParticle.getvY()-myPlanet.getvY(),2)+Math.pow(myParticle.getvZ()-myPlanet.getvZ(),2)));
		 double temp;
		 double stringSize = Math.sqrt(Math.pow(myParticle.getxX()-pendulumX,2)+Math.pow(myParticle.getxY()-pendulumY,2)+Math.pow(myParticle.getxZ()-pendulumZ,2));
		 double cosineTheta = ((-1)*(myParticle.getxZ()-pendulumZ))/stringSize;
		 
		 
		 
		 temp = -(myParticle.getMass()*Math.pow(velocityModule,2)/stringSize) + (cosineTheta * myParticle.getMass() * gravity);
		 
		 if (distance != 0) {
			 	forceX = (myParticle.getxX()-pendulumX)/stringSize;
				forceY = (myParticle.getxY()-pendulumY)/stringSize;
				forceZ = (myParticle.getxZ()-pendulumZ)/stringSize;
			 	} else {
			 		forceX = 0;
			 		forceY = 0;
			 		forceZ = 0;
			 	}
			 	
		 	forceX = forceX*temp;
			forceY = forceY*temp;
			forceZ = forceZ*temp;
		 
			returnList.add(forceX);
			returnList.add(forceY);
			returnList.add(forceZ);
		 
			return returnList;
	 }





	private static int salvarArquivo(ArrayList<Double> meusPontos, String localArquivoSaida) {

		String stringTemp = "Inicialização de variável.";
		String stringFinal = "Inicialização de variável.";
		StringBuilder stringBuilder = new StringBuilder();
		int k = 0;

		for (Double i: meusPontos){
		stringTemp = i.toString() + "\t";
		stringBuilder.append(stringTemp);
		k++;

		if (k==6) {
			stringBuilder.append(System.getProperty("line.separator"));
			k = 0;
		}

		}


		stringFinal = stringBuilder.toString();
		System.out.println(stringFinal);



		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					localArquivoSaida));

			writer.write(stringFinal);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("O arquivo não foi salvo!");
		}

		return 0;

	}


}
