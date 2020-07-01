package com.example.datanermobile.appdynamics

import com.appdynamics.eumagent.runtime.HttpRequestTracker
import com.appdynamics.eumagent.runtime.Instrumentation
import java.net.URL

class AppDynamics {
    fun sendRequest(statusCode: Int, url: String) {
        val tracker: HttpRequestTracker = Instrumentation.beginHttpRequest(URL(url))
        tracker.withResponseCode(statusCode)
            .reportDone()
    }
}
