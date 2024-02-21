
/**
 * Write a description of class StraightLine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StraightLine{
    private Rectangle base;
    private boolean isVisible;
    
    public StraightLine(int radius, double nX, double nY){
        base = new Rectangle(nX, nY);
        base.changeColor("black");
        base.changeSize(2, radius);
        isVisible = false;
    }
    
    
    
    public void rotate(){
        base.changeSize(base.getWidth(), base.getHeight());
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    
    
    private void draw(){
        if(isVisible){
            base.makeVisible();
        }
    }
    
    private void erase(){
        if(isVisible){
            base.makeInvisible();
        }
    }
}
