import java.awt.*;

/**
 * Spider class for SpiderWeb.
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */

public class Spider{
    
    private Circle body;
    private Point direction;
    private Line[] legs;
    private boolean isVisible;
    private Strand strand;
    private Web web;
    
    
    /**
     * Create a spider with default position
     */
    public Spider(Web _web, Strand _strand){
        body = new Circle(427, 240, "green", 16);
        web = _web;
        strand = _strand;
        
        legs = new Line[16];
        
        fillLeftLegs();
        fillRightLegs();
        
        isVisible = false;
    }
    
    /** Returns the position of the spider as a point
     */
    public Point getPosition(){
        double x0 = body.xCoordinate()+8;
        double y0 = body.yCoordinate()+8;
        return new Point(x0, y0);
    }
    
    /**
     * Sits the spider on the center of the web
     */
    public void sit(){
        moveTo(427, 240);
    }
    
    /**
     * Switchs the spider's direction
     * @param _direction the new direction's point
     */
    public void switchDirection(Point _direction){
        direction = _direction;
    }
    
    /**
     * Walk to her direction's point and change it if bridge
     */
    public void spiderWalk(){
        double x0 = body.xCoordinate()+8;
        double y0 = body.yCoordinate()+8;
        double x1 = direction.xCoordinate();
        double y1 = direction.yCoordinate();
        double angle = calculateAngle(x0, x1, y0, y1);
        
        double deltaX = 0;
        double deltaY = 0;
        
        if(y0 >= y1){
            if(x1 >= x0){
                deltaX = Math.cos(angle);
                deltaY = -1*Math.sin(angle);
            }
            else{
                deltaX = -1*Math.cos(Math.PI-angle);
                deltaY = -1*Math.sin(Math.PI-angle);
            }
        }
        else{
            if(x1 >= x0){
                deltaX = Math.cos((2*Math.PI)-angle);
                deltaY = Math.sin((2*Math.PI)-angle);
            }
            else{
                deltaX = -1*Math.cos(angle-Math.PI);
                deltaY = Math.sin(angle-Math.PI);
            }
        }
        
        body.moveTo(body.xCoordinate() + deltaX, body.yCoordinate() + deltaY);
        for(Line l : legs){
                l.moveTo(l.xCoordinate() + deltaX, l.yCoordinate() + deltaY);
            }
            draw();
        }
    
    /**
     * Move the spider to a specific coordinate.
     * @param xPosition the x coordinate of the spider
     * @param yPosition the y coordinate of the spider
     */
    public void moveTo(double xPosition, double yPosition){
        erase();
        double deltaX = xPosition - body.xCoordinate();
        double deltaY = yPosition - body.yCoordinate();
        body.moveTo(xPosition - 8, yPosition - 8);
        for(Line l : legs){
            l.moveTo(l.xCoordinate() + deltaX -8, l.yCoordinate() + deltaY -8);
        }
        draw();
    }
    
    /**
     * Make this spider visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this spider invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /*
     * Draw the spider with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            body.makeVisible();
            for(Line l : legs){
                l.makeVisible();
            }
        }
    }

    /*
     * Erase the spider on screen.
     */
    private void erase(){
        if(isVisible) {
            body.makeInvisible();
            for(Line l : legs){
                l.makeInvisible();
            }
        }
    }
    
    /**
     * Calculate the angle between the spider's direction and her position
     * @Param x0 position x coordinate
     * @Param x1 direction x coordinate
     * @Param y0 position y coordinate
     * @Param y1 direction y coordinate
     */
    private double calculateAngle(double x0, double x1, double y0, double y1){
        double angle = 0;
        
        if( (y0-y1 == 0) || (x0==x1 && y0>y1) || ((y0>y1) &&  (x1>x0)) ){
            angle = Math.atan2((y0-y1),(x1-x0));
        }
        else if(x0>x1 && y0>y1){
            angle = Math.atan2((y0-y1),(x1-x0));
        }
        else{
            angle = Math.atan2((y0-y1),(x1-x0)) + (2*Math.PI);
        }
        
        return angle;
    }
    
    /**
     * Create right legs
     */
    public void fillRightLegs(){
        legs[0] = new Line(new Point(434, 236), new Point(440, 234), "green");
        legs[2] = new Line(new Point(434, 238), new Point(440, 236), "green");
        legs[4] = new Line(new Point(434, 242), new Point(440, 240), "green");
        legs[6] = new Line(new Point(434, 244), new Point(440, 242), "green");
        legs[1] = new Line(new Point(440, 234), new Point(446, 236), "green");
        legs[3] = new Line(new Point(440, 236), new Point(446, 238), "green");
        legs[5] = new Line(new Point(440, 240), new Point(446, 242), "green");
        legs[7] = new Line(new Point(440, 242), new Point(446, 244), "green");
    }
    
    /**
     * Create left legs
     */
    public void fillLeftLegs(){
        legs[8] = new Line(new Point(408, 236), new Point(414, 234), "green");
        legs[10] = new Line(new Point(408, 238), new Point(414, 236), "green");
        legs[12] = new Line(new Point(408, 242), new Point(414, 240), "green");
        legs[14] = new Line(new Point(408, 244), new Point(414, 242), "green");
        legs[9] = new Line(new Point(414, 234), new Point(420, 236), "green");
        legs[11] = new Line(new Point(414, 236), new Point(420, 238), "green");
        legs[13] = new Line(new Point(414, 240), new Point(420, 242), "green");
        legs[15] = new Line(new Point(414, 242), new Point(420, 244), "green");
    }
    
    /**
     * Sets the strand to the newStrand
     * @Param newStrand the strand to be setted
     */
    public void setStrand(Strand newStrand){
        strand = newStrand;
    }
    
    /**
     * Returns the actual strand
     */
    public Strand getStrand(){
        return strand;
    }
    
    /**
     * Returns the actual strand's spot
     */
    public Point getActualStrandSpot(){
        return strand.getFinalPoint();
    }
    
    
}
