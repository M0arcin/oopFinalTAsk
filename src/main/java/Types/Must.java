package Types;

import java.util.ArrayList;
import java.util.List;

public class Must {

    private List<Match> must;

    public Must() {
        this.must = new ArrayList<Match>();
    }

    public void mustMatch(String s1, String s2){
        Match match = new Match(s1, s2);
        must.add(match);
    }

    public List<Match> getMust() {
        return must;
    }

    public void setMust(List<Match> must) {
        this.must = must;
    }
}
