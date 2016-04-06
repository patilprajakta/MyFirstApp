package com.example.synerzip.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends Activity
{

UserDBHelper dbHelper;
    ArrayList<DataProvider> listStudents;
String name,rollno, marks;
TextView textname,textrollno,textmarks;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        listStudents=new ArrayList<>() ;
        dbHelper=new UserDBHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListDataAdapter adapter = new ListDataAdapter(this,R.layout.row_layout,listStudents);

       listView = (ListView) findViewById(R.id.listView);

        textname=(TextView)findViewById(R.id.textRollno);
        textrollno= (TextView) findViewById(R.id.textName);
        textmarks=(TextView)findViewById(R.id.textMarks);

Cursor cursor=dbHelper.viewInfo();
//get info by usin 'cursor' class
        if (cursor.moveToFirst())
        {
            do {
                DataProvider data;
                data=new DataProvider(name,rollno,marks);

                data.setRollno(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setMarks(cursor.getString(2));
                // Adding items to list
                listStudents.add(data);
            } while (cursor.moveToNext());
        }

        listView.setAdapter(adapter);

    }


}


