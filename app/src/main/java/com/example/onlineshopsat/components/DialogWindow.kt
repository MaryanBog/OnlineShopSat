package com.example.onlineshopsat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onlineshopsat.presentation.login.LoginViewModel
import com.example.onlineshopsat.presentation.sign.SignViewModel
import com.example.onlineshopsat.ui.theme.BackgroundColor
import com.example.onlineshopsat.ui.theme.DialogBackgroundColor
import com.example.onlineshopsat.ui.theme.Typography

@Composable
fun DialogWindow(
    isVisible: Boolean,
    onDialogChange: (Boolean) -> Unit,
    signViewModel: SignViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    errorMessage: String
) {


    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DialogBackgroundColor)
                .padding(horizontal = 36.dp)
                .clickable {
                    onDialogChange.invoke(false)
                    signViewModel.reternPersonStateStart()
                    loginViewModel.reternPersonStateStart()
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = BackgroundColor, shape = RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = errorMessage,
                    style = Typography.labelMedium,
                    color = Color.Black
                )
            }
        }
    }
}