package br.edu.scl.ifsp.sdm.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.edu.scl.ifsp.sdm.contactlist.R
import br.edu.scl.ifsp.sdm.contactlist.databinding.TileContactBinding
import br.edu.scl.ifsp.sdm.contactlist.model.Contact
import br.edu.scl.ifsp.sdm.contactlist.view.OnContactClickListener

class ContactRvAdapter (
    private val contactList: MutableList<Contact>,
    private val onContactClickListener: OnContactClickListener
):
    RecyclerView.Adapter<ContactRvAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(tileContactBinging: TileContactBinding) :
        RecyclerView.ViewHolder(tileContactBinging.root) {
        val nameTv: TextView = tileContactBinging.nameTv
        val phoneTv: TextView = tileContactBinging.phoneTv
        val emailTv: TextView = tileContactBinging.emailTv

        init {
            tileContactBinging.root.apply {
                setOnCreateContextMenuListener{menu,_,_->
                    (onContactClickListener as AppCompatActivity).menuInflater.inflate(
                        R.menu.context_menu_main,menu
                    )
                    menu.findItem(R.id.removeContactMi).setOnMenuItemClickListener {
                        onContactClickListener.onRemoveContactClick(adapterPosition)
                        true
                    }
                    menu.findItem(R.id.editContactMi).setOnMenuItemClickListener {
                        onContactClickListener.onEditContactClick(adapterPosition)
                        true
                    }


                }
                setOnClickListener{
                    onContactClickListener.onContactClick(adapterPosition)
                }
            }
        }
    }

    override fun getItemCount() = contactList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TileContactBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            ContactViewHolder(this)
        }



    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contactList[position].also{ contact->
            with(holder){
                nameTv.text = contact.name
                phoneTv.text = contact.phone
                emailTv.text = contact.email
//                itemView.setOnClickListener {
//                    onContactClickListener.onContactClick(position)
//                }
            }
        }
    }

}