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
    private AppCompatButton btnDangNhap, btnDangKy;
    private EditText edtHoTen, edtGmail, edtMatKhau;
    private Spinner spinnerKhoa;
    private MaterialButton btnSubmitDangKy;
    private TextView tvHoTenLabel, tvKhoaLabel;

    private boolean isSinhVien = true;
    private boolean isDangKy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        setupSpinner();
        setupListeners();
        updateFormToggle(); // load mặc định: Đăng ký
        updateRoleToggle(); // load mặc định: Sinh viên
    }

    private void initViews() {
        btnSinhVien = findViewById(R.id.btnSinhVien);
        btnGiangVien = findViewById(R.id.btnGiangVien);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtGmail = findViewById(R.id.edtGmail);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        spinnerKhoa = findViewById(R.id.spinnerKhoa);
        btnSubmitDangKy = findViewById(R.id.btnSubmitDangKy);
        tvHoTenLabel = findViewById(R.id.tvHoTenLabel);
        tvKhoaLabel = findViewById(R.id.tvKhoaLabel);
    }

    private void setupSpinner() {
        String[] khoa = {
                "Chọn khoa",
                "Công nghệ thông tin",
                "Điện - Điện tử",
                "Cơ khí",
                "Hóa học",
                "Kinh tế",
                "Ngoại ngữ",
                "Môi trường"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                khoa
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKhoa.setAdapter(adapter);
    }

    private void setupListeners() {
        btnSinhVien.setOnClickListener(v -> {
            isSinhVien = true;
            updateRoleToggle();
        });

        btnGiangVien.setOnClickListener(v -> {
            isSinhVien = false;
            updateRoleToggle();
        });

        btnDangKy.setOnClickListener(v -> {
            isDangKy = true;
            updateFormToggle();
        });

        btnDangNhap.setOnClickListener(v -> {
            isDangKy = false;
            updateFormToggle();
        });

        btnSubmitDangKy.setOnClickListener(v -> handleSubmit());
    }

    private void updateRoleToggle() {
        if (isSinhVien) {
            btnSinhVien.setBackgroundResource(R.drawable.bg_toggle_selected);
            btnSinhVien.setTextColor(Color.WHITE);
            btnGiangVien.setBackgroundColor(Color.TRANSPARENT);
            btnGiangVien.setTextColor(Color.parseColor("#666666"));
        } else {
            btnGiangVien.setBackgroundResource(R.drawable.bg_toggle_selected);
            btnGiangVien.setTextColor(Color.WHITE);
            btnSinhVien.setBackgroundColor(Color.TRANSPARENT);
            btnSinhVien.setTextColor(Color.parseColor("#666666"));
        }
    }

    private void updateFormToggle() {
        if (isDangKy) {
            // → FORM Đăng Ký
            btnDangKy.setBackgroundResource(R.drawable.bg_toggle_selected);
            btnDangKy.setTextColor(Color.WHITE);

            btnDangNhap.setBackgroundColor(Color.TRANSPARENT);
            btnDangNhap.setTextColor(Color.parseColor("#666666"));

            tvHoTenLabel.setVisibility(View.VISIBLE);
            edtHoTen.setVisibility(View.VISIBLE);
            tvKhoaLabel.setVisibility(View.VISIBLE);
            spinnerKhoa.setVisibility(View.VISIBLE);
            btnSubmitDangKy.setText("Đăng Ký");

        } else {
            // → FORM Đăng Nhập
            btnDangNhap.setBackgroundResource(R.drawable.bg_toggle_selected);
            btnDangNhap.setTextColor(Color.WHITE);

            btnDangKy.setBackgroundColor(Color.TRANSPARENT);
            btnDangKy.setTextColor(Color.parseColor("#666666"));

            tvHoTenLabel.setVisibility(View.GONE);
            edtHoTen.setVisibility(View.GONE);
            tvKhoaLabel.setVisibility(View.GONE);
            spinnerKhoa.setVisibility(View.GONE);
            btnSubmitDangKy.setText("Đăng Nhập");
        }
    }

    private void handleSubmit() {
        String gmail = edtGmail.getText().toString().trim();
        String matKhau = edtMatKhau.getText().toString().trim();

        if (isDangKy) {
            String hoTen = edtHoTen.getText().toString().trim();
            String khoa = spinnerKhoa.getSelectedItem().toString();

            if (hoTen.isEmpty() || gmail.isEmpty() || matKhau.isEmpty() || khoa.equals("Chọn khoa")) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            goToHome();

        } else { // Đăng nhập
            if (gmail.isEmpty() || matKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            goToHome(); // BẮT BUỘC PHẢI CÓ
        }
    }
    private void goToHome() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // kết thúc RegisterActivity để không bị quay lại
    }
}
