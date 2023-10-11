package kp.ran.greatcoroutinesinjetpackcomposeandroidallexamples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kp.ran.greatcoroutinesinjetpackcomposeandroidallexamples.ui.theme.GreatCoRoutinesInJetPackComposeAndroidAllExamplesTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            GreatCoRoutinesInJetPackComposeAndroidAllExamplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

            //        corExpl()

                }
            }
        }
    }
}


@Composable
fun corExpl(){
    val scope =  rememberCoroutineScope()///Remembers a coroutine scope across recompositions.

    var res by remember {
        mutableStateOf("launch result here")
    }
    var asyncResult by remember {
        mutableStateOf("asyncResult here")
    }

    var RunBlockingRes by remember { mutableStateOf("Loading...") }

    Column {
        Button(onClick = {
          val k=   scope.launch {
                res = doNetworkopr()
            }

         }) {
            Text(text = "Start")
        }
        Text(text = "CoroutineScope 'launch' Example")
        Text(text = "Launch example : $res")
        Divider()
        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "async Example")

        Button(onClick = {
            
            scope.launch {
                val k: Deferred<String> = scope.async {  //Used to perform asynchronous operations and wait for their results.
                    delay(5000L)
                    doNetworkopr()
                }
                asyncResult= k.await()
            }
        }) {
            Text(text = "Coroutine async example")
        }
        Text(text = "Async example : $asyncResult")
        
        
        Divider()
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Run Blocking Example")
        Button(onClick = {
            runBlocking {//Creates a coroutine scope that blocks the current thread until its coroutine completes.
                // Simulate a background operation
                delay(9000L)
                RunBlockingRes = "Data loaded using runBlocking"

               /* withContext(Dispatchers.Main){
                    // perform some UI related operation!
                    // in case if you want to switch context
                }*/
            }

        }) {
            Text(text = "Run Blocking - Start")

        }
        Text(text = RunBlockingRes)

        Divider()
        Spacer(modifier = Modifier.padding(10.dp))

        LaunchedEffect(Unit){
            doNetworkopr()
        }
        /*Lifecycle: The block of code inside LaunchedEffect will be
        executed when the composable is first displayed, and it will
        be re-executed on recomposition. It is often used for tasks
        that need to be performed only once.*/

        SideEffect {

        }

        DisposableEffect( Unit){
            onDispose {

            }
        }

       /* Button(onClick = {


        }) {
            Text(text = "LaunchedEffect Example")
        }*/

    }
}

suspend fun doNetworkopr():String{

    delay(3000L)
    return "i am some result"
}















