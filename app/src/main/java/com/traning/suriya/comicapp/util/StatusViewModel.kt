package com.traning.suriya.comicapp.util

import android.arch.lifecycle.LiveData

interface StatusViewModel<STATUS : StatusViewModel.Status<VDB>, out VDB> {
    val status: LiveData<STATUS>

    val currentValue: STATUS
        get() = status.value!!

    abstract class Status<out VDB>(val viewDataBundle: VDB)
}