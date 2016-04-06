package com.example.synerzip.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    Button btnAddCon,btnViewCon,btnUpdateCon,btnDeleteCon;
    TextView tvOut;
    EditText rollno,marks,name;
     public UserDBHelper dbhelper;
    // TextView textRollno, textName, textMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper=new UserDBHelper(this);

        rollno=(EditText)findViewById((R.id.editRollno));
        name=(EditText)findViewById((R.id.editName));
        marks=(EditText)findViewById((R.id.editMarks));

        //textRollno=(TextView)findViewById(R.id.textRollno);
       // textName=(TextView)findViewById(R.id.textName);
       // textMarks=(TextView)findViewById(R.id.textMarks);

        tvOut = (TextView) findViewById(R.id.tvOut);
        btnAddCon=(Button)findViewById(R.id.btnAdd);
        btnAddCon.setOnClickListener(this);

        btnViewCon=(Button)findViewById(R.id.btnView);
        btnViewCon.setOnClickListener(this);

        btnUpdateCon=(Button)findViewById(R.id.btnUpdate);
        btnUpdateCon.setOnClickListener(this);

        btnDeleteCon=(Button)findViewById(R.id.btnDelete);
        btnDeleteCon.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btnAdd:
                //optional
               //dbhelper.open();
                tvOut.setText("ADD button was clicked");
                dbhelper.addInfo(rollno.getText().toString(),name.getText().toString(),marks.getText().toString());
                //dbhelper.close();
                break;

            case R.id.btnView:
                dbhelper.viewInfo();

                Intent intent=new Intent(this,Main2Activity.class);
                startActivity(intent);

                tvOut.setText("VIEW button was clicked");
                break;

            case R.id.btnDelete:
                dbhelper.deleteInfo(rollno.getText().toString());
                tvOut.setText("DELETE button was clicked");
                break;

            case R.id.btnUpdate:

                dbhelper.updateInfo(rollno.getText().toString(),name.getText().toString(),marks.getText().toString());
                tvOut.setText("UPDATE button was clicked");

            default:
                Toast.makeText(getBaseContext(),"Wrong Choice...",Toast.LENGTH_LONG).show();
        }

    }
}










































/*//if class is "abstract" then in manifest file ERROR
public class MainActivity extends Activity
{
    String msg = "Android : ";

    //initializa views
    TextView textRollno;
    TextView textName;
    TextView textMarks;

    EditText editRollno;
    EditText editName;
    EditText editMarks;
    Button btnAdd, btnUpdate, btnDelete, btnView;

    UserDBHelper mydb;
ListView listview;
    //onCreate() method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new UserDBHelper(this);

ArrayList<DataProvider> array_list=new ArrayList<DataProvider>();

        ListDataAdapter adapter=new ListDataAdapter();
        // Attach the adapter to a ListView

        ListView listView = (ListView) findViewById(R.id.list_view);

        listView.setAdapter(adapter);




//initializing controls
        ListView listview=  ( ListView )  findViewById ( R . id . list_view )  ;




        textRollno = (TextView) findViewById(R.id.textRollno);
        textName = (TextView) findViewById(R.id.textName);
        textMarks = (TextView) findViewById(R.id.textMarks);

//here editRollNo should contain in ".xml's editText else cast error occurs
        editRollno = (EditText) findViewById(R.id.editRollno);
        editName = (EditText) findViewById(R.id.editName);
        editMarks = (EditText) findViewById(R.id.editMarks);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnView = (Button) findViewById(R.id.btnView);





        //VIEW DATA
        //a query return cursor object



    }//onCreate() end


    //****************************************************************

    //FUNCTIONS

    //*****************************************************************
//when ADD is clicked
    public void click_add(View view)

    {
        if (editRollno.getText().length() == 0 || editName.getText().length() == 0 || editMarks.getText().length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Please enter values", Toast.LENGTH_LONG).show();
             return;
        }

        Log.d(msg,"details added");
    }

    //***************************************************************
    //when UPDATE is clicked
    public void click_update(View view)
    {

        if(view==btnUpdate)
        {
            // Checking empty roll number
            if(editRollno.getText().toString().trim().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Please enter rollno", Toast.LENGTH_LONG).show();
                return;
            }


            if(c.moveToFirst())
            {
                // Modifying record if found

                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No Records found", Toast.LENGTH_LONG).show();
            }
            clearText();
        }Log.d(msg,"details added");
    }


    //**********************************************************

    //when DELETE is clicked
    public void click_delete(View view)
    {
        if(view==btnDelete)
        {
            // Checking empty roll number
            if(editRollno.getText().toString().trim().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Please enter rollno", Toast.LENGTH_LONG).show();
                return;
            }
            // Searching roll number

            if(c.moveToFirst())
            {
                // Deleting record if found

                Toast.makeText(getApplicationContext(), "record Deleted", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Invalid Rollno", Toast.LENGTH_LONG).show();
            }
            clearText();
        }Log.d(msg,"details added");
    }

//**********************************************
    //VIEW DATA
    public void click_view(View view)
    {
        Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+editRollno.getText()+"'", null);
        if(c.moveToFirst())
        {
            editName.setText(c.getString(1));
            editMarks.setText(c.getString(2));
        }

        c = db.rawQuery("SELECT * FROM student", null);

        if (c.getCount() == 0)
        {
            //Toast used
            Toast.makeText(getApplicationContext(), "No records found", Toast.LENGTH_LONG).show();
            return;
        }
        //In case the Object value can change, and will be modified by multiple threads,
        // use a StringBuffer because StringBuffer is synchronized.
        StringBuffer buffer = new StringBuffer();

        //as cursor points to zeroth location thus use 'moveToNext()'
        while (c.moveToNext())
        {
            buffer.append("Rollno: " + c.getString(0) + "\n");
            buffer.append("Name: " + c.getString(1) + "\n");
            buffer.append("Marks: " + c.getString(2) + "\n\n");
        }
        Toast.makeText(getApplicationContext(), "Student Details", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);

        Log.d(msg, "details added");
        }


//*****************************************************

    //clear the text box
    public void clearText()
    {
        editRollno.setText("");
        editName.setText("");
        editMarks.setText("");
        editRollno.requestFocus();
    }


}//class end





*/