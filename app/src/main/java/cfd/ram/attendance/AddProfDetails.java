package cfd.ram.attendance;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddProfDetails extends AppCompatActivity {

    private final int NEW_POST_GALLERY_REQ = 2;
    private Uri uri;
    private ImageView profImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prof_details);
        profImg = findViewById(R.id.add_prof_img);
        profImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, NEW_POST_GALLERY_REQ);
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
