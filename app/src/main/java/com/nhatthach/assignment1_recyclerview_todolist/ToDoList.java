package com.nhatthach.assignment1_recyclerview_todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhatthach.assignment1_recyclerview_todolist.model.Item;
import com.nhatthach.assignment1_recyclerview_todolist.model.Task;
import com.nhatthach.assignment1_recyclerview_todolist.TaskAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToDoList extends AppCompatActivity {

    private EditText editTextTask, editTextSearch, editTextDeadline;
    private Button buttonAdd;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> taskList;
    private Spinner spinnerFilter;
    private ArrayList<Task> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_do_list);

        // Set padding for system UI (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        editTextTask = findViewById(R.id.editTextTask);
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextDeadline = findViewById(R.id.editTextDeadline);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTasks); // ép kiểu tại đây
        spinnerFilter = findViewById(R.id.spinnerFilter);

        taskList = new ArrayList<>();
        filteredList = new ArrayList<>();
        taskList.add(new Task("Buy groceries", "2025-06-15"));
        taskList.add(new Task("Do homework", "2025-06-20"));
        taskList.add(new Task("Read a book"));
        filteredList.addAll(taskList);

        adapter = new TaskAdapter(filteredList, taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup filter options
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"All", "Completed", "Incomplete"});
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(filterAdapter);

        editTextSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTasks();
            }
            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        buttonAdd.setOnClickListener(view -> {
            String taskTitle = editTextTask.getText().toString().trim();
            String deadline = editTextDeadline.getText().toString().trim();
            if (!taskTitle.isEmpty()) {
                Task newTask = deadline.isEmpty() ? new Task(taskTitle) : new Task(taskTitle, deadline);
                taskList.add(newTask);
                // Sau khi thêm task mới, gọi filterTasks để cập nhật filteredList và adapter
                filterTasks();
                editTextTask.setText("");
                editTextDeadline.setText("");
            }
        });

        // Xử lý chọn deadline bằng DatePickerDialog khi bấm vào ô deadline
        editTextDeadline.setOnClickListener(v -> {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            new android.app.DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String dateStr = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
                editTextDeadline.setText(dateStr);
            },
            calendar.get(java.util.Calendar.YEAR),
            calendar.get(java.util.Calendar.MONTH),
            calendar.get(java.util.Calendar.DAY_OF_MONTH)
            ).show();
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterTasks();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void sortByCreatedTime(ArrayList<Task> list) {
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Long.compare(t2.getCreatedTime(), t1.getCreatedTime());
            }
        });
    }

    private void filterTasks() {
        String keyword = editTextSearch.getText().toString().toLowerCase().trim();
        int filterPos = spinnerFilter.getSelectedItemPosition();
        filteredList.clear();
        for (Task t : taskList) {
            boolean matchStatus = (filterPos == 0) || (filterPos == 1 && t.isCompleted()) || (filterPos == 2 && !t.isCompleted());
            boolean matchKeyword = t.getTitle().toLowerCase().contains(keyword);
            if (matchStatus && matchKeyword) {
                filteredList.add(t);
            }
        }
        sortByCreatedTime(filteredList);
        adapter.notifyDataSetChanged(); // Cập nhật adapter sau khi lọc
    }
}
