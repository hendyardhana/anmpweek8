package com.example.todoappkpc.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
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
import com.example.todoappkpc.util.TodoWorker
import com.example.todoappkpc.viewmodel.DetailTodoViewModel
import java.util.Calendar
import java.util.concurrent.TimeUnit
import kotlin.math.min

class CreateTodoFragment : Fragment(), FragmentEditTodoInterface, DateClickListener, TimeClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var databinding:FragmentCreateTodoBinding
    var year = 0
    var month = 0
    var day = 0
    var hour = 0
    var minute = 0

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
        databinding.dateListener = this
        databinding.timeListener = this

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
        val c = Calendar.getInstance()
        c.set(year, month, day, hour, minute, 0)

        val today = Calendar.getInstance()
        (c.timeInMillis/1000L) - (today.timeInMillis/1000L)

        databinding.todo!!.todo_date = (c.timeInMillis/1000L).toInt()

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

    override fun onDateClick(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let {
            it -> DatePickerDialog(it, this, year, month, day).show()
        }
    }

    override fun onTimeClick(v: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        TimePickerDialog(activity, this, hour, minute, android.text.format.DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        Calendar.getInstance().let {
            it.set(year, month, day)
            databinding.txtDate.setText(day.toString().padStart(2,'0') + "-" + month.toString().padStart(2,'0') + "-" + year)
            this.year = year
            this.month = month
            this.day = day
        }
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        databinding.txtTime.setText(hour.toString().padStart(2,'0') + "-" + minute.toString().padStart(2,'0'))
        this.hour = hour
        this.minute = minute
    }

}