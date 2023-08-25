package com.basarcelebi.cryptoapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.basarcelebi.cryptoapp.models.BaseModel
import com.basarcelebi.cryptoapp.ui.theme.Green
import com.basarcelebi.cryptoapp.ui.theme.Red
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    viewModel:HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit){
        viewModel.getListing()
    }
    val data by viewModel.response.collectAsState()

    Column(modifier = Modifier
        .padding(horizontal = 14.dp)) {
        when(val result = data){
            is BaseModel.Loading->{
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator(color = Color.White)
                }
            }
            is BaseModel.Success->{
                Text(text = "24H Currencies")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    items(result.data.data.cryptoCurrencyList.sortedBy { it.quotes.first().percentChange24h }){

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .shadow(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable { }
                            .padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Box(modifier = Modifier
                                    .size(55.dp)
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(MaterialTheme.colorScheme.secondary),
                                    contentAlignment = Alignment.Center){
                                    AsyncImage(modifier = Modifier.size(40.dp),model = ImageRequest.Builder(LocalContext.current), contentDescription = null )
                                }
                                Column {
                                    Text(it.name, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    Text(it.symbol, fontSize = 12.sp, color = Color.Gray)
                                }
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(text = "${((it.quotes.first().price*100).roundToInt())/100.0}$")
                                Spacer(modifier = Modifier.height(2.dp))
                                val percent = ((it.quotes.first().percentChange24h*100).roundToInt())/100
                                val color = if(percent>= 0) Green else Red
                                Text(text = )

                            }

                        }
                    }
                }
            }
            is BaseModel.Error->{
                Text(result.error)
            }
            null->{

            }
        }

    }
    

    
}