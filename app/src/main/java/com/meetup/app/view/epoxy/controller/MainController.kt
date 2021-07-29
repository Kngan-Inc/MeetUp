package com.meetup.app.view.epoxy.controller

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.meetup.app.`interface`.ItemInterface
import com.meetup.app.view.epoxy.model.text
import java.util.concurrent.CopyOnWriteArrayList

class MainController(
    private var itemInterface: ItemInterface
) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

    private var list: CopyOnWriteArrayList<String> = CopyOnWriteArrayList()

    override fun buildModels() {
        List(100) {
            text {
                id("$it")
                text(it.toString())
                clickListener { _, _, _, _ ->
                    itemInterface.onItemClick(item = it.toString())
                }
            }
        }
    }


    fun submit(list: ArrayList<String>) {
        this.list.addAll(list)
        requestModelBuild()
    }
}