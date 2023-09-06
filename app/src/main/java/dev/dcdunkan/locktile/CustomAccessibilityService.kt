package dev.dcdunkan.locktile

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import java.lang.ref.WeakReference

class CustomAccessibilityService : AccessibilityService() {
    private var info: AccessibilityServiceInfo = AccessibilityServiceInfo()
    override fun onInterrupt() {}
    override fun onAccessibilityEvent(p0: AccessibilityEvent?) {}
    override fun onServiceConnected() {
        instance = WeakReference(this)
        info.apply {
            eventTypes =
                AccessibilityEvent.TYPE_VIEW_CLICKED or AccessibilityEvent.TYPE_VIEW_FOCUSED
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            notificationTimeout = 100
        }
        this.serviceInfo = info
    }
    override fun onUnbind(intent: Intent?): Boolean {
        instance = WeakReference(null)
        return super.onUnbind(intent)
    }

    fun lockScreen(): Boolean = performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)

    companion object {
        private var instance: WeakReference<CustomAccessibilityService> = WeakReference(null)
        fun getInstance(): CustomAccessibilityService? {
            return instance.get()
        }
    }
}