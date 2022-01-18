package com.sirahi.movieapp.view.fragment.util

import androidx.core.os.bundleOf
import androidx.navigation.NavController


fun <T> navigateTo(navController: NavController?, id:Int, firstBundle:String, secondBundle:T){
    val bundle = bundleOf(firstBundle to secondBundle)
    navController?.navigate(id, bundle)
}