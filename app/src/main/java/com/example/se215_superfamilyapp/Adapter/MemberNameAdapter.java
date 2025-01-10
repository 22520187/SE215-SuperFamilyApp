package com.example.se215_superfamilyapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.R;
import com.example.se215_superfamilyapp.model.Member;

import java.util.List;

public class MemberNameAdapter extends RecyclerView.Adapter<MemberNameAdapter.MemberViewHolder> {

    private final List<Member> memberList;
    private OnMemberClickListener onMemberClickListener;

    public interface OnMemberClickListener {
        void onMemberClick(Member selectedCount);
    }

    public void setOnMemberClickListener(OnMemberClickListener listener) {
        this.onMemberClickListener = listener;
    }

    public MemberNameAdapter(List<Member> memberList) {
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_name_item, parent, false);
        return new MemberViewHolder(view);
    }
    private int selectedPosition = -1;
    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        holder.imageAvatar.setImageResource(member.getAvatarResource());
        holder.tvMemberName.setText(member.getName());
        holder.itemView.setSelected(member.getIsSelected() == 1);
        if (selectedPosition == position) {
            holder.itemView.setBackgroundResource(R.drawable.selected_border);
        } else {
            holder.itemView.setBackgroundResource(android.R.color.transparent);
        }
        holder.itemView.setOnClickListener(v -> {
            // Toggle selection
            int previousSelectedPosition = selectedPosition;
            selectedPosition = position;

            // Notify changes for previous and new selection
            notifyItemChanged(previousSelectedPosition);
            notifyItemChanged(selectedPosition);

            // Notify activity
            if (onMemberClickListener != null) {
                onMemberClickListener.onMemberClick(member);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    static class MemberViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public ImageView imageAvatar;
        public TextView tvMemberName;


        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.member_item_container);
            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            tvMemberName = itemView.findViewById(R.id.tv_member_name);
        }
    }
}
