package expressivo;

/**
 * 表示一个数字。
 * 
 * 抽象函数：
 * AF(value) = 表示数字 value 的数学表达式
 * 
 * 表示不变性：
 * - value 不能为 null
 * - Value 须大于等于0
 * 
 * 表示暴露安全性：
 * - 所有字段都是私有的
 * - 没有返回可变对象的方法
 * - 所有方法都是不可变的
 */ 
public class Number implements Expression {
    private double value;

    /**
     * 创建一个新的数字。
     * 
     * @param value 数字的值
     * @throws IllegalArgumentException 如果 value 为 null
     */
    public Number(double value) {
        this.value = value;
        checkRep();
    }

    /**
     * 检查表示不变性。
     * 
     * @throws IllegalStateException 如果表示不变性被违反
     */
    private void checkRep() {
        assert value >= 0 : "value 不能小于 0";
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
