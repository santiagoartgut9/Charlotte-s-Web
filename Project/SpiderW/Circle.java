import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 * @modified (03 March 2024) by Sebastian Galvis & Santiago Arteaga
 */

public class Circle{

    public static final double PI=3.1416;
    
    private double diameter;
    private double xPosition;
    private double yPosition;
    private String color;
    private boolean isVisible;
    
    /**
     * Default constructor for circle
     */
    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
    
    /**
     * New constructor for circle
     * @param  xCoord  the coordinate on x of the circle
     * @param  yCoord  the coordinate on x of the circle
     * @param  _color  the color of the circle
     */
    public Circle(double xCoord, double yCoord, String _color){
        diameter = 10;
        xPosition = xCoord-(diameter/2);
        yPosition = yCoord-(diameter/2);
        color = _color;
        isVisible = false;
    }
    
    /**
     * New constructor for circle
     * @param  xCoord  the coordinate on x of the circle
     * @param  yCoord  the coordinate on x of the circle
     * @param  _color  the color of the circle
     * @param _diameter the diameter of the circle
     */
    public Circle(double xCoord, double yCoord, String _color, double _diameter){
        diameter = _diameter;
        xPosition = xCoord-(diameter/2);
        yPosition = yCoord-(diameter/2);
        color = _color;
        isVisible = false;
    }


    /**
     * Make this circle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this line invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        //xPosition = xPosition-(diameter/2);
        //yPosition = yPosition-(diameter/2);
        draw();
    }

    /**
     * Change the color. 
     * @param newColor the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * Move the circle to a specific coordinate.
     * @param xCoord the x coordinate where to move
     * @param yCoord the y coordinate where to move
     */
    public void moveTo(double xCoord, double yCoord){
        erase();
        xPosition = xCoord;
        yPosition = yCoord;
        draw();
    }
    
    /**
     * Obtains the x coordinate of the circle
     */
    public double xCoordinate(){
        return xPosition;
    }
    
    /**
     * Obtains the y coordinate of the circle
     */
    public double yCoordinate(){
        return yPosition;
    }
    
    
    /*
     * Draw the circle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double((int)xPosition, (int)yPosition, 
                (int)diameter, (int)diameter));
            canvas.wait(10);
        }
    }
    
    /*
     * Erase the circle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

}
