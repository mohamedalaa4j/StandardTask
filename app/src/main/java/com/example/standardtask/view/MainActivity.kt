package com.example.standardtask.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.standardtask.R
import com.example.standardtask.databinding.ActivityMainBinding
import com.example.standardtask.model.MainSliderImagesModel
import com.example.standardtask.utilities.ScreenState
import com.example.standardtask.viewModel.MainActivityVM
import com.google.android.material.snackbar.Snackbar
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null

    private val viewModel : MainActivityVM by lazy{
        ViewModelProvider(this)[MainActivityVM::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        handleSSLHandshake()

        viewModel.getSliderImages()

        viewModel.postMutableLiveData.observe(this) {
            processApiResponse(it)
        }


    }//onCreate/////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun processApiResponse(screenState: ScreenState<MainSliderImagesModel>){
        when(screenState){
            is ScreenState.Loading -> {
               // showProgressDialog()
              //  Snackbar.make(binding?.root!!, "screenState.message", Snackbar.LENGTH_SHORT).show()

            }
            is ScreenState.Success ->{
                if (screenState.data != null) {
                    Snackbar.make(binding?.root!!, "success", Snackbar.LENGTH_SHORT).show()
                }
              //  cancelProgressDialog()
            }
            is ScreenState.Error ->{
               // cancelProgressDialog()
                Snackbar.make(binding?.root!!, screenState.message!!, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // Fix handleSSLHandshake exception
    @SuppressLint("TrulyRandom")
    fun handleSSLHandshake() {
        try {
            val trustAllCerts: Array<TrustManager> =
                arrayOf<TrustManager>(object : X509TrustManager {
                    val acceptedIssuers: Array<Any?>?
                        get() = arrayOfNulls(0)

                    override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
                    override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        TODO("Not yet implemented")
                    }
                })
            val sc: SSLContext = SSLContext.getInstance("SSL")
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
            HttpsURLConnection.setDefaultHostnameVerifier(object : HostnameVerifier {
                override fun verify(arg0: String?, arg1: SSLSession?): Boolean {
                    return true
                }
            })
        } catch (ignored: Exception) {
        }
    }

}