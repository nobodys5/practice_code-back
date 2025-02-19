package youngpil.backend.dto.response;

public interface ResponseMessage {
    String Success = "Success";
    String Validation_Fail = "Validation Fail";
    String TEL_AUTH_Fail = "TAF";
    String SIGN_IN_Fail = "Signin Fail";
    String TOKEN_CREATE_Fail = "Token Create Fail";

    
    String DUPLICATED_USER_ID = "Duplicated Id";
    String DUPLICATED_TELNUMBER = "Duplicated TelNumber";
    
    String DATABASE_ERROR = "Database Error";
}
