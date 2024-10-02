package br.edu.scl.ifsp.sdm.contactlist.view

sealed interface OnContactClickListener {

    fun onContactClick(position: Int)

    fun onRemoveContactClick(position: Int)

    fun onEditContactClick(position: Int)


}
