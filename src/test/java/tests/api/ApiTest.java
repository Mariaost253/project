package tests.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import postalCode.PostCodeHttpRequests;
import postalCode.entities.PostCodeFullEntity;
import postalCode.entities.PostCodeList;
import postalCode.entities.Validator;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.apache.http.protocol.HTTP.CONTENT_TYPE;

public class ApiTest {

    public PostCodeHttpRequests postCodeHttpRequests;

    @BeforeClass
    public void init(){
        postCodeHttpRequests=  new PostCodeHttpRequests();
    }

    @Test
    public void testValidRequest_ValidInput() throws IOException {
        Validator validator = postCodeHttpRequests.validatePostCode("CB3 0FA");
        Assert.assertEquals(validator.getStatus(),200);
    }

    @Test
    public void testValidRequest_InValidInput() throws IOException {
        Validator validator = postCodeHttpRequests.validatePostCode("CZ3 0ZA");
        Assert.assertFalse(validator.getResult());
    }


    @Test
    public void testGetRequest_ValidInputEntityShouldNotBeNull() throws IOException {
        PostCodeFullEntity pr = postCodeHttpRequests.getPostCode("CB3 0FA");
        Assert.assertNotNull(pr);
        Assert.assertEquals(pr.getResult().getPostcode(),"CB3 0FA");
        Assert.assertEquals(pr.getResult().getCountry(),"England");
    }

    @Test
    public void testListNearest_ShouldBeNotNull() throws IOException {
        PostCodeList list = postCodeHttpRequests.getListOfCountries("CB3 0FA");
        Assert.assertNotNull(list);
    }

    @Test
    public void testGet_InvalidInput() throws IOException {
        PostCodeFullEntity postCodeFullEntity = postCodeHttpRequests.getPostCode("ZSS 333");
       Assert.assertEquals(postCodeFullEntity.getStatus(),404);
    }



    /**Rest Assured test**/
    @Test
    public void restAssured_TestPostRequest() throws IOException {
        Response response=given()
                .contentType(CONTENT_TYPE)
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .body("{\"postcodes\" : [\"OX49 5NU\", \"M32 0JG\", \"NE30 1DP\"]}")
                .when()
                .post("http://api.postcodes.io/postcodes")
                .then().statusCode(200)
                .extract()
                .response();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertNotNull(response.statusCode());
    }
}
