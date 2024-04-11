import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
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
    public static ArrayDeque<Object> usedBridges = new ArrayDeque<>();
    private HashMap<Integer, ArrayList<Bridge>> strandBridges = new HashMap<>();
    private int spidersit;
    private boolean hasexecuted = false;
    
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
     * Returns the list of bridges in the web identified by their color
     */
    public String[] bridges(){
        String[] a = new String[1];
        return a;
    }
    
    /**
     * ??
     * @Param color the color of the bridge
     */
    public int[] bridge(String color){
        int[] a = new int[1];
        return a;
    }
    
    /**
     * Returns an array with the spots actually in the web
     */
    public String[] spots(){
        String[] a = new String[1];
        return a;
    }
    
    /**
     * ??
     * @Param color the color of the spot
     */
    public int[] spot(String color){
        int[] a = new int[1];
        return a;
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
    
        public void spiderSit(int strand){
        spider.sit();
        spider.setStrand(strands.get(strand));
        this.spidersit = strand;
    }
    private boolean spiderWalk1(Bridge objective){
        if(objective!=null){
            usedBridges.add(objective);
            Point[] directionPoints = firstLast(objective);
            spider.switchDirection(directionPoints[0]);
            Point position0 = spider.getPosition();
            while(((int)Math.round(spider.getPosition().xCoordinate()) != (int) Math.round(directionPoints[0].xCoordinate())) && ((int)Math.round(spider.getPosition().yCoordinate()) != (int) Math.round(directionPoints[0].yCoordinate()))){
                spider.spiderWalk();
            }
            spider.switchDirection(directionPoints[1]);
            while(((int)Math.round(spider.getPosition().xCoordinate()) != (int) Math.round(directionPoints[1].xCoordinate())) && ((int)Math.round(spider.getPosition().yCoordinate()) != (int) Math.round(directionPoints[1].yCoordinate()))) {
                spider.spiderWalk();
            }
            Point position1 = spider.getPosition();
            int currentStrandIndex = strands.indexOf(spider.getStrand());
            if( ((position1.xCoordinate() <= position0.xCoordinate()) && (position0.yCoordinate() <= 240)) ){
                spider.setStrand( strands.get((currentStrandIndex + 1) % strands.size()) );
            }
            else if ( ((position1.xCoordinate() >= position0.xCoordinate()) && (position0.yCoordinate() >= 240)) ){
                spider.setStrand( strands.get((currentStrandIndex + 1) % strands.size()) );
            }
            else {
                spider.setStrand( strands.get((currentStrandIndex - 1 + strands.size()) % strands.size()) );
            }
            return spiderWalk1(searchSpiderNearestBridge());
        }
        else{
            return true;
        }
    }
    
    public void spiderWalk(boolean advance){
        if(advance){
            Bridge nearestBridge = searchSpiderNearestBridge();
            if(nearestBridge != null){
                boolean finished = spiderWalk1(nearestBridge);
                if(finished){
                    spiderWalk(advance);
                }
            }
        }else {
                Strand actualStrand = spider.getStrand();
                spider.moveTo(actualStrand.getStartingPoint().xCoordinate(), actualStrand.getStartingPoint().yCoordinate());
            }
        }
        
    public Bridge searchSpiderNearestBridge(){
        int i = strands.indexOf(spider.getStrand());
        Bridge closerBridge = null;
        Point spiderPos = spider.getPosition();
        
        Strand actualStrand = strands.get(spidersit);
        Strand previousStrand = (spidersit != 0) ? strands.get(spidersit-1) : strands.get(strands.size() - 1);
        
        Bridge bridge1 = actualStrand.closerBridgeInStrand(spiderPos);
        Bridge bridge2 = previousStrand.closerBridgeInNextStrand(spiderPos);
        
        if ( (bridge1 != null) && (bridge2 == null) && !usedBridges.contains(bridge1)) {
            closerBridge = bridge1;
            spidersit = (spidersit + 1) % strands.size(); // Actualiza spidersit
        }
        else if ( (bridge1 == null) && (bridge2 != null) && !usedBridges.contains(bridge2)) {
            closerBridge = bridge2;
            spidersit = (spidersit - 1 + strands.size()) % strands.size(); // Actualiza spidersit
        }
        else if ( (bridge1 != null) && (bridge2 != null) ) {
            if (!usedBridges.contains(bridge1) && !usedBridges.contains(bridge2)) {
                closerBridge = ( ((bridge1.getStartingPoint()).distance(spiderPos)) <= ((bridge2.getFinalPoint()).distance(spiderPos)) ) ? bridge1 : bridge2;
                if (closerBridge == bridge1) {
                    spidersit = (spidersit + 1) % strands.size(); // Actualiza spidersit
                } else {
                    spidersit = (spidersit - 1 + strands.size()) % strands.size(); // Actualiza spidersit
                }
            } else if (!usedBridges.contains(bridge1)) {
                closerBridge = bridge1;
                spidersit = (spidersit + 1) % strands.size(); // Actualiza spidersit
            } else if (!usedBridges.contains(bridge2)) {
                closerBridge = bridge2;
                spidersit = (spidersit - 1 + strands.size()) % strands.size(); // Actualiza spidersit
            }
        }
        
        // Si no se encontró un puente cercano, mueve a la araña al final del Strand
        if (closerBridge == null && !hasexecuted) {
            actualStrand = strands.get(spidersit);
            spider.moveTo(actualStrand.getFinalPoint().xCoordinate(), actualStrand.getFinalPoint().yCoordinate());
            hasexecuted = true;
        }
        return closerBridge;
    }
    
        
        public Point[] firstLast(Bridge bridge){
            Point[] ordenatedPoints = new Point[2];
        
        
            // Verifica que closerBridge no sea null antes de intentar acceder a getStartingPoint()
            if ( (spider.getStrand()).pointInStartingBridgePoints(bridge.getStartingPoint()) ){
                ordenatedPoints[0] = bridge.getStartingPoint();
                ordenatedPoints[1] = bridge.getFinalPoint();
            }
            else{
                ordenatedPoints[0] = bridge.getFinalPoint();
                ordenatedPoints[1] = bridge.getStartingPoint();
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
