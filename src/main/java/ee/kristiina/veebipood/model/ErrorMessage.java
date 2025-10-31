package ee.kristiina.veebipood.model;

import lombok.Data;

import java.util.Date;

@Data // siin sees on @Getter @Setter. Automaatselt on olemas
public class ErrorMessage {
    private String message;
    private int status;
    private Date timestamp;
}
