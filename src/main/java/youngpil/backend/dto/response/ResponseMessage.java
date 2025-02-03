package youngpil.backend.dto.response;

public interface ResponseMessage {
    String Success = "Success";
    String Validation_Fail = "Validation Fail";

    
    String DUPLICATED_USER_ID = "Duplicated Id";
    String DUPLICATED_TELNUMBER = "Duplicated TelNumber";
    
    String DATABASE_ERROR = "Database Error";
}
