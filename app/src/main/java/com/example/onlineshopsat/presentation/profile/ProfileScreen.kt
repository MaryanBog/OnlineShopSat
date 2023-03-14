package com.example.onlineshopsat.presentation.profile

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.onlineshopsat.R
import com.example.onlineshopsat.navigation.NavigationTree
import com.example.onlineshopsat.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavController, viewModel: ProfileViewModel = hiltViewModel()
) {

    val photoUri = viewModel.photoUri.collectAsState(initial = null).value
    val scope = rememberCoroutineScope()

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            viewModel.getPhoto(photoUri = it)
        })

    val readLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { permissionGranted ->
                if (permissionGranted) {
                    imageLauncher.launch("image/*")
                } else {

                }
            })


    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            viewModel.getPhoto(photoUri = it)
        },
    )

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = BackgroundColor)
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        HeadProfile(
            navController = navController, photoUri = photoUri
        )
        Spacer(modifier = Modifier.height(36.dp))
        UploadButton {
            scope.launch {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pickImage.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                } else {
                    readLauncher.launch(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            TradeIconView(
                idCircleIcon = R.drawable.ic_credit_card,
                idStringRes = R.string.trade_store,
                idArrowIcon = R.drawable.ic_arrow_right,
                idPriceRes = null
            ){}
            TradeIconView(
                idCircleIcon = R.drawable.ic_credit_card,
                idStringRes = R.string.payment_method,
                idArrowIcon = R.drawable.ic_arrow_right,
                idPriceRes = null
            ){}
            TradeIconView(
                idCircleIcon = R.drawable.ic_credit_card,
                idStringRes = R.string.balance,
                idArrowIcon = null,
                idPriceRes = R.string.price
            ){}
            TradeIconView(
                idCircleIcon = R.drawable.ic_credit_card,
                idStringRes = R.string.trade_history,
                idArrowIcon = R.drawable.ic_arrow_right,
                idPriceRes = null
            ){}
            TradeIconView(
                idCircleIcon = R.drawable.ic_arrow_circle,
                idStringRes = R.string.restore_purchase,
                idArrowIcon = R.drawable.ic_arrow_right,
                idPriceRes = null
            ){}
            TradeIconView(
                idCircleIcon = R.drawable.ic_question,
                idStringRes = R.string.help,
                idArrowIcon = null,
                idPriceRes = null
            ){}
            TradeIconView(
                idCircleIcon = R.drawable.ic_log_in,
                idStringRes = R.string.log_out,
                idArrowIcon = null,
                idPriceRes = null
            ){
                navController.navigate(NavigationTree.Sign_In.name)
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun TradeIconView(
    @DrawableRes idCircleIcon: Int,
    @StringRes idStringRes: Int,
    @DrawableRes idArrowIcon: Int?,
    @StringRes idPriceRes: Int?,
    onClickIcon: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 46.dp, bottom = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(color = IconBackgroundColor)
                .padding(12.dp)
                .clickable { onClickIcon.invoke() })
            {
                Icon(
                    painter = painterResource(id = idCircleIcon),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = idStringRes),
                style = Typography.labelMedium,
                color = Color(0xFF040402),
                fontSize = 16.sp
            )
        }
        if (idArrowIcon == null) {
            if (idPriceRes == null) {
                Spacer(modifier = Modifier.size(20.dp))
            } else {
                Text(
                    text = stringResource(id = idPriceRes),
                    style = Typography.labelMedium,
                    color = Color(0xFF040402),
                    fontSize = 16.sp
                )
            }
        } else {
            Icon(
                painter = painterResource(id = idArrowIcon), contentDescription = ""
            )
        }
    }
}

@Composable
fun UploadButton(
    onButtonClick: () -> Unit
) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .padding(horizontal = 44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor, contentColor = TextButtonColor
        ),
        shape = RoundedCornerShape(25.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "share",
            tint = Color.White,
            modifier = Modifier.offset(x = (-70).dp)
        )
        Text(
            text = stringResource(id = R.string.upload_item),
            style = Typography.labelSmall,
        )
    }
}

@Composable
fun HeadProfile(
    navController: NavController, photoUri: Uri?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "arrow left",
            modifier = Modifier.clickable {
                navController.navigate(NavigationTree.Sign_In.name)
            })
        CenterProfileSection(
            photoUri = photoUri
        )
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Composable
fun CenterProfileSection(
    photoUri: Uri?
) {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.profile),
            style = Typography.bodyMedium,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(19.dp))
        if (photoUri == null) {
            Image(
                painter = painterResource(id = R.drawable.im_profile),
                contentDescription = "profile image",
                modifier = Modifier.size(62.dp)
            )
        } else {
            AsyncImage(
                model = photoUri,
                contentDescription = "profile image",
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(id = R.string.change_photo),
            style = Typography.labelMedium,
            fontSize = 8.sp,
            color = Color(0xFF808080),
            modifier = Modifier.padding(top = 6.dp)
        )
        Spacer(modifier = Modifier.height(17.dp))
        Text(
            text = stringResource(id = R.string.satria_adhi),
            style = Typography.bodyMedium,
            fontSize = 17.sp
        )
    }
}
