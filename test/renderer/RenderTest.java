package renderer;


import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import scene.Scene;
import org.junit.Test;
import java.awt.*;

public class RenderTest {

        @Test
        public void emmissionTest() {

                // creates a scene
                Scene scene = new Scene();

                // Sphere
                Sphere sphere = new Sphere(90, new Point3D(0.0, 0.0, -150));
                // First Triangle
                Triangle triangle1 = new Triangle(new Point3D(150, 0, -150),
                        new Point3D(0, 150, -150),
                        new Point3D(150, 150, -150));
                // Second Triangle
                Triangle triangle2 = new Triangle(new Point3D(150, 0, -150),
                        new Point3D(0, -150, -150),
                        new Point3D(150, -150, -150));
                // Third Triangle
                Triangle triangle3 = new Triangle(new Point3D(-150, 0, -150),
                        new Point3D(0, 150, -150),
                        new Point3D(-150, 150, -150));
                // Fourth Triangle
                Triangle triangle4 = new Triangle(new Point3D(-150, 0, -150),
                        new Point3D(0, -150, -150),
                        new Point3D(-150, -150, -150));

                // sets the geometries' Emmissions
                sphere.setEmmission(new Color(100, 3, 255));
                triangle1.setEmmission(new Color(255, 255, 0));
                triangle2.setEmmission(new Color(0, 200, 0));
                triangle3.setEmmission(new Color(0, 0, 200));
                triangle4.setEmmission(new Color(200, 200, 0));

                // add the geometries to the scene
                scene.addGeometry(sphere);
                scene.addGeometry(triangle1);
                scene.addGeometry(triangle2);
                scene.addGeometry(triangle3);
                scene.addGeometry(triangle4);


                ImageWriter imageWriter = new ImageWriter("Emmission test123", 500, 500, 500, 500);
                Render render = new Render(imageWriter, scene);
                render.renderImage();
                render.printGrid(50);
                render.writeToImage();

        }

}