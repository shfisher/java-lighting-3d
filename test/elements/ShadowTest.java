package elements;

import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import org.junit.Test;

import java.awt.*;

public class ShadowTest {

    @Test
    public void shadowTest() {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(600, new Point3D(0.0, 0.0, -1000));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(100, 0, 50));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -2000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -2000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        // scene.addLight(new DirectionalLight(new Color(255,100,100),new Vector(200,200,-100)));
        //scene.addLight(new PointLight(new Color(255,100,100),new Point3D(200,200,-100),0,0.000001,0.0000005));
        scene.addLight(new SpotLight(new Color(200, 200, 0), new Point3D(200, 200, -100),
                new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }



}