package com.zazuba.todolist.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.zazuba.todolist.model.Task
import com.zazuba.todolist.ui.home.HomeScreen
import com.zazuba.todolist.ui.add.AddTaskScreen
import com.zazuba.todolist.ui.detail.TaskDetailScreen
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ToDoAppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: ToDoAppViewModel

    @Before
    fun setup() {
        viewModel = ToDoAppViewModel()
    }

    @After
    fun tearDown() {
        viewModel.clearTasks()
    }

    @Test
    fun testHomeScreenShowsTasks() {
        composeTestRule.setContent {
            HomeScreen(navController = rememberNavController(), viewModel = viewModel)
        }

        viewModel.tasks.forEach { task ->
            composeTestRule.onNodeWithText(task.title).assertExists()
        }

        composeTestRule.onNodeWithText("Task 1").assertExists()
        composeTestRule.onNodeWithText("Task Rumah").assertExists()

        composeTestRule.onAllNodesWithText("Task 1").assertCountEquals(1)
    }

    @Test
    fun testAddingNewTask() {
        composeTestRule.setContent {
            AddTaskScreen(navController = rememberNavController(), viewModel = viewModel)
        }

        composeTestRule.onNodeWithTag("task_title_input").performTextInput("Hello Dunia")

        composeTestRule.onNodeWithText("Task Description").performTextInput("Membuat pengujian untuk aplikasi ToDoList")

        composeTestRule.onNodeWithText("Save").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Hello Dunia").assertExists()
    }

    @Test
    fun testTaskDetailScreen() {
        val testTask = Task("99", "Tes Detail", "Detail dari tugas ini")
        viewModel.addTask(testTask)

        composeTestRule.setContent {
            TaskDetailScreen(
                navController = rememberNavController(),
                viewModel = viewModel,
                taskId = "99"
            )
        }

        composeTestRule.onNodeWithText("Tes Detail").assertExists()
        composeTestRule.onNodeWithText("Detail dari tugas ini").assertExists()
    }

    @Test
    fun testDeleteTask() {
        val testTask = Task("100", "Hapus Tugas", "Tugas ini akan dihapus")
        viewModel.addTask(testTask)

        composeTestRule.setContent {
            TaskDetailScreen(navController = rememberNavController(), viewModel = viewModel, taskId = "100")
        }

        composeTestRule.onNodeWithText("Delete").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Hapus Tugas").assertDoesNotExist()
    }
}
