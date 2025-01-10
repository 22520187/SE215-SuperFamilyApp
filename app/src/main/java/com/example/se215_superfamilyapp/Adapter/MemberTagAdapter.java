package com.example.se215_superfamilyapp.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.R;
import com.example.se215_superfamilyapp.model.MemberTag;

import java.util.List;

public class MemberTagAdapter extends RecyclerView.Adapter<MemberTagAdapter.MemberViewHolder> {
    private List<MemberTag> members;
    private OnMemberClickListener listener;

    public interface OnMemberClickListener {
        void onRemoveClick(int position);
        void onAddClick();
    }

    public MemberTagAdapter(List<MemberTag> members, OnMemberClickListener listener) {
        this.members = members;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member_tag, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        MemberTag member = members.get(position);
        if (member.isAddButton()) {
            holder.tvMemberName.setText("+");
            holder.ivRemove.setVisibility(View.GONE);
            holder.tvMemberName.setOnClickListener(v -> listener.onAddClick());
        } else {
            holder.tvMemberName.setText(member.getName());
            holder.ivRemove.setVisibility(View.VISIBLE);
            holder.ivRemove.setOnClickListener(v -> listener.onRemoveClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView tvMemberName;
        ImageView ivRemove;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMemberName = itemView.findViewById(R.id.tv_member_name);
            ivRemove = itemView.findViewById(R.id.iv_remove);
        }
    }
}
