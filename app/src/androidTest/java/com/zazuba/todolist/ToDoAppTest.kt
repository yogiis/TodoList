package com.zazuba.todolist.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.zazuba.todolist.model.Task
import com.zazuba.todolist.ui.home.HomeScreen
import com.zazuba.todolist.ui.add.AddTaskScreen
import com.zazuba.todolist.ui.detail.TaskDetailScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class ToDoAppTest {


//    @get:Rule(order = 0)
//    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

//    @Inject
//    lateinit var viewModel: ToDoAppViewModel

//    @Before
//    fun init(){
//        hiltRule.inject()
//    }

    // 1️⃣ Test Menampilkan HomeScreen
    @Test
    fun testHomeScreenShowsTasks() {
        val viewModel = ToDoAppViewModel()


        composeTestRule.setContent {
            HomeScreen(navController = rememberNavController(), viewModel = viewModel)

        }
            viewModel.tasks.forEach { task ->
                composeTestRule.onNodeWithText(task.title).assertExists()


            // Memastikan tugas pertama ditampilkan
            composeTestRule.onNodeWithText("Task 1").assertExists()
            // Memastikan tugas kedua ditampilkan
            composeTestRule.onNodeWithText("Task Rumah").assertExists()


            composeTestRule.onNodeWithTag("1").assertExists()
            composeTestRule.onAllNodesWithText("Task 1").assertCountEquals(1)

        }
    }
    // 2️⃣ Test Menambah Tugas Baru
    @Test
    fun testAddingNewTask() {
        val viewModel = ToDoAppViewModel()

        composeTestRule.setContent {
//            val viewModel: ToDoAppViewModel = hiltViewModel() // ✅ Cara yang benar

            AddTaskScreen(navController = rememberNavController(), viewModel = viewModel)
        }

        // Input judul tugas baru
//        composeTestRule.onNodeWithText("Task Title").performTextInput("Belajar UI Testing")
        composeTestRule.onNodeWithTag("task_title_input").performTextInput("Hello Dunia")


        // Input deskripsi tugas baru
        composeTestRule.onNodeWithText("Task Description").performTextInput("Membuat pengujian untuk aplikasi ToDoList")

        // Klik tombol "Save"
        composeTestRule.onNodeWithText("Save").performClick()

        // 🔥 Ganti dengan metode ini untuk memeriksa navigasi tanpa `setContent()` kedua
        composeTestRule.waitForIdle() // Tunggu UI selesai update
        composeTestRule.onNodeWithText("Hello Dunia").assertExists()
    }


    // 3️⃣ Test Menampilkan Detail Tugas
    @Test
    fun testTaskDetailScreen() {
        val viewModel = ToDoAppViewModel()
        val testTask = Task("99", "Tes Detail", "Detail dari tugas ini")

        viewModel.addTask(testTask)

        composeTestRule.setContent {
            TaskDetailScreen(
                navController = rememberNavController(),
                viewModel = viewModel,
                taskId = "99"
            )

        }

//        viewModel.tasks.forEach { task ->
//            composeTestRule.onNodeWithText(task.title).assertExists()
//        }
        composeTestRule.onNodeWithText("Tes Detail").assertExists()
        composeTestRule.onNodeWithText("Detail dari tugas ini").assertExists()
    }
    // 4️⃣ Test Menghapus Tugas
    @Test
    fun testDeleteTask() {
        val viewModel = ToDoAppViewModel()
        val testTask = Task("100", "Hapus Tugas", "Tugas ini akan dihapus")

        viewModel.addTask(testTask)

        composeTestRule.setContent {
            TaskDetailScreen(navController = rememberNavController(), viewModel = viewModel, taskId = "100")
        }

        // Klik tombol "Delete"
        composeTestRule.onNodeWithText("Delete").performClick()

        // Tunggu sampai UI diperbarui
        composeTestRule.waitForIdle()

        // Periksa apakah tugas sudah dihapus tanpa memanggil `setContent()` lagi
        composeTestRule.onNodeWithText("Hapus Tugas").assertDoesNotExist()
    }
}
