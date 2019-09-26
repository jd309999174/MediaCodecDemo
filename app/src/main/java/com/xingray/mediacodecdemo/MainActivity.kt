package com.xingray.mediacodecdemo

import android.media.MediaFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "test"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvStart.setOnClickListener {
            decode(File(cacheDir, "/in.mp3"), File(cacheDir, "/out.aac"))
        }
    }

    fun decode(inputFile: File, outputFile: File) {
        val audioCodec = AudioCodec.newInstance()
        audioCodec.setEncodeType(MediaFormat.MIMETYPE_AUDIO_AAC)
        audioCodec.setIOPath(inputFile.absolutePath, outputFile.absolutePath)
        audioCodec.prepare()
        audioCodec.setOnCompleteListener { audioCodec.release() }
        audioCodec.startAsync()
    }
}
