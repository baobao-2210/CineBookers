package com.example.bcck.poster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.bcck.Chat.ChatActivity;
import com.example.bcck.Chat.ChatDetailActivity;
import com.example.bcck.Chat.ChatFragment;
import com.example.bcck.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DocumentDetailActivity extends AppCompatActivity {

    public static final String EXTRA_DOCUMENT = "DOCUMENT_DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_detail);

        Document document = (Document) getIntent().getSerializableExtra(EXTRA_DOCUMENT);

        if (document != null) {

            // 1. THAM CHIẾU TẤT CẢ CÁC VIEWS (KHAI BÁO MỘT LẦN)
            TextView tvAuthorName = findViewById(R.id.tvAuthorName);
            TextView tvDocumentTitle = findViewById(R.id.tvDocumentTitle);
            TextView tvFileType = findViewById(R.id.tvFileType);
            TextView tvSubject = findViewById(R.id.tvSubject);
            TextView tvTeacher = findViewById(R.id.tvTeacher);
            TextView tvCourse = findViewById(R.id.tvCourse);
            TextView tvYear = findViewById(R.id.tvYear);
            TextView tvUploader = findViewById(R.id.tvUploader);
            TextView tvUploadDate = findViewById(R.id.tvUploadDate);
            TextView tvDownloads = findViewById(R.id.tvDownloads);
            TextView tvRating = findViewById(R.id.tvRating);

            // Tham chiếu các nút thao tác
            Button btnDownload = findViewById(R.id.btnDownload);
            Button btnShare = findViewById(R.id.btnShare);
            Button btnPreview = findViewById(R.id.btnPreview);
            Button btnMessage = findViewById(R.id.btnMessage);
            // KHAI BÁO btnClose LẦN DUY NHẤT Ở ĐÂY
            ImageView btnClose = findViewById(R.id.btnClose);

            // 2. GÁN DỮ LIỆU HIỂN THỊ
            tvAuthorName.setText(document.getAuthorName());
            tvDocumentTitle.setText(document.getTitle());
            tvFileType.setText(document.getDocType());
            tvSubject.setText(document.getSubject());
            tvTeacher.setText(document.getTeacher());
            tvCourse.setText(document.getMajor());
            tvYear.setText("Năm học: " + document.getYear());
            tvUploader.setText("Đăng bởi: " + document.getUploaderName());

            String dateString = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .format(new Date(document.getUploadTimestamp()));
            tvUploadDate.setText("Ngày đăng: " + dateString);

            tvDownloads.setText(String.valueOf(document.getDownloads()));
            tvRating.setText(String.format("%.1f", document.getRating()));


            // 3. GÁN ONCLICK LISTENER (Các nút thao tác)

            // Nút Tải xuống
            btnDownload.setOnClickListener(v -> {
                handleDownload(document);
            });

            // Nút Chia sẻ
            btnShare.setOnClickListener(v -> {
                handleShare(document);
            });

            // Nút Xem trước
            btnPreview.setOnClickListener(v -> {
                handlePreview(document);
            });

            // Nút Nhắn tin
            btnMessage.setOnClickListener(v -> {
                handleMessage(document);
            });

            // Nút Thoát/Đóng (btnClose)
            btnClose.setOnClickListener(v -> {
                finish(); // <-- Thao tác đóng Activity
            });

        } else {
            finish();
        }
    }

    // CÁC HÀM XỬ LÝ THAO TÁC (Giữ nguyên)

    private void handleDownload(Document document) {
        Toast.makeText(this, "Đang tải xuống tài liệu: " + document.getTitle(), Toast.LENGTH_LONG).show();
        // TODO: Viết code kiểm tra document.getFileUrl() và sử dụng DownloadManager
    }

    private void handleShare(Document document) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Chia sẻ tài liệu: " + document.getTitle());
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hãy xem tài liệu này: " + document.getTitle() +
                "\nLink tải (nếu có): https://docs.google.com/document/create?hl=vi");
        startActivity(Intent.createChooser(sendIntent, "Chia sẻ tài liệu qua..."));
    }

    private void handlePreview(Document document) {
        Toast.makeText(this, "Mở trình xem trước cho: " + document.getTitle(), Toast.LENGTH_SHORT).show();
        // TODO: Khởi chạy Activity/Fragment chứa PDF/PPT Viewer
    }

    private void handleMessage(Document document) {
        // Lấy thông tin cần thiết từ Document
        String receiverName = document.getUploaderName();
        // Giả sử có hàm getUploaderId() trong lớp Document
        String receiverId = document.getUploaderId();
        // Kiểm tra dữ liệu và khởi chạy Activity
        if (receiverName != null && !receiverName.isEmpty() && receiverId != null && !receiverId.isEmpty()) {

            // 1. Tạo Intent
            // CHÚ Ý: Đảm bảo đã import com.example.bcck.chat.ChatActivity;
            Intent intent = new Intent(this, ChatDetailActivity.class);

            // 2. Truyền dữ liệu người nhận.
            // SỬ DỤNG CÁC KEY MÀ ChatActivity CỦA BẠN DÙNG ĐỂ NHẬN DỮ LIỆU.
            intent.putExtra("RECEIVER_NAME", receiverName);
            intent.putExtra("RECEIVER_ID", receiverId);

            // 3. Khởi chạy
            startActivity(intent);

        } else {
            Toast.makeText(this, "Không thể xác định người nhận để chat.", Toast.LENGTH_SHORT).show();
        }
    }
}