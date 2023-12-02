package com.dk.boruto.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.dk.boruto.util.Constants.DARK_VIBRANT
import com.dk.boruto.util.Constants.ON_DARK_VIBRANT
import com.dk.boruto.util.Constants.VIBRANT

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val imageResult = loader.execute(request)

        return if(imageResult is SuccessResult){
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColoursFromBitmap(
        bitmap: Bitmap
    ): Map<String, String> {
        return mapOf(
            VIBRANT to parseColourSwatch(colour = Palette.from(bitmap).generate().vibrantSwatch),
            DARK_VIBRANT to parseColourSwatch(colour = Palette.from(bitmap).generate().darkVibrantSwatch),
            ON_DARK_VIBRANT to parseBodyColor(colour = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor)
        )
    }

    private fun parseColourSwatch(
        colour: Palette.Swatch?
    ): String {
        return if(colour != null){
            val parsedColour = Integer.toHexString(colour.rgb)
            return "#$parsedColour"
        } else {
            "#000000"
        }
    }

    private fun parseBodyColor(
        colour: Int?
    ): String {
        return if(colour != null){
            val parsedColour = Integer.toHexString(colour)
            return "#$parsedColour"
        } else {
            "#FFFFFF"
        }
    }


}