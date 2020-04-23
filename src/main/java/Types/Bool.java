package Types;

import java.util.ArrayList;

public class Bool {

    private Must must;
    private Should should;
    private ArrayList<Object> boolList;

    public Bool() {
        must = new Must();
        should = new Should();
    }

    protected Must getMust() {
        return must;
    }

    protected Should getShould() {
        return should;
    }

    public void setBoolList(ArrayList<Object> boolList) {
        this.boolList = boolList;
    }

    public ArrayList<Object> getBool(){
        return boolList;
    }

    //moze jakis list z must i should ktory potem jest dodawany do getBool
}
