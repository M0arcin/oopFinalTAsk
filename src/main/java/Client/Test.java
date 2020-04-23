package Client;

import Converters.JsonPrinter;
import Types.Query;


public class Test {
    public static void main(String[] args){

        JsonPrinter printer = new JsonPrinter();

        //so far it works only multiple mustMatch/shouldMatch

        //Query example1 = new Query.QueryBuilder().bool().mustMatch("item", "Milk").mustMatch("price", "23").shouldMatch("product_location", "New Mexico").bool().shouldMatch("warranty", "2 years").shouldMatch("seller", "to warzywo").build();
        //printer.printJson(example1);

        //Query exampl2 = new Query.QueryBuilder().bool().mustMatch("Name", "Marcin").bool().shouldMatch("sex", "male").shouldMatch("height", ">150cm").build();
        //printer.printJson(example2);

        Query example3 = new Query.QueryBuilder().bool().shouldMatch("lot_number", "307").bool().mustMatch("expiry_date", "Jan 2020").build();
        printer.printJson(example3);
    }
}
