package com.edwinnrw.posapp.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinnrw.posapp.databinding.ActivityMainBinding
import com.edwinnrw.posapp.presentation.state.ProductsState
import com.edwinnrw.posapp.presentation.ui.adapter.ProductAdapter
import com.edwinnrw.posapp.presentation.ui.adapter.ProductSelectedAdapter
import com.edwinnrw.posapp.presentation.viewModel.ProductViewModel
import com.edwinnrw.posapp.util.Constant.MODIFIER_PRODUCT
import com.edwinnrw.posapp.util.Constant.PRICE
import com.edwinnrw.posapp.util.Constant.TAX
import com.edwinnrw.posapp.util.numberFormat
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.io.Serializable
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var viewModel: ProductViewModel

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    var totalCharge = 0.0
    var totalTax = 0.0
    var subTotal = 0.0

    lateinit var binding: ActivityMainBinding

    lateinit var productSelected:MutableList<HashMap<String,String>>

    lateinit var productSelectAdapter:ProductSelectedAdapter

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productSelected = mutableListOf()
        productSelectAdapter = ProductSelectedAdapter(productSelected)
        binding.rvProductSelect.apply {
            adapter = productSelectAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        binding.rvProductSelect
        viewModel.fetchProducts()
        viewModel.productState.observe(this,::manageResponseGetProducts)
        binding.tvService.setOnClickListener {
            val currentService= binding.tvService.text.toString()
            if (currentService.equals("Have here", ignoreCase = true)){
                binding.tvService.text = "Delivery"
            }else{
                binding.tvService.text = "Have here"

            }
        }
        binding.btnCharge.setOnClickListener {
            if (totalCharge != 0.0){
                Intent(this,PaymentActivity::class.java).apply {
                    putExtra("totalCharge",totalCharge.toString())
                    putExtra("subTotal",subTotal.toString())
                    putExtra("totalTax",totalTax.toString())
                    putExtra("products",productSelected as Serializable)
                    startActivity(this)
                }
            }
        }

    }

    private fun manageResponseGetProducts(result:ProductsState){
        when(result){
            is ProductsState.Loading->{
                binding.pbProgressGetProduct.visibility = View.VISIBLE
                binding.rvProducts.visibility = View.GONE


            }
            is ProductsState.HideLoading->{
                binding.pbProgressGetProduct.visibility = View.GONE
                binding.rvProducts.visibility = View.VISIBLE


            }
            is ProductsState.Success->{

                val productAdapter = ProductAdapter(result.data.toMutableList(), onClick = {
                    val dialogDetailProduct = DialogDetailProduct(it)
                    dialogDetailProduct.show(supportFragmentManager,"Detail")
                    dialogDetailProduct.clickDeleteOrAddAction(object : DialogAction{

                        override fun clickAddAction(product: HashMap<String,String>) {
                            productSelected.add(product)
                            productSelectAdapter.notifyDataSetChanged()
                            binding.tvTotalAddProduct.text = "${productSelected.size} Products"
                            sumTotal((product[TAX]?:"0").toDouble())
                            binding.vLine.visibility = View.VISIBLE

                        }



                    })
                })
                binding.rvProducts.apply {
                    adapter = productAdapter
                    layoutManager = LinearLayoutManager(this@MainActivity)
                }



            }
            is ProductsState.Error->{
                Snackbar.make(binding.clProducts,result.message?:"",Snackbar.LENGTH_LONG).show()
            }
        }
    }

    fun sumTotal(tax:Double){
        for (item in productSelected){
            subTotal+=item[PRICE]?.toInt()?:0

        }
        totalTax = tax*subTotal
        binding.tvTaxes.text = "Rp${numberFormat(totalTax.toString())}"
        binding.tvSubTotal.text = "Rp${numberFormat((subTotal).toString())}"

        totalCharge = subTotal+totalTax
        binding.btnCharge.text = "Charge Rp${numberFormat((totalCharge.toString()))}"


    }



}