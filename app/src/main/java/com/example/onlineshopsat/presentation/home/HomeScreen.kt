package com.example.onlineshopsat.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onlineshopsat.R
import com.example.onlineshopsat.ui.theme.ButtonColor
import com.example.onlineshopsat.ui.theme.IconBackgroundColor
import com.example.onlineshopsat.ui.theme.Typography

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val receivedError = viewModel.receivedError.collectAsState(false).value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeadSection(
            navController = navController
        )
        SearchField()
        Spacer(modifier = Modifier.height(17.dp))
        CategoriesStore(
            items = listOf(
                StoreItem(
                    name = stringResource(id = R.string.phones),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_phones)
                ),
                StoreItem(
                    name = stringResource(id = R.string.headphones),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_headphones)
                ),
                StoreItem(
                    name = stringResource(id = R.string.games),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_games)
                ),
                StoreItem(
                    name = stringResource(id = R.string.cars),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_cars)
                ),
                StoreItem(
                    name = stringResource(id = R.string.furniture),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_furniture)
                ),
                StoreItem(
                    name = stringResource(id = R.string.kids),
                    icon = ImageVector.vectorResource(id = R.drawable.ic_kids)
                ),
            )
        )
        Spacer(modifier = Modifier.height(29.dp))
        LatestSection(receivedError)
        Spacer(modifier = Modifier.height(17.dp))
        FlashSaleSection(receivedError)
    }
}

@Composable
fun HeadSection(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp, start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Row(
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.trade_by),
                    style = Typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.data),
                    style = Typography.bodyMedium,
                    color = ButtonColor
                )
            }
            BoxProfil()
        }
    }
}

@Composable
fun CategoriesStore(
    items: List<StoreItem>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        items(
            count = items.size
        ) { index ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(end = 14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = IconBackgroundColor)
                        .padding(16.dp)
                        .clickable { }
                ) {
                    Icon(
                        imageVector = items[index].icon,
                        contentDescription = items[index].name,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Text(
                    text = items[index].name,
                    color = Color(0xFFA6A7AB),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun SearchField() {
    var value by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 9.dp, start = 56.dp, end = 56.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            decorationBox = { innerTextField ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(
                            color = Color(0xFFF5F6F6),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 16.dp)
                ) {
                    innerTextField()
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable { }
                    )
                }
            },
            singleLine = true
        )
        if (value.isEmpty()) {
            Text(
                text = "What are you looking for ?",
                color = Color(0xFF5B5B5B),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BoxProfil() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.im_profile),
            contentDescription = "Image Profile",
            modifier = Modifier.size(34.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 6.dp)
        ) {
            Text(
                text = stringResource(id = R.string.location),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_tick),
                contentDescription = "Tick"
            )
        }
    }
}