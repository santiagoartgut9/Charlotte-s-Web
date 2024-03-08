/**
 * A Strand for the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 */

public class Strand{
    
    private Line line;
    private Point finalP;
    private boolean isVisible;
    private Spot spot;
    
    /**
     * Create a line with a specific starting point and a final one
     */
    public Strand(Point startingPoint, Point finalPoint){
        line = new Line(startingPoint, finalPoint, "black");
        finalP = finalPoint;
        
        isVisible = false;
    }
    
    
    public void addBridge(String color, int distance){
        double radius = distance;
        
    }
    
    public void addSpot(String color){
        spot = new Spot(color, finalP);
        spot.makeVisible();
    }
    
    public boolean checkSpot(String color){
        if(spot!=null) {
                return spot.checkSpot(color);
            }
        return false;
    }
    
    public void delSpot(){
        if(spot!=null) {
                spot.makeInvisible();
                spot = null;
            }
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
            line.makeVisible();
            if(spot!=null) {
                spot.makeVisible();
            }
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            line.makeInvisible();
            if(spot!=null) {
                spot.makeInvisible();
            }
        }
    }
}
