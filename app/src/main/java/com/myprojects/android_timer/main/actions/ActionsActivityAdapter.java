package com.myprojects.android_timer.main.actions;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myprojects.android_timer.R;
import com.myprojects.android_timer.main.data.newdata.entity.ActionEntity;

import java.util.List;

import static android.content.ContentValues.TAG;


public class ActionsActivityAdapter extends RecyclerView.Adapter<ActionsActivityAdapter.Holder>{

    private List<ActionEntity> list;

    public void setList(List<ActionEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ActionEntity reference = list.get(position);
        holder.title.setText(reference.getName());
        holder.description.setText(reference.getDescription());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.activity_item_title);
            description = itemView.findViewById(R.id.activity_item_description);
        }
    }
}
