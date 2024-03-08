
/**
 * Write a description of class Bridge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bridge{

    private Point[] points;
    private Line line;
    private boolean isVisible;
    private String color;

    /**
     * Constructor for objects of class Bridge
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
    
    
    public boolean checkBridge(String _color){
        if(color == _color){
            return true;
        }
        return false;
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
            for(Point p : points){
                p.makeVisible();
            }
            line.makeVisible();
        }
    }

    /*
     * Erase the triangle on screen.
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
