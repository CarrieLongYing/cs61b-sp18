
public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double gravi_constant = 6.67E-11;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet b){

	}

	public double calcDistance(Planet plant_com){
		double dis_squared = (this.xxPos-plant_com.xxPos)*(this.xxPos-plant_com.xxPos) + (this.yyPos-plant_com.yyPos)*(this.yyPos-plant_com.yyPos);
		double dis = Math.sqrt(dis_squared);
		return dis;
	}

	public double calcForceExertedBy(Planet plant_com){
		double force = (gravi_constant*this.mass*plant_com.mass) / (this.calcDistance(plant_com)*this.calcDistance(plant_com));
		return force;
	}

	public double calcForceExertedByX(Planet plant_com){
		double dis = this.calcDistance(plant_com);
		double force = this.calcForceExertedBy(plant_com);
		double force_x = (force * (plant_com.xxPos - this.xxPos) / dis );
		return force_x;
	}

	public double calcForceExertedByY(Planet plant_com){
		double dis = this.calcDistance(plant_com);
		double force = this.calcForceExertedBy(plant_com);
		double force_y = (force* (plant_com.yyPos - this.yyPos) / dis );
		return force_y;
	}	

	public double calcNetForceExertedByX(Planet[] array){
		double net_force_x = 0.0;
		for (Planet planet_i:array){
			if (this.equals(planet_i)){
				continue;
			}
			net_force_x = net_force_x + this.calcForceExertedByX(planet_i);
		}

		return net_force_x;
	}

	public double calcNetForceExertedByY(Planet[] array){
		double net_force_y = 0.0;
		for (Planet planet_i:array){
			if (this.equals(planet_i)){
				continue;
			}
			net_force_y = net_force_y + this.calcForceExertedByY(planet_i);
		}
		return net_force_y;
	}

	public void update(double dt, double fX, double fY){
		double a_x = fX / this.mass;
		double a_y = fY / this.mass;
		double vel_x = this.xxVel + dt*a_x;
		double vel_y = this.yyVel + dt*a_y;
		double pos_x = this.xxPos + dt*vel_x;
		double pos_y = this.xxPos + dt*vel_y;
		xxPos = pos_x;
		yyPos = pos_y;
		xxVel = vel_x;
		yyVel = vel_y;
	}

	public void draw(){
		StdDraw.picture(xxPos/100,yyPos/100,"images/"+imgFileName);
	}
	 
}