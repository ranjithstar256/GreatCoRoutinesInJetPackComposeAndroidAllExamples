package kp.ran.greatcoroutinesinjetpackcomposeandroidallexamples
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

class AnotherExpl : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

 @Composable
fun MyApp() {
     var data by remember { mutableStateOf("Loading...") }
     var isLoading by remember { mutableStateOf(false) }
     var isError by remember { mutableStateOf(false) }
     var showMessage by remember { mutableStateOf(false) }
     var message by remember { mutableStateOf("Message") }

     val coroutineScope = rememberCoroutineScope()

     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(16.dp),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
         // Launched Effect Example
         LaunchedEffect(Unit) {
             delay(2000L)
             data = "Launched Effect Example"
         }

         Spacer(modifier = Modifier.height(16.dp))

         // Disposable Effect Example
         DisposableEffect(Unit) {
             // Setup operations

             onDispose {
                 // Cleanup operations
             }
         }

         Spacer(modifier = Modifier.height(16.dp))

         // Side Effect Example
         SideEffect {
             // Side effect operations
         }

         Spacer(modifier = Modifier.height(16.dp))

         LaunchedEffect(Unit) {


             // Coroutine Scope Example
             coroutineScope.launch {
                 delay(2000L)
                 data = "Coroutine Scope Example"
             }
         }

         Spacer(modifier = Modifier.height(16.dp))

         LaunchedEffect(Unit) {

             // With Context Example
             withContext(Dispatchers.IO) {
                 delay(2000L)
                 data = "With Context Example"
             }
         }

         Spacer(modifier = Modifier.height(16.dp))

         // Run Blocking Example
         runBlocking {
             delay(2000L)
             data = "Run Blocking Example"
         }

         Spacer(modifier = Modifier.height(16.dp))

         // Remember Coroutine Scope Example
         val rememberedScope = rememberCoroutineScope()

         LaunchedEffect(Unit) {

             rememberedScope.launch {
                 delay(2000L)
                 data = "Remember Coroutine Scope Example"
             }
         }
         Spacer(modifier = Modifier.height(16.dp))

         LaunchedEffect(Unit) {

             // Async and Await Example
             val result: String = withContext(Dispatchers.IO) {
                 async {
                     delay(2000L)
                     "Async and Await Example"
                 }.await()
             }
             data = result
         }
         Spacer(modifier = Modifier.height(16.dp))


         // Error Handling Example
         if (isError) {
             Icon(
                 imageVector = Icons.Default.Warning,
                 contentDescription = "Error",
                 tint = Color.Red,
                 modifier = Modifier.size(48.dp)
             )
         } else {
             // Simulate an error
             Button(onClick = {
                 coroutineScope.launch {
                     try {
                         // Simulate a network call
                         delay(2000L)
                         throw IllegalStateException("Simulated Error")
                     } catch (e: Exception) {
                         // Handle the error
                         isError = true
                     }
                 }
             }) {
                 Text(text = "Simulate Error")
             }
         }

         Spacer(modifier = Modifier.height(16.dp))

         // Snackbar Example
         if (showMessage) {
             Snackbar(
                 action = {
                     IconButton(onClick = { showMessage = false }) {
                         Icon(imageVector = Icons.Default.Done, contentDescription = "Done")
                     }
                 }
             ) {
                 Text(text = message, fontWeight = FontWeight.Bold)
             }
         } else {
             Button(onClick = {
                 coroutineScope.launch {
                     showMessage = true
                     message = "Snackbar Example"
                     delay(3000L)
                     showMessage = false
                 }
             }) {
                 Text(text = "Show Snackbar")
             }
         }
     }
 }

@Composable
fun somecpfn(){
    val scope = rememberCoroutineScope()

    var res by remember {
        mutableStateOf("")
    }
    Column {

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                res = doNetworkopr()
                withContext(Dispatchers.Main){
                    // some operations related to ui
                }
            }
        }) {
            Text(text = "get")
        }
        LaunchedEffect(Unit){
            ///res = doNetworkopr()
        }

        Text(text = res)

    }

}