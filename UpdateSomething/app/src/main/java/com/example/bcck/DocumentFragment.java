package com.example.bcck;

import android.content.Context;
import android.content.Intent;
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

    private static final String DOCUMENT_DATA_KEY = "document_data";

    // ✅ Tên Activity chi tiết đã được đặt là Details.class theo yêu cầu
    private static final Class<?> DETAIL_ACTIVITY = Details.class;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        initViews(view);
        setupRecyclerView();
        setupSearch();
        setupButtons();
        loadDocumentData(); // Tải và hiển thị dữ liệu mẫu

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
        Context context = getContext();
        if (context == null) return;

        recyclerViewDocuments.setLayoutManager(new LinearLayoutManager(context));

        documentList = new ArrayList<>();
        filteredDocumentList = new ArrayList<>();

        // 1. Khởi tạo DocumentAdapter và truyền Context vào.
        documentAdapter = new DocumentAdapter(context, filteredDocumentList, new DocumentAdapter.OnDocumentClickListener() {
            @Override
            public void onDocumentClick(Document document) {
                // Xử lý click khi bấm vào TOÀN BỘ item
                openDocumentDetails(document);
            }
        });

        recyclerViewDocuments.setAdapter(documentAdapter);
    }

    /**
     * Phương thức mở Activity chi tiết (Details.class) khi người dùng bấm vào item.
     * Cần đảm bảo class Document implements Parcelable/Serializable.
     */
    private void openDocumentDetails(Document document) {
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, DETAIL_ACTIVITY);
            // Gửi đối tượng Document.
            intent.putExtra(DOCUMENT_DATA_KEY, document);
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

        // Cần notifyDataSetChanged() sau khi load dữ liệu
        if (documentAdapter != null) {
            documentAdapter.notifyDataSetChanged();
        }
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

        // Cần notifyDataSetChanged() sau khi lọc dữ liệu
        if (documentAdapter != null) {
            documentAdapter.notifyDataSetChanged();
        }
    }
}