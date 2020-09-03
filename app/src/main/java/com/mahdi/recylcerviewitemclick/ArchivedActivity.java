package com.mahdi.recylcerviewitemclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ArchivedActivity extends AppCompatActivity implements RecycleViewOnItemClick {
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<NovelsModel> archivedNovels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived);

        recyclerView = findViewById(R.id.archived_rv);


        Parcelable parcelable = getIntent().getParcelableExtra("archivedNovels");
        if (parcelable != null) {
            archivedNovels = Parcels.unwrap(parcelable);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(archivedNovels, this);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}