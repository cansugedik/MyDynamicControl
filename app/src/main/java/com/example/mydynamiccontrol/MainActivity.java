package com.example.mydynamiccontrol;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydynamiccontrol.box.CustomAdapter;
import com.example.mydynamiccontrol.box.CustomAdapterLeft;
import com.example.mydynamiccontrol.box.CustomAdapterTop;
import com.example.mydynamiccontrol.box.MyModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewCreate;
    private RecyclerView mRecyclerViewTop;
    private RecyclerView mRecyclerViewLeft;

    private EditText editTextSutun, editTextSatir;

    private Button buttonOlustur;

    int sutunBilgisi, satirBilgisi;

    private boolean scrolling;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initControlEvents();

    }

    private void initControls() {

        editTextSutun = findViewById(R.id.edittextSutun);
        editTextSatir = findViewById(R.id.editTextSatir);
        buttonOlustur = findViewById(R.id.buttonOlustur);

        mRecyclerViewCreate = findViewById(R.id.recyclerViewCreate);
        mRecyclerViewTop = findViewById(R.id.recyclerViewTop);
        mRecyclerViewLeft = findViewById(R.id.recyclerViewLeft);
    }

    private void initControlEvents() {

        scrolling = false;

        buttonOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSutun.getText().toString().length() > 0 && editTextSatir.getText().toString().length() > 0) {
                    sutunBilgisi = Integer.parseInt(editTextSutun.getText().toString().trim());
                    satirBilgisi = Integer.parseInt(editTextSatir.getText().toString().trim());

                    setRecyclerViewData();
                }
            }
        });

        mRecyclerViewCreate.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!scrolling) {
                    scrolling = true;
                    mRecyclerViewLeft.scrollBy(dx, dy);
                    super.onScrolled(recyclerView, dx, dy);
                }
                else
                    scrolling = false;
            }
        });

        mRecyclerViewLeft.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!scrolling) {
                    scrolling = true;
                    mRecyclerViewCreate.scrollBy(dx, dy);
                    super.onScrolled(recyclerView, dx, dy);
                }
                else
                    scrolling = false;
            }
        });
    }

    private void setRecyclerViewData() {
        mRecyclerViewCreate.setLayoutManager(new GridLayoutManager(MainActivity.this, sutunBilgisi));
        mRecyclerViewCreate.setAdapter(new CustomAdapter(MainActivity.this, getList()));

        mRecyclerViewTop.setLayoutManager(new GridLayoutManager(MainActivity.this,sutunBilgisi));
        mRecyclerViewTop.setAdapter(new CustomAdapterTop(MainActivity.this, getListTop()));

        mRecyclerViewLeft.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerViewLeft.setAdapter(new CustomAdapterLeft(MainActivity.this, getListLeft()));
    }


    private List<MyModel> getList() {

        List<MyModel> list = new ArrayList<>();

        for (int i = 0; i < satirBilgisi*sutunBilgisi; i++){
            list.add(new MyModel(i, ""+i,8));
        }

        return list;
    }

    private List<MyModel> getListLeft() {

        List<MyModel> list = new ArrayList<>();

        for (int i = 0; i <= satirBilgisi; i++){
            list.add(new MyModel(i, ""+i,0));
        }

        return list;
    }

    private List<MyModel> getListTop() {

        List<MyModel> list = new ArrayList<>();

        for (int i = 1; i <= sutunBilgisi; i++){
            list.add(new MyModel(i, ""+i,8));
        }

        return list;
    }
}
