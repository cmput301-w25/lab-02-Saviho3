package com.example.listycity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addCity = findViewById(R.id.add_city);
        Button deleteCity = findViewById(R.id.delete_city);
        Button confirm = findViewById(R.id.confirm_city);
        EditText editCity = findViewById(R.id.edit);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;  // Save the selected position
        });


        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCity.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);

            }
        });

        deleteCity.setOnClickListener(v -> {
            if (selectedPosition != -1) {  // Check if an item is selected
                dataList.remove(selectedPosition);  // Remove the selected item
                cityAdapter.notifyDataSetChanged();  // Notify the adapter to refresh the list
                selectedPosition = -1;  // Reset the selected position
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editCity.getText().toString();
                dataList.add(str);
                cityAdapter.notifyDataSetChanged();
                editCity.setVisibility(View.INVISIBLE);
                confirm.setVisibility(View.INVISIBLE);
            }
        });




    }
}