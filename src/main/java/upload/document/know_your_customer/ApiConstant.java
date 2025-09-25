package upload.document.know_your_customer;


public class ApiConstant {

    public static final String API_ROOT = "/api";
    public static final String API_SERVICE = "/documentKYC";
    public static final String API_V1 = "/v1";

    public static final String API_ROOT_SERVICE = API_ROOT + API_SERVICE + API_V1;

    public static final String USER = "/users";
    public static final String USER_KEY = USER + "/{userKey}";
    public static final String FIRE = USER_KEY + "/fire";
    public static final String ACTIVATE = USER_KEY + "/activate";

    public static final String KYC = "/kycFiles";
    public static final String KYC_KEY = KYC + "/{fileKey}";


    private ApiConstant() {
    }

}
