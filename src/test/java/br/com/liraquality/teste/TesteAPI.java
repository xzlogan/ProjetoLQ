package br.com.liraquality.teste;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.junit.*;
import static com.jayway.restassured.RestAssured.get;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.json.JSONObject;
public class TesteAPI {
  
      
    @Test
    public void testeAPI() {
      RestAssured.baseURI  = "https://jsonplaceholder.typicode.com/todos/1"; 
      Response res = get();
      assertEquals(200, res.getStatusCode());
      String json = res.asString();
      JSONObject jp = new JSONObject(json);  
      assertEquals(1, jp.getInt("userId"));
      assertEquals(1, jp.getInt("id"));
      assertEquals("delectus aut autem".toLowerCase(), jp.getString("title"));
      assertEquals(false, jp.getBoolean("completed"));
      
      //System.out.println(json);
    }
    
    @After
    public void afterTest (){
        
    }
 
    
}