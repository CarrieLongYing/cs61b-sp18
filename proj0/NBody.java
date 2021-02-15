import java.security.PublicKey;

public class NBody {
    public static double readRadius(String file_name) {
        In in = new In(file_name);
        double num_planets = in.readDouble();
        double radius = in.readDouble();
        return radius;
    }


    public static Planet[] readPlanets(String file_name) {
        In in = new In(file_name);
        int num_planets = in.readInt();
        double radius = in.readDouble();
        Planet[] plant_array = new Planet[num_planets];
        for (int i = 0; i < num_planets; i++) {
            double xxpos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet_in_file = new Planet(xxpos, yyPos, xxVel, yyVel, mass, imgFileName);
            plant_array[i] = planet_in_file;
        }
        return plant_array;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planet_array = readPlanets(filename);
        double universe_radius = readRadius(filename);
        StdDraw.setScale(-universe_radius, universe_radius);
        StdDraw.clear();
        //StdDraw.setXscale(-universe_radius, universe_radius);
        //StdDraw.setYscale(-universe_radius, universe_radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet each_planet : planet_array) {
            each_planet.draw();
        }
        //enableDoubleBuffering();
        //double current_time = 0.0;
        for (double current_time = 0.0; current_time < T; current_time = current_time + dt) {


            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int i = 0; i < 5; i++) {
                xForces[i] = planet_array[i].calcNetForceExertedByX(planet_array);
                yForces[i] = planet_array[i].calcNetForceExertedByY(planet_array);
            }
            for (int i = 0; i < 5; i++) {
                planet_array[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet each_planet : planet_array) {
                each_planet.draw();
            }
            StdDraw.show(10);
        }

        StdOut.printf("%d\n", planet_array.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < planet_array.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet_array[i].xxPos, planet_array[i].yyPos, planet_array[i].xxVel,
                    planet_array[i].yyVel, planet_array[i].mass, planet_array[i].imgFileName);
        }
    }
}