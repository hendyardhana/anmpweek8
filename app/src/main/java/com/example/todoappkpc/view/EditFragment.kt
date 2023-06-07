package com.example.todoappkpc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoappkpc.R
import com.example.todoappkpc.viewmodel.DetailTodoViewModel

class EditFragment : Fragment() {

    private lateinit var viewmodel:DetailTodoViewModel
    var uuid = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        if(arguments != null){
            uuid = EditFragmentArgs.fromBundle(requireArguments()).uuid
        }
        viewmodel.selectTodo(uuid)

        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save Edited Todo"

        btnAdd?.setOnClickListener {
            val rdoPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            val rdoValue = view.findViewById<RadioButton>(rdoPriority.checkedRadioButtonId)
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            viewmodel.update(txtTitle?.text.toString(), txtNotes?.text.toString(), rdoValue?.tag.toString().toInt(), uuid)
            Navigation.findNavController(view).popBackStack()
        }

        observeViewModel(view)

    }

    private fun observeViewModel(view:View) {
        viewmodel.todoLD.observe(viewLifecycleOwner, Observer { it ->
            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)

            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)

            val rdoHigh = view.findViewById<RadioButton>(R.id.radioHigh)
            val rdoMed = view.findViewById<RadioButton>(R.id.radioMedium)
            val rdoLow = view.findViewById<RadioButton>(R.id.radioLow)
            when(it.priority){
                1 -> rdoLow?.isChecked = true
                2 -> rdoMed?.isChecked = true
                3 -> rdoHigh?.isChecked = true
            }
        })
    }
}