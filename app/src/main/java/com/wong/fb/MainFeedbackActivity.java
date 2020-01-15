package com.wong.fb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wong.bean.ModuleBean;


public class MainFeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTVBack;
    private TextView mTVTitle;
    private TextView mTVSelectModule;
    private RelativeLayout mRLSelectModule;
    private final static int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feedback);
        initViews();
        initListeners();
        initData();
    }
    private void initViews(){
        mTVBack = findViewById(R.id.tv_start);
        mTVTitle = findViewById(R.id.tv_title);
        mTVSelectModule = findViewById(R.id.tv_select_module);
        mRLSelectModule = findViewById(R.id.rl_select_module);


    }
    private void initData(){
        mTVTitle.setText(getString(R.string.suggest_feedback));
    }
    private void initListeners(){
        mRLSelectModule.setOnClickListener(this);
        mTVBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_select_module:/*选择模块*/
                Intent intent = new Intent(this,ModuleSelectionActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

                break;
            case R.id.tv_start:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE:
                if(resultCode == RESULT_OK && data != null){
                    Object obj = data.getSerializableExtra("module");
                    if(obj instanceof ModuleBean){
                        ModuleBean bean = (ModuleBean)obj;
                        mTVSelectModule.setText(bean.getName());
                    }
                }

                break;
        }
    }
}
