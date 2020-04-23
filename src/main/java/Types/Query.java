package Types;

import java.util.ArrayList;

public class Query {
    private Bool bool;

    private Query(QueryBuilder queryBuilder){
        bool = queryBuilder.bool;
    }

    public static final class QueryBuilder{
        private Bool bool;
        private Bool boolShould;
        ArrayList<Object> boolList = new ArrayList<Object>();
        ArrayList<Object> boolListNested = new ArrayList<Object>();
        private Should should;
        private Should shouldNested;
        private Must must;
        private String lastMethodCalled = "";

        public QueryBuilder bool(){
            this.bool = new Bool();
            if(lastMethodCalled.equals("shouldMatch")){
                this.should.addToShouldList(this.boolShould = new Bool());
                return this;

            }
            this.bool = new Bool();
            return this;
        }

        public QueryBuilder mustMatch(String s1, String s2){
            lastMethodCalled = "mustMatch";
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
            lastMethodCalled = "shouldMatch";
            if(!(this.boolShould == null)){
                this.shouldNested = this.boolShould.getShould();
                this.shouldNested.shouldMatch(s1,s2);
                for(Object o : boolListNested){
                    if(o instanceof Should){
                        return this;
                    }
                }
                boolListNested.add(this.shouldNested);
                return this;
            }
            this.should = this.bool.getShould();
            this.should.shouldMatch(s1,s2);
            // this loop goes through boolList, if there  is  an instance of Should inside it doesn't add new object to the list, if there  isn't  it adds new should
            for(Object o : boolList){
                if (o instanceof Should) {
                    return this;
                }
            }
            boolList.add(this.should);
            return this;
        }

        public Query build(){
            this.bool.setBoolList(boolList);
            this.boolShould.setBoolList(boolListNested);
            return new Query(this);
        }
    }

    // Changed name so it matches the required output, this returns whole object
    public Bool getQuery() {
        return bool;
    }
}
