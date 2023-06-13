package com.example.todoappkpc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoappkpc.R
import com.example.todoappkpc.databinding.FragmentEditTodoBinding
import com.example.todoappkpc.model.Todo
import com.example.todoappkpc.viewmodel.DetailTodoViewModel

class EditFragment : Fragment(), FragmentEditTodoInterface {

    private lateinit var viewmodel:DetailTodoViewModel
    var uuid = 0

    private lateinit var dataBinding:FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        if(arguments != null){
            uuid = EditFragmentArgs.fromBundle(requireArguments()).uuid
        }
        viewmodel.selectTodo(uuid)

        dataBinding.radioListener = this
        dataBinding.saveListenet = this

        observeViewModel(view)

    }

    private fun observeViewModel(view:View) {
        viewmodel.todoLD.observe(viewLifecycleOwner, Observer { it ->
            dataBinding.todo = it
        })
    }

    override fun onRadioClick(view: View, todo: Todo) {
        todo.priority = view.tag.toString().toInt()
    }

    override fun onTodoSaveClick(view: View, todo: Todo) {
        viewmodel.update(todo.title, todo.notes, todo.priority, todo.uuid)
        Navigation.findNavController(view).popBackStack()
    }
}