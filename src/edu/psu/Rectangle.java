package edu.psu;

import java.io.IOException;

public class Rectangle{
    int h, w;
    static private int pH;
    final protected int protH  = 10;
    public int pubH;
    public  Rectangle() throws Exception{}
    public  Rectangle(int w ,int h){}
    protected   Rectangle(double w ,int h){}
    private  Rectangle(int w ,float h){}
    public int getArea(int i, int d, double g) throws ArithmeticException, Exception{ return 1;}
    public static void foo(){}
    protected void protFoo(){}
    private void privateFoo() throws IOException {}
    void packagePrivateFoo(){}
}
