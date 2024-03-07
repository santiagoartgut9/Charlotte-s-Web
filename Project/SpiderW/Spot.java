/**
 * A Strand for the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 */

public class Spot{
    
    private Point point;
    private boolean isVisible;
    
    /**
     * Create a line with a specific starting point and a final one
     */
    public Spot(String color, Point _point){
        point = _point;
        point.changeColor(color);
        Web.spots.put(color, point);
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

    /*
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            point.makeVisible();
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            point.makeInvisible();
        }
    }
}
