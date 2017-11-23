package com.massivcode.realmwithandroidarchitecturecomponents.db.base;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 13:42
 */

public class BaseDao<T extends RealmModel> {
  protected Realm mRealm;

  public BaseDao(Realm mRealm) {
    this.mRealm = mRealm;
  }

//  https://github.com/ericmaxwell2003/android-persistence/blob/master/app/src/main/java/com/example/android/persistence/codelab/realmdb/utils/Realm%2BDao.kt
  protected LiveRealmData<T> asLiveData(RealmResults<T> data) {
    return new LiveRealmData<>(data);
  }

}
