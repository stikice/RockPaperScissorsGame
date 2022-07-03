package com.example.rockpaperscissors

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class ChoiceDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_custom)
        dialog.create()
        with(dialog){
            findViewById<ImageView>(R.id.iv_choice_rock).setOnClickListener{
                (activity as? DialogCallback)?.onChoiceImageClicked(0)
            }
            findViewById<ImageView>(R.id.iv_choice_paper).setOnClickListener {
                (activity as? DialogCallback)?.onChoiceImageClicked(1)
            }
            findViewById<ImageView>(R.id.iv_choice_scissor).setOnClickListener {
                (activity as? DialogCallback)?.onChoiceImageClicked(2)
            }
        }
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        return dialog
    }
}

interface DialogCallback {
    fun onChoiceImageClicked(choice: Int)
}