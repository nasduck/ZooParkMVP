package com.zoopark.demo.user.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zoopark.demo.user.model.entity.GithubUserBean;
import com.zoopark.demo.R;

import java.util.ArrayList;
import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener mListener;

    private Context mContext;
    private List<GithubUserBean> mData = new ArrayList<>();

    public GithubUserAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public GithubUserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_github_user, viewGroup, false);
        GithubUserViewHolder holder = new GithubUserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final GithubUserViewHolder viewHolder, int i) {
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onItemClick(viewHolder.getAdapterPosition());
            }
        });
        Glide.with(viewHolder.ivUserAvatar.getContext())
                .load(mData.get(i).getAvatarUrl())
                .into(viewHolder.ivUserAvatar);
        viewHolder.tvName.setText(mData.get(i).getLogin());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<GithubUserBean> data) {
        this.mData = data;
    }

    public List<GithubUserBean> getDate() {
        return mData;
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public class GithubUserViewHolder extends RecyclerView.ViewHolder {

        LinearLayout container;
        ImageView ivUserAvatar;
        TextView tvName;

        public GithubUserViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            ivUserAvatar = itemView.findViewById(R.id.iv_user);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
