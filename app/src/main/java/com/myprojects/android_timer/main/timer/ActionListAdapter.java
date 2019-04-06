package com.myprojects.android_timer.main.timer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.util.BunchOfDataToSave;
import com.myprojects.android_timer.main.util.PlayButtonHandler;

import java.util.List;

public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.Holder> {

    final List<RecyclerItem> list;
    Context context;
    TextView count;
    final PlayButtonHandler playButtonHandler;
    Drawable play;
    Drawable pause;

    public BunchOfDataToSave getBunchOfDataToSave() {
        if(playButtonHandler != null) {
            return playButtonHandler.getBunchOfDataToSave();
        } else {
            return null;
        }
    }

    public ActionListAdapter(List<RecyclerItem> list, Context context, TextView count, Drawable play, Drawable pause) {
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
        final RecyclerItem reference = list.get(position);
        holder.title.setText(reference.getTitle());

        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButtonHandler.handlePlayButton(count, holder.playButton, holder.title.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
