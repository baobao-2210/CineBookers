package com.example.bcck;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
public class Details extends AppCompatActivity {


        private static final String TAG = "Details";
        private TextView titleTextView;
        private TextView idTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Thiết lập layout cho Activity này
            setContentView(R.layout.activity_details);

            // 1. Ánh xạ các View từ layout activity_details.xml
            titleTextView = findViewById(R.id.detail_document_title);
            idTextView = findViewById(R.id.detail_document_id);

            // 2. Nhận Intent đã khởi động Activity này
            // Bundle là một tập hợp các dữ liệu được truyền qua Intent
            Bundle extras = getIntent().getExtras();

            // 3. Kiểm tra xem có dữ liệu nào được truyền kèm không
            if (extras != null) {

                // 4. Lấy dữ liệu theo KEY đã đặt trong MainActivity (hoặc Activity nguồn)

                // Lấy tiêu đề tài liệu (String)
                String documentTitle = extras.getString("DOCUMENT_TITLE");

                // Lấy ID tài liệu (Giả sử là int, nếu là String thì dùng getString)
                int documentId = extras.getInt("DOCUMENT_ID", -1); // -1 là giá trị mặc định nếu không tìm thấy key

                // 5. Hiển thị dữ liệu lên giao diện
                if (documentTitle != null) {
                    titleTextView.setText(documentTitle);
                    Log.d(TAG, "Document Title: " + documentTitle);
                } else {
                    titleTextView.setText("Không có Tiêu đề");
                }

                if (documentId != -1) {
                    idTextView.setText("ID: " + documentId);
                    Log.d(TAG, "Document ID: " + documentId);
                } else {
                    idTextView.setText("Không có ID");
                }
            } else {
                Log.w(TAG, "Không có Bundle dữ liệu nào được truyền.");
                titleTextView.setText("Lỗi: Không nhận được dữ liệu.");
            }
        }
    }