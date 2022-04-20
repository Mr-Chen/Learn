package com.imooc.popupwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showPopupWindow(View view) {
        View layoutWindow = getLayoutInflater().inflate(R.layout.layout_popupwindow, null);
        Button btn = layoutWindow.findViewById(R.id.diss);
        PopupWindow popupWindow = new PopupWindow(layoutWindow, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(getDrawable(R.drawable.p1));
        popupWindow.showAsDropDown(view,view.getWidth(),-view.getHeight());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
}