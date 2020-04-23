package Types;

import java.util.ArrayList;

public class Query {
    private Bool bool;

    private Query(QueryBuilder queryBuilder){
        bool = queryBuilder.bool;
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
            // this loop goes through boolList, if there  is  an instance of Must inside it doesn't add new object to the list, if there  isn't  it adds new must
            while(true){
                for(Object o : boolList){
                    if ((o instanceof Must)) {
                        return this;
                    }
                }
                boolList.add(this.must);
                return this;
            }
        }

        public QueryBuilder shouldMatch(String s1, String s2){
            this.should = this.bool.getShould();
            this.should.shouldMatch(s1,s2);
            // this loop goes through boolList, if there  is  an instance of Should inside it doesn't add new object to the list, if there  isn't  it adds new should
            while(true){
                for(Object o : boolList){
                    if ((o instanceof Should)) {
                        return this;
                    }
                }
                boolList.add(this.should);
                return this;
            }
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
