package in.curos.firechat.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by curos on 14/04/17.
 */

@IgnoreExtraProperties
public class User {

    public String id;
    public String name;
    public String email;

    public User() {}

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
