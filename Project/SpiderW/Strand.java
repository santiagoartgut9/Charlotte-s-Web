import java.util.ArrayList;
/**
 * A Strand for the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
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
     * Create a line with a specific starting point and a final one
     */
    public Strand(Point startingPoint, Point finalPoint, double _angle, double _alpha){
        line = new Line(startingPoint, finalPoint, "black");
        finalP = finalPoint;
        angle = _angle;
        alpha = _alpha;
        
        isVisible = false;
    }
    
    public void addBridge(String color, int distance){
        double radius = distance;
        double x1 = calculateHorizontal(angle, radius);
        double x2 = calculateHorizontal(angle+alpha, radius);
        double y1 = calculateVertical(angle, radius);
        double y2 = calculateVertical(angle+alpha, radius);
        
        Point bridgeInitialPoint = new Point(x1, y1);
        Point bridgeFinalPoint = new Point(x2, y2);
        
        bridgesLocal.add(new Bridge(bridgeInitialPoint,bridgeFinalPoint,color));
        
        bridgesLocal.get((bridgesLocal.size())-1).makeVisible();
        
        Web.bridges.put(color, bridgesLocal.get((bridgesLocal.size())-1));
    }
    
    private double calculateHorizontal(double angle, double radius){
        double answer = 0;
        if( angle > 0 && angle < Math.PI/2){
            answer = ((radius*Math.cos(angle))+427);
        }
        else if( angle > Math.PI/2 && angle < Math.PI){
            answer = (427-(radius*Math.cos(Math.PI-angle)));
        }
        else if( angle > Math.PI/2 && angle < 3*Math.PI/2){
            answer = (427-(radius*Math.cos(angle-Math.PI)));
        }
        else if( angle > 3*Math.PI/2){
            answer = (427+(radius*Math.cos(2*Math.PI-angle)));
        }
        else if(angle == 0 || angle == Math.PI){
            answer = 427+(Math.cos(angle)*radius);
        }
        else if( angle == Math.PI/2 || angle == 3*Math.PI/2){
            answer = (427.);
        }
        return answer;
    }
    
    private double calculateVertical(double angle, double radius){
        double answer = 0;
        if( angle > 0 && angle < Math.PI/2){
                answer = (240-(radius*Math.sin(angle)));
            }
            else if( angle > Math.PI/2 && angle < Math.PI){
                answer = (240-(radius*Math.sin(Math.PI-angle)));
            }
            else if( angle > Math.PI/2 && angle < 3*Math.PI/2){
                answer = (240+(radius*Math.sin(angle-Math.PI)));
            }
            else if( angle > 3*Math.PI/2){
                answer = (240+(radius*Math.sin(2*Math.PI-angle)));
            }
            else if( angle == 0 || angle == Math.PI ){
                answer = (240.);
            }
            else if(angle == Math.PI/2 || angle == 3*Math.PI/2){
                answer = 240+(-1*Math.cos(angle-(Math.PI/2)))*radius;
            }
        return answer;
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
            for(Bridge b : bridgesLocal){
                b.makeVisible();
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
            for(Bridge b : bridgesLocal){
                b.makeInvisible();
            }
        }
    }
}
