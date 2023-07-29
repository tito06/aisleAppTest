package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit

val respnse = mutableStateOf( false)
class MainActivity : ComponentActivity() {

    var stringState =  mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    OtoScreen()
                    //DashBoard()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtoScreen() {
    var phNumber by remember { mutableStateOf("") }
    val context = LocalContext.current



    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        
        Text(text = "Get OTP",
        modifier = Modifier.padding(10.dp))
        Text(text = "Enter Your",
        fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp,3.dp,0.dp,0.dp))
        Text(text = "Phone Number",
        fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp,3.dp,0.dp,0.dp))


        Row {
            Box(modifier = Modifier
                .height(70.dp)
                .width(60.dp)
                .padding(10.dp, 20.dp, 0.dp, 0.dp)
                .border(BorderStroke(1.dp, Color.Black))) {

                Text(text = "+91",
                modifier = Modifier.padding(10.dp))
            }

            Box(modifier = Modifier
                .height(70.dp)
                .width(200.dp)
                .padding(10.dp, 20.dp, 0.dp, 0.dp)
                .border(BorderStroke(1.dp, Color.Black))) {

                TextField(
                    value =phNumber ,
                    onValueChange = {phNumber = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }

        }
        
        Button(
            modifier = Modifier
                .height(70.dp)
                .width(120.dp)
                .padding(10.dp, 30.dp, 0.dp, 0.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(Color.Yellow),

            onClick = {

                if (phNumber.isEmpty() || phNumber.length > 10 || phNumber.length <10){
                    Toast.makeText(context, "phNumber is either empty or wrong", Toast.LENGTH_SHORT).show()
                } else{
                   getOtp("+91$phNumber")
                    if (respnse.value == true){
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.putExtra("phone", phNumber)
                        context.startActivity(intent)
                    }
                }

            }) {
            Text(text = "Continue")
            
        }



    }


}

@Composable
private fun CustomCircularProgressBar(){
    CircularProgressIndicator(
        modifier = Modifier.size(100.dp),
        color = Color.Green,
        strokeWidth = 10.dp)

}

private  fun getOtp(
    number :String
){



    val dataModel = OtpRequest(number)
    val service = RetrofitHelper.getInstance().create(ApiServices::class.java)



    CoroutineScope(Dispatchers.IO).launch {

        val response = service.getOtp(dataModel)
        withContext(Dispatchers.Main){
            if (response.isSuccessful){
                Log.d("SUCCESS", response.body()?.status.toString())
                respnse.value = true
            }else{

                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
    }

}


