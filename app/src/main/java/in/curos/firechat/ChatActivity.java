package in.curos.firechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import in.curos.firechat.models.Message;
import in.curos.firechat.screens.ChatScreen;

public class ChatActivity extends AppCompatActivity implements ChatScreen.Controller {

    public static final String REFERENCE = "reference";

    ChatScreen view;

    DatabaseReference chatRoom;
    DatabaseReference messages;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new ChatScreen(this, this);
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();

        view.adapter.setCurrentUserId(senderId());

        String reference = getIntent().getStringExtra(REFERENCE);
        chatRoom = FirebaseDatabase.getInstance().getReference("/rooms/"+reference);

        messages = chatRoom.child("/messages");
        view.showLoading();
        messages.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Message> list = new ArrayList<Message>();
                for (DataSnapshot msg : dataSnapshot.getChildren()) {
                    Message message = msg.getValue(Message.class);
                    list.add(message);
                }
                view.setMessages(list);
                view.hideLoading();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        messages.addChildEventListener(new MessageEventListener());

        getSupportActionBar().setTitle(reference);
    }

    @Override
    public void sendMessage(Message message) {
        messages.push().setValue(message);
    }

    @Override
    public String senderId() {
        return user.getUid();
    }

    private class MessageEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Message message = dataSnapshot.getValue(Message.class);
            view.addMessage(message);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
