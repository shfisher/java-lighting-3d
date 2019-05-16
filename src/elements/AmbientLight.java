package elements;

import java.awt.*;

public class AmbientLight extends Light {

    private final double _Ka = 0.1;

    // ***************** Constructors **********************

// FUNCTION
//  AmbientLight
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This functions builds a AmbientLight (Equals The color of the light to 255, 255, 255)
    public AmbientLight() {
        super(new Color(255, 255, 255));
    }

// FUNCTION
//   AmbientLight
// PARAMETERS
//  AmbientLight
// RETURN VALUE
// none
// MEANING
// This functions builds a AmbientLight (Equals The color of the light to the parameter)
    public AmbientLight(AmbientLight aLight) {
        super(aLight._color);
    }

// FUNCTION
//   AmbientLight
// PARAMETERS
// int r, int g, int b
// RETURN VALUE
// none
// MEANING
// This functions builds a AmbientLight (Equals The color of the light to the parameters)
    public AmbientLight(int r, int g, int b) {
        super(new Color(r, g, b));
    }

// ***************** Getters/Setters **********************

// FUNCTION
//   getColor
// PARAMETERS
//   none
// RETURN VALUE
// _color
// MEANING
// This functions returns the color of the light
    public Color getColor() {
        return _color;
    }

// FUNCTION
    // setColor
// PARAMETERS
//  Color
// RETURN VALUE
// none
// MEANING
// This functions sets the color of the light
    public void setColor(Color color) {
        _color = color;
    }

// FUNCTION
    //   getKa
// PARAMETERS
//  none
// RETURN VALUE
// _Ka
// MEANING
// This functions gets the ka (0.1)
    public double getKa() {
        return _Ka;
    }

// FUNCTION
    //  getIntensity
// PARAMETERS
//  none
// RETURN VALUE
// new Color
// MEANING
// This functions sets the Intensity according to the knowing function depends on _Ka (as reminded at class)
    @Override
    public Color getIntensity() {
        return new Color((int) (_color.getRed() * _Ka), (int) (_color.getGreen() * _Ka), (int) (_color.getBlue() * _Ka));
    }

}
