package in.curos.firechat.screens;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.curos.firechat.FireChat;
import in.curos.firechat.MessagesAdapter;
import in.curos.firechat.R;
import in.curos.firechat.models.Message;

/**
 * Created by curos on 14/04/17.
 */

public class ChatScreen extends Screen<ChatScreen.Controller> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.message_input)
    EditText messageInput;

    @BindView(R.id.messages)
    RecyclerView messagesView;

    ArrayList<Message> messages;
    public MessagesAdapter adapter;

    public ChatScreen(Context context, Controller controller) {
        super(context, controller);
    }

    @Override
    public void initialize() {
        inflate(getContext(), R.layout.screen_chat, this);

        ButterKnife.bind(this);

        messages = new ArrayList<>();
        adapter = new MessagesAdapter(messages);
        messagesView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        messagesView.setLayoutManager(linearLayoutManager);
        messagesView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                Toast.makeText(getContext(), "cc", Toast.LENGTH_SHORT).show();
                messagesView.scrollToPosition(0);
            }
        });
    }

    public void sortMessages() {
        Collections.sort(messages);
    }

    public void setMessages(ArrayList<Message> messages) {
        messages.clear();
        messages.addAll(messages);
        sortMessages();
        adapter.notifyDataSetChanged();
        messagesView.scrollToPosition(0);
    }

    public void addMessage(Message message) {
        messages.add(message);
        sortMessages();
        messagesView.scrollToPosition(0);
        adapter.notifyItemInserted(0);
    }

    @OnClick(R.id.send_btn)
    void sendMessage() {
        String messageText = messageInput.getText().toString();
        messageInput.setText("");

        Message message = new Message(messageText, FireChat.timestamp(), controller.senderId());
        controller.sendMessage(message);
    }

    public interface Controller extends Screen.ControllerContract {
        void sendMessage(Message message);
        String senderId();
    }
}
