    package com.example.macrorecapp.features.rotary;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.databinding.DataBindingUtil;
    import android.os.Bundle;
    import android.view.View;
    import android.view.animation.AlphaAnimation;
    import com.example.macrorecapp.R;
    import com.example.macrorecapp.databinding.ActivityRotaryPhotoSettingsBinding;
    import com.example.macrorecapp.models.RotaryPhotoShoot;

    public class RotaryPhotoSettings extends AppCompatActivity {

        private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
        //RotaryView mPhotoRotaryView;
        //private int mTotalMoveInDegrees;
        ActivityRotaryPhotoSettingsBinding mBinding;
        RotaryPhotoShoot mRotaryPhotoShoot;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rotary_photo_settings);
            mRotaryPhotoShoot  = new RotaryPhotoShoot(6.88f, 25);
            mBinding.setPhotoShoot(mRotaryPhotoShoot);

            //mPhotoRotaryView = findViewById(R.id.rotaryPhotoView);
            //mPhotoRotaryView.addTarget(300);
            //mTotalMoveInDegrees = mPhotoRotaryView.getTotalMoveInDegrees();

        }

        public void goBack(View view) {
            view.startAnimation(buttonClick);
            finish();
        }

        public void openThreeSixtyPhotoRotary(View view) {
        }
    }
