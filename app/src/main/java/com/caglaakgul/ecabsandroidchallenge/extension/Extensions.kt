package com.caglaakgul.ecabsandroidchallenge.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import coil.load
import com.caglaakgul.ecabsandroidchallenge.R
import com.caglaakgul.ecabsandroidchallenge.ui.helper.SafeClickListener
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*

fun Boolean?.ifTrue(block: () -> Unit) {
    if (this == true) {
        block()
    }
}

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>?, observer: (T) -> Unit) {
    liveData?.observe(this, Observer(observer))
}

@BindingAdapter("loadImageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        view.load(it)
    }
}

fun MaterialCardView.setSequentialBackgroundColor(position: Int, context: Context) {
    val colors = listOf(
        ContextCompat.getColor(context, R.color.light_pink),
        ContextCompat.getColor(context, R.color.blue),
        ContextCompat.getColor(context, R.color.light_purple),
        ContextCompat.getColor(context, R.color.pink),
        ContextCompat.getColor(context, R.color.light_blue),
        ContextCompat.getColor(context, R.color.purple),
    )
    val index = position % colors.size
    setCardBackgroundColor(colors[index])
}

fun View.safeClickListener(
    onSafeClick: (View) -> Unit
) {
    setOnClickListener(SafeClickListener { v ->
        onSafeClick(v)
    })
}

fun Fragment.navigateSafe(direction: NavDirections) {
    findNavController().navigateSafe(direction)
}

fun NavController.navigateSafe(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

fun String.splitStringBySlash(): Pair<String, String> {
    val parts = this.split('/')
    val beforeSlash = parts.first()
    val afterSlash = parts.last()
    return Pair(beforeSlash, afterSlash)
}

fun String.formatDateTime(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date: Date? = inputFormat.parse(this)
    return date?.let { outputFormat.format(it) } ?: ""
}