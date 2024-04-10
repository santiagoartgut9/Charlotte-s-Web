/**
 * The main class of Spider Web simulator
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */
public class SpiderWeb
{
    private boolean isVisible;
    private Web web;
    
    
    
    /**
     * Create the simulator with a specific quantity of strands and a radius
     * @Param strands quantity of strands
     * @Param radius radius of the strands
     */
    public SpiderWeb(int strands, int radius){
        web = new Web(strands, radius);
        isVisible = false;
    }
    
    
    
    /**
     * Adds a bridge to the simulator
     * @Param color the color of the bridge
     * @Param distance distance of the bridge from the origin
     * @Param firstStrand strand who have the starting point of the bridge
     */
    public void addBridge(String color, int distance, int firstStrand){
        web.addBridge(color, distance, firstStrand);
    }
    
    /**
     * Relocates a bridge from the simulator
     * @Param color color of the bridge to relocate
     * @Param distance distance from the origin to relocate the bridge
     */
    public void relocateBridge(String color, int distance){
        web.relocateBridge(color, distance);
    }
    
    /**
     * Deletes a bridge from the simulator
     * @Param color color of the bridge to be deleted
     */
    public void delBridge(String color){
        web.delBridge(color);
    }
    
    /**
     * Adds a spot to the simulator
     * @Param color color of the spot
     * @Param strand strand who have the spot
     */
    public void addSpot(String color, int strand){
        web.addSpot(color, strand);
    }
    
    /**
     * Deletes a spot from the simulator
     * @Param color color of the spot
     */
    public void delSpot(String color){
        web.delSpot(color);
    }
    
    /**
     * Sits the spider in the strand specified
     * @Param strand the strand to sit the spider
     */
    public void spiderSit(int strand){
        web.spiderSit(strand);
    }
    
    /**
     * Makes the spider walk
     * @Param advance boolean
     */
    public void spiderWalk(boolean advance){
        web.spiderWalk(advance);
    }
    
    /**
     * Returns the last path of the spider
     */
    public int[] spiderLastPath(){
        int[] a = new int[1];
        return a;
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
    
    /**
     * Finish the simulator
     */
    public void finish(){
        makeInvisible();
    }
    
    /**
     * Returns if the last operation did correctly
     */
    public void ok(){
        //??
    }
    
    /**
     * Prints simulator's information
     */
    public void getSimulatorInformation(){
        //??
    }
    
    /*
     * Draw the Web with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            web.makeVisible();
        }
    }

    /*
     * Erase the Web on screen.
     */
    private void erase(){
        web.makeInvisible();
    }
}
