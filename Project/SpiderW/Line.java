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
     * @Param startingPoint its the initial point of the line
     * @Param finalPoint its the final point of the line
     * @Param color is the color of the line
     */
    public Line(Point startingPoint, Point finalPoint, String color){
        points = new Point[2];
        
        if(startingPoint.yCoordinate() <= finalPoint.xCoordinate()){
            points [0] = startingPoint;
            points [1] = finalPoint;
        }
        else{
            points[0] = finalPoint;
            points[1] = startingPoint;
        }
        
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
    
    /**
     * Move the line to a specific coordinate.
     * @param xPosition is the x coordinate where to move
     * @Param yPosition is the y coordinate where to move
     */
    public void moveTo(double xPosition, double yPosition){
        erase();

        double deltaX = xPosition - points[0].xCoordinate();
        double deltaY = yPosition - points[0].yCoordinate();

        points[0].moveTo(xPosition, yPosition);
        points[1].moveTo(points[1].xCoordinate() + deltaX, points[1].yCoordinate() + deltaY);

        draw();
    }
    
    /**
     * Obtains the x coordinate of the line
     */
    public double xCoordinate(){
        return points[0].xCoordinate();
    }
    
    /**
     * Obtains the y coordinate of the line
     */
    public double yCoordinate(){
        return points[0].yCoordinate();
    }
    
    public Point getStartingPoint(){
        return points[0];
    }
    
    
    /*
     * Draw the line with current specifications on screen.
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
     * Erase the line on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
