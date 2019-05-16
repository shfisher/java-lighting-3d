package renderer;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;
import elements.LightSource;
import geometries.FlatGeometry;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

public class Render {

    // Variables
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;

    // ***************** Constructors ********************** //

// FUNCTION
//   Render
// PARAMETERS
// ImageWriter, Scene
// RETURN VALUE
// none
// MEANING
// This functions builds a Render ny an ImageWriter & Scene
    public Render(ImageWriter imageWriter,Scene scene) {
        _imageWriter = new ImageWriter(imageWriter);
        _scene = new Scene(scene);
    }

    // ***************** Operations ******************** //

// FUNCTION
//   renderImage
// PARAMETERS
//   none
// RETURN VALUE
// none
// MEANING
// This function creates an image (by the imageWriter)
    public void renderImage(){

        for (int i = 0; i < _imageWriter.getHeight();i++)
        {
            for (int j = 0; j < _imageWriter.getWidth();j++)
            { // creates a ray for each value of i & j
                Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(),_imageWriter.getNy(),j,
                                i,_scene.getScreenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight());
                Entry<Geometry, Point3D> entry = findClosesntIntersection(ray);

                if (entry == null) // case there r no intersections
                    _imageWriter.writePixel(j,i,_scene.getBackground());
                else // otherwise
                    _imageWriter.writePixel(j,i,calcColor(entry.getKey(),entry.getValue(),ray));
            }
        }
    }

// FUNCTION
// findClosesntIntersection
// PARAMETERS
//  Ray
// RETURN VALUE
// Entry<Geometry, Point3D>
// MEANING
// This function finds The closet intersection to specific point
    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray){

        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        // checks if there r no intersection points
        if (intersectionPoints.isEmpty())
            return null;

        // otherwise - in case there r any intersection points
        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints); // gets the closest point
        Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next(); // creates a way to obtain a reference to the map entry
        return entry;
    }

// FUNCTION
//   printGrid
// PARAMETERS
//  int
// RETURN VALUE
// none
// MEANING
// This functions prints the grid on the picture
    public void printGrid(int interval){

        int h, w;
        h = _imageWriter.getHeight(); // Height
        w = _imageWriter.getWidth(); // Width

        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
                if ((i % interval == 0) || (j % interval == 0))
                    _imageWriter.writePixel(j,i,255,255,255);

        }
    };

// FUNCTION
//   writeToImage
// PARAMETERS
//  none
// RETURN VALUE
// none
// MEANING
// This functions turns on the imageWriter
    public void writeToImage(){
        _imageWriter.writeToimage();
    }

// FUNCTION
// calcColor
// PARAMETERS
//  Geometry, Point3D, Ray
// RETURN VALUE
// Color
// MEANING
// This functions calculates The color

        private Color calcColor(Geometry key, Point3D value, Ray ray) {
            return calcColor(key,value,ray,0);

    }

// FUNCTION
// calcColor
// PARAMETERS
//  Geometry, Point3D, Ray, int
// RETURN VALUE
// Color
// MEANING
// This is a recursive function that calculates The color
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level) {

        if (level == RECURSION_LEVEL)
            return new Color(0, 0, 0);

        // initialize some colors
        Color ambientlight = _scene.getAmbientLight().getIntensity();//ambient light of the scene
        Color emissionLight = geometry.getEmmission();//emission Light of the scene
        Color newColor = addColors(ambientlight, emissionLight);// creates the new color by ambientlight & emissionLight
        Iterator<LightSource> lights = _scene.getLightsIterator();// array list of lights

        Color lightReflected = new Color(0, 0, 0); // the color that reflected

        // calculates The colors for each point
        lightReflected = getColor(geometry, point, lights, lightReflected);

        //calculates The colors for each point that reflected by other points by Recursion
        Color colorNew = addColors(newColor, lightReflected); // creates new colour by newColor & lightReflected
        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay); // finds The closest intersection to specific point
        Color reflected = new Color(0, 0, 0);

        if (reflectedEntry != null) // case there r any intersection points
        {
            reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);// recursive
            double kr = geometry.getMaterial().getKr();
            reflected = new Color((int) (reflected.getRed() * kr), (int) (reflected.getGreen() * kr), (int) (reflected.getBlue() * kr));
        }

        //calculates The colors for each point that reflected by other points by Recursion
        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay); // finds The closet intersection to specific point
        Color refracted = new Color(0, 0, 0);

        if (refractedEntry != null) // case there r any intersection points
        {
            refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = geometry.getMaterial().getKt();
            refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
        }

        Color EColors = addColors(reflected,refracted);
        Color finalColor = addColors(EColors,colorNew);

        return  finalColor;
    }

