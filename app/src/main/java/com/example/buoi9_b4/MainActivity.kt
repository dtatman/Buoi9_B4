package com.example.buoi9_b4
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thiết lập Toolbar cho Activity
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    // Tạo menu khi ứng dụng bắt đầu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    // Xử lý sự kiện khi chọn một mục trong menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                // Xử lý chức năng "Tìm kiếm"
                Toast.makeText(this, "Chức năng Tìm kiếm đang được thực thi", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_settings -> {
                // Xử lý chức năng "Cài đặt"
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_share -> {
                // Xử lý chức năng "Chia sẻ"
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Chia sẻ nội dung từ ứng dụng của tôi!")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Chia sẻ qua"))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
