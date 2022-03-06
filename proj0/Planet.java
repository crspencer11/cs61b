import static java.lang.Math.sqrt;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double gravity = 6.67e-11;
    public Planet(double xP, double yP, 
    double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet a) {
        double xxDistance = this.xxPos - a.xxPos;
        double yyDistance = this.yyPos - a.yyPos;
        double square = (xxDistance * xxDistance) + (yyDistance * yyDistance);
        double distance = sqrt(square);
        return distance;
    }
    public double calcForceExertedBy(Planet a) {
        double force = (gravity * this.mass * a.mass) / (calcDistance(a) * calcDistance(a));
        return force;
    }
    public double calcForceExertedByX(Planet a) {
        double xxDistance = a.xxPos - this.xxPos;
        double xxForce = (xxDistance * calcForceExertedBy(a) / calcDistance(a));
        return xxForce;
    }
    public double calcForceExertedByY(Planet a) {
        double yyDistance = a.yyPos - this.yyPos;
        double yyForce = (yyDistance * calcForceExertedBy(a) / calcDistance(a));
        return yyForce;
    }
    public double calcNetForceExertedByX(Planet[] planetArray) {
        double xxNetForce = 0;
        for(int i = 0; i < planetArray.length; i++) {
            if(this.equals(planetArray[i])) {
                continue;
            }
            xxNetForce += calcForceExertedByX(planetArray[i]);
        }
        return xxNetForce;
    }
    public double calcNetForceExertedByY(Planet[] planetArray) {
        double yyNetForce = 0;
        for(int i = 0; i < planetArray.length; i++) {
            if(this.equals(planetArray[i])) {
                continue;
            }
            yyNetForce += calcForceExertedByY(planetArray[i]);
        }
        return yyNetForce;
    }
    public void update(double dt, double fX, double fY) {
        double xX = fX / mass;
        double yY = fY / mass;
        xxVel = xxVel + (dt * xX);
        yyVel = yyVel + (dt * yY);
        xxPos = xxPos + (dt * xxVel);
        yyPos = yyPos + (dt * yyVel);
    }

    public void draw() {
        StdDraw.picture(xxPos,yyPos,imgFileName);
    }
}