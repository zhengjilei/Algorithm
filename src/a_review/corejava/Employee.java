package a_review.corejava;

import java.util.Objects;

/**
 * created by Ethan-Walker on 2019/2/21
 */
public class Employee  {
    private int age;
    private double salary;
    private String name;

    public Employee() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee e = (Employee) obj;
        return Objects.equals(this.name, e.name) && this.age == e.age && Math.abs(this.salary - e.salary) < 10e-7;
    }

    @Override
    public  Object clone() throws CloneNotSupportedException {
        return  super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
