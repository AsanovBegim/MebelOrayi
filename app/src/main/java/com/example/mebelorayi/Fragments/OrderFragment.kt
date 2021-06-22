package com.example.mebelorayi.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mebelorayi.MebelAdapter.ChildAdapter
import com.example.mebelorayi.MebelAdapter.RemoverFromOrder
import com.example.mebelorayi.MVP.MenuViewHelper
import com.example.mebelorayi.MVP.Presenter
import com.example.mebelorayi.R
import com.example.mebelorayi.data.MenuDB
import com.example.mebelorayi.data.ModelMebel.MagazinMebeli
import com.example.mebelorayi.data.ModelMebel.MebelClickListener
import com.example.mebelorayi.data.dao.MenuDao
import com.example.mebelorayi.ui.DetailActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.dialog_checklist.*
import kotlinx.android.synthetic.main.order_fragment.*

class OrderFragment : Fragment(R.layout.order_fragment), MenuViewHelper, MebelClickListener,
    RemoverFromOrder {


    private lateinit var dao: MenuDao
    private lateinit var presenter: Presenter
    lateinit var mMenu: MagazinMebeli
    private val mAdapter = ChildAdapter(this, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_order.adapter = mAdapter
        rv_order.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        dao = MenuDB.getInstance(requireContext()).dao()
        btnBuy.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val bottomView = LayoutInflater.from(context).inflate(R.layout.dialog_checklist, null)
            var quantity = 0
            var sum = 0
            var nameRus = " "
            for (i in 0 until mAdapter.item.size) {
                sum += mAdapter.item[i].cost.toInt() * mAdapter.item[i].quantity?.toInt()!!
                quantity = mAdapter.item[i].quantity?.toInt() ?: 1
                nameRus =
                    nameRus + "\t" + mAdapter.item[i].nameRus + " ($quantity)x" + "................." + mAdapter.item[i].cost + "сум" + "\n"
            }
            dialog.setContentView(bottomView)
            dialog.behavior.setPeekHeight(1200, true)
            dialog.tvDialogCost.text = "$sum сум "
            dialog.tvDialogItem1.text = " Заказы : \n$nameRus "
            dialog.show()
        }
    }

    override fun onStart() {
        presenter = Presenter(dao, this)
        presenter.getMenuFromOrder()
        if (mAdapter.item.isEmpty()) {
            rv_order.visibility = View.GONE
            tvEmpty.visibility = View.VISIBLE
        } else {
            rv_order.visibility = View.VISIBLE
            tvEmpty.visibility = View.GONE
        }
        super.onStart()
    }

    override fun fillData(models: List<MagazinMebeli>) {
        mAdapter.item = models as MutableList<MagazinMebeli>
    }

    override fun onItemMenuClickListener(id: Int) {
        val mIntent = Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.MENU_ID, id)
        startActivity(mIntent)
    }

    override fun RemoverFromOrder(id: Int) {
        Toast.makeText(requireContext(), "Заказ был удален из списка", Toast.LENGTH_SHORT).show()
        dao.deleteFromOrder()
    }


}

