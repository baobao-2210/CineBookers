package com.example.bcck;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private List<Document> documentList;

    private OnDocumentClickListener clickListener;
    private Context context; // Thêm Context để có thể mở Activity

    public interface OnDocumentClickListener {
        void onDocumentClick(Document document);
        // Đã bỏ phương thức onMoreClick vì ta sẽ xử lý trực tiếp việc mở Activity tại đây.
    }

    // Cập nhật Constructor để nhận Context
    public DocumentAdapter(Context context, List<Document> documentList, OnDocumentClickListener listener) {
        this.context = context; // Khởi tạo Context
        this.documentList = documentList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        Document doc = documentList.get(position);

        // ... (Các đoạn set text khác giữ nguyên)
        holder.tvAuthorName.setText(doc.getAuthorName());
        holder.tvDocTitle.setText(doc.getTitle());
        holder.tvSubject1.setText(doc.getSubject());
        holder.tvTeacher.setText(doc.getTeacher());
        holder.tvMajor.setText(doc.getMajor());
        holder.tvDownloads.setText(String.valueOf(doc.getDownloads()));
        holder.tvLikes.setText(String.valueOf(doc.getLikes()));
        holder.tvRating.setText(String.valueOf(doc.getRating()));

        // Set document type badge
        holder.docTypeBadge.setText(doc.getDocType());
        if ("PDF".equals(doc.getDocType())) {
            holder.docTypeBadge.setTextColor(Color.parseColor("#FF5722"));
            holder.docTypeBadge.setBackgroundColor(Color.parseColor("#FFE5E0"));
        } else if ("PPT".equals(doc.getDocType())) {
            holder.docTypeBadge.setTextColor(Color.parseColor("#FF9800"));
            holder.docTypeBadge.setBackgroundColor(Color.parseColor("#FFF3E0"));
        }

        // Click listeners

        // 1. Click vào toàn bộ item
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onDocumentClick(doc);
            }
        });

        // 2. Click vào nút "More" (btnMore), xử lý việc mở Details Activity
        holder.btnMore.setOnClickListener(v -> {
            // **Thực hiện Intent để chuyển sang Details Activity**

            // Tạo Intent, thay thế DetailActivity.class bằng tên Activity chi tiết của bạn
            // (Ví dụ: DocumentDetailActivity.class)
            Intent intent = new Intent(context, Details.class);

            // Tùy chọn: Gửi dữ liệu của Document sang Activity chi tiết
            // Giả định class Document của bạn đã implements Serializable hoặc Parcelable
           

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return documentList.size();
    }

    static class DocumentViewHolder extends RecyclerView.ViewHolder {
        // ... (Các khai báo View giữ nguyên)
        TextView tvAuthorName, tvDocTitle, docTypeBadge, tvSubject1, tvTeacher, tvMajor;
        TextView tvDownloads, tvLikes, tvRating;
        ImageView btnMore;

        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            // ... (Ánh xạ ID giữ nguyên)
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
            tvDocTitle = itemView.findViewById(R.id.tvDocTitle);
            docTypeBadge = itemView.findViewById(R.id.docTypeBadge);
            tvSubject1 = itemView.findViewById(R.id.tvSubject1);
            tvTeacher = itemView.findViewById(R.id.tvTeacher);
            tvMajor = itemView.findViewById(R.id.tvMajor);
            tvDownloads = itemView.findViewById(R.id.tvDownloads);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvRating = itemView.findViewById(R.id.tvRating);
            btnMore = itemView.findViewById(R.id.btnMore); 
        }
    }
}