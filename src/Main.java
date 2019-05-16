import elements.*;
import geometries.Geometry;
import geometries.Quadrangle;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        int l, a, r, g, b, t, w;
        String f;

        // Colour
        System.out.println("Enter (r,g,b):");
        r = scanner.nextInt();
        g = scanner.nextInt();
        b = scanner.nextInt();
        // Angle
        do {
            System.out.println("Enter angle (0-180) :");
            a = scanner.nextInt();
            Math.toRadians(a);
        } while (a < 0 || a > 180);


        Vector Direction; 

        double tanA = Math.tan(Math.toRadians(a));

                if (a < 90)
                    Direction = new Vector(0, 1, -tanA);

                else if (a == 90)
                    Direction = new Vector(0, 0, -1);

                else {
                    a -= 90;
                    tanA = Math.tan(Math.toRadians(a));
                    Direction = new Vector(0, -1, -1 / tanA);
                }

        // Light
        System.out.println("Enter 1 for directional light, 2 for point light, 3 for spot light: ");
        l = scanner.nextInt();

        System.out.println("Enter file name: ");
        f = scanner.next();

        // Test
        System.out.println("Enter 1 for shadow test, 2 for recursive test or 3 for horse test: ");
        t = scanner.nextInt();

        if (t == 1) {

            Scene scene = new Scene();
            Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
            sphere.setShininess(20);
            sphere.setEmmission(new Color(0, 0, 100));

            scene.addGeometry(sphere);

            Triangle triangle1 = new Triangle(new Point3D(3500, 3500, -2000),
                    new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000));
            Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                    new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000));

            scene.addGeometry(triangle1);
            scene.addGeometry(triangle2);

            if (l == 1)
                scene.addLight(new DirectionalLight(new Color(r, g, b), Direction));
            else if (l == 2)
                scene.addLight(new PointLight(new Color(r, g, b), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
            else if (l == 3)
                scene.addLight(new SpotLight(new Color(r, g, b), new Point3D(200, 200, -100), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));

            ImageWriter imageWriter = new ImageWriter(f, 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        }

        else if (t == 2) {

            System.out.println("Enter recursive level:(1-3) ");
            w = scanner.nextInt();
            if (w == 1) {
                Scene scene = new Scene();
                scene.setScreenDistance(300);

                Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
                sphere.setShininess(20);
                sphere.setEmmission(new Color(0, 0, 100));
                sphere.setKt(0.5);
                scene.addGeometry(sphere);

                Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
                sphere2.setShininess(20);
                sphere2.setEmmission(new Color(100, 20, 20));
                sphere2.setKt(0);
                scene.addGeometry(sphere2);

                if (l == 1)
                    scene.addLight(new DirectionalLight(new Color(r, g, b), Direction));
                else if (l == 2)
                    scene.addLight(new PointLight(new Color(r, g, b), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
                else if (l == 3)
                    scene.addLight(new SpotLight(new Color(r, g, b), new Point3D(200, 200, -100), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));

                ImageWriter imageWriter = new ImageWriter(f, 500, 500, 500, 500);

                Render render = new Render(imageWriter, scene);

                render.renderImage();
                render.writeToImage();
            }
            else if (w == 2) {
                Scene scene = new Scene();
                scene.setScreenDistance(300);

                Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
                sphere.setShininess(20);
                sphere.setEmmission(new Color(0, 0, 100));
                sphere.setKt(0.5);
                scene.addGeometry(sphere);

                Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
                sphere2.setShininess(20);
                sphere2.setEmmission(new Color(100, 20, 20));
                sphere2.setKt(0);
                scene.addGeometry(sphere2);

                Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500),
                        new Point3D(-1500, 1500, -1500), new Point3D(200, 200, -375));
                Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500),
                        new Point3D(-1500, 1500, -1500), new Point3D(-1500, -1500, -1500));

                triangle.setEmmission(new Color(20, 20, 20));
                triangle2.setEmmission(new Color(20, 20, 20));
                triangle.setKr(1);
                triangle2.setKr(0.5);
                scene.addGeometry(triangle);
                scene.addGeometry(triangle2);

                if (l == 1)
                    scene.addLight(new DirectionalLight(new Color(r, g, b), Direction));
                else if (l == 2)
                    scene.addLight(new PointLight(new Color(r, g, b), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
                else if (l == 3)
                    scene.addLight(new SpotLight(new Color(r, g, b), new Point3D(200, 200, -100), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));

                ImageWriter imageWriter = new ImageWriter(f, 500, 500, 500, 500);

                Render render = new Render(imageWriter, scene);

                render.renderImage();
                render.writeToImage();
            }
            else if (w == 3) {
                Scene scene = new Scene();
                scene.setScreenDistance(300);

                Sphere sphere = new Sphere(300, new Point3D(0, 0, -1000));
                sphere.setShininess(20);
                sphere.setEmmission(new Color(0, 0, 100));
                sphere.setKt(0.5);
                scene.addGeometry(sphere);

                Sphere sphere2 = new Sphere(150, new Point3D(0, 0, -1000));
                sphere2.setShininess(20);
                sphere2.setEmmission(new Color(100, 20, 20));
                sphere2.setKt(0);
                scene.addGeometry(sphere2);

                Triangle triangle = new Triangle(new Point3D(2000, -1000, -1500),
                        new Point3D(-1000, 2000, -1500), new Point3D(700, 700, -375));
                Triangle triangle2 = new Triangle(new Point3D(2000, -1000, -1500),
                        new Point3D(-1000, 2000, -1500), new Point3D(-1000, -1000, -1500));

                triangle.setEmmission(new Color(20, 20, 20));
                triangle2.setEmmission(new Color(20, 20, 20));
                triangle.setKr(1);
                triangle2.setKr(0.5);
                scene.addGeometry(triangle);
                scene.addGeometry(triangle2);

                if (l == 1)
                    scene.addLight(new DirectionalLight(new Color(r, g, b), Direction));
                else if (l == 2)
                    scene.addLight(new PointLight(new Color(r, g, b), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
                else if (l == 3)
                    scene.addLight(new SpotLight(new Color(r, g, b), new Point3D(200, 200, -100), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));

                ImageWriter imageWriter = new ImageWriter(f, 500, 500, 500, 500);

                Render render = new Render(imageWriter, scene);

                render.renderImage();
                render.writeToImage();
            }
        }

        else if (t == 3) {
            Scene scene = new Scene(new AmbientLight(), new Color(0, 0, 0),
                    new Camera(new Point3D(0, 0, 10), new Vector(1, 1, 0), new Vector(0, 0, -1)), 50);
            scene.setScreenDistance(50);

            if (l == 1)
                scene.addLight(new DirectionalLight(new Color(r, g, b), Direction));
            else if (l == 2)
                scene.addLight(new PointLight(new Color(r, g, b), new Point3D(200, 200, -100), 0, 0.000001, 0.0000005));
            else if (l == 3)
                scene.addLight(new SpotLight(new Color(r, g, b), new Point3D(200, 200, -100), new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));

            Geometry[] shapes = {new Quadrangle(P(162, 309),P(77,325),P(71,338), P(150,355)),
               new Quadrangle(P(162,309 ),P(150,355 ), P(211,366 ), P(223,313 )),
               new Triangle(P(150,355 ),P(211,366 ),P(165,389 )),
               new Quadrangle(P(150,355 ),P(168,389 ),P(103,407 ), P(86,394 )),
               //new Triangle(P(168,389 ),P(103,407 ),P(245,403 )),
               new Triangle(P(168,389),P(245,403),P(211,366)),
               new Triangle(P(245,403),P(211,366),P(257,350)),
               new Triangle(P(211,366),P(257,350),P(223,313)),
               new Quadrangle(P(223,313),P(257,350),P(245,403), P(287,353)),
               new Triangle(P(223,313),P(287,353),P(380,206)),
               new Triangle(P(287,353),P(380,206),P(386,360)),
               new Triangle(P(380,206),P(386,360),P(455,264)),
               new Triangle(P(386,360),P(455,264),P(459,305)),
               new Triangle(P(288,360),P(263,397),P(294,408)),
               new Triangle(P(288,360),P(294,408),P(336,372)),
               new Quadrangle(P(294,408),P(336,372),P(353,390), P(313,442)),
               new Triangle(P(294,408),P(313,442),P(287,437)),
			   new Triangle(P(380,206),P(455,264),P(498,228)),
			   new Triangle(P(455,264),P(498,228),P(520,246)),
			   new Triangle(P(455,264),P(520,246),P(464,310)),
			   new Triangle(P(520,246),P(464,310),P(498,323)),
			   new Triangle(P(464,310),P(498,323),P(471,345)),
			   new Quadrangle(P(464,310),P(448,331),P(455,345), P(471,345)),
			   new Triangle(P(380,206),P(560,150),P(500,226)),
			   new Triangle(P(560,150),P(500,226),P(524,245)),
			   new Triangle(P(560,150),P(524,245),P(607,170)),
			   new Triangle(P(560,150),P(607,170),P(626,96)),
			   new Triangle(P(626,96),P(607,170),P(656,88)),
			   new Quadrangle(P(626,96),P(656,88),P(649,41), P(640,48)),
			   new Triangle(P(649,41),P(640,48),P(620,33))
               
            };
               for (int i = 0 ; i < shapes.length; i++)
               {
                   //shapes[i].setEmmission(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
                   if (i%2 == 0)
                        shapes[i].setEmmission(new Color(40+i*3,80+i*3,i));
                   else
                        shapes[i].setEmmission(new Color(60+i*3,100+i*3,i));
                   scene.addGeometry(shapes[i]);
               }
                
              ImageWriter imageWriter = new ImageWriter("Horse test", 702*2, 702*2, 702*2, 702*2);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		//render.printGrid(25);
		render.writeToImage();
        }

    }

 private static Point3D P(int x, int y)
      {
          return new Point3D(702-y,x-477,-50);
      }

}