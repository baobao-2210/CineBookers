package com.example.myprofile;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    LinearLayout menuSection, footerBtns;
    ImageView avatar;

    // Chọn ảnh từ thư viện
    ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        avatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = findViewById(R.id.imageView);
        menuSection = findViewById(R.id.menuSection);
        footerBtns = findViewById(R.id.footerBtns);

        setupMenuClicks();
        setupFooterClicks();
        setupAvatarClick();
    }

    private void setupMenuClicks() {

        menuSection.getChildAt(0).setOnClickListener(v ->
                Toast.makeText(this, "Tài liệu", Toast.LENGTH_SHORT).show());

        menuSection.getChildAt(1).setOnClickListener(v ->
                Toast.makeText(this, "Bài đăng", Toast.LENGTH_SHORT).show());

        menuSection.getChildAt(2).setOnClickListener(v ->
                Toast.makeText(this, "Thư viện của tôi", Toast.LENGTH_SHORT).show());

        menuSection.getChildAt(3).setOnClickListener(v ->
                Toast.makeText(this, "Lịch sử tải xuống", Toast.LENGTH_SHORT).show());

        menuSection.getChildAt(4).setOnClickListener(v ->
                Toast.makeText(this, "Tài liệu của tôi", Toast.LENGTH_SHORT).show());
    }


    private void setupFooterClicks() {

        footerBtns.getChildAt(0).setOnClickListener(v ->
                Toast.makeText(this, "Cập nhật thông tin tài khoản", Toast.LENGTH_SHORT).show());

        footerBtns.getChildAt(1).setOnClickListener(v ->
                Toast.makeText(this, "Bảo mật & Quyền riêng tư", Toast.LENGTH_SHORT).show());
    }


    private void setupAvatarClick() {
        avatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            pickImageLauncher.launch(intent);
        });
    }
}

