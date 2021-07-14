package com.example.yoogad_task.view;

import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yoogad_task.model.PostModel;
import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<PostModel> list;

    public PostAdapter() {
        list = new ArrayList();
    }

    public void setPostsList(List<PostModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(com.example.yoogad_task.R.layout.post_layout, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.body.setText(list.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(com.example.yoogad_task.R.id.title);
            body = itemView.findViewById(com.example.yoogad_task.R.id.body);
        }
    }

}

