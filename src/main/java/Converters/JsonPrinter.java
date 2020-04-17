package Converters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonPrinter {
    public void printJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

            System.out.println(jsonInString2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
