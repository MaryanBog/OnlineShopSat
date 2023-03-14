package com.example.onlineshopsat.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
fun FlashSaleSection(
    receivedError: Boolean
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(text = stringResource(id = R.string.flash_sale), style = TextStyle(
                letterSpacing = 0.7.sp,
                fontSize = 15.sp,
                color = Color(0xFF040402),
                fontWeight = FontWeight.Medium
            ), modifier = Modifier.clickable {

            })
            Text(text = stringResource(id = R.string.view_all), style = TextStyle(
                letterSpacing = 0.7.sp,
                fontSize = 10.sp,
                color = Color(0xFF808080),
                fontWeight = FontWeight.Medium
            ), modifier = Modifier.clickable {

            })
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
            FlashCardSections()
        }
    }
}

@Composable
fun FlashCardSections(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val saleList = viewModel.saleFlow.collectAsState().value

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        items(saleList.size) { index ->
            Box(modifier = Modifier
                .height(220.dp)
                .width(170.dp)
                .clip(RoundedCornerShape(10))
                .clickable { }) {
                AsyncImage(
                    model = saleList[index].imageUrl,
                    contentDescription = "Card goods",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.wrapContentSize()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.im_ellipse),
                        contentDescription = "Image Ellipse",
                        modifier = Modifier
                            .size(26.dp)
                            .clip(CircleShape)
                            .clickable {  }
                    )
                    Text(
                        modifier = Modifier
                            .width(50.dp)
                            .height(20.dp)
                            .background(
                                color = Color.Red, shape = RoundedCornerShape(9.dp)
                            )
                            .padding(2.dp)
                            .clickable { },
                        text = "${saleList[index].discount}% off",
                        style = TextStyle(
                            color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .width(170.dp)
                        .height(110.dp)
                        .align(Alignment.BottomCenter)
                        .padding(10.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(35.dp)
                            .height(12.dp)
                            .padding(top = 3.dp)
                            .background(
                                color = Color(0xFFC4C4C4), shape = RoundedCornerShape(5.dp)
                            )
                            .clickable { }, text = saleList[index].category, style = TextStyle(
                            color = Color(0xFF070604),
                            fontSize = 6.sp,
                            fontWeight = FontWeight.SemiBold
                        ), textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .width(75.dp)
                            .height(26.dp)
                            .padding(top = 3.dp), text = saleList[index].name, style = TextStyle(
                            color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.SemiBold
                        ), maxLines = 2
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .width(40.dp)
                            .height(15.dp),
                        text = "$ ${saleList[index].price}",
                        style = TextStyle(
                            color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.SemiBold
                        ),
                    )
                    Row(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        verticalAlignment = CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_love),
                            contentDescription = "icon love",
                            modifier = Modifier
                                .size(28.dp)
                                .offset(y = 2.dp)
                                .background(color = Color(0xFFE5E9EF), shape = CircleShape)
                                .padding(6.dp)
                                .clickable { },
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "icon plus",
                            modifier = Modifier
                                .size(35.dp)
                                .offset(x = 2.dp, y = 2.dp)
                                .background(color = Color(0xFFE5E9EF), shape = CircleShape)
                                .padding(6.dp)
                                .clickable { },
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}