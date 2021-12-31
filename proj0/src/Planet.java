public class Planet {
	//six instance variables//
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	//two constructors//
	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	//finding sqrt without using Math.pow. To be used in calcDistance//
	public static double squareRoot(double num)
	{
//temporary variable
		double t;
		double sqrtroot=num/2;
		do
		{
			t=sqrtroot;
			sqrtroot=(t+(num/t))/2;
		}
		while((t-sqrtroot)!= 0);
		return sqrtroot;
	}

	//calcDistance method calculates the distance between two Planets. Implemented by calling this method on the current planet and
	// calculating the distance between itself and the planet position brought in as a parameter.
	public double calcDistance (Planet p){
		double pDistance;
		double pDistanceSq;
		pDistanceSq = (p.xxPos-this.xxPos)*(p.xxPos-this.xxPos) + (p.yyPos-this.yyPos)*(p.yyPos - this.yyPos);
		pDistance = squareRoot(pDistanceSq);
		return pDistance;
	}

	//calcForceExertedby calculates the force from this planet to the planet called by the method
	// it needs this to the current planet and the called planet to be the planet expressing force.
	public double calcForceExertedBy(Planet p){
		double G = 6.67E-11;
		double ForceVector;
		ForceVector = (G*this.mass*p.mass)/(calcDistance(p)*calcDistance(p));
		return ForceVector;
	}
	//calcNetForceExertedByX and calcNetForceExertedByY calculate the force from "Force" splitted to x and y direction
	//by the planet called by the method.
	//create an array for all planets.

	public double calcForceExertedByX(Planet p) {
		double ForceX = (calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / calcDistance(p);
		return ForceX;
	}

	public double calcForceExertedByY(Planet p){
		double ForceY = (calcForceExertedBy(p)*(p.yyPos-this.yyPos))/calcDistance(p);
		return ForceY;
	}
	//methods calcNetForceExertedByX and calcNetForceExertedByY that each take in an array of Planets and calculate the
	// net X and net Y force exerted by all planets in that array upon the current Planet.
	public double calcNetForceExertedByX (Planet[] allPlanets){
		double ForceNetX = 0.0;
		for (Planet p: allPlanets){

			if (p.equals(this)){
				continue;
			}
			else{
				ForceNetX = ForceNetX + calcForceExertedByX(p);
			}
		}
		return ForceNetX;
	}
	public double calcNetForceExertedByY (Planet[] allPlanets){
		double ForceNetY = 0.0;
		for (Planet p: allPlanets){

			if (p.equals(this)){
				continue;
			}
			else{
				ForceNetY = ForceNetY + calcForceExertedByY(p);
			}
		}
		return ForceNetY;
	}
	//method that determines how much the forces exerted on the planet will cause that planet to accelerate, and the
	// resulting change in the planet’s velocity and position in a small period of time dt (any time interval). No return.
	public void update (double dt, double fX, double fY){
		double aNetX = fX/this.mass;
		double aNetY = fY/this.mass;
		this.xxVel = this.xxVel + aNetX*dt;
		this.yyVel = this.yyVel + aNetY*dt;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos + this.yyVel*dt;
	}
}