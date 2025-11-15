package com.histora.app

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.histora.app.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("histora_settings", MODE_PRIVATE)

        setupToolbar()
        loadSettings()
        setupListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadSettings() {
        // Load language setting
        val language = prefs.getString("language", "zh")
        when (language) {
            "zh" -> binding.radioChinese.isChecked = true
            "en" -> binding.radioEnglish.isChecked = true
        }

        // Load narration style
        val narrationStyle = prefs.getString("narration_style", "academic")
        when (narrationStyle) {
            "academic" -> binding.radioAcademic.isChecked = true
            "story" -> binding.radioStory.isChecked = true
            "concise" -> binding.radioConcise.isChecked = true
        }

        // Load notification settings
        binding.notificationsSwitch.isChecked = prefs.getBoolean("notifications_enabled", true)
        binding.eventUpdatesSwitch.isChecked = prefs.getBoolean("event_updates_enabled", true)
    }

    private fun setupListeners() {
        // Language selection
        binding.languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val language = when (checkedId) {
                R.id.radioChinese -> "zh"
                R.id.radioEnglish -> "en"
                else -> "zh"
            }
            prefs.edit().putString("language", language).apply()
            Toast.makeText(this, "语言设置已保存", Toast.LENGTH_SHORT).show()
        }

        // Narration style selection
        binding.narrationStyleRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val style = when (checkedId) {
                R.id.radioAcademic -> "academic"
                R.id.radioStory -> "story"
                R.id.radioConcise -> "concise"
                else -> "academic"
            }
            prefs.edit().putString("narration_style", style).apply()
            Toast.makeText(this, "讲解风格已保存", Toast.LENGTH_SHORT).show()
        }

        // Notification switches
        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notifications_enabled", isChecked).apply()
        }

        binding.eventUpdatesSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("event_updates_enabled", isChecked).apply()
        }
    }
}
