package com.manyan.anime.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.manyan.anime.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private var _binding : FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLanguageSetting.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        binding.tvApiSetting.setOnClickListener {
            val url = "https://kitsu.docs.apiary.io/"
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }

        binding.tvGithub.setOnClickListener{
            val url = "https://github.com/ardianjafar46/"
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }

        if (binding.tvGithub.isClickable) {
            Toast.makeText(context, "Update Soon", Toast.LENGTH_SHORT).show()
        }
    }

}