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

        val numberPicker = NumberPicker(mContext).apply {
            minValue = 0
            maxValue = policeArr.size - 1
            displayedValues = policeArr
            wrapSelectorWheel = true
        }

        val dialogBuilder = AlertDialog.Builder(mContext).apply {

            setTitle("관할 경찰서 선택")
            setPositiveButton("확인") { dialogInterface, i ->

                valueChangedListener.onValueChange(numberPicker, numberPicker.value, numberPicker.value)
                dialogInterface.dismiss()
            }
            setNegativeButton("취소")  { dialogInterface, i ->
                valueChangedListener.onValueChange(numberPicker, 0, 0)
                dialogInterface.dismiss()
            }

            setView(numberPicker)
        }

        return dialogBuilder.create()

    }

    fun setValueChangedListener(changedListener: NumberPicker.OnValueChangeListener) {
        valueChangedListener = changedListener
    }
}