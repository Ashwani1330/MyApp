package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.ui.theme.MyAPPTheme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlin.system.measureTimeMillis




class MainActivity : ComponentActivity() {

    val TAG = "Main Activity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonsrtactivity.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    delay(1000L)
                    Log.d(TAG, "Still running")
                }
            }
            GlobalScope.launch {
                delay(5000L)
                Intent(this@MainActivity, MainActivity2::class.java).also {
                    startActivity(it)
                    finish()
                }
            }


        }
    }
}

/*
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }
                Log.d(TAG, "Answer1: ${answer1.await()}")
                Log.d(TAG, "Answer2: ${answer2.await()}")
            }
            Log.d(TAG, "Response time: $time ms.")
        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }
*/


    /*
            val job = GlobalScope.launch(Dispatchers.Default) {
                Log.d(TAG, "Starting long running calculation...")
                withTimeout(3000L) {
                    for (i in 30..50) {
                        if (isActive) {
                            Log.d(TAG, "Result for i = $i: ${fib(i)}")
                        }
                    }
                }
                Log.d(TAG, "Ending long running calculation...")
            }
    */

    /*
            runBlocking {  // Manual cancellation of the job {commented}
                delay(2000L)
                job.cancel()
                Log.d(TAG, "Cancelled job!")
            }
    */

fun fib(n: Int): Long {
    return if (n == 0) 0
    else if (n == 1) 1
    else fib(n - 1) + fib(n - 2)
}

/*
        GlobalScope.launch(Dispatchers.Main) { // this won't block the main thread

        }


        Log.d(TAG, "Before runBlocking")
        runBlocking {  // will block the main thread
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 1.")
            }

            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG, "Finished IO Coroutine 2.")
            }

            Log.d(TAG, "Start of runBlocking")
            delay(5000L)
            Log.d(TAG, "End of runBlocking")
        }
        Log.d(TAG, "After runBlocking")
*/

// Simplest way to start a coroutine, but not the best way
//        GlobalScope.launch(Dispatchers.IO) {
//            Log.d(TAG, "Starting Coroutine in thread ${Thread.currentThread().name}")
//            val answer = doNetworkCall()
//            withContext(Dispatchers.Main) {
//                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
//                binding.button.text = answer
//            }
//        }
//    }
//
//
//    suspend fun doNetworkCall(): String {
//        delay(3000L)
//        return "This is the answer."
//    }
//
//    suspend fun doNetworkCall2(): String {
//        delay(3000L)
//        return "This is the answer."
//    }

/*
enableEdgeToEdge()
setContent {
    MyAPPTheme {
        Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
            Greeting(
                name = "Android",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var presses by remember {
        mutableIntStateOf(0)
    };

    Scaffold(

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Face, contentDescription = "Multiplu")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                fontSize = 20.sp,
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAPPTheme {
        Greeting("Android")
    }
}