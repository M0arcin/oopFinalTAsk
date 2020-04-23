package Types;

import java.util.ArrayList;
import java.util.List;

public class Must {

    private List<Object> must;

    public Must() {
        this.must = new ArrayList<Object>();
    }

    public void mustMatch(String s1, String s2){
        Match match = new Match(s1, s2);
        addToMustList(match);
    }

    public List<Object> getMust() {
        return must;
    }

    public void setMust(List<Object> must) {
        this.must = must;
    }

    protected void addToMustList(Object o){
        must.add(o);
    }
}
