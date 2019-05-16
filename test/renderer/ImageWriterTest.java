package renderer;

import java.awt.Color;
import java.util.Random;
import org.junit.Test;
//import static org.junit.Assert.*;


public class ImageWriterTest {

	@Test
	public void writeImageTest(){
		
	ImageWriter imageWriter = new ImageWriter("Image writer test", 450, 300, 1, 1);
              //Random rand = new Random();		
		for(int i = 0; i< imageWriter.getHeight()/2; i++)
                    for (int j = 0; j < imageWriter.getHeight(); j++)
                        imageWriter.writePixel(i, j, 0, 135, 81); // green
                for(int i = imageWriter.getHeight()/2; i< imageWriter.getWidth(); i++)
                    for (int j = 0; j < imageWriter.getHeight()/2; j++)
                        imageWriter.writePixel(i, j, 252, 209, 22); // Yellow
                for(int i = imageWriter.getHeight()/2; i< imageWriter.getWidth(); i++)
                    for (int j = imageWriter.getHeight()/2; j < imageWriter.getHeight(); j++)
                        imageWriter.writePixel(i, j, 232, 17, 45); // red
	
		imageWriter.writeToimage();

	} 
}
