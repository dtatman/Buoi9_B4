package com.example.buoi9_b4
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchDarkMode: Switch
    private lateinit var seekBarTextSize: SeekBar
    private lateinit var radioGroupColor: RadioGroup
    private lateinit var tvSampleText: TextView

    private val sharedPrefs: SharedPreferences by lazy {
        getSharedPreferences("app_preferences", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Khởi tạo các phần tử UI
        switchDarkMode = findViewById(R.id.switchDarkMode)
        seekBarTextSize = findViewById(R.id.seekBarTextSize)
        radioGroupColor = findViewById(R.id.radioGroupColor)
        tvSampleText = findViewById(R.id.tvSettings)

        // Lấy dữ liệu từ SharedPreferences nếu có
        loadSettings()

        // Cập nhật chế độ tối khi chuyển Switch
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Lưu lựa chọn chế độ tối vào SharedPreferences
            sharedPrefs.edit().putBoolean("dark_mode", isChecked).apply()
        }

        // Cập nhật kích thước chữ khi thay đổi SeekBar
        seekBarTextSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvSampleText.textSize = (progress + 10).toFloat() // Điều chỉnh kích thước chữ
                // Lưu kích thước chữ vào SharedPreferences
                sharedPrefs.edit().putInt("text_size", progress + 10).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Cập nhật màu chữ khi chọn màu
        radioGroupColor.setOnCheckedChangeListener { _, checkedId ->
            val color = when (checkedId) {
                R.id.radioRed -> getColor(android.R.color.holo_red_light)
                R.id.radioGreen -> getColor(android.R.color.holo_green_light)
                R.id.radioBlue -> getColor(android.R.color.holo_blue_light)
                else -> getColor(android.R.color.black)
            }
            tvSampleText.setTextColor(color)
            // Lưu màu sắc vào SharedPreferences
            sharedPrefs.edit().putInt("text_color", color).apply()
        }
    }

    // Lấy cài đặt từ SharedPreferences khi ứng dụng khởi động
    private fun loadSettings() {
        val darkMode = sharedPrefs.getBoolean("dark_mode", false)
        val textSize = sharedPrefs.getInt("text_size", 16)
        val textColor = sharedPrefs.getInt("text_color", getColor(android.R.color.black))

        // Áp dụng chế độ tối
        switchDarkMode.isChecked = darkMode
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Áp dụng kích thước chữ
        seekBarTextSize.progress = textSize - 10
        tvSampleText.textSize = textSize.toFloat()

        // Áp dụng màu chữ
        tvSampleText.setTextColor(textColor)
    }
}
