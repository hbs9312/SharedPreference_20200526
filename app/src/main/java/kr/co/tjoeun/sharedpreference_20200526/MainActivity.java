package kr.co.tjoeun.sharedpreference_20200526;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import kr.co.tjoeun.sharedpreference_20200526.databinding.ActivityMainBinding;
import kr.co.tjoeun.sharedpreference_20200526.utils.ContextUtil;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvent();
        setValues();
    }

    @Override
    public void setupEvent() {

        binding.idSaveCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                ContextUtil.setIdSave(mContext, isChecked);

            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isIdSave = binding.idSaveCheck.isChecked();
                
                if(isIdSave) {
                    String inputId = binding.emailEdt.getText().toString();

                    ContextUtil.setUserID(mContext,inputId);
                }
                
            }
        });
    }

    @Override
    public void setValues() {
        binding.emailEdt.setText(ContextUtil.getUserId(mContext));
        binding.idSaveCheck.setChecked(ContextUtil.isIdSave(mContext));
    }
}
