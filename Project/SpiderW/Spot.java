/**
 * A Spot for the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */

public class Spot{
    
    private Point point;
    private String color;
    private boolean isVisible;
    
    /**
     * Create a line with a specific starting point and a final one
     */
    public Spot(String _color, Point _point){
        point = _point;
        color = _color;
        point.changeColor(color);
        Web.spots.put(color, point);
        isVisible = false;
    }
    
    /**
     * Checks if the color of the actual spot its the specified, false in other case.
     */
    public boolean checkSpot(String _color){
        if(color == _color){
            return true;
        }
        return false;
    }
    
    /**
     * Make this spot visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this spot invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /*
     * Draw the spot with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            point.makeVisible();
        }
    }

    /*
     * Erase the spot on screen.
     */
    private void erase(){
        if(isVisible) {
            point.makeInvisible();
        }
    }
}
