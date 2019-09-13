package com.example.macrorecapp.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.example.macrorecapp.features.shared.views.RotaryView;

public class RotaryPhotoShoot extends BaseObservable {

    private static final String TAG = "Rotary Photo Shoot";
    private float anglePerPhotos;
    private int numberOfPhotos;


    public RotaryPhotoShoot(float anglePerPhotos, int numberOfPhotos) {
        this.anglePerPhotos = anglePerPhotos;
        this.numberOfPhotos = numberOfPhotos;
    }

    @Bindable
    public float getAnglePerPhotos() {
        return RotaryView.getTotalMoveInDegrees()/(float) numberOfPhotos;
    }

    @Bindable
    public int getNumberOfPhotos() {
        return numberOfPhotos;
    }

    @Bindable
    public void setAnglePerPhotos(float anglePerPhotos) {
        this.anglePerPhotos = RotaryView.getTotalMoveInDegrees()/numberOfPhotos;
    }

    @Bindable
    public void setNumberOfPhotos(int numberOfPhotos) {
        this.numberOfPhotos = numberOfPhotos;
        notifyPropertyChanged(com.example.macrorecapp.BR.numberOfPhotos);
        notifyPropertyChanged(com.example.macrorecapp.BR.anglePerPhotos);
    }

}
