package expressivo;

/**
 * 表示一个变量。
 * 
 * 抽象函数：
 * AF(name) = 表示变量 name 的数学表达式
 * 
 * 表示不变性：
 * - name 不能为 null
 * - name 不能为空字符串
 * 
 * 表示暴露安全性：
 * - 所有字段都是私有的
 * - 没有返回可变对象的方法
 * - 所有方法都是不可变的
 */
public class Variable implements Expression {
    private final String name;

    /**
     * 创建一个新的变量。
     * 
     * @param name 变量的名称
     * @throws IllegalArgumentException 如果 name 为 null
     */ 
    public Variable(String name) {
        this.name = name;
        checkRep();
    }

    /** 
     * 检查表示不变性。
     * 
     * @throws IllegalStateException 如果表示不变性被违反
     */
    private void checkRep() {
        assert name != null : "name 不能为 null";
        assert !name.isEmpty() : "name 不能为空字符串";
    }

    /**
     * 返回变量的字符串表示。
     * 
     * @return 变量的名称
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * 比较此变量与另一个对象是否相等。
     * 
     * @param obj 要比较的对象
     * @return 如果对象相等则返回 true，否则返回 false
     */ 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Variable variable = (Variable) obj;
        return name.equals(variable.name);
    }

    /**
     * 返回此变量的哈希码。
     * 
     * @return 此变量的哈希码
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
