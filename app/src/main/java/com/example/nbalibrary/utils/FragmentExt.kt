package com.example.nbalibrary.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>): T {
    return ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory()).get(viewModelClass)
}
