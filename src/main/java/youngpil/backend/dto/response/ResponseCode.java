package youngpil.backend.dto.response;

public interface ResponseCode {
    String Success = "SU";
    String Validation_Fail = "VF";
    
    String DUPLICATED_USER_ID = "DI";
    String DUPLICATED_TELNUMBER = "DT";
    
    String DATABASE_ERROR = "DBE";
}
