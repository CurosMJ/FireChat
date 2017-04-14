package in.curos.firechat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.curos.firechat.models.Message;

/**
 * Created by curos on 14/04/17.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.VH> {

    private String currentUserId;
    public ArrayList<Message> messages;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    Date date = new Date();
    DateFormat dateFormat = SimpleDateFormat.getTimeInstance(DateFormat.SHORT);

    public MessagesAdapter(ArrayList<Message> messages) {
        this.currentUserId = null;
        this.messages = messages;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
        notifyDataSetChanged();
    }

    public int normalizePosition(int position) {
        return position;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = R.layout.layout_message_item_left;
        if (viewType == RIGHT) {
            layout = R.layout.layout_message_item_right;
        }
        return new VH(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Message message = messages.get(normalizePosition(position));
        date.setTime(message.timestamp * 1000);
        holder.time.setText(dateFormat.format(date));
        holder.message.setText(message.message);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(normalizePosition(position));
        if (message.senderId.equals(currentUserId)) {
            return RIGHT;
        } else {
            return LEFT;
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class VH extends RecyclerView.ViewHolder {

        @BindView(R.id.message)
        TextView message;

        @BindView(R.id.timestamp)
        TextView time;

        View base;

        VH(View itemView) {
            super(itemView);
            base = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
