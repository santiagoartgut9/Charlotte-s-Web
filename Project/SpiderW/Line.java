import java.awt.*;

/**
 * A line that can be manipulated and that draws itself on a canvas.
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */

public class Line{
    
    private Point[] points;
    private String color;
    private boolean isVisible;
    
    /**
     * Create a line with a specific starting point and a final one
     * @Param startingPoint its a point than .....
     */
    public Line(Point startingPoint, Point finalPoint, String color){
        points = new Point[2];
        points[0] = startingPoint;
        points[1] = finalPoint;
        
        changeColor(color);
        
        isVisible = false;
    }
    
    /**
     * Make this line visible. If it was already visible, do nothing.
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
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { (int)points[0].xCoordinate(), (int)points[1].xCoordinate() };
            int[] ypoints = { (int)points[0].yCoordinate(), (int)points[1].yCoordinate() };
            canvas.draw(this, color, new Polygon(xpoints, ypoints, 2));
            canvas.wait(10);
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
