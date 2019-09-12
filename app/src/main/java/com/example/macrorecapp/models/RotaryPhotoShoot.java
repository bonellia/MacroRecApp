package com.example.macrorecapp.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.macrorecapp.features.shared.views.RotaryView;

public class RotaryPhotoShoot extends BaseObservable {
    private static final String TAG = "Rotary Photo Shoot";

    private float anglePerPhotos;
    private int numberOfPhotos;
    private int totalMoveDegrees;



    public RotaryPhotoShoot(float anglePerPhotos,int numberOfPhotos, int totalMoveDegrees) {
        this.anglePerPhotos = anglePerPhotos;
        this.numberOfPhotos = numberOfPhotos;
        this.totalMoveDegrees = totalMoveDegrees;
    }
    @Bindable
    public float getAnglePerPhotos() {
        return anglePerPhotos;
    }
    @Bindable
    public int getNumberOfPhotos() {
        return numberOfPhotos;
    }
    @Bindable
    public int getTotalMoveDegrees() {
        return totalMoveDegrees;
    }
    @Bindable
    public void setAnglePerPhotos(float anglePerPhotos) {
        this.anglePerPhotos = anglePerPhotos;
    }
    @Bindable
    public void setNumberOfPhotos(int numberOfPhotos) {
        this.numberOfPhotos = numberOfPhotos;
    }
    @Bindable
    public void setTotalMoveDegrees(int totalMoveDegrees) {
        this.totalMoveDegrees = totalMoveDegrees;
    }


}
