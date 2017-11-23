package com.massivcode.realmwithandroidarchitecturecomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.massivcode.realmwithandroidarchitecturecomponents.db.Memo;
import com.massivcode.realmwithandroidarchitecturecomponents.db.MemoDao;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 14:06
 */

public class MainViewModel extends ViewModel {
  private Realm mRealm;
  private MemoDao mMemoDao;
  private LiveData<RealmResults<Memo>> mMemos;

  public MainViewModel() {
    System.out.println("MainViewModel.constructor()");
    mRealm = Realm.getDefaultInstance();
    mMemoDao = new MemoDao(mRealm);
    subscribeMemos();
  }

  private void subscribeMemos() {
    System.out.println("MainViewModel.subscribeMemos()");
    mMemos = mMemoDao.findAllMemos();
  }

  public LiveData<RealmResults<Memo>> getMemos() {
    System.out.println("MainViewModel.getMemos(): " + mMemos.getValue());
    return mMemos;
  }

  public void addMemo(Memo memo) {
    mMemoDao.add(memo);
  }

  public void onItemDeleteClick(Memo memo) {
    mMemoDao.delete(memo);
  }

  public void onItemUpdateClick(Memo oldOne, Memo newOne) {
    mMemoDao.update(oldOne, newOne);
  }

  @Override
  protected void onCleared() {
    System.out.println("MainViewModel.onCleared()");
    mRealm.close();
    super.onCleared();
  }
}
