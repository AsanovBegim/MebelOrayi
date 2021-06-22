package com.example.mebelorayi.MenuRecycleFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mebelorayi.MebelAdapter.MainAdapter
import com.example.mebelorayi.Fragments.HomeFragment
import com.example.mebelorayi.R
import com.example.mebelorayi.data.MenuDB
import com.example.mebelorayi.data.ModelMebel.MagazinMebeli
import com.example.mebelorayi.data.ModelMebel.MebelClickListener
import com.example.mebelorayi.data.dao.MenuDao
import com.example.mebelorayi.ui.DetailActivity
import kotlinx.android.synthetic.main.fragment_recycler.*

class FragmentMenu : Fragment(R.layout.fragment_recycler), MebelClickListener {

    private val mAdapter = MainAdapter(this)
    lateinit var dao: MenuDao
    lateinit var mMenu: MagazinMebeli

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv1.adapter = mAdapter
        val type = arguments?.getInt(HomeFragment.TYPE_ID) ?:1
        dao = MenuDB.getInstance(requireContext()).dao()
        fillData(type)

    }

    private fun fillData(type: Int){
        Log.d("modeller", dao.getMenuByType(type).toString())
        mAdapter.item = dao.getMenuByType(type)
    }

    override fun onItemMenuClickListener(id:Int) {
        val mIntent = Intent(requireActivity(), DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.MENU_ID, id)
        startActivity(mIntent)
    }

}