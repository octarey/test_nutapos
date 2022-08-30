package com.example.natu_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit private var testLabel : TextView
    lateinit private var invoiceButton : Button
    lateinit private var pajakButton : Button
    lateinit private var ojolButton : Button
    lateinit private var discountButton : Button
    private var transactionHistory : MutableList<String> = mutableListOf<String>()
    private var currentNumber = 1
    lateinit private var lastTimeOrder: LocalDateTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testLabel = findViewById(R.id.test)
        invoiceButton = findViewById(R.id.test_invoice)
        pajakButton = findViewById(R.id.test_pajak)
        ojolButton = findViewById(R.id.test_bagiHasil)
        discountButton = findViewById(R.id.test_discount)
        if (transactionHistory.size == 0 ){
            lastTimeOrder = LocalDateTime.now()
        }
        loadData()

        invoiceButton.setOnClickListener(this)
        pajakButton.setOnClickListener(this)
        ojolButton.setOnClickListener(this)
        discountButton.setOnClickListener(this)

    }

    //jawaban no 4
    private fun numberTransaction(){
        var currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyMMdd")
        val invoice = "UM/" + currentTime.format(formatter) + "/$currentNumber"
        transactionHistory.add(invoice)
        if (lastTimeOrder.format(formatter) == currentTime.format(formatter)) {
            currentNumber += 1
        }else{
            currentNumber = 1
        }
        lastTimeOrder = currentTime
        loadData()
    }

    //jawaban no 8
    private fun hitungPajak(total: Int, tax: Int){
        var tax_value = total*tax/100
        var net_sales = total-tax_value

        testLabel.text = "total pembayaran : $net_sales. \n Pajak yang berlaku  $tax % : $tax_value"

    }

    //jawaban no 9
    private fun discount(total: Int, discounts: List<Int>){
        var tot_discount_value = 0
        for (i in 0 until discounts.size){
            tot_discount_value = tot_discount_value + (discounts[i]*total/100)
        }

        var total_harga = total-tot_discount_value
        testLabel.text = "total diskon : $tot_discount_value \n total pembayaran $total_harga"
    }

    //jawaban no 10
    private fun shareRevenue(total:Int,markUp_percent:Int , share_percent:Int){
        var markup_value = total*markUp_percent/100
        var price = total+markup_value
        var fee_ojol = price*share_percent/100
        var net_resto = price-fee_ojol

        testLabel.text = "untuk resto : $net_resto. \n untuk ojol  : $fee_ojol"
    }

    private fun loadData(){
        testLabel.text = transactionHistory.toString()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.test_invoice -> {
                numberTransaction()
            }
            R.id.test_pajak -> {
                hitungPajak(47000,10)
            }
            R.id.test_bagiHasil -> {
                shareRevenue(35000,10,10)
            }
            R.id.test_discount -> {
                discount(10000, listOf(10,20))
            }
        }
    }
}