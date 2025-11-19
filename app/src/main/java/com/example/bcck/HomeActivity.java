package com.example.bcck;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cardview.widget.CardView;
public class HomeActivity extends AppCompatActivity
{
    // Filter buttons
    private Button btnAll, btnPopular, btnNewest;

    // Bottom navigation
    private ConstraintLayout navDocs, navGroup, navLibrary, navChat, navProfile;
    private ImageView navDocsIcon, navGroupIcon, navLibraryIcon, navChatIcon, navProfileIcon;
    private TextView navDocsText, navGroupText, navLibraryText, navChatText, navProfileText;

    // Cloud storage cards
    private CardView cardDropbox, cardGDrive, cardOneDrive1, cardOneDrive2;

    // PDF items
    private CardView pdfItem1, pdfItem2, pdfItem3;

    // Search
    private ImageView searchIcon, addIcon;
    private TextView searchBox;

    // Current selected filter
    private String currentFilter = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        initViews();
        setupSearchBar();
        setupCloudStorageCards();
        setupFilterButtons();
        setupPdfItems();
        setupBottomNavigation();

        // Set home as selected by default
        selectBottomNavItem(navDocs, navDocsIcon, navDocsText);
    }

    private void initViews() {
        // Filter buttons
        btnAll = findViewById(R.id.btnAll);
        btnPopular = findViewById(R.id.btnPopular);
        btnNewest = findViewById(R.id.btnNewest);

        // Bottom navigation containers
        navDocs = findViewById(R.id.navDocs);
        navGroup = findViewById(R.id.navGroup);
        navLibrary = findViewById(R.id.navLibrary);
        navChat = findViewById(R.id.navChat);
        navProfile = findViewById(R.id.navProfile);

        // Bottom navigation icons
        navDocsIcon = findViewById(R.id.navDocsIcon);
        navGroupIcon = findViewById(R.id.navGroupIcon);
        navLibraryIcon = findViewById(R.id.navLibraryIcon);
        navChatIcon = findViewById(R.id.navChatIcon);
        navProfileIcon = findViewById(R.id.navProfileIcon);

        // Bottom navigation texts
        navDocsText = findViewById(R.id.navDocsText);
        navGroupText = findViewById(R.id.navGroupText);
        navLibraryText = findViewById(R.id.navLibraryText);
        navChatText = findViewById(R.id.navChatText);
        navProfileText = findViewById(R.id.navProfileText);

        // Cloud storage cards
        cardDropbox = findViewById(R.id.cardDropbox);
        cardGDrive = findViewById(R.id.cardGDrive);
        cardOneDrive1 = findViewById(R.id.cardOneDrive1);
        cardOneDrive2 = findViewById(R.id.cardOneDrive2);

        // PDF items
        pdfItem1 = findViewById(R.id.pdfItem1);
        pdfItem2 = findViewById(R.id.pdfItem2);
        pdfItem3 = findViewById(R.id.pdfItem3);

        // Search
        searchIcon = findViewById(R.id.searchIcon);
        addIcon = findViewById(R.id.addIcon);
        searchBox = findViewById(R.id.searchBox);
    }

    private void setupSearchBar() {
        searchBox.setOnClickListener(v -> {
            Toast.makeText(this, "Mở tìm kiếm", Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            // startActivity(intent);
        });

        searchIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Tìm kiếm", Toast.LENGTH_SHORT).show();
        });

        addIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Thêm mới", Toast.LENGTH_SHORT).show();
            // Mở dialog hoặc activity để thêm tài liệu mới
        });
    }

    private void setupCloudStorageCards() {
        cardDropbox.setOnClickListener(v -> {
            openCloudStorage("Dropbox", "1.2 GB", 12);
        });

        cardGDrive.setOnClickListener(v -> {
            openCloudStorage("Google Drive", "13.5 GB", 52);
        });

        cardOneDrive1.setOnClickListener(v -> {
            openCloudStorage("Nirwna - OneDrive", "184.3 GB", 31);
        });

        cardOneDrive2.setOnClickListener(v -> {
            openCloudStorage("PIDT - OneDrive", "1311 GB", 62);
        });
    }

    private void openCloudStorage(String name, String storage, int percent) {
        Toast.makeText(this, name + " - " + storage + " (" + percent + "%)", Toast.LENGTH_SHORT).show();
        // Intent intent = new Intent(MainActivity.this, CloudStorageActivity.class);
        // intent.putExtra("storage_name", name);
        // intent.putExtra("storage_size", storage);
        // intent.putExtra("storage_percent", percent);
        // startActivity(intent);
    }

    private void setupFilterButtons() {
        btnAll.setOnClickListener(v -> {
            currentFilter = "all";
            filterDocuments("all");
            updateFilterButtonStyle(btnAll);
        });

        btnPopular.setOnClickListener(v -> {
            currentFilter = "popular";
            filterDocuments("popular");
            updateFilterButtonStyle(btnPopular);
        });

        btnNewest.setOnClickListener(v -> {
            currentFilter = "newest";
            filterDocuments("newest");
            updateFilterButtonStyle(btnNewest);
        });
    }

    private void updateFilterButtonStyle(Button selectedButton) {
        // Reset tất cả buttons về màu xám
        resetFilterButton(btnAll);
        resetFilterButton(btnPopular);
        resetFilterButton(btnNewest);

        // Highlight button được chọn
        selectedButton.setBackgroundTintList(getColorStateList(R.color.purple_500));
        selectedButton.setTextColor(getColor(android.R.color.white));
    }

    private void resetFilterButton(Button button) {
        button.setBackgroundTintList(getColorStateList(android.R.color.darker_gray));
        button.setTextColor(getColor(android.R.color.black));
    }

    private void filterDocuments(String filter) {
        Toast.makeText(this, "Lọc tài liệu theo: " + getFilterName(filter), Toast.LENGTH_SHORT).show();

        // Logic lọc tài liệu theo filter
        switch (filter) {
            case "all":
                // Hiển thị tất cả tài liệu
                showAllDocuments();
                break;
            case "popular":
                // Hiển thị tài liệu phổ biến (rating cao)
                showPopularDocuments();
                break;
            case "newest":
                // Hiển thị tài liệu mới nhất
                showNewestDocuments();
                break;
        }
    }

    private String getFilterName(String filter) {
        switch (filter) {
            case "all": return "Tất Cả";
            case "popular": return "Phổ Biến";
            case "newest": return "Mới Nhất";
            default: return "Tất Cả";
        }
    }

    private void showAllDocuments() {
        // Hiển thị tất cả PDF items
        pdfItem1.setVisibility(View.VISIBLE);
        pdfItem2.setVisibility(View.VISIBLE);
        pdfItem3.setVisibility(View.VISIBLE);
    }

    private void showPopularDocuments() {
        // Chỉ hiển thị tài liệu có rating >= 4.5
        pdfItem1.setVisibility(View.VISIBLE);
        pdfItem2.setVisibility(View.VISIBLE);
        pdfItem3.setVisibility(View.VISIBLE); // rating 4.9
    }

    private void showNewestDocuments() {
        // Hiển thị theo thứ tự mới nhất
        pdfItem1.setVisibility(View.VISIBLE);
        pdfItem2.setVisibility(View.VISIBLE);
        pdfItem3.setVisibility(View.VISIBLE);
    }

    private void setupPdfItems() {
        pdfItem1.setOnClickListener(v -> {
            openPdfDetail("Lập Trình Hướng Đối Tượng Chương I", 156, 29, 4.3);
        });

        pdfItem2.setOnClickListener(v -> {
            openPdfDetail("Lập trình Điện Thoại Di Động Chương II", 156, 29, 4.3);
        });

        pdfItem3.setOnClickListener(v -> {
            openPdfDetail("Lập trình Điện Thoại Di Động Chương III", 412, 89, 4.9);
        });
    }

    private void openPdfDetail(String title, int downloads, int likes, double rating) {
        Toast.makeText(this, "Mở: " + title, Toast.LENGTH_SHORT).show();

        // Chuyển sang PdfDetailActivity
        // Intent intent = new Intent(MainActivity.this, PdfDetailActivity.class);
        // intent.putExtra("pdf_title", title);
        // intent.putExtra("pdf_downloads", downloads);
        // intent.putExtra("pdf_likes", likes);
        // intent.putExtra("pdf_rating", rating);
        // startActivity(intent);
    }

    private void setupBottomNavigation() {
        navDocs.setOnClickListener(v -> {
            selectBottomNavItem(navDocs, navDocsIcon, navDocsText);
            // Đã ở trang home, không cần làm gì
            Toast.makeText(this, "Trang Tài liệu", Toast.LENGTH_SHORT).show();
        });

        navGroup.setOnClickListener(v -> {
            selectBottomNavItem(navGroup, navGroupIcon, navGroupText);
            navigateToScreen("GroupActivity");
        });

        navLibrary.setOnClickListener(v -> {
            selectBottomNavItem(navLibrary, navLibraryIcon, navLibraryText);
            navigateToScreen("LibraryActivity");
        });

        navChat.setOnClickListener(v -> {
            selectBottomNavItem(navChat, navChatIcon, navChatText);
            navigateToScreen("ChatActivity");
        });

        navProfile.setOnClickListener(v -> {
            selectBottomNavItem(navProfile, navProfileIcon, navProfileText);
            navigateToScreen("ProfileActivity");
        });
    }

    private void selectBottomNavItem(ConstraintLayout selectedNav, ImageView selectedIcon, TextView selectedText) {
        // Reset tất cả bottom nav items
        resetBottomNavItem(navDocsIcon, navDocsText);
        resetBottomNavItem(navGroupIcon, navGroupText);
        resetBottomNavItem(navLibraryIcon, navLibraryText);
        resetBottomNavItem(navChatIcon, navChatText);
        resetBottomNavItem(navProfileIcon, navProfileText);

        // Highlight item được chọn
        selectedIcon.setColorFilter(getColor(R.color.purple_500));
        selectedText.setTextColor(getColor(R.color.purple_500));
    }

    private void resetBottomNavItem(ImageView icon, TextView text) {
        icon.setColorFilter(getColor(android.R.color.darker_gray));
        text.setTextColor(getColor(android.R.color.darker_gray));
    }

    private void navigateToScreen(String activityName) {
        Toast.makeText(this, "Chuyển đến: " + activityName, Toast.LENGTH_SHORT).show();

        // Uncomment để chuyển sang activity tương ứng
        /*
        Intent intent = null;
        switch (activityName) {
            case "GroupActivity":
                intent = new Intent(MainActivity.this, GroupActivity.class);
                break;
            case "LibraryActivity":
                intent = new Intent(MainActivity.this, LibraryActivity.class);
                break;
            case "ChatActivity":
                intent = new Intent(MainActivity.this, ChatActivity.class);
                break;
            case "ProfileActivity":
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
            overridePendingTransition(0, 0); // Không có animation chuyển màn hình
        }
        */
    }

    @Override
    public void onBackPressed() {
        // Xử lý khi nhấn nút back
        if (!currentFilter.equals("all")) {
            // Nếu đang filter, quay về "Tất Cả"
            btnAll.performClick();
        } else {
            // Nếu đang ở "Tất Cả", hiện dialog xác nhận thoát
            super.onBackPressed();
        }
    }

}

