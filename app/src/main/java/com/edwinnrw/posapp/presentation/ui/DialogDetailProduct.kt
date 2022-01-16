package com.edwinnrw.posapp.presentation.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.edwinnrw.posapp.databinding.DialogDetailProductBinding
import com.edwinnrw.posapp.domain.entity.ProductsEntity
import com.edwinnrw.posapp.util.Constant.MODIFIER_PRODUCT
import com.edwinnrw.posapp.util.Constant.NOTES
import com.edwinnrw.posapp.util.Constant.PRICE
import com.edwinnrw.posapp.util.Constant.PRODUCT_NAME
import com.edwinnrw.posapp.util.Constant.QUANTITY
import com.edwinnrw.posapp.util.Constant.TAX

interface DialogAction{
    fun clickAddAction(productSelect:HashMap<String,String>)

}

class DialogDetailProduct(private val product: ProductsEntity) : DialogFragment() {

    lateinit var binding:DialogDetailProductBinding

    lateinit var dialogAction: DialogAction

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDetailProductBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvProductName.text = product.name
        binding.ivClose.setOnClickListener {
            dismiss()
        }
        binding.ibAdd.setOnClickListener {
            val currentQuantity =  binding.etQuantity.text.toString().toInt()
            val newQuantity = currentQuantity+1
            binding.etQuantity.setText(newQuantity.toString())
            binding.btnAddProduct.text = "Add Product"

        }
        binding.ibMinus.setOnClickListener {
            val currentQuantity =  binding.etQuantity.text.toString().toInt()
            val newQuantity = currentQuantity-1
            if (newQuantity > 0){
                binding.etQuantity.setText(newQuantity.toString())
                binding.btnAddProduct.text = "Add Product"

            }else{
                binding.etQuantity.setText("1")
            }

        }
        binding.rbHalf.setOnCheckedChangeListener { compoundButton, isChaecked ->
            binding.rbWhole.isChecked = !isChaecked
        }
        binding.rbWhole.setOnCheckedChangeListener { compoundButton, isChaecked ->
            binding.rbHalf.isChecked = !isChaecked
        }
        binding.btnAddProduct.setOnClickListener {
            dismiss()
            val productSelect = HashMap<String,String>()
            productSelect[PRODUCT_NAME] = product.name
            productSelect[PRICE] = product.price
            productSelect[QUANTITY] = binding.etQuantity.text.toString()

            when{
                binding.rbHalf.isChecked ->{
                    productSelect[MODIFIER_PRODUCT] = "Half"

                }
                binding.rbWhole.isChecked ->{
                    productSelect[MODIFIER_PRODUCT] = "Whole"
                    productSelect[PRICE] = (product.price.toInt()+20000).toString()


                }
                else ->{
                    productSelect[MODIFIER_PRODUCT] = "-"

                }
            }


            when {
                binding.scOnOffGst.isChecked -> {
                    productSelect[TAX] = "0.1"

                }
                binding.scOnOffServiceCharge.isChecked -> {

                    productSelect[TAX] = "0.05"

                }
                else -> {
                    productSelect[TAX] = "0.0"

                }
            }
            productSelect[NOTES] = binding.etAdditionalNotes.text.toString()

            dialogAction.clickAddAction(productSelect)

        }
    }

    fun clickDeleteOrAddAction(dialogAction: DialogAction){
        this.dialogAction = dialogAction
    }
}