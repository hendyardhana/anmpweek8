package com.example.todoappkpc.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.todoappkpc.R
import com.example.todoappkpc.databinding.FragmentCreateTodoBinding
import com.example.todoappkpc.model.Todo
import com.example.todoappkpc.util.NotificationHelper
import com.example.todoappkpc.util.TodoWorker
import com.example.todoappkpc.viewmodel.DetailTodoViewModel
import java.util.concurrent.TimeUnit

class CreateTodoFragment : Fragment(), FragmentEditTodoInterface {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var databinding:FragmentCreateTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_todo, container, false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databinding.todo = Todo("", "", 3, 0, 0)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        databinding.radioListener = this
        databinding.savelistener = this
//        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
//        btnAdd.setOnClickListener {
//            //NotificationHelper(view.context).createNotification("Todo Created", "A new todo has been created! Stay focus!")
//            val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
//                .setInitialDelay(10, TimeUnit.SECONDS)
//                .setInputData(workDataOf(
//                    "title" to "Todo Created",
//                    "message" to "A new todo has been created! Stay focus!")).build()
//            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
//            val txtTitle = view.findViewById<EditText>(R.id.txtTitle)
//            val txtNotes = view.findViewById<EditText>(R.id.txtNotes)
//            val rdoPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
//            val rdoValue = view.findViewById<RadioButton>(rdoPriority.checkedRadioButtonId)
//            val todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), rdoValue.tag.toString().toInt(), 0)
//
//            viewModel.addTodo(todo)
//            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(it).popBackStack()
//        }
    }

    override fun onRadioClick(view: View, todo: Todo) {
        todo.priority = view.tag.toString().toInt()
    }

    override fun onTodoSaveClick(view: View, todo: Todo) {
        viewModel.addTodo(todo)
        val myWorkRequest = OneTimeWorkRequestBuilder<TodoWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "Todo Created",
                "message" to "A new todo has been created! Stay focus!")).build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
        Navigation.findNavController(view).popBackStack()
    }

}