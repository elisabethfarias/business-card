package br.com.dio.businesscard.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import br.com.dio.businesscard.App
import br.com.dio.businesscard.R
import br.com.dio.businesscard.data.BusinessCard
import br.com.dio.businesscard.databinding.ActivityAddBusinessCardBinding
import br.com.dio.businesscard.util.Colors

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        val items = listOf(Colors.BLUE, Colors.GREEN, Colors.RED)
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        (binding.autoComplete as? AutoCompleteTextView)?.setAdapter(adapter)



        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            val color = binding.autoComplete.text
            val colorEnum = Colors.valueOf(color.toString()).rgb
            Log.e("color:", color.toString())
            Log.e("colorEnum:", colorEnum.toString())


            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                company = binding.tilCompanyName.editText?.text.toString(),
                phone = binding.tilPhone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                customBackground = colorEnum.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}