package Client;

import Converters.JsonPrinter;
import Types.Query;


public class Test {
    public static void main(String[] args){

        JsonPrinter printer = new JsonPrinter();

        //so far it works only with 1 argument for should/match
        Query builder = new Query.QueryBuilder().bool().mustMatch("item", "Milk").shouldMatch("product_location", "New Mexico").build();
        printer.printJson(builder);
    }
}
