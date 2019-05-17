package com.myprojects.android_timer.main.timer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.util.BunchOfDataToSave;
import com.myprojects.android_timer.main.util.PlayButtonHandler;

import java.util.List;

public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.Holder> {

    List<ActionEntity> list;
    private Context context;
    private TextView count;
    final PlayButtonHandler playButtonHandler;
    private Drawable play;
    private Drawable pause;

    public BunchOfDataToSave getBunchOfDataToSave() {
        if(playButtonHandler != null) {
            return playButtonHandler.getBunchOfDataToSave();
        } else {
            return null;
        }
    }

    public ActionListAdapter(List<ActionEntity> list, Context context, TextView count, Drawable play, Drawable pause) {
        this.list = list;
        this.context = context;
        this.count = count;
        this.play = play;
        this.pause = pause;
        playButtonHandler = new PlayButtonHandler(play, pause);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_view, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {
        final ActionEntity reference = list.get(position);
        holder.title.setText(reference.getName());

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButtonHandler.handlePlayButton(count, holder.playButton, holder.title.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null? 0 : list.size();
    }

    public void setList(List<ActionEntity> actionEntities) {
        this.list = actionEntities;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ConstraintLayout layout;
        public TextView title;
        public FloatingActionButton playButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item);
            layout = itemView.findViewById(R.id.card_layout);
            title = itemView.findViewById(R.id.card_title);
            playButton = itemView.findViewById(R.id.fab_play);
        }
    }
}
