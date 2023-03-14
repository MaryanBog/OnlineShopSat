package com.example.onlineshopsat.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.onlineshopsat.R
import com.example.onlineshopsat.components.DialogWindow
import com.example.onlineshopsat.navigation.NavigationTree
import com.example.onlineshopsat.presentation.sign.HomeButton
import com.example.onlineshopsat.presentation.sign.LabelTextField
import com.example.onlineshopsat.presentation.sign.PersonaState
import com.example.onlineshopsat.ui.theme.BackgroundColor
import com.example.onlineshopsat.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    var firstNameFieldState by remember { mutableStateOf("") }
    var passwordFieldState by remember { mutableStateOf("") }
    var isVisibleDialog by remember { mutableStateOf(false) }
    val errorMessage = viewModel.errorMessage.collectAsState().value

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                lifecycleOwner.lifecycleScope.launch {
                    viewModel.statePersonal.collect { personal ->
                        when (personal) {
                            PersonaState.Error -> {
                                isVisibleDialog = true
                            }
                            PersonaState.Success -> {
                                navController.navigate(NavigationTree.Page_1.name)
                            }
                            PersonaState.Enter -> {
                                viewModel.onEnterAccount()
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
            .padding(42.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(87.dp))
        Text(
            text = stringResource(id = R.string.welcome_back),
            style = Typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(70.dp))
        LabelTextField(
            textFieldState = firstNameFieldState,
            labelText = stringResource(id = R.string.first_name),
            onTextFieldChange = { firstNameFieldState = it }
        )
        Spacer(modifier = Modifier.height(35.dp))
        LabelIconTextField(
            textFieldState = passwordFieldState,
            onTextFieldChange = { passwordFieldState = it },
            labelText = stringResource(id = R.string.password)
        )
        Spacer(modifier = Modifier.height(115.dp))
        HomeButton(
            textHomeButton = stringResource(id = R.string.login)
        ) {
            viewModel.isExisted(firstName = firstNameFieldState)
        }
    }

    DialogWindow(
        isVisible = isVisibleDialog,
        onDialogChange = { isVisibleDialog = it },
        errorMessage = errorMessage
    )
}

@Composable
fun LabelIconTextField(
    textFieldState: String,
    onTextFieldChange: (String) -> Unit,
    labelText: String
) {

    var visualTransformation by remember { mutableStateOf(true) }

    TextField(
        value = textFieldState,
        placeholder = {
            Text(
                text = labelText,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 14.dp),
                textAlign = TextAlign.Center,
                style = Typography.labelMedium
            )
        },
        onValueChange = onTextFieldChange,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(15.dp)),
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    visualTransformation = visualTransformation != true
                },
                painter = painterResource(id = R.drawable.ic_eye_off),
                contentDescription = "Eye off"
            )
        },
        visualTransformation = if (visualTransformation) PasswordVisualTransformation('*')
        else VisualTransformation.None
    )
}