package com.example.roomdatabasepracticewithallcrudeoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class checkfetchingdata extends AppCompatActivity implements MyFetchAdapter.ItemClickListener {
    RecyclerView recyclerView;
    MyFetchAdapter myFetchAdapter;
    List<User> users;
    String aman,dhiman;
    Button fetch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkfetchingdata);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetch1=findViewById(R.id.fetch1);
        fetchdata();
fetch1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(aman !=null){
            Toast.makeText(checkfetchingdata.this, "Everything is ok", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(checkfetchingdata.this, "please select the checkbox", Toast.LENGTH_SHORT).show();
        }
    }
});

    }

    private void fetchdata() {
        AppDatabase db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao=db.userDao();
        users=userDao.getAll();
        myFetchAdapter=new MyFetchAdapter(checkfetchingdata.this,users,this);
        recyclerView.setAdapter(myFetchAdapter);
    }

    @Override
    public void onClick(int position) {
         aman=users.get(position).getFirstName();
         dhiman=users.get(position).getLastName();
        Toast.makeText(this, aman+"  "+dhiman, Toast.LENGTH_SHORT).show();
        Log.e("nskc",">>>>.>>>>>>>>"+aman+" "+dhiman);
    }
}