// FUNCTION
// getColor
// PARAMETERS
// Geometry, Point3D, Iterator<LightSource>, Color
// RETURN VALUE
// Color
// MEANING
//calculates The colors for each point
    private Color getColor(Geometry geometry, Point3D point, Iterator<LightSource> lights, Color lightReflected) {

        while (lights.hasNext())
        {
            LightSource light = lights.next();

            if (!occluded(light, point, geometry))
            {
                Color lightIntensity = light.getIntensity(point); // intensity (strength)
                Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),
                        geometry.getNormal(point), light.getL(point), lightIntensity);
                Color lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(),
                        new Vector(point, _scene.getCamera().getP0()), geometry.getNormal(point), light.getL(point), geometry.getShininess(), lightIntensity);
                lightReflected = addColors(lightDiffuse, lightSpecular);
            }
       }

        return lightReflected;
    }

// FUNCTION
//   getClosestPoint
// PARAMETERS
//  Map<Geometry, List<Point3D>>
// RETURN VALUE
// Map<Geometry, Point3D>
// MEANING
// This functions finds the closest point to the camera
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints){

        double distance = Double.MAX_VALUE; // initialize max distance
        Point3D P0 = _scene.getCamera().getP0(); // scene's camera's center
        Map<Geometry,Point3D> minDistancePoint = new HashMap<>();

        MinDistance(intersectionPoints, distance, P0, minDistancePoint);
        return minDistancePoint;
    }

// FUNCTION
//  MinDistance
// PARAMETERS
// 2 Map<Geometry, List<Point3D>>, double, Point3D
// RETURN VALUE
// void
// MEANING
// This function finds the minimum distance to the camera (between all the points)
    private void MinDistance(Map<Geometry, List<Point3D>> intersectionPoints, double distance,
                             Point3D p0, Map<Geometry, Point3D> minDistancePoint) {

        for (Entry<Geometry,List<Point3D>> entry: intersectionPoints.entrySet())
        {
            for (Point3D point : entry.getValue())
            {
                // looks for the minimum distance
                double pointDistance = p0.distance(point);
                if (pointDistance<distance)
                {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(),new Point3D(point));
                    distance = pointDistance;
                }
            }
        }
    }

// FUNCTION
//   getSceneRayIntersections
// PARAMETERS
//  Ray
// RETURN VALUE
// Map<Geometry,List<Point3D>>
// MEANING
// This function finds the intersections of a Ray with the geometries
    private Map<Geometry,List<Point3D>> getSceneRayIntersections(Ray ray) {

        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry,List<Point3D>> intersectionPoints = new HashMap<>();

        while (geometries.hasNext()) // in case there r any elements
        {
            Geometry geometry = geometries.next(); // get the geometry
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray); // list of intersection points

            if (!geometryIntersectionPoints.isEmpty()) // case there r any intersection points
                intersectionPoints.put(geometry,geometryIntersectionPoints);
        }

               return intersectionPoints;
    }

// FUNCTION
// constructRefractedRay
// PARAMETERS
// Geometry, Point3D, Ray
// RETURN VALUE
// Ray
// MEANING
// This function creates a refracted ray (refracted by the Geometry)
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay) {

        Vector normal = geometry.getNormal(point);
        normal.scale(-2);
        point.add(normal);

        return new Ray (point, inRay.getDirection());

    }

// FUNCTION
//  constructReflectedRay
// PARAMETERS
//  Vector, Point3D, Ray
// RETURN VALUE
// Ray
// MEANING
// This functions creates a reflected ray
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) {

        // creates normalized R by normal & inRay
        Vector R = Normalized_Vector(normal, inRay);

        point.add(normal);
        Ray reflectedRay = new Ray(point, R);

        return reflectedRay;
    }

