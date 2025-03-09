package com.example.testsqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsqlite.MainActivity;
import com.example.testsqlite.R;
import com.example.testsqlite.model.NotesModel;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    //Khai báo biến toàn cục
    private MainActivity context;
    private int layout;
    private List<NotesModel> noteList;

    //tạo constructor
    public NotesAdapter(MainActivity context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    //tạo viewHolder
    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //gọi viewHolder
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.textViewNote = (TextView) convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDelete = (ImageView) convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //lấy giá trị
        NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());


        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.XoaCongViec(notes.getNameNote(),notes.getIdNote());
            }
        });


        // Lấy giá trị
        viewHolder.textViewNote.setText(notes.getNameNote());

        // Bắt sự kiện nút cập nhật
        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Cập nhật " + notes.getNameNote(), Toast.LENGTH_SHORT).show();
                // Gọi Dialog trong MainActivity.java
                context.DialogCapNhatNotes(notes.getNameNote(), notes.getIdNote());
            }
        });



        return convertView;
    }

}
