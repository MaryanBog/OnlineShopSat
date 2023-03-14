package com.example.onlineshopsat.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.onlineshopsat.R
import com.example.onlineshopsat.ui.theme.Typography

@Composable
fun LatestSection(
    receivedError: Boolean
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.latest_deals),
                style = TextStyle(
                    letterSpacing = 0.7.sp,
                    fontSize = 15.sp,
                    color = Color(0xFF040402),
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.clickable {

                }
            )
            Text(
                text = stringResource(id = R.string.view_all),
                style = TextStyle(
                    letterSpacing = 0.7.sp,
                    fontSize = 10.sp,
                    color = Color(0xFF808080),
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.clickable {

                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (receivedError){
            Text(
                text = "Can't get data",
                style = Typography.labelMedium,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 36.dp, vertical = 24.dp)
            )
        } else {
            LatestCardSections()
        }
    }
}

@Composable
fun LatestCardSections(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val latestList = viewModel.latestFlow.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.loadData()
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        items(latestList.size) { index ->
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(10))
                    .clickable { }
            ) {
                AsyncImage(
                    model = latestList[index].imageUrl,
                    contentDescription = "Card goods",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.wrapContentSize()
                )
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(75.dp)
                        .align(Alignment.BottomCenter)
                        .padding(7.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(35.dp)
                            .height(12.dp)
                            .padding(top = 3.dp)
                            .background(
                                color = Color(0xFFC4C4C4),
                                shape = RoundedCornerShape(5.dp)
                            )
                            .clickable { },
                        text = latestList[index].category,
                        style = TextStyle(
                            color = Color(0xFF070604),
                            fontSize = 6.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(75.dp)
                            .height(26.dp)
                            .padding(top = 3.dp),
                        text = latestList[index].name,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        maxLines = 2
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .width(40.dp)
                            .height(15.dp),
                        text = "$ ${latestList[index].price}",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "icon plus",
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(x = 2.dp, y = 2.dp)
                            .background(color = Color(0xFFE5E9EF), shape = CircleShape)
                            .padding(4.dp)
                            .clickable { },
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}