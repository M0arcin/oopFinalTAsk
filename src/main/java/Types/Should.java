package Types;

import java.util.ArrayList;
import java.util.List;

public class Should {

    private List<Match> should;

    public Should(String s1, String s2) {
        this.should = new ArrayList<Match>();
    }

    public void shouldMatch(String s1, String s2){
        Match match = new Match(s1,s2);
        should.add(match);
    }

    public List<Match> getShould() {
        return should;
    }

}