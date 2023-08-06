package com.example.weatherapp.presentation.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun ImageView.loadImage(image: String) {
    Glide.with(this).load(image).into(this)
}
