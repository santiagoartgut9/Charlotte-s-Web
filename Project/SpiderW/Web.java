import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Web of the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */

public class Web{
    
    private ArrayList<Strand> strands = new ArrayList<>();
    public static HashMap<String, Bridge> bridges = new HashMap<>();
    public static HashMap<String, Point> spots = new HashMap<>();
    public static Point origin;
    private boolean isVisible;
    
    
    
    /**
     * Create the web with a specific quantity of strands and a radius
     */
    public Web(int _strands, int radius){
        origin = new Point(427, 240);
        fillStrands(_strands, radius);
        
        isVisible = false;
    }
    
    /**
     * Adds a bridge to the simulator
     */
    public void addBridge(String color, int distance, int firstStrand){
        strands.get(firstStrand).addBridge(color, distance);
    }
    
    /**
     * Adds a spot to the simulator
     */
    public void addSpot(String color, int strand){
        strands.get(strand).addSpot(color);
    }
    
    /**
     * Deletes a spot from the simulator
     */
    public void delSpot(String color){
        spots.remove(color);
        for(Strand s : strands){
            if(s.checkSpot(color)){
                s.delSpot();
            }
        }
    }
    
    /**
     * Make the Web visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make the Web invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Fill the strands ArrayList with each strand of the web
     */
    public void fillStrands(int _strands, double radius){ // Pese a que ocupa una pantalla, podría ocupar menos si se divide bien
        double alpha = 2*Math.PI/_strands;
        double limit = 2 * Math.PI;
        
        ArrayList<Double> angles = new ArrayList<>();

        for (double angle = 0; angle < limit; angle += alpha) {
            angles.add(angle);
        }
        
        ArrayList<Double> coordinates = new ArrayList<>();

        for (double angle : angles){
            coordinates.add(calculateHorizontal(angle, radius));
            coordinates.add(calculateVertical(angle, radius));
        }
        
        int longitud = angles.size();
        int cont = 0;
        for (int i = 0; i < longitud ; i++) {
            Point finalPoint = new Point (coordinates.get(cont), coordinates.get(cont+1));
            strands.add(new Strand(origin, finalPoint,angles.get(i), alpha));
            cont = cont + 2;
        }
    }

    
    
    /*
     * Draw the Web with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            for(Strand s : strands){
                s.makeVisible();
            }
            origin.makeVisible();
        }
    }

    /*
     * Erase the Web on screen.
     */
    private void erase(){
        if(isVisible) {
            for(Strand s : strands){
                s.makeInvisible();
            }
            origin.makeInvisible();
        }
    }
    
    
    
    /**
     * Calculate the vertical coordinate of a vector with his angle and radius
     */
    public static double calculateVertical(double angle, double radius){
        double base = origin.yCoordinate();
        double answer = 0;
        if( angle > 0 && angle < Math.PI/2){
                answer = (base-(radius*Math.sin(angle)));
            }
            else if( angle > Math.PI/2 && angle < Math.PI){
                answer = (base-(radius*Math.sin(Math.PI-angle)));
            }
            else if( angle > Math.PI/2 && angle < 3*Math.PI/2){
                answer = (base+(radius*Math.sin(angle-Math.PI)));
            }
            else if( angle > 3*Math.PI/2){
                answer = (base+(radius*Math.sin(2*Math.PI-angle)));
            }
            else if( angle == 0 || angle == Math.PI ){
                answer = (base);
            }
            else if(angle == Math.PI/2 || angle == 3*Math.PI/2){
                answer = base+(-1*Math.cos(angle-(Math.PI/2)))*radius;
            }
        return answer;
    }
    
    /**
     * Calculate the horizontal coordinate of a vector with his angle and radius
     */
    public static double calculateHorizontal(double angle, double radius){
        double base = origin.xCoordinate();
        double answer = 0;
        if( angle > 0 && angle < Math.PI/2){
            answer = ((radius*Math.cos(angle))+base);
        }
        else if( angle > Math.PI/2 && angle < Math.PI){
            answer = (base-(radius*Math.cos(Math.PI-angle)));
        }
        else if( angle > Math.PI/2 && angle < 3*Math.PI/2){
            answer = (base-(radius*Math.cos(angle-Math.PI)));
        }
        else if( angle > 3*Math.PI/2){
            answer = (base+(radius*Math.cos(2*Math.PI-angle)));
        }
        else if(angle == 0 || angle == Math.PI){
            answer = base+(Math.cos(angle)*radius);
        }
        else if( angle == Math.PI/2 || angle == 3*Math.PI/2){
            answer = (base);
        }
        return answer;
    }
}
