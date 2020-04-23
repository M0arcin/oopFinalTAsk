package Types;

import java.util.ArrayList;
import java.util.List;

public class Should {

    private List<Object> should;

    public Should() {
        this.should = new ArrayList<Object>();
    }

    public void shouldMatch(String s1, String s2){
        Match match = new Match(s1,s2);
        addToShouldList(match);
    }

    public List<Object> getShould() {
        return should;
    }

    public void setShould(List<Object> should) {
        this.should = should;
    }

    protected void addToShouldList(Object o){
        should.add(o);
    }
}