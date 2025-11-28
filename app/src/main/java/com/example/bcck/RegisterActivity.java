package com.example.bcck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatButton btnSinhVien, btnGiangVien;

    // Loại bỏ btnDangNhap, btnDangKy
    // Loại bỏ edtHoTen, spinnerKhoa, tvHoTenLabel, tvKhoaLabel

    private EditText edtGmail, edtMatKhau;
    private MaterialButton btnSubmitDangNhap; // Đổi tên từ btnSubmitDangKy

    private boolean isSinhVien = true;
    // Đặt mặc định luôn là Đăng nhập
    private final boolean isDangKy = false; // Luôn là false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Đảm bảo bạn đang sử dụng layout Đăng nhập đã được chỉnh sửa
        setContentView(R.layout.activity_register);

        initViews();
        // Không cần setupSpinner() vì đã bỏ chức năng Đăng ký
        setupListeners();
        // Không cần updateFormToggle(), sẽ đặt trực tiếp trạng thái Đăng nhập
        setupInitialState(); // Thiết lập trạng thái ban đầu (Đăng nhập và Sinh viên)
        updateRoleToggle(); // load mặc định: Sinh viên
    }

    private void initViews() {
        btnSinhVien = findViewById(R.id.btnSinhVien);
        btnGiangVien = findViewById(R.id.btnGiangVien);

        // Loại bỏ: btnDangNhap = findViewById(R.id.btnDangNhap);
        // Loại bỏ: btnDangKy = findViewById(R.id.btnDangKy);

        // Loại bỏ: edtHoTen = findViewById(R.id.edtHoTen);
        edtGmail = findViewById(R.id.edtGmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        // Loại bỏ: spinnerKhoa = findViewById(R.id.spinnerKhoa);

        // Đã đổi ID trong XML thành btnSubmitDangNhap
        btnSubmitDangNhap = findViewById(R.id.btnSubmitDangNhap);

        // Loại bỏ: tvHoTenLabel = findViewById(R.id.tvHoTenLabel);
        // Loại bỏ: tvKhoaLabel = findViewById(R.id.tvKhoaLabel);

        // Cần ẩn các view Đăng ký nếu chúng vẫn còn trong Layout nhưng chưa bị xóa
        // View vLayoutFormToggle = findViewById(R.id.layoutFormToggle);
        // if (vLayoutFormToggle != null) vLayoutFormToggle.setVisibility(View.GONE);
    }

    private void setupInitialState() {
        // Đảm bảo nút submit hiển thị text Đăng Nhập
        if (btnSubmitDangNhap != null) {
            btnSubmitDangNhap.setText("Đăng Nhập");
        }

        // Nếu layout vẫn còn chứa các view Đăng ký (vì bạn chưa sửa XML)
        // Chúng ta cần ẩn chúng đi. (Nhưng nếu bạn đã sửa XML thì không cần)
        // TextView tvHoTenLabel = findViewById(R.id.tvHoTenLabel);
        // EditText edtHoTen = findViewById(R.id.edtHoTen);
        // TextView tvKhoaLabel = findViewById(R.id.tvKhoaLabel);
        // Spinner spinnerKhoa = findViewById(R.id.spinnerKhoa);

        // if (tvHoTenLabel != null) tvHoTenLabel.setVisibility(View.GONE);
        // if (edtHoTen != null) edtHoTen.setVisibility(View.GONE);
        // if (tvKhoaLabel != null) tvKhoaLabel.setVisibility(View.GONE);
        // if (spinnerKhoa != null) spinnerKhoa.setVisibility(View.GONE);


    }


    // Hàm này không cần thiết vì chỉ còn chức năng Đăng nhập
    /*
    private void setupSpinner() {
        // ... (đã bị xóa)
    }
    */

    private void setupListeners() {
        btnSinhVien.setOnClickListener(v -> {
            isSinhVien = true;
            updateRoleToggle();
        });

        btnGiangVien.setOnClickListener(v -> {
            isSinhVien = false;
            updateRoleToggle();
        });

        // Loại bỏ Listeners cho btnDangNhap và btnDangKy
        /*
        btnDangKy.setOnClickListener(v -> {
            isDangKy = true;
            updateFormToggle();
        });

        btnDangNhap.setOnClickListener(v -> {
            isDangKy = false;
            updateFormToggle();
        });
        */

        btnSubmitDangNhap.setOnClickListener(v -> handleSubmit());
    }

    private void updateRoleToggle() {
        // Giữ nguyên: Chuyển đổi giữa Sinh viên / Giảng viên
        if (isSinhVien) {
            btnSinhVien.setBackgroundResource(R.drawable.bg_toggle_selected);
            btnSinhVien.setTextColor(Color.parseColor("#FFFFFF")); // Dùng #FFFFFF cho màu trắng
            btnGiangVien.setBackgroundColor(Color.TRANSPARENT);
            btnGiangVien.setTextColor(Color.parseColor("#090909")); // Đổi màu xám sang #090909 cho khớp XML
        } else {
            btnGiangVien.setBackgroundResource(R.drawable.bg_toggle_selected);
            btnGiangVien.setTextColor(Color.parseColor("#FFFFFF"));
            btnSinhVien.setBackgroundColor(Color.TRANSPARENT);
            btnSinhVien.setTextColor(Color.parseColor("#090909"));
        }
    }

    // Hàm này đã bị xóa vì không cần Toggle giữa Đăng ký và Đăng nhập
    /*
    private void updateFormToggle() {
        // ... (đã bị xóa)
    }
    */


    private void handleSubmit() {
        String gmail = edtGmail.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();

        // Chỉ xử lý logic Đăng nhập (vì isDangKy luôn là false)
        if (gmail.isEmpty() || matKhau.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: THÊM LOGIC XÁC THỰC TÀI KHOẢN (API/Database) VÀO ĐÂY

        // Nếu xác thực thành công:
        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        goToHome();
    }

    private void goToHome() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // kết thúc RegisterActivity để không bị quay lại
    }
}