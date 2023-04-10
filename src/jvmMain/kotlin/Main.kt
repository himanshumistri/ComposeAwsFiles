import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import repository.ReadAwsData

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val coroutineScope = rememberCoroutineScope()
    MaterialTheme {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Row(modifier = Modifier.width(200.dp)) {
                    Button(onClick = {
                        text = "Fetch Data"
                        coroutineScope?.launch {
                            ReadAwsData.listBucketObjects("MybucketName")
                        }

                    }) {
                        Text(text)
                    }
                }

            }
        }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
