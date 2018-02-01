package cfd.ram.attendance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.HashMap;

import static com.google.android.gms.internal.zzbfq.NULL;

public class AddProfDetails extends AppCompatActivity {

    private final int NEW_POST_GALLERY_REQ = 2;
    private Uri uri;
    private ImageView profImg;
    private DatabaseReference mDatabaseRef1;
    private Button add_prof;
    static String profName = NULL;
    static String profEmail = NULL;
    TextInputEditText prof_name_mansi;
    TextInputEditText prof_email_mansi;
    TextInputEditText prof_rank_mansi;
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prof_details);
        profImg = findViewById(R.id.add_prof_img);
        add_prof = findViewById(R.id.add_prof);
        prof_name_mansi = findViewById(R.id.prof_name);
        prof_email_mansi = findViewById(R.id.email_id);
        prof_rank_mansi = findViewById(R.id.rank);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
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
                 profName = prof_name_mansi.getText().toString().trim();
                 profEmail = prof_email_mansi.getText().toString().trim();
                String profRank = prof_rank_mansi.getText().toString().trim();

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
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    profImg.setImageBitmap(bitmap);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

        }
    }

}
