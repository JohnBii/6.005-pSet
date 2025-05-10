package expressivo;

public class Number implements Expression {
    private double value;

    public Number(double value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return Double.toString(value);
    }   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Number number = (Number) obj;
        return Double.compare(number.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
    
}
