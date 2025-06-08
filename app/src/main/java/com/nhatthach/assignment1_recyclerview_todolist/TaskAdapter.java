package com.nhatthach.assignment1_recyclerview_todolist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhatthach.assignment1_recyclerview_todolist.model.Task;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private ArrayList<Task> taskList;

    public TaskAdapter(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        Button buttonComplete, buttonDelete, buttonEdit;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            buttonComplete = itemView.findViewById(R.id.buttonComplete);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.textTitle.setText(task.getTitle());

        // Kiểm tra nếu task đã hoàn thành -> cập nhật giao diện
        if (task.isCompleted()) {
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.buttonComplete.setEnabled(false);
        } else {
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.buttonComplete.setEnabled(true);
        }

        holder.buttonComplete.setOnClickListener(v -> {
            task.setCompleted(true);
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.buttonComplete.setEnabled(false);

            Toast.makeText(v.getContext(), "Đã hoàn thành: " + task.getTitle(), Toast.LENGTH_SHORT).show();
        });

        holder.buttonDelete.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            taskList.remove(currentPos);
            notifyItemRemoved(currentPos);
        });

        holder.buttonEdit.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            EditText editText = new EditText(context);
            editText.setText(task.getTitle());
            new android.app.AlertDialog.Builder(context)
                .setTitle("Edit Task")
                .setView(editText)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newTitle = editText.getText().toString().trim();
                    if (!newTitle.isEmpty()) {
                        task.setTitle(newTitle);
                        notifyItemChanged(position);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
