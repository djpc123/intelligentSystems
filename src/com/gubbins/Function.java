package com.gubbins;

import java.util.ArrayList;
import java.util.Iterator;

public class Function {

    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    //f(x)=a + bx + cx2 + dx3 + ex4 + fx5
    public Function(double a, double b, double c, double d, double e, double f) {
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder("f(x) = ");
        if(a != 0) {
            build.append(a);
            build.append(" + ");
        }
        if(b != 0) {
            if (b != 1) {
                build.append(b);
            }
            build.append("x + ");
        }
        if(c != 0) {
            if (c != 1) {
                build.append(c);
            }
            build.append("x^2 + ");
        }
        if(d != 0) {
            if (d != 1) {
                build.append(d);
            }
            build.append("x^3 + ");
        }
        if(e != 0) {
            if (e != 1) {
                build.append(e);
            }
            build.append("x^4 + ");
        }
        if(f != 0) {
            if (f != 1) {
                build.append(f);
            }
            build.append("x^5");
        }

        return build.toString();
    }

    public double calculate(double x) {
        return (a + (b*x) + (c*Math.pow(x,2)) + (d*Math.pow(x,3)) + (e*Math.pow(x,4)) + (f*Math.pow(x,5)));
    }

    public ArrayList<Double> calculate(ArrayList<Double> x) {
        ArrayList<Double> result = new ArrayList<>();
        Iterator<Double> it = x.iterator();
        while(it.hasNext()) {
            result.add(calculate(it.next()));
        }
        return result;
    }

    public double[] getGenome() {
        return new double[] {a,b,c,d,e,f};
    }

}
