package Types;

import java.util.HashMap;

public class Match {
    private HashMap<String, String> match;

    public Match(String s1, String s2) {
        match = new HashMap<String, String>(1);
        match.put(s1, s2);
    }

    public HashMap<String, String> getMatch() {
        return match;
    }

    public void setMatch(HashMap<String, String> match) {
        this.match = match;
    }
}
