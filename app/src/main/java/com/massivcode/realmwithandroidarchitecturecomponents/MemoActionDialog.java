package com.massivcode.realmwithandroidarchitecturecomponents;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.massivcode.realmwithandroidarchitecturecomponents.db.Memo;
import java.util.Date;

/**
 * Created by massivcode@gmail.com on 2017. 11. 22. 14:47
 */

public class MemoActionDialog extends DialogFragment {

  public interface Callback {

    void onItemAdd(Memo memo);

    void onItemUpdate(Memo oldOne, Memo newOne);
  }

  private static Callback sCallback;
  private static Memo sMemo;

  public static void show(Memo updateTarget, AppCompatActivity activity, Callback callback) {
    sCallback = callback;
    sMemo = updateTarget;

    MemoActionDialog fragment = new MemoActionDialog();
    fragment.show(activity.getSupportFragmentManager(), "");
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.dialog_item_action, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    final EditText titleEditText = view.findViewById(R.id.titleEt);
    final EditText contentsEditText = view.findViewById(R.id.contentsEt);

    view.findViewById(R.id.cancelBtn).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        dismiss();
      }
    });

    Button confirmButton = view.findViewById(R.id.confirmBtn);

    if (sMemo != null) {
      confirmButton.setText("수정");
      titleEditText.setText(sMemo.getTitle());
      contentsEditText.setText(sMemo.getContents());
    } else {
      confirmButton.setText("추가");
    }

    confirmButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        String title = titleEditText.getText().toString();

        if (TextUtils.isEmpty(title)) {
          Toast.makeText(getContext(), "제목을 입력하세요!", Toast.LENGTH_SHORT).show();
          return;
        }

        String contents = contentsEditText.getText().toString();

        if (TextUtils.isEmpty(contents)) {
          Toast.makeText(getContext(), "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
          return;
        }


        if (sMemo != null) {
          Memo memo = new Memo(title, contents, sMemo.getCreatedAt(), new Date());
          sCallback.onItemUpdate(sMemo, memo);
        } else {
          Memo memo = new Memo(title, contents, new Date(), new Date());
          sCallback.onItemAdd(memo);
        }

        dismiss();
        sCallback = null;
        sMemo = null;
      }
    });
  }
}
