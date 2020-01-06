
import edu.duke.*;
import java.io.*;

/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatchInversions {

    public static final void print(Object x) { System.out.println(x); }
    //I started with the image I wanted (inImage)
    public ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            //set pixel's red pixel to inverted
            pixel.setRed(255 - inPixel.getRed());
            //set pixel's green to inverted
            pixel.setGreen(255 - inPixel.getGreen());
            //set pixel's blue to inverted
            pixel.setBlue(255 - inPixel.getBlue());
        }
        //outImage is your answer
        return outImage;
    }
    // Select and convert multipe files to to gray
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            ImageResource invertedImage = makeInversion(inImage);
            invertedImage.setFileName(newName);
            invertedImage.draw();
	    invertedImage.save();
        }
    }
    // Convert a single file to gray
    public void testInverted() {
        ImageResource ir = new ImageResource();
        ImageResource invert = makeInversion(ir);
        invert.draw();
    }
}

