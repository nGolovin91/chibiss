package com.yoite.chibissapp.ui.hits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import com.yoite.chibissapp.R
import com.yoite.chibissapp.ui.hits.LogoDialog.Companion.IMAGE_URL


fun getLogoDialogInstance(url: String) =
    LogoDialog().also {
        val bundle = Bundle()
        bundle.putString(IMAGE_URL, url)
        it.arguments = bundle
    }

class LogoDialog: DialogFragment() {

    companion object {
        const val IMAGE_URL = "LogoDialog.ImageArgument"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.logo_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logo = view.findViewById<ImageView>(R.id.hits_big_logo)
        val url = arguments?.getString(IMAGE_URL, "")
        Picasso.get().load(url)
            .fit()
            .into(logo)
    }
}