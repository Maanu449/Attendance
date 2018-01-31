package cfd.ram.attendance;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InstitutePage extends AppCompatActivity {

    private List<Prof> profList = new ArrayList<>();
    private RecyclerView mProfRecView;
    private ProfInstiPageAdapter mProfAdapter;
    private DatabaseReference mDatabaseRef1,mDatabaseRef2;
    private FloatingActionButton addProfDetailsFab;
    String profName, profEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_page);

        addProfDetailsFab = findViewById(R.id.fab_to_add_prof_details);
        mDatabaseRef1 = FirebaseDatabase.getInstance().getReference().child("Institute").child("Institute").child("Institute Name");

        mProfRecView=findViewById(R.id.recycler_view);

        mProfAdapter = new ProfInstiPageAdapter(profList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mProfRecView.setLayoutManager(mLayoutManager);
        mProfRecView.setItemAnimator(new DefaultItemAnimator());
        mProfRecView.setAdapter(mProfAdapter);

        mDatabaseRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AddProfDetails obj1 = new AddProfDetails();
                String pName = obj1.getName();
                String pEmail = obj1.getEmail();
                Toast.makeText(getApplicationContext(),pName + "  " + pEmail,Toast.LENGTH_SHORT).show();
                Prof obj = new Prof(pName, pEmail);
                profList.add(obj);
                mProfAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
        mDatabaseRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profName = (String) dataSnapshot.child("abcd").child("Name").getValue();
                profEmail = (String) dataSnapshot.child("abcd").child("Email").getValue();

                Prof profff = new Prof(profName,profEmail);
                profList.add(profff);
                mProfAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
*/
        Prof obj = new Prof("aarah","gmailid");
        profList.add(obj);
        mProfAdapter.notifyDataSetChanged();

        Prof newa = new Prof("ritwik", "facebook");
        profList.add(newa);
        mProfAdapter.notifyDataSetChanged();

        addProfDetailsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstitutePage.this, AddProfDetails.class);
                startActivity(intent);
            }
        });

    }

}
