package com.example.mebelorayi.MVP

import com.example.mebelorayi.data.dao.MenuDao

class Presenter(private val dao: MenuDao, private val view : MenuViewHelper) {

    fun  getMenuFromOrder(){
        view.fillData(dao.getAllFromIsOrdered())
        }
}