package com.nhatthach.assignment1_recyclerview_todolist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
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
    private ArrayList<Task> filteredList;

    public TaskAdapter(ArrayList<Task> filteredList, ArrayList<Task> taskList) {
        this.filteredList = filteredList;
        this.taskList = taskList;
        // filteredList là danh sách hiển thị (có thể đã lọc/search), taskList là danh sách gốc (tất cả task)
        // Khi xóa task, cần xóa ở cả hai list để đảm bảo task biến mất ở mọi chế độ lọc/search
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDeadline;
        Button buttonComplete, buttonDelete, buttonEdit;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDeadline = itemView.findViewById(R.id.textDeadline);
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
        Task task = filteredList.get(position);
        holder.textTitle.setText(task.getTitle());
        if (task.getDeadline() != null && !task.getDeadline().isEmpty()) {
            // Chuyển định dạng yyyy-MM-dd -> dd-MM-yyyy
            String[] parts = task.getDeadline().split("-");
            if (parts.length == 3) {
                String formatted = parts[2] + "-" + parts[1] + "-" + parts[0];
                holder.textDeadline.setText("Deadline: " + formatted);
            } else {
                holder.textDeadline.setText("Deadline: " + task.getDeadline());
            }
            holder.textDeadline.setVisibility(View.VISIBLE);
        } else {
            holder.textDeadline.setText("");
            holder.textDeadline.setVisibility(View.GONE);
        }

        if (task.isCompleted()) {
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.buttonComplete.setEnabled(false);
            holder.buttonComplete.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#BDBDBD")));
        } else {
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.buttonComplete.setEnabled(true);
            holder.buttonComplete.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }

        holder.buttonComplete.setOnClickListener(v -> {
            task.setCompleted(true);
            holder.buttonComplete.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#BDBDBD")));
            holder.buttonComplete.setEnabled(false);

            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            Toast.makeText(v.getContext(), "Đã hoàn thành: " + task.getTitle(), Toast.LENGTH_SHORT).show();
        });

        holder.buttonDelete.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            Task toRemove = filteredList.get(currentPos);

            taskList.remove(toRemove);
            filteredList.remove(currentPos);
            notifyItemRemoved(currentPos);
        });

        holder.buttonEdit.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            EditText editText = new EditText(context);
            editText.setText(task.getTitle());
            editText.setHint("Task title");
            EditText editDeadline = new EditText(context);
            editDeadline.setFocusable(false);
            editDeadline.setClickable(true);
            editDeadline.setHint("Deadline (dd-MM-yyyy)");

            if (task.getDeadline() != null && !task.getDeadline().isEmpty()) {
                String[] parts = task.getDeadline().split("-");
                if (parts.length == 3) {
                    editDeadline.setText(parts[2] + "-" + parts[1] + "-" + parts[0]);
                } else {
                    editDeadline.setText(task.getDeadline());
                }
            }
            // Chọn ngày mới bằng DatePicker
            editDeadline.setOnClickListener(view1 -> {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                String current = editDeadline.getText().toString();
                if (current.matches("\\d{2}-\\d{2}-\\d{4}")) {
                    String[] p = current.split("-");
                    calendar.set(Integer.parseInt(p[2]), Integer.parseInt(p[1]) - 1, Integer.parseInt(p[0]));
                }
                new android.app.DatePickerDialog(context, (view2, year, month, dayOfMonth) -> {
                    String dateStr = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year);
                    editDeadline.setText(dateStr);
                },
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH),
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
                ).show();
            });
            layout.addView(editText);
            layout.addView(editDeadline);
            new android.app.AlertDialog.Builder(context)
                .setTitle("Edit Task")
                .setView(layout)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newTitle = editText.getText().toString().trim();
                    String newDeadline = editDeadline.getText().toString().trim();
                    if (!newTitle.isEmpty()) {
                        task.setTitle(newTitle);

                        if (newDeadline.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            String[] p = newDeadline.split("-");
                            task.setDeadline(p[2] + "-" + p[1] + "-" + p[0]);
                        } else if (newDeadline.isEmpty()) {
                            task.setDeadline("");
                        }
                        notifyItemChanged(position);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
}
