package Types;

import java.util.ArrayList;

public class Query {
    private Bool bool;
/*    private Should should;
    private Must must;*/

    private Query(QueryBuilder queryBuilder){
        bool = queryBuilder.bool;
/*        should = queryBuilder.should;
        must = queryBuilder.must;*/
    }

    public static final class QueryBuilder{
        private Bool bool;
        ArrayList<Object> boolList = new ArrayList<Object>();
        private Should should;
        private Must must;

        public QueryBuilder bool(){
            this.bool = new Bool();
            return this;
        }

        public QueryBuilder mustMatch(String s1, String s2){
            this.must = this.bool.getMust();
            this.must.mustMatch(s1,s2);
            boolList.add(this.must);
            return this;
        }

        public QueryBuilder shouldMatch(String s1, String s2){
            this.should = this.bool.getShould();
            this.should.shouldMatch(s1,s2);
            boolList.add(this.should);
            return this;
        }

        public Query build(){
            this.bool.setBoolList(boolList);
            return new Query(this);
        }
    }

    // Changed name so it matches the required output, this returns whole object
    public Bool getQuery() {
        return bool;
    }
}
