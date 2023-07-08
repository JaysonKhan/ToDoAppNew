package uz.gita.todoappnew.util.navigator

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.todoappnew.util.navigator.NavigationArg

interface NavigationHandler {
    val navigatorBuffer:SharedFlow<NavigationArg>
}