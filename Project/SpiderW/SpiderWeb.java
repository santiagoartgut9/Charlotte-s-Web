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
     */
    public SpiderWeb(int strands, int radius){
        web = new Web(strands, radius);
        isVisible = false;
    }
    
    
    
    /**
     * Adds a bridge to the simulator
     */
    public void addBridge(String color, int distance, int firstStrand){
        web.addBridge(color, distance, firstStrand);
    }
    
    /**
     * Relocates a bridge from the simulator
     */
    public void relocateBridge(String color, int distance){
        web.relocateBridge(color, distance);
    }
    
    /**
     * Deletes a bridge from the simulator
     */
    public void delBridge(String color){
        web.delBridge(color);
    }
    
    /**
     * Adds a spot to the simulator
     */
    public void addSpot(String color, int strand){
        web.addSpot(color, strand);
    }
    
    /**
     * Deletes a spot from the simulator
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
