package com.example.datanermobile

import android.app.Application
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class DatanerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        // Load CAs from an InputStream
//        // (could be from a resource or ByteArrayInputStream or ...)
//        val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
//        // From https://www.washington.edu/itconnect/security/ca/load-der.crt
//        val caInput: InputStream = BufferedInputStream(FileInputStream("apache-selfsigned.crt"))
//        val ca: X509Certificate = caInput.use {
//            cf.generateCertificate(it) as X509Certificate
//        }
//        println("ca=" + ca.subjectDN)
//
//        // Create a KeyStore containing our trusted CAs
//        val keyStoreType = KeyStore.getDefaultType()
//        val keyStore = KeyStore.getInstance(keyStoreType).apply {
//            load(null, null)
//            setCertificateEntry("ca", ca)
//        }
//
//        // Create a TrustManager that trusts the CAs inputStream our KeyStore
//        val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
//        val tmf: TrustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm).apply {
//            init(keyStore)
//        }
//
//        // Create an SSLContext that uses our TrustManager
//        val context: SSLContext = SSLContext.getInstance("TLS").apply {
//            init(null, tmf.trustManagers, null)
//        }
//
//        // Tell the URLConnection to use a SocketFactory from our SSLContext
//        val url = URL("https://52.4.141.220")
//        val urlConnection = url.openConnection() as HttpsURLConnection
//        urlConnection.sslSocketFactory = context.socketFactory
//        val inputStream: InputStream = urlConnection.inputStream
////        copyInputStreamToOutputStream(inputStream, System.out)
//        inputStream.copyTo(System.out)
    }
}
