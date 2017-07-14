package com.example.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";
    BookManager bookManager;

    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = BookManager.Stub.asInterface(service);
            if(bookManager == null){
                Log.i(TAG,"connect failed");
            }else{
                try {
                    Log.i(TAG,"conne success and the book is " + bookManager.getBooks().get(0).getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.example.aidl");
        intent.setPackage("com.example.aidl");
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }


    public void addBook(View view){
        Toast.makeText(this,"hahah",Toast.LENGTH_LONG).show();
        Book b = new Book();
        b.setName("上下五千年");
        b.setPrice(33);

        try {
            Book reply = bookManager.addBook(b);
            Log.i(TAG,"Reply is : " +  b.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookOut(View view){
        Toast.makeText(this,"hahah",Toast.LENGTH_LONG).show();
        Book b = new Book();
        b.setName("上下五千年out");
        b.setPrice(33);

        try {
            Book reply = bookManager.addBookOut(b);
            Log.i(TAG,"Reply is : " +  b);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBookInOut(View view){
        Toast.makeText(this,"hahah",Toast.LENGTH_LONG).show();
        Book b = new Book();
        b.setName("上下五千年inout");
        b.setPrice(33);

        try {
            Book reply = bookManager.addBookInOut(b);
            Log.i(TAG,"Reply is : " +  b);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
