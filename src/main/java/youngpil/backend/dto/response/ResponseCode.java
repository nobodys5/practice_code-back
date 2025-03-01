package youngpil.backend.dto.response;

public interface ResponseCode {
    String Success = "SU";
    String Validation_Fail = "VF";
    String TEL_AUTH_Fail = "TAF";

    String SIGN_IN_Fail = "SF";
    String TOKEN_CREATE_Fail = "TCF";
    String AUTHENTICATION_Fail = "AF";
    

    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TELNUMBER = "DT";
    String NO_EXIST_USER_ID = "NI";

    
    String DATABASE_ERROR = "DBE";
}
