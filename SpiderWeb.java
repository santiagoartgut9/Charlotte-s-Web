import java.util.ArrayList; // para separar los arreglos
import java.util.List; // arriba
import java.util.Arrays; // para ordenar los arregols
import java.util.Comparator; // arriba

/**
 * Write a description of class SpiderWeb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpiderWeb
{
    private Circle origin;
    private StraightLine head;
    double[] angles;
    StraightLine[] straightLines;
    BiDiagonal[] biDiagonals;
    RightDiagonal[] rightDiagonals;
    LeftDiagonal[] leftDiagonals;
    private boolean isVisible;
    
    public SpiderWeb(int strands, int radius){
        origin = new Circle(422, 235);
        head = new StraightLine(radius, 427, 240);
        angles = buildangles(strands);
        
        double[] speciallsArray = getSpecialls(angles);
        double[] sortedSmaller180Array = compareSmaller(getSmallerThan180(angles));
        double[] sortedBigger180Array = compareBigger(getBiggerThan180(angles));
        
        if (strands % 2 == 0){
            method1();
        }
        else{
            method2(sortedSmaller180Array, sortedBigger180Array, radius);
        }
        
        isVisible = false;
    }
    
    public void method1(){
        //Dibujar de mas cercano a 90 al mas alejado de 90 para todos los cuadrantes, dividir angles a la mitad antes de ordenarlo por lejania . Por ultimo dibujar las rectas (si las hay).
    }
    
    public void method2(double[] sortedSmaller180Array, double[] sortedBigger180Array, int radius){
        //Dibujar las diagonales en el orden de smaller y bigger, si pertenecen a x cuadrante usar left o right, por ultimo dibujar las rectas (si las hay).
        rightDiagonals = buildRightDiagonals(angles);
        leftDiagonals = buildLeftDiagonals(angles);
        
        int ind = 0;
        for (double angle : sortedSmaller180Array) {
            if (angle > 90) {
                rightDiagonals[ind]=new RightDiagonal(radius, angle, 427, 240);
                ind += 1;
            }
        }
        
        ind = 0;
        for (double angle : sortedBigger180Array) {
            if (angle > 270) {
                rightDiagonals[ind]=new RightDiagonal(radius, angle, 427, 240);
                ind += 1;
            }
        }
        
        ind = 0;
        for (double angle : sortedSmaller180Array) {
            if (angle < 90) {
                leftDiagonals[ind]=new LeftDiagonal(radius, angle, 427, 240);
                ind += 1;
            }
        }
        
        ind = 0;
        for (double angle : sortedBigger180Array) {
            if (angle < 270) {
                leftDiagonals[ind]=new LeftDiagonal(radius, angle, 427, 240);
                ind += 1;
            }
        }
        
    }
    
    public RightDiagonal[] buildRightDiagonals(double[] angles){
        int cont = 0;
        for (double angle : angles) {
            if ((angle < 180 && angle > 90) || (angle > 270)) {
                cont += 1;
            }
        }
        rightDiagonals = new RightDiagonal[cont];
        
        return rightDiagonals;
    }
    
    public LeftDiagonal[] buildLeftDiagonals(double[] angles){
        int cont = 0;
        for (double angle : angles) {
            if ((angle < 90) || (angle > 180 && angle < 270)) {
                cont += 1;
            }
        }
        leftDiagonals = new LeftDiagonal[cont];
        
        return leftDiagonals;
    }
    
    public double[] compareSmaller(double[] Array){
        double A = 90.0;
        double[] sortedSmaller180Array = sortFromFarthest(Array, A);
        return sortedSmaller180Array;
    }
    
    public double[] compareBigger(double[] Array){
        double B = 270.0;
        double[] sortedBigger180Array = sortFromFarthest(Array, B);
        return sortedBigger180Array;
    }
    
    public static double[] getSmallerThan180(double[] angles) {
        List<Double> smaller180 = new ArrayList<>();

        for (double angle : angles) {
            if (angle > 0 && angle < 180 && angle != 90) {
                smaller180.add(angle);
            }
        }

        return smaller180.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static double[] getBiggerThan180(double[] angles) {
        List<Double> bigger180 = new ArrayList<>();

        for (double angle : angles) {
            if (angle > 180 && angle < 360 && angle != 270) {
                bigger180.add(angle);
            }
        }

        return bigger180.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static double[] getSpecialls(double[] angles) {
        List<Double> specialls = new ArrayList<>();

        for (double angle : angles) {
            if (angle == 0 || angle == 90 || angle == 180 || angle == 270) {
                specialls.add(angle);
            }
        }

        return specialls.stream().mapToDouble(Double::doubleValue).toArray();
    }

    public static double[] sortFromFarthest(double[] Arrayl, double A) { //Codigo asistido con ChatGPT
        
        double[] distances = new double[Arrayl.length];
        for (int i = 0; i < Arrayl.length; i++) {
            distances[i] = Math.abs(Arrayl[i] - A);
        }
        
        Integer[] indices = new Integer[Arrayl.length];
        for (int i = 0; i < Arrayl.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (i1, i2) -> Double.compare(distances[i2], distances[i1]));
        
        double[] sortedArray = new double[Arrayl.length];
        for (int i = 0; i < Arrayl.length; i++) {
            sortedArray[i] = Arrayl[indices[i]];
        }
        
        return sortedArray;
    }
    
    
    public double[] buildangles(int strands){
        angles = new double[strands-1];
        
        double alphaZero = 360/strands;
        double alpha = alphaZero;
        
        for (int i = 0; i < angles.length; i++) {
            angles[i] = alpha;
            alpha += alphaZero;
        }
        return angles;
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
            origin.makeVisible();
            head.makeVisible();
            //for (RightDiagonal i : rightDiagonals){
            //    i.makeVisible();
            //}
            //for (LeftDiagonal i : leftDiagonals){
            //    i.makeVisible();
            //}
            //for (StraightLine i : straightLines){
            //    i.makeVisible();
            //}
        }
    }
    
    private void erase(){
        if(isVisible){
            origin.makeInvisible();
            head.makeInvisible();
            //for (RightDiagonal i : rightDiagonals){
            //    i.makeInvisible();
            //}
            //for (LeftDiagonal i : leftDiagonals){
            //    i.makeInvisible();
            //}
            //for (StraightLine i : straightLines){
            //    i.makeInvisible();
            //}
        }
    }
}
