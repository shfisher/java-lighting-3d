package scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometry;

public class Scene {

    // Variables
    private Color _background;
    private AmbientLight _ambientLight;
    private List<Geometry> _geometries = new ArrayList<>();
    private Camera _camera;
    private double _screenDistance;
    private List<LightSource> _lights = new ArrayList<>();
    private String _sceneName = "scene";

    // ***************** Constructors ********************** //

// FUNCTION
// Scene
// PARAMETERS
//  none
// RETURN VALUE
// none
// MEANING
// This functions builds a Scene with black color
    public Scene(){
        _background = new Color(0, 0, 0);
        _ambientLight = new AmbientLight();
        setCamera(new Camera());
        _screenDistance = 100;
    }

// FUNCTION
//   Scene
// PARAMETERS
//   scene
// RETURN VALUE
// none
// MEANING
// This functions builds a Scene with parameter colors that she get
    public Scene (Scene scene){
        _background = scene.getBackground();
        _ambientLight = scene.getAmbientLight();
        _geometries = scene._geometries;
        _lights = scene._lights;
        _camera = scene.getCamera();
        _screenDistance = scene._screenDistance;
    }

// FUNCTION
//   Scene
// PARAMETERS
//  AmbientLight aLight, Color background,Camera camera, double screenDistance
// RETURN VALUE
// none
// MEANING
// This functions builds a Scene with parameters that she get
    public Scene(AmbientLight aLight, Color background,
                 Camera camera, double screenDistance){

        _background = background;
        _ambientLight = new AmbientLight(aLight);
        setCamera(new Camera(camera));
        _screenDistance = screenDistance;
    }

    // ***************** Getters/Setters ********************** //

    //FUNCTIONS
// getBackground
// PARAMETERS
//   none
// RETURN VALUE
// Color
// MEANING
// This function returns the background
    public Color getBackground() {
        return _background;
    }

    //FUNCTIONS
// getAmbientLight
// PARAMETERS
//   none
// RETURN VALUE
// AmbientLight
// MEANING
// This function returns the AmbientLight
    public AmbientLight getAmbientLight() {
        return new AmbientLight(_ambientLight);
    }

    //FUNCTIONS
// getCamera
// PARAMETERS
//   none
// RETURN VALUE
// Camera
// MEANING
// This function returns the Camera
    public Camera getCamera() {
        return new Camera(_camera);
    }

    //FUNCTIONS
// getSceneName
// PARAMETERS
//   none
// RETURN VALUE
// String
// MEANING
// This function returns the sceneName
    public String getSceneName() {
        return _sceneName;
    }

    //FUNCTIONS
// getScreenDistance
// PARAMETERS
//   none
// RETURN VALUE
// double
// MEANING
// This function returns the screenDistance
    public double getScreenDistance() {
        return _screenDistance;
    }

    //FUNCTIONS
//  setBackground
// PARAMETERS
//  Color
// RETURN VALUE
// none
// MEANING
// Those functions sets the background
    public void setBackground(Color _background) {
        this._background = _background;
    }

    //FUNCTIONS
// setAmbientLight
// PARAMETERS
// AmbientLight
// RETURN VALUE
// none
// MEANING
// This function sets the AmbientLight
    public void setAmbientLight(AmbientLight ambientLight) {
        this._ambientLight = new AmbientLight(_ambientLight);
    }

    //FUNCTIONS
// setCamera
// PARAMETERS
// Camera
// RETURN VALUE
// none
// MEANING
// This function sets the Camera
    public void setCamera(Camera camera) {
        this._camera = new Camera(camera);
    }

    //FUNCTIONS
//  setSceneName
// PARAMETERS
// String
// RETURN VALUE
// none
// MEANING
// This function sets the sceneName
    public void setSceneName(String sceneNAme) {
        this._sceneName = sceneNAme;
    }

    //FUNCTIONS
//  setScreenDistance
// PARAMETERS
//  double
// RETURN VALUE
// none
// MEANING
// This function sets the screenDistance
    public void setScreenDistance(double screenDistance) {
        this._screenDistance = screenDistance;
    }

    // ***************** Operations ******************** //

    //FUNCTIONS
// addGeometry
// PARAMETERS
//Geometry
// RETURN VALUE
// none
// MEANING
// this function add a geometry to the scene
    public void addGeometry(Geometry geometry){
        _geometries.add(geometry);
    }

    //FUNCTIONS
// getGeometriesIterator
// PARAMETERS
//Geometry
// RETURN VALUE
// Iterator
// MEANING
// this function returns a geometry iterator
    public Iterator<Geometry> getGeometriesIterator(){
        return _geometries.iterator();
    }

    //FUNCTIONS
// addLight
// PARAMETERS
//LightSource
// RETURN VALUE
// none
// MEANING
// this function add a light to the scene
    public void addLight(LightSource light){
        _lights.add(light);
    }

    //FUNCTIONS
// getLightsIterator
// PARAMETERS
//LightSource
// RETURN VALUE
// Iterator
// MEANING
// this function returns a light source iterator
    public Iterator<LightSource> getLightsIterator(){
        return _lights.iterator();
    }

}
