import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;

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
    private Spider spider;
    
    public void spiderSit(int strand){
        spider.sit();
        spider.setStrand(strands.get(strand));
    }
    
    private boolean spiderWalk(Bridge objective){
        if(objective!=null){
            Point[] directionPoints = firstLast(objective);
            spider.switchDirection(directionPoints[0]);
            Point position0 = spider.getPosition();
            while(!((spider.getPosition()).equals(directionPoints[0]))){
                spider.spiderWalk();
            }
            spider.switchDirection(directionPoints[1]);
            while(!((spider.getPosition()).equals(directionPoints[1]))){
                spider.spiderWalk();
            }
            Point position1 = spider.getPosition();
            if( ((position1.xCoordinate() <= position0.xCoordinate()) && (position0.yCoordinate() <= 240)) ){
                spider.setStrand( strands.get((strands.indexOf(spider.getStrand())) + 1) );
            }
            else if ( ((position1.xCoordinate() >= position0.xCoordinate()) && (position0.yCoordinate() >= 240)) ){
                spider.setStrand( strands.get((strands.indexOf(spider.getStrand())) + 1) );
            }
            else { spider.setStrand( strands.get((strands.indexOf(spider.getStrand())) - 1) ); }
            return spiderWalk(searchSpiderNearestBridge());
        }
        else{
            Point direction = spider.getActualStrandSpot();
            while(!((spider.getPosition()).equals(direction))){
                spider.spiderWalk();
            }
            return true;
        }
    }
    
    public void spiderWalk(boolean advance){
        if(advance){
            boolean finished = spiderWalk(searchSpiderNearestBridge());
        }
    }
    
    public Bridge searchSpiderNearestBridge(){
        int i = strands.indexOf(spider.getStrand());
        Bridge closerBridge = null;
        Point spiderPos = spider.getPosition();
        
        Strand actualStrand = strands.get(i);
        Strand previousStrand = (i != 0) ? strands.get(i-1) : strands.get(strands.size() - 1);
        
        Bridge bridge1 = actualStrand.closerBridgeInStrand(spiderPos);
        Bridge bridge2 = previousStrand.closerBridgeInNextStrand(spiderPos);
        
        if ( (bridge1 != null) && (bridge2 == null) ) { closerBridge = bridge1; }
        else if ( (bridge1 == null) && (bridge2 != null) ) { closerBridge = bridge2; }
        else if ( (bridge1 == null) && (bridge2 == null) ) { }
        else{
            closerBridge = ( ((bridge1.getStartingPoint()).distance(spiderPos)) <= ((bridge2.getFinalPoint()).distance(spiderPos)) ) ? bridge1 : bridge2;
        }
        
        return closerBridge;
    }
    
    public Point[] firstLast(Bridge bridge){
        Point[] ordenatedPoints = new Point[2];
        
        Bridge closerBridge = searchSpiderNearestBridge();
        
        if ( (spider.getStrand()).pointInStartingBridgePoints(closerBridge.getStartingPoint()) ){
            ordenatedPoints[0] = closerBridge.getStartingPoint();
            ordenatedPoints[1] = closerBridge.getFinalPoint();
        }
        else{
            ordenatedPoints[0] = closerBridge.getFinalPoint();
            ordenatedPoints[1] = closerBridge.getStartingPoint();
        }
        
        return ordenatedPoints;
    }
    
    /**
     * Check if a point belongs to a spot of the web
     * @Param _point the point to check
     */
    public static boolean pointIsSpot(Point _point){
        for(Point p : spots.values()){
            if(p.equals(_point)){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Create the web with a specific quantity of strands and a radius
     * @Param _strands quantity of strands in the web
     * @Param radius radius of the strands
     */
    public Web(int _strands, int radius){
        origin = new Point(427, 240);
        fillStrands(_strands, radius);
        spider = new Spider(this, strands.get(0));
        
        isVisible = false;
    }
    
    /**
     * Adds a bridge to the simulator
     * @Param color color of the bridge
     * @Param distance the distance of the bridge from the origin
     * @Param firstStrand the strand with the origin of the bridge
     */
    public void addBridge(String color, int distance, int firstStrand){
        // Verificar que el puente no existe ya
        strands.get(firstStrand).addBridge(color, distance);
    }
    
    /**
     * Deletes a bridge from the simulator
     * @Param color the color of the bridge to delete
     */
    public void delBridge(String color){
        bridges.remove(color);
        for(Strand s : strands){
            if(s.checkBridge(color)){
                s.delBridge(color);
            }
        }
    }
    
    /**
     * Relocates a bridge from the simulator
     * @Param color the color ofthe bridge to relocate
     * @Param distance the new distance from the origin
     */
    public void relocateBridge(String color, int distance){
        bridges.remove(color);
        for(Strand s : strands){
            if(s.checkBridge(color)){
                s.relocateBridge(color, distance);
            }
        }
    }
    
    /**
     * Adds a spot to the simulator
     * @Param color the color of the spot
     * @Param strand the strand where the spot is
     */
    public void addSpot(String color, int strand){
        // Verificar que no exista
        strands.get(strand).addSpot(color);
    }
    
    /**
     * Deletes a spot from the simulator
     * @Param color the color of the spot to delete
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
     * @Param _strands quantity of strands
     * @Param radius radius of the strands
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
            spider.makeVisible();
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
            spider.makeVisible();
        }
    }
    
    
    
    /**
     * Calculate the vertical coordinate of a vector with his angle and radius
     * @Param angle the angle of the vector
     * @Param radius the radius of the vector
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
     * @Param angle the angle of the vector
     * @Param radius the radius of the vector
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
