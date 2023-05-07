package experiments.util;

//an n-dimensional vector
public class Nvector {
    double[] components;

    public Nvector(int dimensions) {
        super();
        components = new double[Math.abs(dimensions)];
    }

    public Nvector(double[] components) {
        this.components = components;
    }

    public double getComponent(int index){
        return components[index];
    }

    public void setComponent(int index, double value){
        components[index] = value;
    }

    //get length of a Vector
    public double getLength(){
        return Math.sqrt(getLengthSquare());
    }

    //getting the length without the squareroot
    public double getLengthSquare(){
        double sum = 0;
        for(double d : components){
            sum += Math.pow(d, 2);
        }

        return sum;
    }

    public int getDimensions(){
        return components.length;
    }

    //algebra
    //dot product -> returns null if not equal
    public double dotProduct(Nvector other){
        //check if same dimensions
        if(this.getDimensions() != other.getDimensions()){
            return Double.NaN;
        }

        double sum = 0;
        for(int i = 0; i < getDimensions(); i++){
            sum += components[i] * other.getComponent(i);
        }

        return sum;
    }

    //subtracts other vector from this
    public Nvector add(Nvector other){
        if(getDimensions() != other.getDimensions()){
            return null;
        }

        double[] newVec = new double[getDimensions()];

        for(int i = 0; i < getDimensions(); i++){
            newVec[i] = components[i] + other.getComponent(i);
        }

        return new Nvector(newVec);
    }


    public Nvector sub(Nvector other){
        if(getDimensions() != other.getDimensions()){
            return null;
        }

        double[] newVec = new double[getDimensions()];

        for(int i = 0; i < getDimensions(); i++){
            newVec[i] = components[i] - other.getComponent(i);
        }

        return new Nvector(newVec);
    }

    //scales the vector by a defined amount
    public void scale(double scale){
        for(int i = 0; i < getDimensions(); i++){
            components[i] *= scale;
        }
    }

    //normalizes the Vector to have the magnitude of one 
    public void normalize(){
        for(int i = 0; i < getDimensions(); i++){
            components[i] *= 1 / getLength();
        }
    }

    //returns an independent copy of the current vector
    public Nvector copy(){
        return new Nvector(components);
    }

}
