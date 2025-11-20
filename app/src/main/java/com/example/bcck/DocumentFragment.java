package com.example.bcck;

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
                // Mở chi tiết tài liệu
                Toast.makeText(getContext(), "Mở: " + document.getTitle(), Toast.LENGTH_SHORT).show();
                // TODO: Chuyển sang DocumentDetailActivity hoặc mở PDF Viewer
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

        btnUpload.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Đăng tải tài liệu", Toast.LENGTH_SHORT).show();
            // TODO: Mở màn hình upload tài liệu
        });
    }

    private void loadDocumentData() {
        // Dữ liệu mẫu
        documentList.clear();
        documentList.add(new Document(
                "TS.Nguyễn Tấn Thuận",
                "Lập trình hướng đối tượng chương I",
                "PDF",
                "Lập trình hướng đối tượng",
                "T.S Nguyễn Quốc Hùng",
                "Công nghệ thông tin",
                234, 45, 4.5f
        ));

        documentList.add(new Document(
                "TS.Hoàng Thị Mỹ Lệ",
                "Cấu trúc dữ liệu và giải thuật",
                "PPT",
                "Lập trình hướng đối tượng",
                "T.S Nguyễn Quốc Hùng",
                "Công nghệ thông tin",
                189, 38, 4.7f
        ));

        documentList.add(new Document(
                "TS.Trần Văn A",
                "Cơ sở dữ liệu nâng cao",
                "PDF",
                "Cơ sở dữ liệu",
                "TS.Lê Thị B",
                "Công nghệ thông tin",
                312, 67, 4.8f
        ));

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