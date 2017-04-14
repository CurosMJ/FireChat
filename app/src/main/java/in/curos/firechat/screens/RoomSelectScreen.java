package in.curos.firechat.screens;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.curos.firechat.R;

/**
 * Created by curos on 14/04/17.
 */

public class RoomSelectScreen extends Screen<RoomSelectScreen.Controller> {

    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    OnClickListener clickListener;

    public RoomSelectScreen(Context context, Controller controller) {
        super(context, controller);
    }

    @Override
    public void initialize() {
        inflate(getContext(), R.layout.screen_room_select, this);
        ButterKnife.bind(this);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String reference = v.getTag().toString();
                controller.roomSelected(reference);
            }
        };
    }

    public void clearList() {
        layout.removeAllViewsInLayout();
    }

    public void addRoom(String name) {
        layout.addView(createItem(name));
    }

    protected LinearLayout createItem(String roomName) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(params);

        TextView title = new TextView(getContext());
        title.setTextSize(20);
        title.setPadding(40, 40, 40, 40);
        title.setText(roomName);

        View view = new View(getContext());
        view.setBackgroundColor(Color.parseColor("#9e9e9e"));

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        view.setLayoutParams(params1);

        layout.addView(title);
        layout.addView(view);

        layout.setTag(roomName);
        layout.setOnClickListener(clickListener);

        return layout;
    }

    @OnClick(R.id.fab)
    protected void createNewRoom() {
        controller.createNewRoom();
    }

    public interface Controller extends Screen.ControllerContract {
        void roomSelected(String ref);
        void createNewRoom();
    }
}
