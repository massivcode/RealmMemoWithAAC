package com.massivcode.realmwithandroidarchitecturecomponents;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 14:44
 */

public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    Realm.init(this);
    Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
        .schemaVersion(1)
        .build());
  }
}
