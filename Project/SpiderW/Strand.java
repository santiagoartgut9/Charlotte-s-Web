import java.util.ArrayList;
/**
 * Strand Class for the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */

public class Strand{
    
    private Line line;
    private Point finalP;
    private double angle;
    private double alpha;
    private boolean isVisible;
    private Spot spot;
    private ArrayList<Bridge> bridgesLocal = new ArrayList<>();
    
    /**
     * Create a Strand with a specific starting point and a final one, almacenates the angle of the strand and the initial alpha
     */
    public Strand(Point startingPoint, Point finalPoint, double _angle, double _alpha){
        line = new Line(startingPoint, finalPoint, "black");
        finalP = finalPoint;
        angle = _angle;
        alpha = _alpha;
        
        isVisible = false;
    }
    
    /**
     * Adds a Bridge to the simulator
     */
    public void addBridge(String color, int distance){
        double radius = distance;
        double x1 = Web.calculateHorizontal(angle, radius);
        double x2 = Web.calculateHorizontal(angle+alpha, radius);
        double y1 = Web.calculateVertical(angle, radius);
        double y2 = Web.calculateVertical(angle+alpha, radius);
        
        Point bridgeInitialPoint = new Point(x1, y1);
        Point bridgeFinalPoint = new Point(x2, y2);
        
        bridgesLocal.add(new Bridge(bridgeInitialPoint,bridgeFinalPoint,color));
        
        bridgesLocal.get((bridgesLocal.size())-1).makeVisible();
        
        Web.bridges.put(color, bridgesLocal.get((bridgesLocal.size())-1));
    }
    
    
    /**
     * Adds a Spot to the simulator
     */
    public void addSpot(String color){
        spot = new Spot(color, finalP);
        spot.makeVisible();
    }
    
    /**
     * Checks if the color of the spot is the specified, false other case
     */
    public boolean checkSpot(String color){
        if(spot!=null) {
                return spot.checkSpot(color);
            }
        return false;
    }
    
    /**
     * Deletes a Spot from the simulator
     */
    public void delSpot(){
        if(spot!=null) {
                spot.makeInvisible();
                spot = null;
            }
    }
    
    /**
     * Make this Strand visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this Strand invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /*
     * Draw the strand with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            line.makeVisible();
            if(spot!=null) {
                spot.makeVisible();
            }
            for(Bridge b : bridgesLocal){
                b.makeVisible();
            }
        }
    }

    /*
     * Erase the strand on screen.
     */
    private void erase(){
        if(isVisible) {
            line.makeInvisible();
            if(spot!=null) {
                spot.makeInvisible();
            }
            for(Bridge b : bridgesLocal){
                b.makeInvisible();
            }
        }
    }
}
