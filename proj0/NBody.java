public class NBody {
/**The goal of this class is to simulate a universe specified in one of the data files. */

// The input format is a text file that contains the information for a particular universe (in SI units).
// The first value is an integer, n, that represents the number of planets
// The second value is a real number, r, that represents the radius of the universe.
public static double readRadius(String txt) {
    In in = new In(txt);
    int numPlanets = in.readInt();
    double radius = in.readDouble();
    return radius;
}
    public static Planet[] readPlanets(String txt) {
        In in = new In(txt);
        int numPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numPlanets];
        int i = 0;
        //this is where building the array starts.
        while (!in.isEmpty() && i < planets.length) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            //concatenation of relevant string
            String img = "images/" + in.readString();
            Planet newPlanet = new Planet(xP, yP, xV, yV, m, img);
            planets[i] = newPlanet;
            i++;
        }
        return planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);
        //StdDraw.picture(0,0,"images/starfield.jpg");
        //for (Planet s : planets) {
        //s.draw();
        //}
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time<=T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                double xxNetForce = planets[i].calcNetForceExertedByX(planets);
                double yyNetForce = planets[i].calcNetForceExertedByY(planets);
                xForces[i] = xxNetForce;
                yForces[i] = yyNetForce;
                planets[i].update(dt, xxNetForce, yyNetForce);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet s : planets) {
                s.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}