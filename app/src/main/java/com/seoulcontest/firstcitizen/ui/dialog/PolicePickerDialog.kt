package com.seoulcontest.firstcitizen.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class PolicePickerDialog(private val mContext: Context, private val policeArr: Array<String>) : DialogFragment() {

    private lateinit var valueChangedListener: NumberPicker.OnValueChangeListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val numberPicker = NumberPicker(mContext)
        numberPicker.minValue = 0
        numberPicker.maxValue = policeArr.size - 1
        numberPicker.displayedValues = policeArr
        numberPicker.wrapSelectorWheel = true

        val dialogBuilder = AlertDialog.Builder(mContext)
        dialogBuilder.setTitle("관할 경찰서 선택")


        dialogBuilder.setPositiveButton("확인", DialogInterface.OnClickListener { dialogInterface, i ->

            valueChangedListener.onValueChange(numberPicker, numberPicker.value, numberPicker.value)
            dialogInterface.dismiss()
        })

        dialogBuilder.setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->
            valueChangedListener.onValueChange(numberPicker,0,0)
            dialogInterface.dismiss()
        })

        dialogBuilder.setView(numberPicker)

        return dialogBuilder.create()

    }

    private fun getValueChangedListener(): NumberPicker.OnValueChangeListener {
        return valueChangedListener
    }

    fun setValueChangedListener(changedListener: NumberPicker.OnValueChangeListener) {
        valueChangedListener = changedListener
    }
}