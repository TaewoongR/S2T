package com.ryu.s2t.feature.record

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import java.text.SimpleDateFormat

fun saveTextFileToMediaStore(context: Context, text: String, isComplete: (Boolean) -> Unit) {
    val values = ContentValues().apply {
        put(MediaStore.Files.FileColumns.DISPLAY_NAME, "${SimpleDateFormat("yyyy_MM_dd_HH:mm:ss", java.util.Locale.getDefault()).format(System.currentTimeMillis())}.txt")
        put(MediaStore.Files.FileColumns.MIME_TYPE, "text/plain")
        put(
            MediaStore.Files.FileColumns.RELATIVE_PATH,
            Environment.DIRECTORY_DOCUMENTS
        )
    }

    val resolver = context.contentResolver
    val uri = resolver.insert(
        MediaStore.Files.getContentUri("external"),
        values
    )

    uri?.let {
        resolver.openOutputStream(it)?.use { outputStream ->
            outputStream.write(text.chunked(30).joinToString("\n").toByteArray(Charsets.UTF_8))
        }
        isComplete(true)
    } ?: run {
        isComplete(false)
    }
}
