package com.example.roomdatabasepracticewithallcrudeoperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText t1,t2,t3;
Button b1,fetch;

TextView listdatain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        b1=findViewById(R.id.Insert);
        fetch=findViewById(R.id.fetch);
        //listdatain=findViewById(R.id.listdatain);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



if( t3.getText().toString().trim().isEmpty()) {
    Toast.makeText(MainActivity.this, " Plz Enetr the uid ", Toast.LENGTH_SHORT).show();
}else if(t1.getText().toString().trim().isEmpty()){
    Toast.makeText(MainActivity.this, " Plz Enetr the Name ", Toast.LENGTH_SHORT).show();
}else if(t2.getText().toString().trim().isEmpty()){
    Toast.makeText(MainActivity.this, " Plz Enetr the LastName ", Toast.LENGTH_SHORT).show();
}
 else{

    AppDatabase db= Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "room_db").allowMainThreadQueries().build();
    UserDao userDao=db.userDao();
//    User user=new User();
//    user.setFirstName(t1.getText().toString());
//    userDao.inserrecord(user);
    Boolean check =userDao.is_exist(Integer.parseInt(t3.getText().toString()));
    if (check == false) {
        userDao.inserrecord(new User(Integer.parseInt(t3.getText().toString()), t1.getText().toString(), t2.getText().toString()));
//        FetchDataToRecyclerview detailsFragment = new FetchDataToRecyclerview();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.replace,detailsFragment);
//        fragmentTransaction.commit();

        t1.setText("");
        t2.setText("");
        t3.setText("");
        Toast.makeText(MainActivity.this, "Insert Record Successfullly", Toast.LENGTH_SHORT).show();
startActivity(new Intent(MainActivity.this,checkfetchingdata.class));


//        List<User>users=userDao.getAll();
//        String str="";
//        for(User user :users)
//            str=str+"\t  "+user.getUid()+"  "+user.getFirstName()+" "+user.getLastName()+"\n\n";
//        listdatain.setText(str);



    } else {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        Toast.makeText(MainActivity.this, " Record is Already Exsist ", Toast.LENGTH_SHORT).show();
    }
}


           //  new bgthread().start();
            }
        });
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,checkfetchingdata.class));
//                FetchDataToRecyclerview detailsFragment = new FetchDataToRecyclerview();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.replace,detailsFragment);
//                fragmentTransaction.commit();
//                AppDatabase db= Room.databaseBuilder(getApplicationContext(),
//                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
//                UserDao userDao=db.userDao();
//                List<User>users=userDao.getAll();
//                 String str="";
//                 for(User user :users)
//                     str=str+"\t  "+user.getUid()+"  "+user.getFirstName()+" "+user.getLastName()+"\n\n";
//                 listdatain.setText(str);
            }
        });
    }
//    class bgthread extends Thread
//    {
//        public void run(){
//            super.run();
//            AppDatabase db= Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "room_db").build();
//            UserDao userDao=db.userDao();
//            userDao.inserrecord(new User(1,t1.getText().toString(),t2.getText().toString()));
//            t1.setText("");
//            t2.setText("");
//
//        }
//    }
}