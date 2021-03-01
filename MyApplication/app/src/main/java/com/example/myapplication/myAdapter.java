package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

 public class myAdapter extends CursorAdapter {
    public myAdapter(Context context, Cursor cursor)
    {
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_items, parent,
                false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView txtID = (TextView) view.findViewById(R.id.id);
        TextView txtname = (TextView) view.findViewById(R.id.name);
        TextView txtfamilyName = (TextView) view.findViewById(R.id.familyName);

        // Extract properties from cursor
        String id = cursor.getString(1);
        String name = cursor.getString(2);
        String familyName = cursor.getString(3);

         // Populate fields with extracted properties
        txtID.setText(id);
        txtname.setText(name);
        txtfamilyName.setText(familyName);

    }

}
