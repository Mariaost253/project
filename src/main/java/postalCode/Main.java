package postalCode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

       PostCodesModel postCodesModel = new PostCodesModel();
       //Remove comment for user input
       //postCodesModel.init(args[0]);
		postCodesModel.init("CB3 0FA");
}
}
