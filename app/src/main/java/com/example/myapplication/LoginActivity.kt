package com.example.myapplication

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class LoginActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    OtpScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpScreen() {
    var otpCode by remember { mutableStateOf("") }
    val context = LocalContext.current
    val intent = (context as LoginActivity).intent

    val phoneNo = intent.getStringExtra("phone")



    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        Text(text = "+91 $phoneNo",
            modifier = Modifier.padding(10.dp))
        Text(text = "Enter The",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp,3.dp,0.dp,0.dp))
        Text(text = "OTP",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp,3.dp,0.dp,0.dp))


        Row {


            Box(modifier = Modifier
                .height(70.dp)
                .width(200.dp)
                .padding(10.dp, 20.dp, 0.dp, 0.dp)
                .border(BorderStroke(1.dp, Color.Black))) {

                TextField(
                    value =otpCode ,
                    onValueChange = {otpCode = it},
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

                if (otpCode.isEmpty()){
                    Toast.makeText(context, "otpCode can not be empty", Toast.LENGTH_SHORT).show()
                } else{
                    login(phoneNo.toString(), otpCode, context)
                    val intent = Intent(context, HomePage::class.java)
                    context.startActivity(intent)
                }

            }) {
            Text(text = "Continue")

        }



    }


}

@SuppressLint("SuspiciousIndentation")
private  fun login(
    number :String,
    otp : String,
    context:Context
){



    val dataModel = VerifyOtpRequest("+91$number", otp)
    val service = RetrofitHelper.getInstance().create(ApiServices::class.java)

    var token :String = ""
    val pref = UserPref(context)
    var sessionManager = SessionManager(context)


    CoroutineScope(Dispatchers.IO).launch {

        val response = service.login(dataModel)
        withContext(Dispatchers.Main){
            if (response.isSuccessful){
                Log.d("SUCCESS", response.body()!!.token)
                //respnse.value = true
                token = response.body()!!.token
               // pref.saveToken(token)
                    sessionManager.saveAuthToken(token)

            }else{

                Log.e("RETROFIT_ERROR", response.code().toString())
            }
        }
    }

}