package com.example.countries.extencions

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.GenericRequestBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.caverock.androidsvg.SVG
import java.io.InputStream


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.setUrlImage(url: String) {
    val requestBuilder: GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> =
        Glide.with(context)
            .using(Glide.buildStreamModelLoader(Uri::class.java, context), InputStream::class.java)
            .from(Uri::class.java)
            .`as`(SVG::class.java)
            .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
            .sourceEncoder(StreamEncoder())
            .cacheDecoder(FileToStreamDecoder(SvgDecoder()))
            .decoder(SvgDecoder())
            .listener(SvgSoftwareLayerSetter())

    requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
        .load(Uri.parse(url))
        .into(this)
}