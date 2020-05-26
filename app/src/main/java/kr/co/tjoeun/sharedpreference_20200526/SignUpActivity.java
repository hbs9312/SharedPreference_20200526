package kr.co.tjoeun.sharedpreference_20200526;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import kr.co.tjoeun.sharedpreference_20200526.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        setupEvent();
        setValues();
    }

    @Override
    public void setupEvent() {


        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int clikedRadioBtnId = binding.workTypeRdaioGroup.getCheckedRadioButtonId();
                
                if (clikedRadioBtnId == -1) {
                    Toast.makeText(mContext, "아무 항목도 고르지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (clikedRadioBtnId == R.id.fullTimeRadioBtn) {
                        Toast.makeText(mContext, "풀타임", Toast.LENGTH_SHORT).show();
                    }
                    else if(clikedRadioBtnId == R.id.partTimeRadioBtn){
                        Toast.makeText(mContext, "파트타임", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Log.d("오류","잘못된 선택지");
                    }
                }

            }
        });


//        비밀번호 확인에 뭐라고 적히는지를 타이핑할 때마다 확인.
//        조건에 따라 문구 변경
//         => 한글자도 없다 : 비밀번호를 입력해주세요. 글자색 #A0A0A0
//         => 8글자 미만 : 비밀번호가 너무 짧습니다. 글자색 빨간색
//         => 8글자 이상인데, 그냥 비밀번호와 다르다 => 비밀번호가 서로 다릅니다. 빨간색
//         => 8글자 이상 + 그냥 비밀번호와 같다 => 사용해도 좋은 비밀번호입니다. #2767E3

        binding.passwordCheckEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String inputPw = s.toString();
                String passwordEdt = binding.passwordEdt.getText().toString();


                if (inputPw.equals("")) {
                    binding.passwordResultTxt.setText("비밀번호를 입력해주세요.");
                    binding.passwordResultTxt.setTextColor(Color.parseColor("#A0A0A0"));
                }
                else if(inputPw.length() < 8) {
                    binding.passwordResultTxt.setText("비밀번호가 너무 짧습니다.");
                    binding.passwordResultTxt.setTextColor(Color.RED);
                }
                else {
                    String originalPw = binding.passwordEdt.getText().toString();
                    if (!originalPw.equals(inputPw)) {
                        binding.passwordResultTxt.setText("비밀번호가 서로 다릅니다.");
                        binding.passwordResultTxt.setTextColor(Color.RED);
                    }
                    else {
                        binding.passwordResultTxt.setText("사용해도 좋은 비밀번호입니다.");
                        binding.passwordResultTxt.setTextColor(Color.parseColor("#2767E3"));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.emailEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String input = s.toString();
                Log.d("변경된내용", s.toString());

//                @를 포함 + 6글자 이상. => 이메일로 인정
                if(input.contains("@") && input.length() >=6 ) {
                    binding.emailCheckResultTxt.setText("사용해도 좋은 이메일 입니다.");

                    binding.emailCheckResultTxt.setTextColor(Color.parseColor("#2767E3"));
                }
                else {
                    if(input.length() == 0) {
                        binding.emailCheckResultTxt.setText("이메일을 입력해주세요.");
                        binding.emailCheckResultTxt.setTextColor(Color.parseColor("#A0A0A0"));
                    }
                    else {
                        binding.emailCheckResultTxt.setText("이메일 양식으로 입력해주세요.");
                        binding.emailCheckResultTxt.setTextColor(Color.RED);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setValues() {

    }
}
