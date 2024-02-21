
/**
 * Draw a diagonal
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RightDiagonal{
    private Triangle triangleSup;
    private Triangle triangleInf;
    private boolean isVisible;
    
    
    public RightDiagonal(int radius, double angle, double nX, double nY){
        angle = Math.toRadians(angle);
        double height = 2*radius*Math.tan(angle)/Math.sqrt((4*(Math.pow(Math.tan(angle),2)))+2);
        height = Math.abs(height);
        double baseLenght = (4*radius)/Math.sqrt((4*(Math.pow(Math.tan(angle),2)))+2);
        baseLenght = Math.abs(baseLenght);
        if(Math.PI/2<angle && angle<Math.PI){
            triangleSup = case2Sup(height, baseLenght, angle, nX, nY);
            triangleInf = case2Inf(height, baseLenght, angle, nX, nY);
        }
        else if(angle>Math.PI*2/3 && Math.PI*2>angle){
            angle = (Math.PI*2) - angle;
            triangleSup = case3Sup(height, baseLenght, angle, nX, nY);
            triangleInf = case3Inf(height, baseLenght, angle, nX, nY);
        }

        
        triangleSup.changeColor("black");
        triangleInf.changeColor("white");
        
        isVisible = false;
    }
    
    
    
    public Triangle case2Sup(double height, double baseLenght, double angle, double nX, double nY){
        nX = nX - baseLenght/2;
        nY = nY - height;
        triangleSup = new Triangle(height, baseLenght, nX, nY);
        return triangleSup;
    }
    
    public Triangle case2Inf(double height, double baseLenght, double angle, double nX, double nY){
        nX = nX - baseLenght/2;
        nY = nY - height;
        triangleInf = triangleInfBuilder(height, baseLenght, nX, nY, angle);
        return triangleInf;
    }
    
    public Triangle case3Sup(double height, double baseLenght, double angle, double nX, double nY){
        triangleSup = new Triangle(height, baseLenght, nX, nY);
        return triangleSup;
    }
    
    public Triangle case3Inf(double height, double baseLenght, double angle, double nX, double nY){
        triangleInf = triangleInfBuilder(height, baseLenght, nX, nY, angle);
        return triangleInf;
    }
    
    
    
    public Triangle triangleInfBuilder(double height, double baseLenght, double nX, double nY, double angle){
        if(angle<Math.PI*3/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-(angle*180/Math.PI)-65, nY+2);
        }
        else if(angle<Math.PI*4/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-(angle*180/Math.PI)-45, nY+2);
        }
        else if(angle<=Math.PI*7/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-(angle*180/Math.PI)-25, nY+2);
        }
        else if(angle<=Math.PI*7/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-(angle*180/Math.PI)-16, nY+2);
        }
        else if(angle<=Math.PI*10/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-(angle*180/Math.PI)-6, nY+2);
        }
        else if(angle<=Math.PI*11/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-(angle*180/Math.PI), nY+2);
        }
        else if(angle<=Math.PI*15/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-8, nY+2);
        }
        else if(angle<Math.PI*45/180){
            triangleInf = new Triangle(height-2, baseLenght-2, nX-4, nY+2);
        }
        else{
            triangleInf = new Triangle(height-2, baseLenght-2, nX-2, nY+2);
        }
        return triangleInf;
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
            triangleSup.makeVisible();
            triangleInf.makeVisible();
        }
    }
    
    private void erase(){
        if(isVisible){
            triangleSup.makeInvisible();
            triangleInf.makeInvisible();
        }
    }
    
}
