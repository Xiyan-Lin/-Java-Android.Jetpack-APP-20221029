package com.example.app_paging.paging;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_paging.R;
import com.example.app_paging.model.Book;
import com.squareup.picasso.Picasso;

public class BookPagedListAdapter extends PagedListAdapter<Book, BookPagedListAdapter.BookListViewHolder> {

    private Context context;

    public BookPagedListAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    // 比對處理
    private static DiffUtil.ItemCallback<Book> diffCallback =
            new DiffUtil.ItemCallback<Book>() {
                @Override
                public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
                    return oldItem.getIsbn13().equals(newItem.getIsbn13());
                }
            };

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.books_list_item, parent, false
        );
        return new BookListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        Book book = getItem(position);
        if(book == null) return;
        // 配置文字
        holder.bookTitle.setText(book.getTitle());
        holder.bookPrice.setText(book.getPrice());
        holder.bookIsbn.setText(book.getIsbn13());
        // 配置圖片
        Picasso.get().load(book.getImage()).placeholder(R.drawable.loading).into(holder.bookImage);
        //Picasso.get().load(book.getImage()).placeholder(R.drawable.loading_icon).into(holder.bookImage);
        // 分享資料
        holder.bookImage.setOnLongClickListener(view -> {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, book.getImage());
            shareIntent.setType("image/*");
            context.startActivity(Intent.createChooser(shareIntent, "好書分享"));
            return true;
        });
    }

    class BookListViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle, bookPrice, bookIsbn;

        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookPrice = itemView.findViewById(R.id.book_price);
            bookIsbn = itemView.findViewById(R.id.book_isbn);
        }
    }

}
