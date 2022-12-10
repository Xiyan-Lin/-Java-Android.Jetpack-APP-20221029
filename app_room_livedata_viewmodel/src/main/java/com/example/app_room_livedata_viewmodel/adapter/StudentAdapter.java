package com.example.app_room_livedata_viewmodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app_room_livedata_viewmodel.R;
import com.example.app_room_livedata_viewmodel.entity.Student;

import java.util.List;


public class StudentAdapter extends BaseAdapter {
    private List<Student> students;
    private LayoutInflater layoutInflater;

    public StudentAdapter(Context context, List<Student> students) {
        this.students = students;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    class ViewHolder {
        TextView tvId, tvName, tvAge;
    }

    @Override
    public int getCount() {
        return (students == null) ? 0 : students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvId = convertView.findViewById(R.id.tvId);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvAge = convertView.findViewById(R.id.tvAge);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = students.get(position);
        viewHolder.tvId.setText(student.id + "");
        viewHolder.tvName.setText(student.name);
        viewHolder.tvAge.setText(student.age + "");
        return convertView;
    }
}
