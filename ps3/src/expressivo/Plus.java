package expressivo;

import java.util.Objects;

public class Plus implements Expression {
    private Expression left;
    private Expression right;

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }   

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Plus plus = (Plus) obj; 
        return left.equals(plus.left) && right.equals(plus.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
