package youngpil.backend.service;

public interface JwtService {
    String getjwt(String name);
    String validjwt(String jwt);
}
