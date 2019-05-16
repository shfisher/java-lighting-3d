package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light implements LightSource {

    // Variables
    private Vector _direction;

    // ***************** Constructors ********************** //

// FUNCTION
//   DirectionalLight
// PARAMETERS
//   Color, Vector
// RETURN VALUE
// none
// MEANING
// This function builds a DirectionalLight and makes its values to the values the function gets
    public DirectionalLight(Color color, Vector direction){
         super (color);
        _direction = new Vector(direction);

    }

    // ***************** Getters/Setters ********************** //

// FUNCTION
//  getIntensity
// PARAMETERS
//  Point3D
// RETURN VALUE
// Color
// MEANING
// This function returns the intensity (strength) of the light
    @Override
    public Color getIntensity(Point3D point) {
        return getIntensity();
    }

// FUNCTION
//  getDirection
// PARAMETERS
//  none
// RETURN VALUE
// Vector
// MEANING
// This function returns the direction of the light
    public Vector getDirection(){
        return new Vector(_direction);
    }

// FUNCTION
//  setDirection
// PARAMETERS
//  Vector
// RETURN VALUE
// none
// MEANING
// This function sets the direction of the light according to the vector it gets
    public void setDirection(Vector _direction){
        this._direction = _direction;
    }

// FUNCTION
//  getL
// PARAMETERS
//  Point3D
// RETURN VALUE
// Vector
// MEANING
// This function returns the vector from the light to the point3D
    @Override
    public Vector getL(Point3D point) {
        return getDirection();
    }
}
