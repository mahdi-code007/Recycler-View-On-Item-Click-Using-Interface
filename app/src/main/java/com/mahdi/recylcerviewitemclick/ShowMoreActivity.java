package com.mahdi.recylcerviewitemclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ShowMoreActivity extends AppCompatActivity {
private ImageView book_image2;
private TextView book_intros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_more);
        book_image2 = findViewById(R.id.book_img2);
        book_intros = findViewById(R.id.books_intros_tv2);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("book_intros");
            int img = extras.getInt("book_images");
            book_intros.setText(value);
            book_image2.setImageResource(img);
        }
    }
}
