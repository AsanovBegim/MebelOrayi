package com.example.mebelorayi.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mebelorayi.MenuRecycleFragment.FragmentMenu
import com.example.mebelorayi.R
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {
    companion object {
        const val TYPE_ID = "typeId"
        const val CHILD = 1
        const val GUEST = 2
        const val BEDROOM = 3
        const val KOMPLEKT = 4
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = FragmentMenu()
        val bundle = Bundle()
        bundle.putInt(TYPE_ID, CHILD)
        fragment.arguments = bundle
        childFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
//        btnChild.setImageResource(R.drawable.back_item)
//        btnKomplekt.setImageResource(R.drawable.burgerfocused)
//        btnBedroom.setImageResource(R.drawable.lavashfocused)
//        btnGuest.setImageResource(R.drawable.pizzafocused)


        btnChild.setOnClickListener {
//            btnChild.setImageResource(R.drawable.back_item)
//            btnKomplekt.setImageResource(R.drawable.burgerfocused)
//            btnBedroom.setImageResource(R.drawable.lavashfocused)
//            btnGuest.setImageResource(R.drawable.pizzafocused)
            val mFragment = FragmentMenu()
            val mBundle = Bundle()
            mBundle.putInt(TYPE_ID, CHILD)
            mFragment.arguments = mBundle
            childFragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment)
                .commit()

        }

        btnGuest.setOnClickListener {
//            btnChild.setImageResource(R.drawable.back_cost)
//            btnKomplekt.setImageResource(R.drawable.burgerfocused)
//            btnBedroom.setImageResource(R.drawable.lavashfocused)
//            btnGuest.setImageResource(R.drawable.pizza)
            val mFragment = FragmentMenu()
            val mBundle = Bundle()
            mBundle.putInt(TYPE_ID, GUEST)
            mFragment.arguments = mBundle
            childFragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment)
                .commit()
        }

        btnBedroom.setOnClickListener {
//            btnChild.setImageResource(R.drawable.drinkfocused)
//            btnKomplekt.setImageResource(R.drawable.burgerfocused)
//            btnBedroom.setImageResource(R.drawable.lavash)
//            btnGuest.setImageResource(R.drawable.pizzafocused)
            val mFragment = FragmentMenu()
            val mBundle = Bundle()
            mBundle.putInt(TYPE_ID, BEDROOM)
            mFragment.arguments = mBundle
            childFragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment)
                .commit()
        }

        btnKomplekt.setOnClickListener {
//            btnChild.setImageResource(R.drawable.drinkfocused)
//            btnKomplekt.setImageResource(R.drawable.burger)
//            btnBedroom.setImageResource(R.drawable.lavashfocused)
//            btnGuest.setImageResource(R.drawable.pizzafocused)
            val mFragment = FragmentMenu()
            val mBundle = Bundle()
            mBundle.putInt(TYPE_ID, KOMPLEKT)
            mFragment.arguments = mBundle
            childFragmentManager.beginTransaction().replace(R.id.fragment_container, mFragment)
                .commit()
        }
    }
}