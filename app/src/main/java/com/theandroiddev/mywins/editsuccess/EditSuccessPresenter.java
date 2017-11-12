package com.theandroiddev.mywins.editsuccess;

import android.content.Context;

import com.theandroiddev.mywins.MyWinsApplication;
import com.theandroiddev.mywins.data.models.Success;
import com.theandroiddev.mywins.data.models.SuccessImage;
import com.theandroiddev.mywins.data.repositories.SuccessesRepository;

import java.util.ArrayList;

/**
 * Created by jakub on 12.11.17.
 */

public class EditSuccessPresenter implements EditSuccessContract.Presenter {

    private EditSuccessContract.View view;
    private SuccessesRepository repository;

    public EditSuccessPresenter(Context context) {
        ((MyWinsApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(EditSuccessContract.View view) {

        this.view = view;
    }

    @Override
    public void setRepository(SuccessesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public void editSuccess(Success editSuccess, ArrayList<SuccessImage> successImageList) {
        repository.editSuccess(editSuccess);
        repository.editSuccessImages(successImageList, editSuccess.getId());

        view.displaySlider();
    }
}
