package com.example.se215_superfamilyapp.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.se215_superfamilyapp.R;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Integer> imageList; // Danh sách ID ảnh
    private Context context;
    private OnImageClickListener onImageClickListener;

    public ImageAdapter(Context context, List<Integer> imageList, OnImageClickListener listener) {
        this.context = context;
        this.imageList = imageList;
        this.onImageClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
        holder.imageView.setOnClickListener(v -> onImageClickListener.onImageClick(imageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public interface OnImageClickListener {
        void onImageClick(int imageResId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
