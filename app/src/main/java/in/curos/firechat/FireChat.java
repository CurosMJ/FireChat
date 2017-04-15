package in.curos.firechat;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by curos on 14/04/17.
 */

public class FireChat extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static int timestamp() {
        return (int) (System.currentTimeMillis()/1000);
    }
}
