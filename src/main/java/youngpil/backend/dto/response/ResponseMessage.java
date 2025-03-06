package youngpil.backend.dto.response;

public interface ResponseMessage {
    String Success = "Success";
    String Validation_Fail = "Validation Fail";
    String TEL_AUTH_Fail = "TAF";
    String SIGN_IN_Fail = "Signin Fail";
    String TOKEN_CREATE_Fail = "Token Create Fail";
    String AUTHENTICATION_Fail = "Authentication Fail";

    
    String DUPLICATED_USER_ID = "Duplicated Id";
    String DUPLICATED_TELNUMBER = "Duplicated TelNumber";
    String NO_EXIST_USER_ID = "No exist user id";
    String NO_EXIST_TOOL = "No Exist Tool";
    
    String DATABASE_ERROR = "Database Error";
}
