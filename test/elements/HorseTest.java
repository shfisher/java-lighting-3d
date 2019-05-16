package elements;

import geometries.Geometry;
import geometries.Quadrangle;
import geometries.Triangle;
import primitives.Point3D;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import org.junit.Test;


import java.awt.*;

public class HorseTest {


 public Point3D P(int x, int y)
      {
          return new Point3D(650-y,x-650,-50);
      }
        
      @Test 
	public void HorseTest(){
		
		Scene scene = new Scene();
              	scene.setScreenDistance(50);
 	
//  new Triangle(P(,),P(,),P(,)),
//  new Quadrangle(P(,),P(,),P(,), P(,)),
               
                             
              Geometry [] shapes = {
               new Quadrangle(P(162, 309),P(77,325),P(71,338), P(150,355)),
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
                
               // x = 650 - x (picture)
               // y = y (picture) - 650
               for (int i = 0 ; i < shapes.length; i++)
               {
                   //shapes[i].setEmmission(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
                   if (i%2 == 0)
                        shapes[i].setEmmission(new Color(40+i*3,80+i*3,i));
                   else
                        shapes[i].setEmmission(new Color(60+i*3,100+i*3,i));
                   scene.addGeometry(shapes[i]);
               }
                
              ImageWriter imageWriter = new ImageWriter("Horse test", 1300, 1300, 1800, 1800);
		
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		//render.printGrid(25);
		render.writeToImage();
        }
}