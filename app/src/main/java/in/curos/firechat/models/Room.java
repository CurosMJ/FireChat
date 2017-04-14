package in.curos.firechat.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by curos on 14/04/17.
 */

@IgnoreExtraProperties
public class Room {
    public String name;

    public Room() {

    }

    public Room(String name) {
        this.name = name;
    }
}
