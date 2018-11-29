package com.example.maks.trenerovka;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    public EditText SendMsg;
    public EditText Id;
    public TextView GetMsg;
    public String Gm,Sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SendMsg = (EditText) findViewById(R.id.editText);
        GetMsg = (TextView) findViewById(R.id.textView2);

        SendMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

               // Sm=GetMsg.getText().toString();
                Sm= s.toString();
                myRef.child("send").setValue(Sm);
                GetmessagefromDB();
             //   GetMsg.setText(Gm);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
              }
        });
    }

    public void GetmessagefromDB (){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Gm = dataSnapshot.child("send").getValue().toString();
                GetMsg.setText(Gm);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
               // Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.addValueEventListener(postListener);
    }
    public void click(View view) {

       // Sm=SendMsg.getText().toString();
        //myRef.child("send").setValue(Sm);
     //   GetmessagefromDB();
        //GetMsg.setText(Gm);
    }


}
