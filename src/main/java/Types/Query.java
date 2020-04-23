package Types;

import java.util.ArrayList;

public class Query {
    private Bool bool;

    private Query(QueryBuilder queryBuilder){
        bool = queryBuilder.bool;
    }

    public static final class QueryBuilder{
        // bools required to make this work - one main bool and two other ones that can be nested
        private Bool bool;
        private Bool boolShould;
        private Bool boolMust;

        ArrayList<Object> boolList = new ArrayList<Object>();
        ArrayList<Object> boolListNested = new ArrayList<Object>();

        private Should should;
        private Should shouldNested;
        private Must must;
        private Must mustNested;

        // String used to determine last function called, it is used to nest a bool inside must/should
        private String lastMethodCalled = "";

        public QueryBuilder bool(){
            // It checks whether last method that was called it shouldMatch/mustMatch and if that's true, it nest a bool inside it
            if(lastMethodCalled.equals("shouldMatch")){
                this.should.addToShouldList(this.boolShould = new Bool());
            } else if (lastMethodCalled.equals("mustMatch")) {
                this.must.addToMustList(this.boolMust = new Bool());

            } else{
                this.bool = new Bool();
            }
            return this;
        }

        public QueryBuilder mustMatch(String s1, String s2){
            lastMethodCalled = "mustMatch";
            if(!(this.boolShould == null)){
                this.mustNested = this.boolShould.getMust();
                this.mustNested.mustMatch(s1, s2);
                // this loop goes through boolList, if there  is  an instance of Must inside it doesn't add new object to the list, if there  isn't  it adds new must; SAME FOR EVERY LOOP INSIDE THIS AND MUST METHOD
                for(Object o : boolListNested){
                    if(o instanceof Must){
                        return this;
                    }
                }
                boolListNested.add(this.mustNested);
                return this;

            } else if(!(this.boolMust == null)){
                this.mustNested = this.boolMust.getMust();
                this.mustNested.mustMatch(s1, s2);
                for(Object o : boolListNested){
                    if(o instanceof Must){
                        return this;
                    }
                }
                boolListNested.add(this.mustNested);
                return this;

            } else{
                this.must = this.bool.getMust();
                this.must.mustMatch(s1, s2);
                for(Object o : boolList){
                    if (o instanceof Must) {
                        return this;
                    }
                }
                boolList.add(this.must);
            }
            return this;



        }

        public QueryBuilder shouldMatch(String s1, String s2){
            lastMethodCalled = "shouldMatch";

            if(!(this.boolShould == null)){
                this.shouldNested = this.boolShould.getShould();
                this.shouldNested.shouldMatch(s1, s2);
                // this loop goes through boolList, if there  is  an instance of Should inside it doesn't add new object to the list, if there  isn't  it adds new should; SAME FOR EVERY LOOP INSIDE THIS AND MUST METHOD
                for(Object o : boolListNested){
                    if(o instanceof Should){
                        return this;
                    }
                }
                boolListNested.add(this.shouldNested);
                return this;

            } else if(!(this.boolMust == null)){
                this.shouldNested = this.boolMust.getShould();
                this.shouldNested.shouldMatch(s1, s2);
                for(Object o : boolListNested){
                    if(o instanceof Should){
                        return this;
                    }
                }
                boolListNested.add(this.shouldNested);
                return this;

            } else{
                this.should = this.bool.getShould();
                this.should.shouldMatch(s1, s2);
                for(Object o : boolList){
                    if (o instanceof Should) {
                        return this;
                    }
                }
                boolList.add(this.should);
            }
            return this;
        }

        public Query build(){
            this.bool.setBoolList(boolList);
            if(!(boolShould == null)){
                this.boolShould.setBoolList(boolListNested);
            }
            if(!(boolMust == null)){
                this.boolMust.setBoolList(boolListNested);
            }
            return new Query(this);
        }
    }

    // Changed name so it matches the required output, this returns whole object
    public Bool getQuery() {
        return bool;
    }
}
