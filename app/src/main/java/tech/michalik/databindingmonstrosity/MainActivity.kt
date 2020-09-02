package tech.michalik.databindingmonstrosity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.michalik.databindingmonstrosity.databinding.ListItemBindingImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}