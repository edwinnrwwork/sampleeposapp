package com.edwinnrw.posapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.edwinnrw.posapp.R
import com.edwinnrw.posapp.databinding.ActivityPaymentBinding
import com.edwinnrw.posapp.util.digitsOnly
import com.edwinnrw.posapp.util.numberFormat
import java.io.Serializable

class PaymentActivity : AppCompatActivity() {

    lateinit var binding:ActivityPaymentBinding
    lateinit var totalCharge:String
    lateinit var totalTax:String
    lateinit var subTotal:String
    lateinit var products:MutableList<HashMap<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        totalCharge = intent.getStringExtra("totalCharge")?:"0"
        totalTax = intent.getStringExtra("totalTax")?:"0"
        subTotal = intent.getStringExtra("subTotal")?:"0"
        products = intent.getSerializableExtra("products") as MutableList<HashMap<String, String>>
        binding.tvTotalCharge.text = "Rp${numberFormat(totalCharge)}"
        binding.tvAmountActual.text = "Rp${numberFormat(totalCharge)}"
        val amount1 = totalCharge.toDouble()+5000
        binding.tvAmount1.text = "Rp${numberFormat((amount1.toString()))}"
        val amount2 = totalCharge.toDouble()+10000
        binding.tvAmount2.text = "Rp${numberFormat((amount2.toString()))}"
        val amount3 = totalCharge.toDouble()+15000
        binding.tvAmount3.text = "Rp${numberFormat((amount3.toString()))}"

        binding.clBankTransfer.setOnClickListener {
            goToReceipt()
        }
        binding.clDebitAndCredit.setOnClickListener {
            goToReceipt()
        }
        binding.clQris.setOnClickListener {
            goToReceipt()
        }

        binding.tvAmount1.setOnClickListener {
            goToReceipt(amount1.toString())

        }
        binding.tvAmount2.setOnClickListener {
            goToReceipt(amount2.toString())

        }
        binding.tvAmount3.setOnClickListener {
            goToReceipt(amount3.toString())

        }
        binding.tvAmountActual.setOnClickListener {
            goToReceipt()

        }
        binding.btnSubmit.setOnClickListener {
            goToReceipt(binding.etCustomAmount.text.toString())
        }
        binding.tvCustomAmount.setOnClickListener {
            binding.clGroup.visibility = View.VISIBLE

        }
    }


    fun goToReceipt(newAmount:String=totalCharge){
        val changes = newAmount.toDouble()-totalCharge.toDouble()
        Intent(this,ReceiptActivity::class.java).apply {
            putExtra("changes",changes)
            putExtra("totalCharge",totalCharge)
            putExtra("subTotal", subTotal)
            putExtra("totalTax", totalTax)
            putExtra("products",products as Serializable)
            startActivity(this)
        }
    }


}