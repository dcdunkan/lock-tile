package dev.dcdunkan.locktile

import android.content.Intent
import android.service.quicksettings.TileService
import android.widget.Toast

class LockScreenTileService : TileService() {
    override fun onClick() {
        super.onClick()
        val service = CustomAccessibilityService.getInstance()
        if (service != null) {
            service.lockScreen()
        } else {
            Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_LONG).show()
            startService(Intent(this, CustomAccessibilityService::class.java))
        }
    }
}