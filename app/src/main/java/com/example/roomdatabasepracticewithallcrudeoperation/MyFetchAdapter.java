package com.example.roomdatabasepracticewithallcrudeoperation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MyFetchAdapter extends RecyclerView.Adapter<MyFetchAdapter.MyviewHolder>{
    List<User> users;
Context context;
    ItemClickListener itemClickListener;
    public MyFetchAdapter(Context context,List<User> users,ItemClickListener itemClickListener) {
        this.users = users;
        this.context=context;
this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
holder.Aman.setText(users.get(position).getFirstName());
holder.dhiman.setText(users.get(position).getLastName());
holder.numeric.setText(String.valueOf(users.get(position).getUid()));

      //  Toast.makeText(context, aman, Toast.LENGTH_SHORT).show();
holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
      if(compoundButton.isChecked()){
//          int position=holder.getAdapterPosition();
          itemClickListener.onClick(position);

//              String aman= users.get(position).getFirstName().toString();
//        Log.e("nskc",">>>>.>>>>>>>>"+aman);
//         Toast.makeText(context,aman, Toast.LENGTH_SHORT).show();
      }else {
          users.remove(position);


          Toast.makeText(context, " plz select the checkbox", Toast.LENGTH_SHORT).show();
      }
    }
});

holder.deletedata.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AppDatabase db= Room.databaseBuilder(holder.Aman.getContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao=db.userDao();
userDao.delete(position);
        // this is delete the record by roomdatabase
       // userDao.delete(users.get(position).getUid());

        //this is delete record by recyclerview list
        users.remove(position);
Toast.makeText(context, "datadelete Succesfully", Toast.LENGTH_SHORT).show();
        //update fresh list to list
        notifyDataSetChanged();
    }
});
    }
    public interface ItemClickListener {
        void onClick(int position);
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    class  MyviewHolder extends RecyclerView.ViewHolder {
        TextView numeric,Aman,dhiman;
        CheckBox checkBox;
        ImageView deletedata;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            numeric=itemView.findViewById(R.id.numeric);
            Aman=itemView.findViewById(R.id.Aman);
            dhiman=itemView.findViewById(R.id.dhiman);
            checkBox=itemView.findViewById(R.id.checkbox);
            deletedata=itemView.findViewById(R.id.delete);

        }
    }
}
