package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;


public class MyStepdefs {
    private Response responsePost;
    private Response responseGet;
    private RequestSpecification requestPost;
    private RequestSpecification requestGet;
    private String ENDPOINT_GET_BOOK = "https://fakerestapi.azurewebsites.net/api/Books";

    @Given("^an insertion of a book with id '(.*?)', title '(.*?)', description '(.*?)', pageCount '(.*?)', excerpt '(.*?)', PublishDate '(.*?)'$")
    public void an_insertion_of_a_book_with_id_title_description_pageCount_excerpt_PublishDate(int id, String title, String description, String pageCount, String excerpt, String PublishDate) {
        JSONObject book = new JSONObject();

        book.put("ID", id);
        book.put("Title", title);
        book.put("Description", description);
        book.put("PageCount", pageCount);
        book.put("Excerpt", excerpt);
        book.put("PublishDate", PublishDate);

        requestPost = RestAssured.given().
                request().
                header("Content-Type", "application/json").
                body(book.toJSONString());

    }

    @When("I post new book")
    public void i_post_new_book() {
        responsePost = requestPost.when().post(ENDPOINT_GET_BOOK);
        System.out.println("response: " + responsePost.prettyPrint());
    }

    @Then("^the book with title '(.*?)' was successfully created and statusCode return '(.*?)'$")
    public void the_book_with_title_was_successfully_created_and_statusCode_return(String title, int statusCode) {
          responsePost.then().
            statusCode(statusCode).
            assertThat().
            body("Title", Matchers.equalTo(title));
    }

    @Given("fetch active book")
     public void fetch_active_book() {
            requestGet = RestAssured.given().header("Content-Type", "application/json");
    }

    @When("^fetch book with id '(.*?)'$")
    public void fetch_book_with_title(String id){
        responseGet = requestGet.when().get(ENDPOINT_GET_BOOK + "/" + id);
        https://fakerestapi.azurewebsites.net/api/Books/
        System.out.println("response: " + responseGet.prettyPrint());
    }

    @Then("^the book with title '(.*?)' was successfully found")
    public void the_book_with_title_was_successfully_found(String title) {
        responseGet.then().statusCode(200);
    }
}