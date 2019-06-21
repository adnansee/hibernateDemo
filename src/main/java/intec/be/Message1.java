package intec.be;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message1 {

    @Id

    private int id;  //Primary key must be primitive

    public Message1() {
    }

    public  int getId() {
        return id;
    }

    public Message1 setId(int id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message1 setMessage(String message) {
        this.message = message;
        return this;
    }

    public Message1(int id, String message) {
        this.id = id;
        this.message = message;
    }

    private String message;

}
