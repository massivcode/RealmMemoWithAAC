package com.massivcode.realmwithandroidarchitecturecomponents.db.base;

import android.arch.lifecycle.LiveData;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * https://github.com/ericmaxwell2003/android-persistence/blob/master/app/src/main/java/com/example/android/persistence/codelab/realmdb/utils/LiveRealmData.java
 */

public class LiveRealmData<T extends RealmModel> extends LiveData<RealmResults<T>> {

  private RealmResults<T> results;

  private final RealmChangeListener<RealmResults<T>> listener = new RealmChangeListener<RealmResults<T>>() {
    @Override
    public void onChange(RealmResults<T> results) {
      setValue(results);
    }
  };

  public LiveRealmData(RealmResults<T> realmResults) {
    results = realmResults;
    setValue(realmResults);
  }

  @Override
  protected void onActive() {
    System.out.println("LiveRealmData.onActive(): " + results);
    results.addChangeListener(listener);
  }

  @Override
  protected void onInactive() {
    System.out.println("LiveRealmData.onInactive()");
    results.removeChangeListener(listener);
  }
}
