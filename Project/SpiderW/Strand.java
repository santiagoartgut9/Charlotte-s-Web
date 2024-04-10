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
     * @Param startingPoint the starting point of the strand
     * @Param finalPoint  the final point of the strand
     * @Param _angle the angle from 0 to the strand
     * @Param _alpha the angle from the nearest strand to the strand
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
     * @Param color the color of the bridge
     * @Param distance the distance of the bridge from the origin of the strand
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
     * Relocates a bridge from the simulator
     * @Param color the color of the bridge to relocate
     * @Param distance the distance from the origin to relocate
     */
    public void relocateBridge(String color, int distance){
        delBridge(color);
        addBridge(color, distance);
    }
    
    /**
     * Deletes a Bridge from the simulator
     * @Param the color of the bridge to delete
     */
    public void delBridge(String color){
        for(Bridge b : bridgesLocal){
            if(b.checkBridge(color)){
                b.makeInvisible();
                bridgesLocal.remove(b);
                break;
            }
        }
    }
    
    /**
     * Adds a Spot to the simulator
     * @Param color the color of the spot
     */
    public void addSpot(String color){
        spot = new Spot(color, finalP);
        spot.makeVisible();
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
     * Make this Strand visible. If it was already visible, do nothing.z
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
    
    /**
     * Checks if the color of any of its bridges is the specified, false other case
     * @Param color the color to check
     */
    public boolean checkBridge(String color){
        if(bridgesLocal.size()>0) {
                for(Bridge b : bridgesLocal){
                    if(b.checkBridge(color)){
                        return true;
                    }
                }
            }
        return false;
    }
    
    /**
     * Checks if the color of the spot is the specified, false other case
     * @Param color the color to check
     */
    public boolean checkSpot(String color){
        if(spot!=null) {
                return spot.checkSpot(color);
            }
        return false;
    }
    
    /**
     * Checks if the a point is in the bridgeLocal's starting points
     * @Param point the point to check
     */
    public boolean pointInStartingBridgePoints(Point point){
        for(Bridge b : bridgesLocal){
                if( b.getStartingPoint() == point ){
                    return true;
                }
            }
        return false;
    }
    
    /**
     * Returns the closer bridge in the strand next to the specified starting point
     * @Param startingPoint the point to check
     */
    public Bridge closerBridgeInStrand(Point startingPoint){
        if(bridgesLocal.size() > 0){
            double lesserDistance = 9999999;
            Bridge closerBridge = null;
            for(Bridge b : bridgesLocal){
                double distance = startingPoint.distance(b.getStartingPoint());
                if( distance < lesserDistance ){
                    lesserDistance = distance;
                    closerBridge = b;
                }
            }
            return closerBridge;
        }
        else{
            return null;
        }
    }
    
    /**
     * Returns the closer bridge in the strand next to the specified starting point
     * @Param startingPoint the point to check
     */
    public Bridge closerBridgeInNextStrand(Point startingPoint){
        Bridge closerBridge = null;
        if(bridgesLocal.size() > 0){
            double lesserDistance = 9999999;
            for(Bridge b : bridgesLocal){
                double distance = startingPoint.distance(b.getFinalPoint());
                if( distance < lesserDistance ){
                    lesserDistance = distance;
                    closerBridge = b;
                }
            }
        }
        return closerBridge;
    }
    
    /**
     * Returns the final point of the strand
     */
    public Point getFinalPoint(){
        return finalP;
    }
    
    
}
