/**
 * Strand Class for the Spider Web
 * 
 * @Author: Sebastian Galvis Briceno
 * @Author: Santiago Arteaga
 * @version (03 March 2024)
 */
public class Spider{

    private Point position;
    private Point direction;
    
    private boolean walking;
    private boolean isVisible;
    
    private Circle body;
    
    /**
     * Constructor for objects of class Spider
     */
    public Spider(Point _position){
        position = _position;
        isVisible = false; // Esta se crea como objeto de Web como lo hace un Strand
    }
    
    /**
     * Changes the spider's walk direction
     */
    public void changeDirection(Point _direction){
        direction = _direction;
    }
    
    /**
     * Changes the spider's walk direction
     */
    public void spiderWalk(boolean advance){
        //
    }
    
    /**
     * Moves the spider to a point
     */
    public void moveTo(Point point){
        // slowMoveHorizontal(int distance) y slowMoveVertical(int distance) son los metodos de circle que permiten movimiento lento
        while(position.xCoordinate()!=point.xCoordinate() && position.yCoordinate()!=point.yCoordinate()){
            //
        }
    }
    
    /**
     * Sits the spider in a specified strand
     * 
     *          EN STRAND
     *          
     *          public Point finalPoint();
     *              return finalP;
     *          
     *          
     *          EN WEB
     *          
     *          private Spider spider;
     *          public void spiderSit(int strand){
     *              //spider.changeDirection(strands.get(strand).finalPoint());  NO, RETORNAR EL PUNTO DE PUENTE MAS CERCANO, ESTO SOLO SI NO HAY PUENTE
     *          }  
     */
    public void spiderSit(int Strand){
        //
    }   
    
}
