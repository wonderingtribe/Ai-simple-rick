package com.simplerick.ai

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PERMISSION_CODE = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btn_start_overlay)

        btnStart.setOnClickListener {
            if (canDrawOverlays()) {
                startService(Intent(this, FloatingAssistantService::class.java))
                finish()
            } else {
                requestOverlayPermission()
            }
        }
    }

    private fun canDrawOverlays(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(this)
        } else true
    }

    private fun requestOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, PERMISSION_CODE)
        }
    }

    override fun onActivityResult(req: Int, res: Int, data: Intent?) {
        super.onActivityResult(req, res, data)
        if (req == PERMISSION_CODE) {
            if (canDrawOverlays()) {
                startService(Intent(this, FloatingAssistantService::class.java))
                finish()
            } else {
                Toast.makeText(this, "Overlay permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
