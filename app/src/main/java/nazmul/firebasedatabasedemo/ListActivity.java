package nazmul.firebasedatabasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView dataLV;
    ArrayList<String>info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dataLV= (ListView) findViewById(R.id.dataList);
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("userinfo");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                info=new ArrayList<>();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    User user=postSnapshot.getValue(User.class);
                    info.add(user.getName()+" "+user.getEmail());
                }
                ArrayAdapter<String>adapter=new ArrayAdapter<String>(ListActivity.this,android.R.layout.simple_list_item_1,info);
                dataLV.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }

    public void add(View view) {
        Intent intent=new Intent(ListActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
