package com.example.se215_superfamilyapp;
import com.example.se215_superfamilyapp.Adapter.ChatAdapter;
import com.example.se215_superfamilyapp.Adapter.ImageAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Arrays;
import java.util.List;
import android.view.inputmethod.InputMethodManager;
import android.content.Intent;
import android.net.Uri;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.view.MotionEvent;
import androidx.recyclerview.widget.GridLayoutManager;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvChatMessages;
    private EditText etMessage;
    private ImageButton btnSend;
    private ChatAdapter chatAdapter;
    private List<String> chatMessages;
    private TextView tvHintMessage;
    private View horizontalSuggestions;

    private static final int REQUEST_IMAGE_PICK = 1;
    private List<String> emojiList;

    private PopupWindow emojiPopup;

    private LinearLayout emojiContainer;
    private boolean isEmojiVisible = false;


    private void initializeEmojiList() {
        emojiList = new ArrayList<>();
        emojiList.add("\uD83D\uDE00"); // 😀
        emojiList.add("\uD83D\uDE02"); // 😂
        emojiList.add("\uD83D\uDE0D"); // 😍
        emojiList.add("\uD83D\uDE12"); // 😒
        emojiList.add("\uD83D\uDE14"); // 😔
        emojiList.add("\uD83D\uDE4F"); // 🙏
        emojiList.add("\uD83D\uDE22"); // 😢
        emojiList.add("\uD83D\uDE44"); // 🙄
        emojiList.add("\uD83D\uDC4D"); // 👍
        emojiList.add("\uD83D\uDC4E"); // 👎
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rvChatMessages = findViewById(R.id.rvChatMessages);
        etMessage = findViewById(R.id.etMessage);
        ImageButton btnSend = findViewById(R.id.btnSend);
        tvHintMessage = findViewById(R.id.tvHintMessage);
        horizontalSuggestions = findViewById(R.id.horizontalSuggestions);
        emojiContainer = findViewById(R.id.emojiContainer);
        GridView gridEmoji = findViewById(R.id.gridEmoji);
        View rootView = findViewById(android.R.id.content);
        ImageButton btnPrevious = findViewById(R.id.btnPrevious);


        tvHintMessage.setCompoundDrawablesRelativeWithIntrinsicBounds(
                R.drawable.ic_i, 0, R.drawable.ic_x, 0);
        tvHintMessage.setOnClickListener(v -> hideHints());


        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);

        rvChatMessages.setLayoutManager(new LinearLayoutManager(this));
        rvChatMessages.setAdapter(chatAdapter);


        btnPrevious.setOnClickListener(v -> {
//            Intent intent = new Intent(ChatActivity.this, MessageFragment.class);
//            intent.putExtra("openFragment", "MessageFragment");
//            startActivity(intent);
            finish();
        });

        etMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, android.view.KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    return true;
                }
                return false;
            }
        });

        btnSend.setOnClickListener(v -> sendMessage());

        initializeEmojiList();

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
            boolean isKeyboardVisible = heightDiff > dpToPx(200);
            if (isKeyboardVisible) {
                hideEmojiKeyboard();
            }
        });
        ArrayAdapter<String> emojiAdapter = new ArrayAdapter<>(this, R.layout.emoji_item, emojiList);
        gridEmoji.setAdapter(emojiAdapter);


        gridEmoji.setOnItemClickListener((parent, view, position, id) -> {
            String selectedEmoji = emojiList.get(position);
            etMessage.getText().append(selectedEmoji);
        });

        etMessage.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (etMessage.getRight() - etMessage.getCompoundDrawables()[2].getBounds().width())) {
                    toggleEmojiKeyboard();
                    return true;
                }
            }
            return false;
        });

        btnSend.setOnClickListener(v -> showImagePicker());
    }


    private void showImagePicker() {
        // Tạo danh sách ảnh mẫu
        List<Integer> imageList = Arrays.asList(
                R.drawable.welcome1,
                R.drawable.welcome2,
                R.drawable.welcome3
//                R.drawable.image4,
//                R.drawable.image5,
//                R.drawable.image6
        );

        // Hiển thị BottomSheetDialog
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.layout_image_picker, null);
        dialog.setContentView(view);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 cột

        ImageAdapter adapter = new ImageAdapter(this, imageList, imageResId -> {
            // Xử lý khi chọn ảnh
            dialog.dismiss();
            // Ví dụ: Hiển thị ảnh được chọn trong Log hoặc ImageView
            System.out.println("Selected image: " + imageResId);
        });

        recyclerView.setAdapter(adapter);
        dialog.show();
    }



    private void toggleEmojiKeyboard() {
        if (emojiContainer.getVisibility() == View.VISIBLE) {
            emojiContainer.setVisibility(View.GONE);
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etMessage.getWindowToken(), 0);

            emojiContainer.setVisibility(View.VISIBLE);
        }
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    private void showEmojiKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etMessage.getWindowToken(), 0);

        emojiContainer.setVisibility(View.VISIBLE);
        isEmojiVisible = true;
    }

    private void hideEmojiKeyboard() {
        emojiContainer.setVisibility(View.GONE);
        isEmojiVisible = false;
    }

    private void sendMessage() {
        String message = etMessage.getText().toString().trim();
        if (!message.isEmpty()) {
            chatMessages.add(message);
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            rvChatMessages.scrollToPosition(chatMessages.size() - 1);
            etMessage.setText(""); // Xóa nội dung EditText
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
        }
    }

    private void hideHints() {
        tvHintMessage.setVisibility(View.GONE);
        horizontalSuggestions.setVisibility(View.GONE);
    }
}
