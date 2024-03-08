import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Web of the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 */

public class Web{
    
    private ArrayList<Strand> strands = new ArrayList<>();
    public static HashMap<String, Bridge> bridges = new HashMap<>();
    public static HashMap<String, Point> spots = new HashMap<>();
    private Point origin;
    private boolean isVisible;
    
    /**
     * Create a line with a specific starting point and a final one
     */
    public Web(int _strands, int radius){
        origin = new Point(427, 240);
        fillStrands(_strands, radius);
        
        isVisible = false;
    }
    
    public void fillStrands(int _strands, double radius){ // IMPORTANTE DIVIDIR ENTRE MÁS MÉTODOS, ESTE ESTÁ MUY LARGO
        double alpha = 2*Math.PI/_strands;
        double limit = 2 * Math.PI;
        
        ArrayList<Double> angles = new ArrayList<>();

        for (double angle = 0; angle < limit; angle += alpha) {
            angles.add(angle);
        }
        
        ArrayList<Double> coordinates = new ArrayList<>();

        for (double angle : angles){
            if( angle > 0 && angle < Math.PI/2){
                coordinates.add((radius*Math.cos(angle))+427);
                coordinates.add(240-(radius*Math.sin(angle)));
            }
            else if( angle > Math.PI/2 && angle < Math.PI){
                coordinates.add(427-(radius*Math.cos(Math.PI-angle)));
                coordinates.add(240-(radius*Math.sin(Math.PI-angle)));
            }
            else if( angle > Math.PI/2 && angle < 3*Math.PI/2){
                coordinates.add(427-(radius*Math.cos(angle-Math.PI)));
                coordinates.add(240+(radius*Math.sin(angle-Math.PI)));
            }
            else if( angle > 3*Math.PI/2){
                coordinates.add(427+(radius*Math.cos(2*Math.PI-angle)));
                coordinates.add(240+(radius*Math.sin(2*Math.PI-angle)));
            }
            else if( angle == 0){
                coordinates.add(427+radius);
                coordinates.add(240.);
            }
            else if( angle == Math.PI/2){
                coordinates.add(427.);
                coordinates.add(240-radius);
            }
            else if( angle == Math.PI){
                coordinates.add(427-radius);
                coordinates.add(240.);
            }
            else if( angle == 3*Math.PI/2){
                coordinates.add(427.);
                coordinates.add(240+radius);
            }
        }
        
        int longitud = angles.size();
        int cont = 0;
        for (int i = 0; i < longitud ; i++) {
            Point finalPoint = new Point (coordinates.get(cont), coordinates.get(cont+1));
            strands.add(new Strand(origin, finalPoint,angles.get(i), alpha));
            cont = cont + 2;
        }
    }
    
    public void addBridge(String color, int distance, int firstStrand){
        strands.get(firstStrand).addBridge(color, distance);
    }
    
    public void addSpot(String color, int strand){
        strands.get(strand).addSpot(color);
    }
    
    public void delSpot(String color){
        spots.remove(color);
        for(Strand s : strands){
            if(s.checkSpot(color)){
                s.delSpot();
            }
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
            for(Strand s : strands){
                s.makeVisible();
            }
            origin.makeVisible();
        }
    }

    /*
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            for(Strand s : strands){
                s.makeInvisible();
            }
            origin.makeInvisible();
        }
    }
}
