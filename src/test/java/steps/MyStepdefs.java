package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Assert;


public class MyStepdefs {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private String ENDPOINT_GET_BOOK_BY_ISBN = "https://fakerestapi.azurewebsites.net/api/Books";


    @Given("a book exists with an isbn of x")
    public void aBookExistsWithAnIsbnOfX() {
        JSONObject book = new JSONObject();

        book.put("ID", 123);
        book.put("Title", "Betania");
        book.put("Description", "betania é d+");
        book.put("PageCount", 200);
        book.put("Excerpt", "Você é luz");
        book.put("PublishDate", "2020-05-18T20:43:36.324Z");

        request = RestAssured.given().
                                request().
                                header("Content-Type", "application/json").
                                body(book.toJSONString());
    }

    @When("a user retrieves the book by isbn variavel")
    public void a_user_retrieves_the_book_by_isbn(int variavel){
        response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN + "/"+ variavel);
        https://fakerestapi.azurewebsites.net/api/Books/200
        System.out.println("response: " + response.prettyPrint());

    }

    @When("I post")
    public void iPost() {
        response = request.when().post(ENDPOINT_GET_BOOK_BY_ISBN);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is x")
    public void verify_status_code(){
        json = response.then().
                            statusCode(200).
                            assertThat().
                            body("Title", Matchers.equalTo("Betania"));
    }

//    /**
//     * asserts on json fields with single values
//     */
//    @And("response includes the following$")
//    public void response_equals(Map<String,String> responseFields){
//        for (Map.Entry<String, String> field : responseFields.entrySet()) {
//            if(StringUtils.isNumeric(field.getValue())){
//                json.body(field.getKey(), CoreMatchers.equalTo(Integer.parseInt(field.getValue())));
//            }
//            else{
//                json.body(field.getKey(), CoreMatchers.equalTo(field.getValue()));
//            }
//        }
//    }
}
