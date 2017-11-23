package com.massivcode.realmwithandroidarchitecturecomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import com.massivcode.realmwithandroidarchitecturecomponents.MemoActionDialog.Callback;
import com.massivcode.realmwithandroidarchitecturecomponents.db.Memo;
import com.massivcode.realmwithandroidarchitecturecomponents.lifecycle.LifecycleListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity  {

  private MemoAdapter mMemoAdapter;
  private MainViewModel mMainViewModel;
  private LifecycleListener mLifecycleListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mLifecycleListener = new LifecycleListener(this);
    getLifecycle().addObserver(mLifecycleListener);

    mMemoAdapter = new MemoAdapter(mOnMemoItemClickListener, mOnMemoItemUpdateClickListener,
        mOnMemoItemDeleteClickListener);

    RecyclerView recyclerView = findViewById(R.id.rv);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    recyclerView.setAdapter(mMemoAdapter);

    findViewById(R.id.confirmBtn).setOnClickListener(mOnMemoAddClickListener);

    mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    mMainViewModel.getMemos().observe(this, new Observer<RealmResults<Memo>>() {
      @Override
      public void onChanged(@Nullable RealmResults<Memo> memos) {
        System.out.println("RealmResults<Memo>.onChanged: " + memos);
        mMemoAdapter.onUpdate(memos);
      }
    });

  }


  private View.OnClickListener mOnMemoAddClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {
      MemoActionDialog.show(null, MainActivity.this, new Callback() {
        @Override
        public void onItemAdd(Memo memo) {
          mMainViewModel.addMemo(memo);
        }

        @Override
        public void onItemUpdate(Memo oldOne, Memo newOne) {

        }
      });
    }
  };

  private View.OnClickListener mOnMemoItemClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {

    }
  };

  private View.OnClickListener mOnMemoItemUpdateClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {
      Memo oldOne = (Memo) view.getTag();

      MemoActionDialog.show(oldOne, MainActivity.this, new Callback() {
        @Override
        public void onItemAdd(Memo memo) {
        }

        @Override
        public void onItemUpdate(Memo oldOne, Memo newOne) {
          mMainViewModel.onItemUpdateClick(oldOne, newOne);
        }
      });
    }
  };

  private View.OnClickListener mOnMemoItemDeleteClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {
      Memo clickedItem = (Memo) view.getTag();
      mMainViewModel.onItemDeleteClick(clickedItem);
    }
  };


}
