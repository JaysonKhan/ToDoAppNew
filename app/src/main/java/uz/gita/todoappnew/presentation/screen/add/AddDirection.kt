package uz.gita.todoappnew.presentation.screen.add

import uz.gita.todoappnew.presentation.screen.home.HomeScreen
import uz.gita.todoappnew.util.navigator.AppNavigator
import javax.inject.Inject

interface AddDirection {
    suspend fun back()
}

class AddDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):AddDirection {
    override suspend fun back() {
        navigator.back()
    }

}