package com.example.app_retrofit.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_retrofit.R;
import com.example.app_retrofit.model.Post;

import java.util.List;

// 適配器 Adapter
// 雲端Json資料的每一筆紀錄要如何配置到指定的 UI 上面
public class PostAdapter extends BaseAdapter {
    private List<Post> postList;
    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList == null ? 0 : postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList == null ? null : postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return postList == null ? 0 : postList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // Layout 配置
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            // 將 ui 結構全部放到 holder 物件中
            holder.tvId = convertView.findViewById(R.id.tv_id);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvAuthor = convertView.findViewById(R.id.tv_author);
            // 放到 convertView 中保存
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 將資料逐筆配置到 UI 上
        Post post = postList.get(i);
        holder.tvId.setText(post.id + "");
        holder.tvTitle.setText(post.title);
        holder.tvAuthor.setText(post.author);
        return convertView;
    }

    // 優化 ListView 避免重新加載, 提高呈現效能
    static class ViewHolder {
        TextView tvId;
        TextView tvTitle;
        TextView tvAuthor;
    }
}
