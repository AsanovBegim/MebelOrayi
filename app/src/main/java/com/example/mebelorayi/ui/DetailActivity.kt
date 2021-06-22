package com.example.mebelorayi.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mebelorayi.R
import com.example.mebelorayi.data.MenuDB
import com.example.mebelorayi.data.ModelMebel.MagazinMebeli
import com.example.mebelorayi.data.dao.MenuDao
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val MENU_ID = "menuId"
    }

    private lateinit var mMenu: MagazinMebeli
    private var menuId: Int = 0
    private lateinit var dao: MenuDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.hide()
        menuId = intent.getIntExtra(MENU_ID, 0)

        dao = MenuDB.getInstance(this).dao()
        mMenu = dao.getMenuById(menuId)
        tvItemProductDetailName.text = mMenu.nameRus
        tvItemProductDetailCost.text = "${mMenu.cost} сум"
        tvDetails.text = mMenu.details

        //item picture
        val imageResName = "pictures${menuId}"
        Glide
            .with(this)
            .load(
                resources
                    .getIdentifier(imageResName, "drawable", packageName)
            )
            .into(ivItemDetailPicture)
        setTitle()
        btnOrder.setOnClickListener {
            setOrder()
            setTitle()
        }

        ivFinish.setOnClickListener {
            finish()
        }
        btnQuantity.setOnValueChangeListener { view, oldValue, newValue ->
            mMenu.quantity = newValue.toString()
        }

    }

    private fun setOrder() {
        if (mMenu.isOrdered == null) {
            mMenu.isOrdered = 1
            Toast.makeText(
                this,
                "Вы заказали ${mMenu.nameRus}",
                Toast.LENGTH_SHORT
            )
                .show()
            dao.updateMenu(mMenu)
        } else {
            mMenu.isOrdered = 1 - mMenu.isOrdered!!
            Toast.makeText(this, "Вы отменили заказ ${mMenu.nameRus}", Toast.LENGTH_SHORT).show()
            setTitle()
            dao.updateMenu(mMenu)
        }
    }

    private fun setTitle() {
        if (mMenu.isOrdered == 1) {
            btnOrder.text = "Вы уже заказали"
            btnOrder.setBackgroundResource(R.drawable.back_reorder)
        } else {
            btnOrder.text = "Заказать"
            btnOrder.setBackgroundResource(R.drawable.back_cost)
        }
    }

}