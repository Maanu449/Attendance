package cfd.ram.attendance;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class InstitutePage extends AppCompatActivity {

    private List<Prof> profList = new ArrayList<>();
    private RecyclerView mProfRecView;
    private ProfInstiPageAdapter mProfAdapter;
    private DatabaseReference mDatabaseRef1,mDatabaseRef2;
    private FloatingActionButton addProfDetailsFab;

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
/*
        mDatabaseRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String profName = dataSnapshot.child("adni").child("Namatch_parentme").getValue().toString();
                String profEmail = dataSnapshot.child("adni").child("Email id").getValue().toString();
                Log.e("Name",profName );
                Log.e("Email", profEmail);
                Prof profff = new Prof(profName,profEmail);
                profList.add(profff);

               /*
                String profName1 = dataSnapshot.child("prof2").child("Name").getValue().toString();
               String profEmail1 = dataSnapshot.child("prof2").child("Email id").getValue().toString();
                profff = new Prof(profName1, profEmail1);
                profList.add(profff);
                mProfAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
