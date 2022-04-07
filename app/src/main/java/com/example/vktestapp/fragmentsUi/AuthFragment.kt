package com.example.vktestapp.fragmentsUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.vktestapp.R
import com.example.vktestapp.databinding.FragmentAuthBinding
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope


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

        val authLauncher = requireActivity().let {
            VK.login(it) { result : VKAuthenticationResult ->
                when (result) {
                    is VKAuthenticationResult.Success -> {
                        val apiKey = result.token.accessToken
                        val transition = AuthFragmentDirections.actionAuthFragmentToListFragment(
                            apiKey,
                            requireContext().resources.getString(R.string.app_id)
                        )
                        binding.root.findNavController().navigate(transition)
                    }
                    is VKAuthenticationResult.Failed -> {
                        // User didn't pass authorization
                    }
                }
            }
        }

        authLauncher.launch(arrayListOf(VKScope.FRIENDS))


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