// FUNCTION
//  Normalized_Vector
// PARAMETERS
//  Vector, Ray
// RETURN VALUE
// Vector
// MEANING
// This function creates a new normalized vector by a ray & a vector
    private Vector Normalized_Vector(Vector normal, Ray inRay) {

        Vector l = inRay.getDirection();
        try{
        l.normalize();}
        catch(Exception e){}
        normal.scale(-2 * l.dotProduct(normal));
        l.add(normal);

        Vector R = new Vector(l);
        try{
        R.normalize();}
        catch(Exception e){}

        return R;
    }

// FUNCTION
// calcSpecularComp
// PARAMETERS
// 2 doubles, 3 Vectors, Color
// RETURN VALUE
// Color
// MEANING
// This functions calculates a specular light
    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity) {

        // creates the normalized R by v, normal & l
        Vector R = Vectors_Normalization(v, normal, l);
        double k = 0;

        // avoids from glamour at the ends
        if (v.dotProduct(R) > 0)
            k = ks * Math.pow(v.dotProduct(R), shininess);

        return new Color ((int)(lightIntensity.getRed()*k), (int)(lightIntensity.getGreen()*k), (int)(lightIntensity.getBlue()*k));
    }

// FUNCTION
// Vectors_Normalization
// PARAMETERS
// 3 Vectors
// RETURN VALUE
// Vector
// MEANING
// This function returns new normalized vector that calculated by the vectors the function gets
    private Vector Vectors_Normalization(Vector v, Vector normal, Vector l) {

        try{v.normalize();
        normal.normalize();
        l.normalize();}
        catch(Exception e){}

        normal.scale(-2 * l.dotProduct(normal));
        l.add(normal);

        Vector R = new Vector(l);
        try{
            R.normalize();
        }
        catch(Exception e){}

        return R;
    }

// FUNCTION
//   calcDiffusiveComp
// PARAMETERS
// 1 doubles, 2 Vectors, Color
// RETURN VALUE
// Color
// MEANING
// This functions calculates a Diffusive light
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity) {

        // both vectors' normalization
        try{
        normal.normalize();
        l.normalize();}
        catch(Exception e){}

        double k = Math.abs(kd * normal.dotProduct(l));
        // calculates the new colour by using k's value
        return new Color ((int)(lightIntensity.getRed()*k), (int)(lightIntensity.getGreen()*k), (int)(lightIntensity.getBlue()*k));
    }

// FUNCTION
// occluded
// PARAMETERS
// LightSource, Point3D, Geometry
// RETURN VALUE
// boolean
// MEANING
// This function checks if there is a shadow in a specific point
    private boolean occluded(LightSource light, Point3D point, Geometry geometry) {

        // gets normalized lightDirection Vector
        Vector lightDirection = getlightDirectionVector(light, point);

        Point3D geometryP = new Point3D(point); // the geometry's point3D that get checked
        Vector V = new Vector(geometry.getNormal(point));
        V.scale(2);
        geometryP.add(V);

        Ray lightRay = new Ray(geometryP, lightDirection); // creates a ray by the geometry point3D that get checked & the lightDirection vector
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

        // checks the geometry isn't FlatGeometry (because it's can't intersect itself)
        if (geometry instanceof FlatGeometry)
            intersectionPoints.remove(geometry);

        for (Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
            if (entry.getKey().getMaterial().getKt() == 0) // case there is a shadow
                return true;
        // otherwise
        return false;

    }

// FUNCTION
// getlightDirectionVector
// PARAMETERS
// LightSource, Point3D
// RETURN VALUE
// Vector
// MEANING
// This function returns the normalized lightDirection Vector
    private Vector getlightDirectionVector(LightSource light, Point3D point) {
        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);
        try{
            lightDirection.normalize();
        }
        catch(Exception e){}
        return lightDirection;
    }

// FUNCTION
//   addColors
// PARAMETERS
// 2 colors
// RETURN VALUE
// color
// MEANING
// This functions add Color to geometry
    private Color addColors(Color a, Color b){

        int B, G, R; // Colours

        // Red
        R = a.getRed() + b.getRed();
        if (R > 255)
            R = 255;
        // Green
        G = a.getGreen() + b.getGreen();
        if (G > 255)
            G = 255;
        // Blue
        B = a.getBlue() + b.getBlue();
        if (B > 255)
            B = 255;

        Color I = new Color (R, G, B);
        return I;

    }
}