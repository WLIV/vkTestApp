package com.example.vktestapp.fragmentsUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.vktestapp.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater)

        val webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                view.loadUrl(url)
                //Я еще получение токена переделаю, тут так пока что чтобы просто посмотреть как работает апи в приложении
                if(url.contains("/blank.html")){
                    println(url)
                    val apiKey = getApiKeyFromUrl(url)
                    val id = getIdFromUrl(url)

                    val transition = AuthFragmentDirections.actionAuthFragmentToListFragment(apiKey, id)
                    view.findNavController().navigate(transition)
                }
                return true
            }
        }

        binding.authWebView.apply {
            loadUrl("https://oauth.vk.com/authorize?client_id=8110024&response_type=token")
            canGoForward()
            setWebViewClient(webViewClient)
        }


        return binding.root
    }

    private fun getIdFromUrl(url: String): String {
        var idString = url.removeRange(0,url.indexOf("id"))
        return idString.removePrefix("id=")
    }

    private fun getApiKeyFromUrl(url: String) : String{
        var index = url.indexOf("access_token")
        var apiKey = url.removeRange(0, index)
        apiKey = apiKey.removePrefix("access_token=")
        apiKey = apiKey.removeRange(apiKey.indexOf("expires"), apiKey.length)
        return apiKey.removeSuffix("&")

    }

    companion object { fun newInstance() = AuthFragment }
    }
