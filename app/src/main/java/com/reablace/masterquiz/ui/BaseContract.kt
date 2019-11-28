package com.reablace.masterquiz.ui

/**
 * BaseContract to abstract common functionality for every UI element
 */
class BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
    }

    interface View

}