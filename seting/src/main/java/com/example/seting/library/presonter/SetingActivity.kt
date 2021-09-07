package com.example.seting.library.presonter

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.mylibrary.arouter.ARouterPath
import com.example.mylibrary.frame.mvp.presenter.ActivityPresenter
import com.example.seting.R
import com.example.seting.library.model.SetingModel
import com.example.seting.library.view.SetingView

class SetingActivity : ActivityPresenter<SetingModel, SetingView>() {

    override fun getRootModelClass(): Class<SetingModel> {

        return SetingModel::class.java
    }

    override fun getRootViewClass(): Class<SetingView> {
       return SetingView::class.java
    }

    override fun bindActivity(): Activity {
      return this
    }

    override fun inCreate(savedInstanceState: Bundle?) {



    }
}