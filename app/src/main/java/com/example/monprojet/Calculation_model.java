package com.example.monprojet;
public class Calculation_model {
    String t1,t2,t3,t4,t5;
    int id;

    public Calculation_model(int id ,  String t1, String t2, String t3, String t4,String t5) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
        this.t5 = t5;
        this.id = id;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getT4() { return t4; }

    public void setT4(String t4) { this.t4 = t4; }

    public String getT5() { return t5; }

    public void setT5(String t5) { this.t5 = t5; }




    @Override
    public String toString() {
        return "Calculation_model{" +
                "t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                ", id=" + id +
                ", t4=" + t4 +
                ", t5=" + t5 +
                '}';
    }
}
