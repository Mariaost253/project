package postalCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import postalCode.entities.PostCodeFullEntity;
import postalCode.entities.PostCodeList;
import postalCode.entities.Result;
import postalCode.entities.Validator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostCodesModel {

    static Logger logger = LoggerFactory.getLogger(PostCodesModel.class);
    static PostCodeHttpRequests postCodeHttpRequests;
    static String code;

    public void init(String code) throws IOException {
        postCodeHttpRequests = new PostCodeHttpRequests();
        this.code=code;
        if(this.validatePostCodeInput(code)){
            if(this.validatePostCodeRequest()){
                this.postCodeGetRequest();
               this.getListOfNearest();
            }
       }else{
           System.out.println("Invalid postal code");
        }
    }

    public static boolean validatePostCodeRequest() throws IOException {
        Validator validator = postCodeHttpRequests.validatePostCode(code);
        if(validator.getStatus()==200){
            logger.info("Given post code is valid, Http status : "+validator.getStatus());
            return true;
        }else{
            logger.error("Given post code is invalid, Http status : "+validator.getStatus());
            return false;
        }
    }


    public static void postCodeGetRequest() throws IOException {
        PostCodeFullEntity postCodeFullEntity= postCodeHttpRequests.getPostCode(code);
        if(postCodeFullEntity.getStatus()==200){
            logger.info("Get request parsed successfully Http status : "+postCodeFullEntity.getStatus());
            System.out.println("Country : "+postCodeFullEntity.getResult().getCountry());
            System.out.println("Region: "+postCodeFullEntity.getResult().getRegion());
        }else {
            logger.error("Something went wrong http status: "+ postCodeFullEntity.getStatus());
        }
    }

    public static void getListOfNearest() throws IOException {
        PostCodeList postCodeList= postCodeHttpRequests.getListOfCountries(code);
        if(postCodeList.getStatus()==200){
            logger.info("Get list of nearest countries passed successfully Http status : "+postCodeList.getStatus());
          for(int i=0;i<postCodeList.getResult().length;i++) {
              Result[] r = postCodeList.getResult();
              System.out.println("Country : " + r[i].getCountry());
              System.out.println("Region: "+ r[i].getRegion());
          }
        }else {
            logger.error("Something went wrong http status: "+ postCodeList.getStatus());
        }
    }

    public static boolean validatePostCodeInput(String code){
        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }
}
