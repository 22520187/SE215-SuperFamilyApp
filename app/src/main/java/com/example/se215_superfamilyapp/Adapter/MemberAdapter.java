package com.example.se215_superfamilyapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.se215_superfamilyapp.R;
import com.example.se215_superfamilyapp.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {

    private static List<Member> memberList=new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    // Interface để xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Member member);
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageAvatar;
        public LinearLayout countLayout;
        public TextView countText;
        public FrameLayout container;

        public MemberViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            countLayout = itemView.findViewById(R.id.count);
            container = itemView.findViewById(R.id.container);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(memberList.get(position));
                }
            });

        }
    }

    public MemberAdapter(List<Member> memberList, OnItemClickListener listener) {
        this.memberList = memberList;
        this.onItemClickListener = listener;
    }
    public void selectMember(int position) {
        for (int i = 0; i < memberList.size(); i++) {
            boolean tmp = i==position;
            if (tmp)
                memberList.get(i).setIsSelected(1);
            else
                memberList.get(i).setIsSelected(0);

        }
        notifyDataSetChanged();
    }
    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new MemberViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        Member member = memberList.get(position);
        if (member.getUnshownCount() == 1) {
            holder.countLayout.setVisibility(View.VISIBLE);
            holder.imageAvatar.setVisibility(View.INVISIBLE);
        } else {
            holder.imageAvatar.setImageResource(member.getAvatarResource());
            holder.countLayout.setVisibility(View.INVISIBLE);
            holder.imageAvatar.setVisibility(View.VISIBLE);

            if (member.getIsSelected()==1) {
                holder.container.setBackgroundResource(R.drawable.bg_selected_ava);
            } else {
                holder.container.setBackgroundResource(R.drawable.circle_background);
            }
        }
    }


    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
