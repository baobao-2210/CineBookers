package com.example.trangcanhan;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    // Khai báo view
    EditText etSearch;

    ImageView ivUploadProfile;
    ImageView ivAuthorProfile1, ivAuthorProfile2;

    LinearLayout llInfo1, llInfo2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view theo đúng ID layout bạn gửi
        etSearch = findViewById(R.id.et_search);

        ivUploadProfile = findViewById(R.id.iv_upload_profile);

        ivAuthorProfile1 = findViewById(R.id.iv_author_profile_1);
        ivAuthorProfile2 = findViewById(R.id.iv_author_profile_2);

        llInfo1 = findViewById(R.id.ll_info_1);
        llInfo2 = findViewById(R.id.ll_info_2);


        // ===============================
        //        XỬ LÝ CÁC SỰ KIỆN
        // ===============================

        // Ô tìm kiếm
        etSearch.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Bạn đang tìm kiếm tài liệu...", Toast.LENGTH_SHORT).show();
        });

        // Click upload tài liệu
        ivUploadProfile.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Mở màn hình đăng tài liệu", Toast.LENGTH_SHORT).show();
            // startActivity(new Intent(MainActivity.this, UploadActivity.class));
        });

        // Click tài liệu
        llInfo1.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Mở tài liệu 1", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(MainActivity.this, DocumentDetailActivity.class);
            // intent.putExtra("doc_id", 1);
            // startActivity(intent);
        });

        llInfo2.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Mở tài liệu 2", Toast.LENGTH_SHORT).show();
        });
        ivAuthorProfile1.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Xem profile tác giả 1", Toast.LENGTH_SHORT).show();
        });

        ivAuthorProfile2.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Xem profile tác giả 2", Toast.LENGTH_SHORT).show();
        });
    }
}
