package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.ui.theme.MyApplicationTheme


var tokentext = ""

class HomePage  : ComponentActivity(){

    val homeVm by viewModels<DashBoardViewModel> ()
    val context = this



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    homeVm.getUserPhoto(context)

                    DashBoard(userList = homeVm.photi)
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DashBoard(userList : List<String>) {


    /*   val context = LocalContext.current
    val pref = UserPref(context)
    tokentext = pref.tokenFlow.collectAsState(initial = "")
    Toast.makeText(context, "${tokentext}", Toast.LENGTH_SHORT).show()

    Log.d("TOKEN RETR", tokentext)*/


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "NOTES",
            fontSize = 25.sp,
            fontWeight = FontWeight.Black
        )

        Text(text = "Personal message to you")


        LazyRow(
            modifier = Modifier
                .width(400.dp)
                .height(400.dp)
                .padding(10.dp, 20.dp),
            userScrollEnabled = true
        ) {
            items(userList) { item ->


                Image(
                    painter = rememberAsyncImagePainter(item),
                    contentDescription = null,
                    modifier = Modifier
                        .width(400.dp)
                        .height(500.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )


//            Log.d("PHOTO", userList[0].photo)
            }
        }
        Card(
            modifier = Modifier
                .width(350.dp)
                .height(70.dp),

        ) {

                
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(2.dp)) {
                    Text(text = "Interested In You",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black)

                    Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Get Premiumn",
                            fontSize = 13.sp)

                        Button(
                            modifier = Modifier
                                .height(100.dp)
                                .width(120.dp)
                                .padding(0.dp, 0.dp, 0.dp, 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(Color.Yellow),
                            onClick = {

                            }) {
                            Text(text = "Upgrade")

                        }
                    }


                }
                

            
        }


        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(10.dp, 10.dp),
            userScrollEnabled = true
        ) {
            items(userList) {item ->


                    Image(
                        painter = rememberAsyncImagePainter(item),
                        contentDescription = null,
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .blur(
                                radiusX = 15.dp,
                                radiusY = 15.dp,
                                edgeTreatment = BlurredEdgeTreatment(
                                    RoundedCornerShape(0.dp)

                                )
                            )
                    )
            }

            items(userList) {item ->


                Image(
                    painter = rememberAsyncImagePainter(item),
                    contentDescription = null,
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)
                        .blur(
                            radiusX = 15.dp,
                            radiusY = 15.dp,
                            edgeTreatment = BlurredEdgeTreatment(
                                RoundedCornerShape(0.dp)

                            )
                        )
                )
            }

        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
    )
    {
        BottomAppBar(
            modifier = Modifier.align(alignment = Alignment.BottomCenter),
            containerColor = Color.White,
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Column {
                    androidx.compose.foundation.Image(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = ""
                    )
                    Text(text = "Dashboard")
                }

                Column {
                    androidx.compose.foundation.Image(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = ""
                    )
                    Text(text = "Notes")
                }

                Column {
                    androidx.compose.foundation.Image(
                        imageVector = Icons.Default.Done,
                        contentDescription = ""
                    )
                    Text(text = "Matches")
                }

                Column(verticalArrangement = Arrangement.Center) {
                    androidx.compose.foundation.Image(
                        imageVector = Icons.Default.Person,
                        contentDescription = ""
                    )
                    Text(text = "Profile")
                }

            }

        }

    }

}






