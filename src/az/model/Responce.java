package az.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responce")
public class Responce {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
