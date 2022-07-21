package com.example.roomdatabasepracticewithallcrudeoperation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void inserrecord(User users);

    @Query("SELECT EXISTS (SELECT * FROM User WHERE uid =:userId)")
    Boolean is_exist(int userId);

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("DELETE   FROM User WHERE uid =:id")
    void delete(int id);
}
