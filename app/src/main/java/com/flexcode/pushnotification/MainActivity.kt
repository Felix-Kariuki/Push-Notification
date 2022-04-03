package com.flexcode.pushnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.flexcode.pushnotification.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDonate.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(this,"Fields Empty",Toast.LENGTH_SHORT).show()
            }

            val userDataMap = HashMap<String, String>()
            userDataMap["name"] = name
            userDataMap["amount"] = amount

            FirebaseDatabase.getInstance().getReference("donation").push().setValue(userDataMap)

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()

        }

    }
}