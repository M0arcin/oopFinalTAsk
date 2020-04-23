package Client;

import Converters.JsonPrinter;
import Types.Query;


public class Test {
    public static void main(String[] args){

        JsonPrinter printer = new JsonPrinter();

        //so far it works only multiple mustMatch/shouldMatch
        Query builder = new Query.QueryBuilder().bool().mustMatch("item", "Milk").mustMatch("price", "23").shouldMatch("product_location", "New Mexico").shouldMatch("warranty", "2 years").build();
        printer.printJson(builder);
    }
}
