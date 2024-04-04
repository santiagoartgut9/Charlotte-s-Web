/**
 * The Bridge for SpiderWeb
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */
public class Bridge{

    private Point[] points;
    private Line line;
    private boolean isVisible;
    private String color;

    /**
     * Create a bridge with a starting and final point, and a color
     * 
     */
    public Bridge(Point startingPoint, Point finalPoint, String _color){
        points = new Point[2];
        points[0] = startingPoint;
        points[1] = finalPoint;
        color = _color;
        for(Point p : points){
            p.changeColor(color);
        }
        line = new Line(startingPoint, finalPoint, color);
        isVisible = false;
    }
    
    /**
     * Checks if the color of the bridge is the specified, false other case
     */
    public boolean checkBridge(String _color){
        if(color == _color){
            return true;
        }
        return false;
    }
    
    /**
     * Make this bridge visible. If it was already visible, do nothing
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this bridge invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Return the starting point of the bridge
     */
    public Point getStartingPoint(){
        return points[0];
    }
    
    /**
     * Return the final point of the bridge
     */
    public Point getFinalPoint(){
        return points[1];
    }
    
    /*
     * Draw the bridge with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            for(Point p : points){
                p.makeVisible();
            }
            line.makeVisible();
        }
    }

    /*
     * Erase the bridge on screen.
     */
    private void erase(){
        if(isVisible) {
            for(Point p : points){
                p.makeInvisible();
            }
            line.makeInvisible();
        }
    }
}
