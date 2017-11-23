package com.massivcode.realmwithandroidarchitecturecomponents.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by massivcode@gmail.com on 2017. 11. 23. 14:15
 */

public class LifecycleListener implements LifecycleObserver {

  private Context mContext;

  public LifecycleListener(Context mContext) {
    this.mContext = mContext;
  }

  @OnLifecycleEvent(Event.ON_CREATE)
  void onCreate() {
    Toast.makeText(mContext, "LifecycleListener.onCreate()", Toast.LENGTH_SHORT).show();
  }

  @OnLifecycleEvent(Event.ON_RESUME)
  void onResume() {
    Toast.makeText(mContext, "LifecycleListener.onResume()", Toast.LENGTH_SHORT).show();
  }

  @OnLifecycleEvent(Event.ON_STOP)
  void onPause() {
    Toast.makeText(mContext, "LifecycleListener.onPause()", Toast.LENGTH_SHORT).show();
  }

  @OnLifecycleEvent(Event.ON_START)
  void onStart() {
    Toast.makeText(mContext, "LifecycleListener.onStart()", Toast.LENGTH_SHORT).show();
  }


  @OnLifecycleEvent(Event.ON_STOP)
  void onStop() {
    Toast.makeText(mContext, "LifecycleListener.onStop()", Toast.LENGTH_SHORT).show();
  }

  @OnLifecycleEvent(Event.ON_DESTROY)
  void onDestroy() {
    Toast.makeText(mContext, "LifecycleListener.onDestory()", Toast.LENGTH_SHORT).show();
  }

}
