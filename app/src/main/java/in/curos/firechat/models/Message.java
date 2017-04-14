package in.curos.firechat.models;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by curos on 14/04/17.
 */

@IgnoreExtraProperties
public class Message implements Comparable<Message> {
    public String message;
    public int timestamp;
    public String senderId;

    public Message() {
    }

    public Message(String message, int timestamp, String senderId) {
        this.message = message;
        this.timestamp = timestamp;
        this.senderId = senderId;
    }

    @Override
    public int compareTo(@NonNull Message o) {
        return Integer.compare(o.timestamp, timestamp);
    }
}
