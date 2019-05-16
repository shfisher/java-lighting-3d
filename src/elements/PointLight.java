package elements;

import primitives.Point3D;
import primitives.Vector;
import java.awt.*;

public class PointLight extends Light implements LightSource {

    // Variables
    Point3D _position;
    double _Kc, _Kl, _Kq;

    // ***************** Constructors ********************** //

// FUNCTION
//  PointLight
// PARAMETERS
//  Color, Point3D, 3 double
// RETURN VALUE
// none
// MEANING
// This function builds a PointLight and makes its values to the values the function gets
    public PointLight(Color color, Point3D position, double kc, double kl, double kq) {
        super(color);
        _position = new Point3D(position);
        _Kc = kc;
        _Kl = kl;
        _Kq = kq;
    }

// ***************** Getters/Setters ********************** //

// FUNCTION
// getIntensity
// PARAMETERS
//  Point3D
// RETURN VALUE
// Color
// MEANING
// This functions returns the strength of the light
    @Override
    public Color getIntensity(Point3D point) {

        int r, g, b;
        double d, k;

        r = _color.getRed(); // Red
        g = _color.getGreen(); // Green
        b = _color.getBlue(); // Blue

        d = _position.distance(point); // the distance between the light and the point
        k = 1 / (_Kc + _Kl * d + _Kq * Math.pow(d, 2));

        if (k > 1)
            k = 1;

        // return the color's strength according to k's influence
        return new Color((int) (r * k), (int) (g * k), (int) (b * k));
    }

// FUNCTION
//  getL
// PARAMETERS
//  Point3D
// RETURN VALUE
// Vector
// MEANING
// This function returns the vector from the light to the point3D
    public Vector getL(Point3D point) {
        return new Vector(_position, point);
    }

}