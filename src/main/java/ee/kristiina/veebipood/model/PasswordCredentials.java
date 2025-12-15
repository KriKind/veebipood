package ee.kristiina.veebipood.model;

import lombok.Data;

@Data
public class PasswordCredentials {
    public Long id;
    private String oldPassword;
    private String newPassword;
}
