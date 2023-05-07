package util;

public class Union<K, V> {
    //a Union of two items
    K left;
    V right;

    public Union(K left, V right) {
        this.left = left;
        this.right = right;
    }

    public Union() {
    }

    public K getLeft() {
        return left;
    }

    public V getRight() {
        return right;
    }

    public void setLeft(K left) {
        this.left = left;
    }

    public void setRight(V right) {
        this.right = right;
    }

    public boolean equals(Union<?, ?> obj) {
        //type checking;
        if((obj.left.getClass() == left.getClass()) && (obj.right.getClass() == right.getClass())){
            return obj.left.equals(left) && obj.right.equals(right);
        }else{
            System.out.println("Union.equals()");
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " | " + right.toString() + ")"; 
    }
}
