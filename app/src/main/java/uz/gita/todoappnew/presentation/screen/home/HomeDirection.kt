package uz.gita.todoappnew.presentation.screen.home

import uz.gita.todoappnew.data.model.TaskData
import uz.gita.todoappnew.presentation.screen.add.AddScreen
import uz.gita.todoappnew.util.navigator.AppNavigator
import javax.inject.Inject

interface HomeDirection {
    suspend fun navigateToAddScreen(taskData: TaskData)

}

class HomeDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):HomeDirection {
    override suspend fun navigateToAddScreen(taskData: TaskData) {
        navigator.navigateTo(AddScreen(taskData))
    }

}