package in.curos.firechat;

import android.app.Application;

/**
 * Created by curos on 14/04/17.
 */

public class FireChat extends Application {
    public static int timestamp() {
        return (int) (System.currentTimeMillis()/1000);
    }
}
