package com.example.bcck.poster;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import com.example.bcck.R;

import java.util.ArrayList;
import java.util.List;

public class DocumentFragment extends Fragment {

    private RecyclerView recyclerViewDocuments;
    private DocumentAdapter documentAdapter;
    private List<Document> documentList;
    private List<Document> filteredDocumentList;

    private EditText searchBox;
    private ImageView iconNotification, iconProfile;
    private CardView btnUpload;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        initViews(view);
        setupRecyclerView();
        setupSearch();
        setupButtons();
        loadDocumentData();
        return view;

    }

    private void initViews(View view) {
        recyclerViewDocuments = view.findViewById(R.id.recyclerViewDocuments);
        searchBox = view.findViewById(R.id.searchBox);
        iconNotification = view.findViewById(R.id.iconNotification);
        iconProfile = view.findViewById(R.id.iconProfile);
        btnUpload = view.findViewById(R.id.btnUpload);
    }

    private void setupRecyclerView() {
        recyclerViewDocuments.setLayoutManager(new LinearLayoutManager(getContext()));

        documentList = new ArrayList<>();
        filteredDocumentList = new ArrayList<>();

        documentAdapter = new DocumentAdapter(filteredDocumentList, new DocumentAdapter.OnDocumentClickListener() {
            @Override
            public void onDocumentClick(Document document) {
                // **ĐOẠN CODE CẦN THAY THẾ TOAST**
                openDocumentDetail(document);
            }
            @Override
            public void onMoreClick(Document document) {
                // Hiển thị menu options
                Toast.makeText(getContext(), "Options cho: " + document.getTitle(), Toast.LENGTH_SHORT).show();
                // TODO: Hiển thị Bottom Sheet với options: Download, Share, Report...
            }
        });
        recyclerViewDocuments.setAdapter(documentAdapter);
    }

    // 2. TẠO HÀM MỚI ĐỂ XỬ LÝ VIỆC CHUYỂN MÀN HÌNH
    private void openDocumentDetail(Document document) {
        // Đảm bảo getContext() không null (luôn kiểm tra trong Fragment)
        if (getContext() != null) {
            // 1. Tạo Intent và chỉ định DocumentDetailActivity
            Intent intent = new Intent(getContext(), DocumentDetailActivity.class);

            // 2. Đính kèm đối tượng Document (BẮT BUỘC Document phải là Parcelable)
            // Đảm bảo "DOCUMENT_DETAIL" khớp với key bạn dùng trong DocumentDetailActivity
            intent.putExtra("DOCUMENT_DETAIL", document);

            // 3. Khởi chạy Activity
            startActivity(intent);
        }
    }


    private void setupSearch() {
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterDocuments(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupButtons() {
        iconNotification.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Thông báo", Toast.LENGTH_SHORT).show();
            // TODO: Mở màn hình thông báo
        });

        iconProfile.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Cá nhân", Toast.LENGTH_SHORT).show();
            // TODO: Chuyển sang tab Cá nhân
        });

        // --- Sửa đoạn code này để thêm thao tác chuyển màn hình ---
        btnUpload.setOnClickListener(v -> {
            // Mở Activity Tải lên Tài liệu
            Intent intent = new Intent(requireContext(), UploadDocumentActivity.class);
            startActivity(intent);

            // Chú ý: Bạn cần đảm bảo đã tạo UploadDocumentActivity và nó đã được khai báo trong AndroidManifest.xml
        });
    }

    private void loadDocumentData() {
        // Dữ liệu mẫu
        documentList.clear();

        // TÀI LIỆU 1
        Document doc1 = new Document(
                "TS.Nguyễn Tấn Thuận",
                "Lập trình hướng đối tượng chương I",
                "PDF",
                "Lập trình hướng đối tượng",
                "T.S Nguyễn Quốc Hùng",
                "Công nghệ thông tin",
                234, 45, 4.5f
        );
        // Gán ID người đăng cho tài liệu này (ID giả định)
        doc1.setUploaderId("user_id_001_thuan");
        documentList.add(doc1);

        // TÀI LIỆU 2
        Document doc2 = new Document(
                "TS.Hoàng Thị Mỹ Lệ",
                "Cấu trúc dữ liệu và giải thuật",
                "PPT",
                "Lập trình hướng đối tượng",
                "T.S Nguyễn Quốc Hùng",
                "Công nghệ thông tin",
                189, 38, 4.7f
        );
        // Gán ID người đăng cho tài liệu này
        doc2.setUploaderId("user1");
        documentList.add(doc2);

        // TÀI LIỆU 3
        Document doc3 = new Document(
                "TS.Trần Văn A",
                "Cơ sở dữ liệu nâng cao",
                "PDF",
                "Cơ sở dữ liệu",
                "TS.Lê Thị B",
                "Công nghệ thông tin",
                312, 67, 4.8f
        );
        // Gán ID người đăng cho tài liệu này
        doc3.setUploaderId("user2");
        documentList.add(doc3);

        filteredDocumentList.clear();
        filteredDocumentList.addAll(documentList);
        documentAdapter.notifyDataSetChanged();
    }
    private void filterDocuments(String query) {
        filteredDocumentList.clear();

        if (query.isEmpty()) {
            filteredDocumentList.addAll(documentList);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Document doc : documentList) {
                if (doc.getTitle().toLowerCase().contains(lowerQuery) ||
                        doc.getAuthorName().toLowerCase().contains(lowerQuery) ||
                        doc.getSubject().toLowerCase().contains(lowerQuery)) {
                    filteredDocumentList.add(doc);
                }
            }
        }

        documentAdapter.notifyDataSetChanged();
    }

}