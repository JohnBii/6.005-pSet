package expressivo;

import java.util.Objects;

/**
 * 表示两个表达式的加法运算。
 * 
 * 抽象函数：
 * AF(left, right) = 表示 left + right 的数学表达式
 * 
 * 表示不变性：
 * - left 和 right 不能为 null
 * 
 * 表示暴露安全性：
 * - 所有字段都是私有的
 * - 没有返回可变对象的方法
 * - 所有方法都是不可变的
 */
public class Plus implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * 创建一个新的加法表达式。
     * 
     * @param left 左操作数表达式
     * @param right 右操作数表达式
     * @throws IllegalArgumentException 如果 left 或 right 为 null
     */
    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }   

    /**
     * 检查表示不变性。
     * 
     * @throws IllegalStateException 如果表示不变性被违反
     */
    private void checkRep() {
        assert left != null : "left 不能为 null";
        assert right != null : "right 不能为 null";
    }

    /**
     * 返回表达式的字符串表示。
     * 格式为 "(left + right)"，其中 left 和 right 是子表达式的字符串表示。
     * 
     * @return 表达式的字符串表示
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }   

    /**
     * 比较此表达式与另一个对象是否相等。
     * 两个 Plus 表达式相等当且仅当它们的左操作数和右操作数分别相等。
     * 
     * @param obj 要比较的对象
     * @return 如果对象相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Plus plus = (Plus) obj; 
        return left.equals(plus.left) && right.equals(plus.right);
    }

    /**
     * 返回此表达式的哈希码。
     * 哈希码基于左操作数和右操作数的哈希码计算。
     * 
     * @return 此表达式的哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    /**
     * 计算加法表达式相对于给定变量的导数
     * 
     * @param variable 要进行求导的变量
     * @return 表达式相对于给定变量的导数
     */
    @Override
    public Expression differentiate(String variable) {
        return new Plus(left.differentiate(variable), right.differentiate(variable));
    }
}
