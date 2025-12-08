package com.example.bcck.poster;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bcck.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class UploadDocumentActivity extends AppCompatActivity {

    // --- Khai báo View Components ---
    private ImageView btnBack;
    private MaterialCardView uploadArea;
    private TextInputEditText etDocumentName, etSubject, etTeacher, etDescription;
    private Spinner spinnerCourse, spinnerYear;
    private Button btnUpload;

    // Mã yêu cầu (Request code) cho việc chọn tệp
    private static final int PICK_FILE_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file); // Thay bằng tên file XML của bạn

        // 1. Ánh xạ các View
        mapViews();

        // 2. Thiết lập Dropdown (Spinner)
        setupSpinners();

        // 3. Thiết lập Thao tác Click
        setupClickListeners();
    }

    private void mapViews() {
        btnBack = findViewById(R.id.btnBack);
        uploadArea = findViewById(R.id.uploadArea);
        etDocumentName = findViewById(R.id.etDocumentName);
        etSubject = findViewById(R.id.etSubject);
        etTeacher = findViewById(R.id.etTeacher);
        etDescription = findViewById(R.id.etDescription);
        spinnerCourse = findViewById(R.id.spinnerCourse);
        spinnerYear = findViewById(R.id.spinnerYear);
        btnUpload = findViewById(R.id.btnUpload);
    }

    // --- Thiết lập dữ liệu cho Spinner ---
    private void setupSpinners() {
        // Dữ liệu mẫu cho Khoa
        String[] courses = new String[]{"Chọn Khoa", "CNTT", "Kỹ thuật Xây dựng", "Kinh tế", "Ngoại ngữ"};
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        spinnerCourse.setAdapter(courseAdapter);

        // Dữ liệu mẫu cho Năm học
        String[] years = new String[]{"Chọn Năm học", "2023-2024", "2022-2023", "2021-2022"};
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spinnerYear.setAdapter(yearAdapter);
    }

    // --- Thiết lập Listener cho các nút ---
    private void setupClickListeners() {

        // 3.1. Nút Back: Quay lại
        btnBack.setOnClickListener(v -> finish());

        // 3.2. Khu vực Chọn Tệp
        uploadArea.setOnClickListener(v -> openFilePicker());

        // 3.3. Nút Tải lên
        btnUpload.setOnClickListener(v -> {
            if (validateForm()) {
                // Nếu form hợp lệ, tiến hành lưu trữ
                submitDocument();
            }
        });
    }

    // --- 4. Thao tác Mở trình chọn Tệp ---
    private void openFilePicker() {
        // Sử dụng Intent để mở trình chọn tệp của hệ thống
        android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_GET_CONTENT);

        // Đặt kiểu MIME type để lọc các loại tệp (ví dụ: tất cả tệp)
        // Bạn có thể chỉnh sửa để chỉ cho phép PDF/DOC/Hình ảnh:
        // intent.setType("application/pdf|image/*|application/msword");
        intent.setType("*/*");
        intent.addCategory(android.content.Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    android.content.Intent.createChooser(intent, "Chọn tệp tài liệu"),
                    PICK_FILE_REQUEST_CODE
            );
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Không có ứng dụng nào có thể mở tệp.", Toast.LENGTH_SHORT).show();
        }
    }

    // --- 5. Kiểm tra kết quả sau khi chọn tệp ---
    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            android.net.Uri fileUri = data.getData();
            // TODO: Hiển thị tên tệp đã chọn lên giao diện (ví dụ: thay thế text "Nhấn để chọn tệp")
            Toast.makeText(this, "Đã chọn tệp: " + fileUri.getLastPathSegment(), Toast.LENGTH_LONG).show();
            // Tiếp tục lưu fileUri này để tải lên Firebase Storage hoặc Backend.
        }
    }

    // --- 6. Kiểm tra Form Hợp lệ (Validation) ---
    private boolean validateForm() {
        if (etDocumentName.getText().toString().trim().isEmpty() ||
                etSubject.getText().toString().trim().isEmpty() ||
                etTeacher.getText().toString().trim().isEmpty() ||
                spinnerCourse.getSelectedItemPosition() == 0 || // Kiểm tra chưa chọn Khoa
                spinnerYear.getSelectedItemPosition() == 0) { // Kiểm tra chưa chọn Năm học

            Toast.makeText(this, "Vui lòng điền đủ các trường bắt buộc (*).", Toast.LENGTH_SHORT).show();
            return false;
        }
        // TODO: Thêm logic kiểm tra xem người dùng đã chọn tệp chưa.
        return true;
    }

    // --- 7. Thao tác Tải lên tài liệu ---
    private void submitDocument() {
        // Thu thập tất cả dữ liệu
        String name = etDocumentName.getText().toString().trim();
        String subject = etSubject.getText().toString().trim();
        String teacher = etTeacher.getText().toString().trim();
        String course = spinnerCourse.getSelectedItem().toString();
        String year = spinnerYear.getSelectedItem().toString();
        String description = etDescription.getText().toString().trim();

        // Ở đây, bạn sẽ gọi một phương thức để xử lý việc tải lên:
        // 1. Tải tệp đã chọn (fileUri) lên Cloud Storage (ví dụ: Firebase Storage).
        // 2. Sau khi tệp được tải lên thành công, lấy URL của tệp.
        // 3. Lưu thông tin form (name, subject, teacher, course, year, url_tệp,...) vào Database (ví dụ: Firestore/MySQL).

        Toast.makeText(this, "Đang tiến hành tải lên: " + name, Toast.LENGTH_LONG).show();
        // Sau khi quá trình tải lên hoàn tất thành công: finish();
    }
}
