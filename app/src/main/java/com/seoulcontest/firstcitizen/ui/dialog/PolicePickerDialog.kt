package com.seoulcontest.firstcitizen.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class PolicePickerDialog(private val policeArr: Array<String>) : DialogFragment() {

    private lateinit var valueChangedListener: NumberPicker.OnValueChangeListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val numberPicker = NumberPicker(requireContext()).apply {
            minValue = 0
            maxValue = policeArr.size - 1
            displayedValues = policeArr
            wrapSelectorWheel = true
        }

        return AlertDialog.Builder(requireContext()).apply {

            setTitle("관할 경찰서 선택")
            setPositiveButton("확인") { dialogInterface, _ ->

                valueChangedListener.onValueChange(numberPicker, numberPicker.value, numberPicker.value)
                dialogInterface.dismiss()
            }
            setNegativeButton("취소") { dialogInterface, position ->
                valueChangedListener.onValueChange(numberPicker, position, 0)
                dialogInterface.dismiss()
            }
            setView(numberPicker)
        }.create()

    }

    fun setValueChangedListener(changedListener: NumberPicker.OnValueChangeListener) {
        valueChangedListener = changedListener
    }
}