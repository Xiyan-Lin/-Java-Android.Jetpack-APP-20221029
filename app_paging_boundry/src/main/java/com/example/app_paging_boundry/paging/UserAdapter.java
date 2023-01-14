package com.example.app_paging_boundry.paging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app_paging_boundry.R;
import com.example.app_paging_boundry.model.User;
import com.squareup.picasso.Picasso;

public class UserAdapter extends PagedListAdapter<User, UserAdapter.UserViewHolder> {
    private Context context;
    public UserAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    // 比對處理
    private static DiffUtil.ItemCallback<User> diffCallback =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }
            };

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getItem(position);
        if(user == null) return;;
        holder.tvName.setText(user.getId() + ": " + user.getLoginName());
        Picasso.get().load(user.getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(holder.ivAvatar);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvName;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
