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
