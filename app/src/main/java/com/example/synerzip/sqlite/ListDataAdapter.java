package com.example.synerzip.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

//CUSTOM ADAPTER
public class ListDataAdapter extends ArrayAdapter
{
    List list;
//constructor
    public ListDataAdapter(Context context, int resource, List<DataProvider> list)
    {
        //WHY CONTEXT???
        // Loading resources, launching a new Activity, obtaining a system service, getting internal file paths, and creating views and more
        //It's like access of android activity to the app's resource
        super(context, resource);
        this.list = list;
    }
    static class LayoutHandler
    {
        TextView textRollno, textName,textMarks;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row=convertView;
        LayoutHandler layoutHandler;

        if(row==null)
        {
            //Basically it is needed to create (or fill) View based on XML file in runtime.
            // A LayoutInflater reads an XML in which we describe how we want a UI layout to be. It then creates actual Viewobjects for UI from that XML.
           //  LayoutInflater is used to get and split a layout defined via XML in your code.
            //-this class is used to instantiate layout XML file into its corresponding View objects.
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);

            //a Handler allows you to send and process
            layoutHandler=new LayoutHandler();

            layoutHandler.textRollno=(TextView)row.findViewById(R.id.row_rollno);
            layoutHandler.textName=(TextView)row.findViewById(R.id.row_name);
            layoutHandler.textMarks=(TextView)row.findViewById(R.id.row_marks);

            row.setTag(layoutHandler);//tag is a mechanism to make your views remember something

        }
        else
        {
            layoutHandler=(LayoutHandler)row.getTag();
        }
        //  DataProvider dataProvider=(DataProvider)this.getItem(position);
        DataProvider dataProvider = (DataProvider) list.get(position);
        layoutHandler.textRollno.setText(String.valueOf(dataProvider.getRollno()));
        layoutHandler.textName.setText(dataProvider.getName());
        layoutHandler.textMarks.setText(dataProvider.getMarks());

        return row;
    }
}
