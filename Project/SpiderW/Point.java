/**
 * Point class for SpiderWeb
 * 
 * @author Sebastian Galvis Briceño
 * @author Santiago Arteaga
 * @version (03 March 2024)
 */

public class Point
{
    private double xCoord;
    private double yCoord;
    private Circle representation;
    boolean isVisible;
    
    /**
     * Creates a point on an exact position
     */
    public Point(double _xCoord, double _yCoord){
        xCoord = _xCoord;
        yCoord = _yCoord;
        representation = new Circle(_xCoord, _yCoord, "black");
        isVisible = false;
    }
    
    /**
     * Changes the color of the Point's representation
     */
    public void changeColor(String color){
        representation.changeColor(color);
    }
    
    /**
     * Obtains the distance between this point and another point
     */
    public double distance(Point otherPoint){
        double answer;
        double x2 = otherPoint.xCoordinate();
        double y2 = otherPoint.yCoordinate();
        
        answer = Math.sqrt(Math.pow((x2-xCoord), 2) + Math.pow((y2-yCoord), 2));
        
        return answer;
    }
    
    /**
     * Obtains the x coordinate of the point
     */
    public double xCoordinate(){
        return xCoord;
    }
    
    /**
     * Obtains the y coordinate of the point
     */
    public double yCoordinate(){
        return yCoord;
    }
    
    /**
     * Makes the point's representation visible
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Makes the point's representation invisible
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Draws the point's representation
     */
    private void draw(){
        if(isVisible) {
            representation.makeVisible();
        }
    }
    
    /**
     * Erases the point's representation
     */
    private void erase(){
        if(isVisible) {
            representation.makeInvisible();
        }
    }
}
