package com.myprojects.android_timer.main.timer;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;
import com.myprojects.android_timer.main.data.newdata.entity.LogEntity;


import java.util.List;

public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.Holder> {

    List<ActionEntity> list;
    private Drawable play;
    private Drawable pause;
    BoundService boundService;

    public LogEntity getBunchOfDataToSave() {
        if(boundService != null) {
            return boundService.getBunchOfDataToSave();
        } else {
            return null;
        }
    }

    public ActionListAdapter(List<ActionEntity> list, Drawable play, Drawable pause) {
        this.list = list;
        this.play = play;
        this.pause = pause;
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
                boundService.handlePlayButton(holder.playButton, reference);
            }
        });
        if (boundService != null && reference.equals(boundService.getActionCurrentlyClicked())) {
            holder.playButton.setImageDrawable(pause);
        }
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
//        public ConstraintLayout layout;
        public TextView title;
        public FloatingActionButton playButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_item);
//            layout = itemView.findViewById(R.id.card_layout);
            title = itemView.findViewById(R.id.card_title);
            playButton = itemView.findViewById(R.id.fab_play);
        }
    }

    public void setBoundService(BoundService boundService) {
        this.boundService = boundService;
    }

    public void updateView() {
        if (boundService != null) {
            this.notifyItemChanged(list.indexOf(boundService.getActionCurrentlyClicked()));
        }
    }
}
