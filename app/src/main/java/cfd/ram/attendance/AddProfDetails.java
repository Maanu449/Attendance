package cfd.ram.attendance;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddProfDetails extends AppCompatActivity {

    private final int NEW_POST_GALLERY_REQ = 2;
    private Uri uri;
    private ImageView profImg;
    private DatabaseReference mDatabaseRef1;
    private Button add_prof;
    private TextInputEditText prof_name;
    private TextInputEditText prof_email;
    private TextInputEditText prof_rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prof_details);
        profImg = findViewById(R.id.add_prof_img);
        add_prof = findViewById(R.id.add_prof);
        prof_name = findViewById(R.id.prof_name);
        prof_email = findViewById(R.id.email_id);
        prof_rank = findViewById(R.id.rank);
        profImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, NEW_POST_GALLERY_REQ);
            }
        });

        add_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseRef1 = FirebaseDatabase.getInstance().getReference();
                String profName = prof_name.getText().toString().trim();
                String profEmail = prof_email.getText().toString().trim();
                String profRank = prof_rank.getText().toString().trim();

                HashMap<String, String> mdataMap = new HashMap<String, String>();
                mdataMap.put("Name", profName);
                mdataMap.put("Email", profEmail);
                mdataMap.put("Rank", profRank);
                mDatabaseRef1.child("Institute").child("Institute").child("Institute Name").child(profName).setValue(mdataMap);

                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_POST_GALLERY_REQ && resultCode == RESULT_OK) {
            uri = data.getData();
            profImg.setImageURI(uri);
        }
    }
}
