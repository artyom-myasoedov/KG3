package utils;

public class DoubleContainer<A> {
    private String name;
    private A a;

    public DoubleContainer(String name, A a) {
        this.name = name;
        this.a = a;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
