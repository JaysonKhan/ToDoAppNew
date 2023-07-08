package uz.gita.todoappnew.presentation.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.todoappnew.data.model.TaskData
import uz.gita.todoappnew.domain.repository.TaskRepository
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val direction: AddDirection
) : ViewModel(), AddContract.Model {
    override val container =
        container<AddContract.UiState, AddContract.SideEffect>(AddContract.UiState.Welcome)

    override fun eventDispatcher(intent: AddContract.Intent) {
        when (intent) {
            is AddContract.Intent.AddTask -> {
                addTask(intent.task)
                viewModelScope.launch {
                    direction.back()
                }
            }

            is AddContract.Intent.UpdateTask -> {
                updateTask(intent.task)
            }

            is AddContract.Intent.Back -> {
                viewModelScope.launch{
                    direction.back()
                }
            }

        }
    }

    private fun addTask(task: TaskData) {
        viewModelScope.launch {
            repository.addTask(task)
        }
    }

    private fun updateTask(task: TaskData) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
}