package com.massivcode.realmwithandroidarchitecturecomponents.db;

import android.arch.lifecycle.LiveData;
import com.massivcode.realmwithandroidarchitecturecomponents.db.base.BaseDao;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 14:00
 */

public class MemoDao extends BaseDao<Memo> {

  public MemoDao(Realm mRealm) {
    super(mRealm);
  }

  public void add(Memo memo) {
    memo.setId(maxId());

    mRealm.beginTransaction();
    mRealm.copyToRealm(memo);
    mRealm.commitTransaction();
  }

  private long maxId() {
    Number maxId = mRealm.where(Memo.class).max("id");

    if (maxId == null) {
      return 1;
    } else {
      return maxId.longValue() + 1;
    }
  }

  public void update(Memo oldOne, Memo newOne) {
    mRealm.beginTransaction();
    oldOne.setTitle(newOne.getTitle());
    oldOne.setContents(newOne.getContents());
    oldOne.setUpdatedAt(newOne.getUpdatedAt());
    mRealm.commitTransaction();
  }

  public void delete(Memo memo) {
    mRealm.beginTransaction();
    memo.deleteFromRealm();
    mRealm.commitTransaction();
  }

  public LiveData<RealmResults<Memo>> findAllMemos() {
    return asLiveData(mRealm.where(Memo.class).findAllSorted("updatedAt", Sort.DESCENDING));
  }
}
