package com.mahdi.recylcerviewitemclick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<NovelsModel> novelsModels;
    private RecycleViewOnItemClick recycleViewOnItemClick;

    public RecyclerAdapter(List<NovelsModel> novelsModels, RecycleViewOnItemClick recycleViewOnItemClick) {
        this.novelsModels = novelsModels;
        this.recycleViewOnItemClick = recycleViewOnItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.book_name.setText(novelsModels.get(position).getNovelNames());
        holder.book_author.setText(novelsModels.get(position).getNovelAuthors());
        holder.book_image.setImageResource(novelsModels.get(position).getNovelImage());
    }

    @Override
    public int getItemCount() {
        return novelsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView row_bg, book_image;
        private TextView book_name, book_author;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_bg = itemView.findViewById(R.id.row_bg_img);
            book_image = itemView.findViewById(R.id.book_img);
            book_name = itemView.findViewById(R.id.book_name_tv);
            book_author = itemView.findViewById(R.id.book_author);
            ratingBar = itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recycleViewOnItemClick.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recycleViewOnItemClick.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
