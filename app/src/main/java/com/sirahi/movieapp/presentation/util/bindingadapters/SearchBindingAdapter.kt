package com.sirahi.movieapp.presentation.util.bindingadapters

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.sirahi.movieapp.presentation.MenuViewModel

@BindingAdapter("addOnTextChanged")
fun addOnTextChanged(editText: EditText,viewModel:MenuViewModel){
    editText.doOnTextChanged { text,_,_,_ ->
        viewModel.onSearch(text.toString())
    }
}