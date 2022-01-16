package com.edwinnrw.posapp.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.edwinnrw.posapp.R
import com.edwinnrw.posapp.databinding.ActivityReceiptBinding
import com.edwinnrw.posapp.util.Constant.MODIFIER_PRODUCT
import com.edwinnrw.posapp.util.Constant.PRICE
import com.edwinnrw.posapp.util.Constant.PRODUCT_NAME
import com.edwinnrw.posapp.util.Constant.QUANTITY
import com.edwinnrw.posapp.util.numberFormat

class ReceiptActivity : AppCompatActivity() {

    lateinit var binding: ActivityReceiptBinding

    lateinit var totalCharge:String
    lateinit var totalTax:String
    lateinit var subTotal:String
    lateinit var products:MutableList<HashMap<String, String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val changes = intent.getDoubleExtra("changes",0.0)

        totalCharge = intent.getStringExtra("totalCharge")?:"0"
        totalTax = intent.getStringExtra("totalTax")?:"0"
        subTotal = intent.getStringExtra("subTotal")?:"0"
        products = intent.getSerializableExtra("products") as MutableList<HashMap<String, String>>

        if (changes != 0.0){
            binding.tvTotalChanges.text = "Rp${numberFormat(changes.toString())} Changes"
        }

        binding.tvTotalCharge.text = "Out of Rp${numberFormat(totalCharge)}"
        binding.btnWhatsapp.setOnClickListener {
            if (!binding.etEmail.text.toString().isEmpty()){
                val uri = "https://api.whatsapp.com/send?phone=${binding.etWhatsApp.text}&text=${createReceipt()}"
                val sendIntent = Intent(Intent.ACTION_VIEW)
                sendIntent.data = Uri.parse(uri)
                startActivity(sendIntent)
            }else{
                Toast.makeText(this,"Anda belum mengisi alamat email",Toast.LENGTH_LONG).show()

            }

        }
        binding.btnSendByEmail.setOnClickListener {
            if (binding.etWhatsApp.text.toString().isEmpty()){
                val uri = Uri.parse("mailto:${binding.etEmail.text}")
                    .buildUpon()
                    .appendQueryParameter("subject", "Receipt")
                    .appendQueryParameter("body", createReceipt())
                    .build()
                val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(Intent.createChooser(emailIntent, "Send Receipt"))
            }else{
                Toast.makeText(this,"Anda belum mengisi nomor whatsapp",Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_sale, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menuNewSale->{
                Intent(this,MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

                    startActivity(this)
                }
                true
            }
            else->{

                super.onOptionsItemSelected(item)
            }
        }
    }


    private fun createReceipt() : String{
        var receiptText = ""
        for (item in products){
            receiptText+= "${item[PRODUCT_NAME]} (${item[QUANTITY]}x) - ${item[MODIFIER_PRODUCT]}  \nRp${numberFormat(item[PRICE]?:"0")}\n\n"
        }
        receiptText+="SubTotal: Rp${numberFormat(subTotal)}\nTax: Rp${numberFormat(totalTax)}\n" +
                "Total: Rp${numberFormat(totalCharge)}"

        return receiptText

    }

    override fun onBackPressed() {

    }
}