package com.massivcode.realmwithandroidarchitecturecomponents;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.massivcode.realmwithandroidarchitecturecomponents.MemoAdapter.MemoViewHolder;
import com.massivcode.realmwithandroidarchitecturecomponents.db.Memo;
import io.realm.RealmResults;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 14:21
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoViewHolder> {

  private RealmResults<Memo> mData;
  private View.OnClickListener mOnItemClickListener, mOnItemUpdateClickListener, mOnItemDeleteClickListener;

  public MemoAdapter(OnClickListener mOnItemClickListener,
      OnClickListener mOnItemUpdateClickListener,
      OnClickListener mOnItemDeleteClickListener) {
    this.mOnItemClickListener = mOnItemClickListener;
    this.mOnItemUpdateClickListener = mOnItemUpdateClickListener;
    this.mOnItemDeleteClickListener = mOnItemDeleteClickListener;
  }

  @Override
  public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new MemoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false));
  }

  @Override
  public void onBindViewHolder(MemoViewHolder holder, int position) {
    holder.onBindItem(mData.get(position));
  }

  @Override
  public int getItemCount() {
    return mData == null ? 0 : mData.size();
  }

  public void onUpdate(RealmResults<Memo> data) {
    System.out.println("MemoAdapter.onUpdate: " + data);
    mData = data;
    notifyDataSetChanged();
  }

  public class MemoViewHolder extends ViewHolder {

    TextView mTitleTextView, mContentsPreviewTextView, mLastDateTextView;

    public MemoViewHolder(View itemView) {
      super(itemView);

      itemView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          view.setTag(mData.get(getAdapterPosition()));
          mOnItemClickListener.onClick(view);
        }
      });


      itemView.findViewById(R.id.memoUpdateTv).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          view.setTag(mData.get(getAdapterPosition()));
          mOnItemUpdateClickListener.onClick(view);
        }
      });

      itemView.findViewById(R.id.memoDeleteTv).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          view.setTag(mData.get(getAdapterPosition()));
          mOnItemDeleteClickListener.onClick(view);
        }
      });

      mTitleTextView = itemView.findViewById(R.id.memoTitleTv);
      mContentsPreviewTextView = itemView.findViewById(R.id.memoContentsPreviewTv);
      mLastDateTextView = itemView.findViewById(R.id.memoLastTimeTv);
    }

    public void onBindItem(Memo memo) {
      mTitleTextView.setText(memo.getTitle());
      mContentsPreviewTextView.setText(memo.getContents());

      if (memo.getUpdatedAt().after(memo.getCreatedAt())) {
        mLastDateTextView.setText(memo.getUpdatedAt().toString());
      } else {
        mLastDateTextView.setText(memo.getCreatedAt().toString());
      }
    }
  }
}
