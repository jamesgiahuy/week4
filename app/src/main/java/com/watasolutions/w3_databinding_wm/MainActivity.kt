package com.watasolutions.w3_databinding_wm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.watasolutions.w3_databinding_wm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var  layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecycleAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            viewModel.checkEmailAndPassword(email, password)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
           startActivity(intent);
        }
        listenerSuccessEvent()
        listenerErrorEvent()
    }



    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(this) { isSuccess ->
            if (isSuccess) {
                val email = binding.edtEmail.text.toString().trim()
                val password = binding.edtPassword.text.toString().trim()
                val intent = Intent(this, RestaurantActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(Constants.KEY_USER, Student(email, password))
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }
}