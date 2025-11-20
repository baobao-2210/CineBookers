package com.example.bcck;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Nhận dữ liệu từ Intent
        String groupName = getIntent().getStringExtra("groupName");
        int memberCount = getIntent().getIntExtra("memberCount", 0);

        // Hiển thị thông tin
        TextView tvTitle = findViewById(R.id.tvChatTitle);
        tvTitle.setText(groupName);

        ImageView btnBack = findViewById(R.id.btnBackChat);
        btnBack.setOnClickListener(v -> finish());

        Toast.makeText(this, "Chat với nhóm: " + groupName + " (" + memberCount + " thành viên)", Toast.LENGTH_SHORT).show();
    }
}