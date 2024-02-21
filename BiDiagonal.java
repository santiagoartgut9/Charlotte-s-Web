
/**
 * Draw a diagonal
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BiDiagonal{
    private LeftDiagonal leftDiagonal;
    private RightDiagonal rightDiagonal;
    private boolean isVisible;
    
    
    public BiDiagonal(int radius, double angle, double nX, double nY){
        double height = 2*radius*Math.tan(angle)/Math.sqrt((4*(Math.pow(Math.tan(angle),2)))+2);
        height = Math.abs(height);
        double baseLenght = (4*radius)/Math.sqrt((4*(Math.pow(Math.tan(angle),2)))+2);
        baseLenght = Math.abs(baseLenght);
        if(0<angle && angle<90){
            leftDiagonal = new LeftDiagonal(radius, angle, nX, nY);
            rightDiagonal = new RightDiagonal(radius, 180-angle, nX, nY);
        }
        else if(90<angle && angle<180){
            leftDiagonal = new LeftDiagonal(radius, 180-angle, nX, nY);
            rightDiagonal = new RightDiagonal(radius, angle, nX, nY);
        }
        else if(180<angle && angle<270){
            leftDiagonal = new LeftDiagonal(radius, angle, nX, nY);
            rightDiagonal = new RightDiagonal(radius, 360-(angle-180), nX+3, nY); // Parecido a como desplazamos para que se viera mas grueso, hay que crear una funcion que
                                                                                 // devuelva un nX de acuerdo a que tan lejos (o cerca) está de la horizontal. 
        }
        else if(270<angle && angle<360){
            leftDiagonal = new LeftDiagonal(radius, (360-angle)+180, nX-3, nY);
            rightDiagonal = new RightDiagonal(radius, angle, nX, nY);
            
            isVisible = false;
        }
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
            leftDiagonal.makeVisible();
            rightDiagonal.makeVisible();
        }
    }
    
    private void erase(){
        if(isVisible){
            leftDiagonal.makeInvisible();
            rightDiagonal.makeInvisible();
        }
    }
    
}
