package com.zazuba.todolist.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zazuba.todolist.ui.add.AddTaskScreen
import com.zazuba.todolist.ui.detail.TaskDetailScreen
import com.zazuba.todolist.ui.home.HomeScreen

@Composable
fun ToDoApp(
    viewModel: ToDoAppViewModel
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("add"){
            AddTaskScreen(navController = navController, viewModel = viewModel)
        }
        composable("detail/{taskId}"){ backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            TaskDetailScreen(navController = navController, viewModel = viewModel,
                taskId = taskId
            )
        }
    }
}