package au.com.future-airlines.entities.cardDefinition;

/**
 * Created by boses on 26/06/2018.
 */
public class Data {

    public String type;
    public String id;
    public Payload payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
