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

    private EditText editTextTask;
    private Button buttonAdd;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> taskList;
    private Spinner spinnerFilter;
    private ArrayList<Task> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_do_list);

        // Set padding for system UI (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewTasks); // ép kiểu tại đây
        spinnerFilter = findViewById(R.id.spinnerFilter);

        // Initialize list and adapter
        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList);
        taskList.add(new Task("Buy groceries"));
        taskList.add(new Task("Do homework"));
        taskList.add(new Task("Read a book"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup filter options
        filteredList = new ArrayList<>();
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"All", "Completed", "Incomplete"});
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(filterAdapter);

        // Default: show all
        filteredList.addAll(taskList);
        adapter = new TaskAdapter(filteredList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Hàm sắp xếp danh sách theo thời gian tạo (mới nhất lên đầu)
        buttonAdd.setOnClickListener(view -> {
            // Lấy nội dung task từ EditText
            String taskTitle = editTextTask.getText().toString().trim();
            if (!taskTitle.isEmpty()) {
                // Tạo task mới và thêm vào danh sách gốc
                Task newTask = new Task(taskTitle);
                taskList.add(newTask);
                // Lọc lại filteredList theo filter hiện tại trên Spinner
                int filterPos = spinnerFilter.getSelectedItemPosition();
                filteredList.clear();
                switch (filterPos) {
                    case 1: // Nếu đang chọn Completed, chỉ hiển thị task đã hoàn thành
                        for (Task t : taskList) if (t.isCompleted()) filteredList.add(t);
                        break;
                    case 2: // Nếu đang chọn Incomplete, chỉ hiển thị task chưa hoàn thành
                        for (Task t : taskList) if (!t.isCompleted()) filteredList.add(t);
                        break;
                    default: // Nếu đang chọn All, hiển thị tất cả task
                        filteredList.addAll(taskList);
                }
                // Sắp xếp danh sách theo thời gian tạo
                sortByCreatedTime(filteredList);
                // Cập nhật lại giao diện danh sách
                adapter.notifyDataSetChanged();
                // Xóa nội dung EditText sau khi thêm
                editTextTask.setText("");
            }
        });

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filteredList.clear();
                switch (position) {
                    case 1: // Completed
                        for (Task t : taskList) if (t.isCompleted()) filteredList.add(t);
                        break;
                    case 2: // Incomplete
                        for (Task t : taskList) if (!t.isCompleted()) filteredList.add(t);
                        break;
                    default: // All
                        filteredList.addAll(taskList);
                }
                // Sắp xếp danh sách theo thời gian tạo
                sortByCreatedTime(filteredList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    // Hàm sắp xếp danh sách theo thời gian tạo (mới nhất lên đầu)
    private void sortByCreatedTime(ArrayList<Task> list) {
        Collections.sort(list, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Long.compare(t1.getCreatedTime(), t2.getCreatedTime()); // Descending: newest first
            }
        });
    }
}

