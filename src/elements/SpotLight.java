package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class SpotLight extends PointLight {

    // Variables
    private Vector _direction;

// ***************** Constructor ********************** //

// FUNCTION
//  SpotLight
// PARAMETERS
//  Color, Point3D, Vector, 3 double
// RETURN VALUE
// none
// MEANING
// This function builds a SpotLight and makes its values to the values the function gets
    public SpotLight(Color color, Point3D position, Vector direction, double kc, double kl, double kq){

        super(color, position, kc, kl, kq);
        _direction = new Vector(direction);
        try{
        _direction.normalize();}
        catch(Exception e){}
    }

// ***************** Getters/Setters ********************** //

// FUNCTION
//  getIntensity
// PARAMETERS
// Point3D
// RETURN VALUE
// Color
// MEANING
// This function returns the color's strength
    @Override
    public Color getIntensity(Point3D point){

        Color pointColor = super.getIntensity(point);

        Vector l = getL(point);
        try{
        l.normalize();}
        catch (Exception e){}
        double k = Math.abs(_direction.dotProduct(l));

        // prevents light magnification
        if (k > 1)
            k = 1;

        // returns the strength
        return new Color((int)(pointColor.getRed() * k), (int)(pointColor.getGreen() * k), (int)(pointColor.getBlue() * k));
    }
}