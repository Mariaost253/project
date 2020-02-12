package tests.unit;

import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.mockito.MockitoAnnotations;
import postalCode.PostCodeHttpRequests;
import postalCode.entities.PostCodeFullEntity;
import postalCode.entities.Validator;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class UnitTest {

    @Mock
    private PostCodeHttpRequests postCodeHttpRequests;

    @BeforeTest
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetValidateRequest() throws Exception {
        Validator data = stubData(200,true);
        when(postCodeHttpRequests.validatePostCode("CB3 0FA")).thenReturn(data);
        Assert.assertEquals(postCodeHttpRequests.validatePostCode("CB3 0FA"),data);
    }

    @Test
    public void testGet_returnsNotNull() throws IOException {
        PostCodeFullEntity entity = new PostCodeFullEntity();
        when(postCodeHttpRequests.getPostCode("CB3 0FA")).thenReturn(entity);
        Assert.assertSame(entity,postCodeHttpRequests.getPostCode("CB3 0FA"));
    }

    @Test
    public void testGetValidateRequestInvalid() throws Exception {
        Validator data = stubData(200,false);
        when(postCodeHttpRequests.validatePostCode("ZZQ 0FA")).thenReturn(data);
        Validator res = postCodeHttpRequests.validatePostCode("ZZQ 0FA");
        Assert.assertEquals(res.getResult(),data.getResult());
    }

    @Test
    public void testGet_returnsNull() throws IOException {
        PostCodeFullEntity entity = new PostCodeFullEntity();
        when(postCodeHttpRequests.getPostCode("0FA")).thenReturn(entity);
        Assert.assertSame(entity,postCodeHttpRequests.getPostCode("0FA"));
    }

    private Validator stubData(int s,boolean r) {
        Validator p = new Validator();
        p.setStatus(s);
        p.setResult(r);
        return p;
    }
}
