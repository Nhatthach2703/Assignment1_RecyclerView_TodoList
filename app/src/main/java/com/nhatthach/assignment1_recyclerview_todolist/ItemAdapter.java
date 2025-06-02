package com.nhatthach.assignment1_recyclerview_todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhatthach.assignment1_recyclerview_todolist.model.Item;

import java.util.List;
public class ItemAdapter extends
        RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }
    public static class ViewHolder extends
            RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                 parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {
//        Item item = itemList.get(position);
//        holder.textView.setText(item.getName());
//        holder.imageView.setImageResource(item.getImageResId());

        Item item = itemList.get(position);
        holder.textView.setText(item.getName());
        holder.imageView.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
