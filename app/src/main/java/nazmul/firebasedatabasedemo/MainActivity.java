package nazmul.firebasedatabasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText nameET;
    EditText emailET;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET= (EditText) findViewById(R.id.name);
        emailET= (EditText) findViewById(R.id.email);
        database = FirebaseDatabase.getInstance();
    }

    public void save(View view) {
        User user=new User(nameET.getText().toString(),emailET.getText().toString());
        DatabaseReference myRef = database.getReference("userinfo");
        myRef.push().setValue(user);
        Intent intent=new Intent(MainActivity.this,ListActivity.class);
        startActivity(intent);
    }
}
