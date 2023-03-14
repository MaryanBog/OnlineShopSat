package com.example.onlineshopsat.presentation.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.onlineshopsat.R
import com.example.onlineshopsat.components.DialogWindow
import com.example.onlineshopsat.navigation.NavigationTree
import com.example.onlineshopsat.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavHostController,
    viewModel: SignViewModel = hiltViewModel()
) {

    var firstNameFieldState by remember { mutableStateOf("") }
    var lastNameFieldState by remember { mutableStateOf("") }
    var emailFieldState by remember { mutableStateOf("")    }
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
                            is PersonaState.Exists -> {
                                viewModel.onEmailValid(
                                    email = personal.email,
                                    lastName = personal.lastName,
                                    firstName = personal.firstName
                                )
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
            text = stringResource(id = R.string.sign_in),
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
        LabelTextField(
            textFieldState = lastNameFieldState,
            labelText = stringResource(id = R.string.last_name),
            onTextFieldChange = { lastNameFieldState = it }
        )
        Spacer(modifier = Modifier.height(35.dp))
        EmailTextField(
            emailFieldState = emailFieldState,
            onEmailFieldChange = { emailFieldState = it },
            labelText = stringResource(id = R.string.email)
        )
        Spacer(modifier = Modifier.height(35.dp))
        HomeButton(
            textHomeButton = stringResource(id = R.string.sign_in)
        ) {
            viewModel.isExisted(
                email = emailFieldState,
                firstName = firstNameFieldState,
                lastName = lastNameFieldState
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        ChipSection(navController = navController)
        Spacer(modifier = Modifier.height(70.dp))
        ClickIconButton(
            navController = navController,
            descriptionText = "Google icon",
            iconResource = R.drawable.ic_google,
            textResource = R.string.with_google
        )
        Spacer(modifier = Modifier.height(35.dp))
        ClickIconButton(
            navController = navController,
            descriptionText = "Apple icon",
            iconResource = R.drawable.ic_apple,
            textResource = R.string.with_apple
        )
    }

    DialogWindow(
        isVisible = isVisibleDialog,
        onDialogChange = { isVisibleDialog = it },
        errorMessage = errorMessage
    )
}

@Composable
fun ClickIconButton(
    navController: NavController,
    descriptionText: String,
    iconResource: Int,
    textResource: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(NavigationTree.Login.name) },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = descriptionText,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = stringResource(id = textResource),
            style = Typography.labelMedium,
            color = Color.Black
        )
    }
}

@Composable
fun ChipSection(
    navController: NavController
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.already_have),
            style = TextStyle(
                color = Color(0xFF808080),
                fontSize = 12.sp,
                fontStyle = FontStyle(R.font.montserrat_black)
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = R.string.log_in),
            style = TextStyle(
                color = Color(0xFF254FE6),
                fontSize = 12.sp,
                fontStyle = FontStyle(R.font.montserrat_black)
            ),
            modifier = Modifier
                .clickable { navController.navigate(NavigationTree.Login.name) }
        )
    }
}

@Composable
fun HomeButton(
    textHomeButton: String,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor,
            contentColor = TextButtonColor
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = textHomeButton,
            style = Typography.labelSmall
        )
    }
}

@Composable
fun LabelTextField(
    textFieldState: String,
    onTextFieldChange: (String) -> Unit,
    labelText: String
) {

    TextField(
        value = textFieldState,
        placeholder = {
            Text(
                text = labelText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.labelMedium
            )
        },
        onValueChange = onTextFieldChange,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(15.dp)),
    )
}

@Composable
fun EmailTextField(
    emailFieldState: String,
    onEmailFieldChange: (String) -> Unit,
    labelText: String
) {
    TextField(
        value = emailFieldState,
        placeholder = {
            Text(
                text = labelText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = Typography.labelMedium
            )
        },
        onValueChange = onEmailFieldChange,
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(0.dp)
            .clip(RoundedCornerShape(15.dp)),
    )
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    OnlineShopSatTheme {
        val navController = rememberNavController()
        SignInScreen(navController = navController)
    }
}