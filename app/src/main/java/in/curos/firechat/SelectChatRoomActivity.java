package in.curos.firechat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import in.curos.firechat.screens.RoomSelectScreen;

/**
 * Created by curos on 14/04/17.
 */

public class SelectChatRoomActivity extends AppCompatActivity implements RoomSelectScreen.Controller {

    RoomSelectScreen roomSelectScreen;
    DatabaseReference rooms;

    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        roomSelectScreen = new RoomSelectScreen(this, this);
        setContentView(roomSelectScreen);

        user = FirebaseAuth.getInstance().getCurrentUser();

        getSupportActionBar().setTitle("FireChat : Subscribed Rooms");

        roomSelectScreen.showLoading();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        rooms = reference.child("/users/"+user.getUid()+"/rooms");

        rooms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                roomSelectScreen.clearList();
                roomSelectScreen.hideLoading();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    roomSelectScreen.addRoom(item.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_chat_room_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void roomSelected(String ref) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.REFERENCE, ref);

        startActivity(intent);
    }

    @Override
    public void createNewRoom() {
        AlertDialog.Builder alertDialog = (new AlertDialog.Builder(this));

        View base = getLayoutInflater().inflate(R.layout.layout_create_room_dialog, null);
        final EditText roomName = (EditText) base.findViewById(R.id.room_name);

        alertDialog.setView(base)
            .setTitle("Subscribe to a Room")
            .setPositiveButton("Subscribe", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    rooms.push().setValue(roomName.getText().toString());
                }
            })
            .setCancelable(true)
            .create().show();
    }
}
