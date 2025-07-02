package org.example.white.presentation.onBoarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.example.white.presentation.ParentScreen
import org.example.white.presentation.`common-components`.Popup
import org.example.white.ui.theme.AppTheme
import org.example.white.ui.theme.labelNormal

class OnBoardingScreen : ParentScreen() {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<OnBoardingViewModel>()
        val navigator = LocalNavigator.current

        var dialogText by remember { mutableStateOf<String?>(null) }
        var email by remember { mutableStateOf("abuMuslem") }
        var password by remember { mutableStateOf("123456") }
        var showPassword by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            viewModel.uiEvent.collect {
                when (it) {
                    is OnBoardingUiEvent.Error -> dialogText = it.errorText
                    OnBoardingUiEvent.Success -> navigator?.push(ChatListScreen())
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = AppTheme.colorScheme.secondary,
                        contentColor = AppTheme.colorScheme.third
                    ),
                    elevation = CardDefaults.cardElevation(6.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "تذكر الخطأ الأول هو الأخير",
                            style = AppTheme.type.titleLarge
                        )
                    }
                }


                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("احساب", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Preview, contentDescription = null, tint = Color.Gray)
                    },
                    singleLine = true,
                    textStyle = labelNormal(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colorScheme.secondary),
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("كلمة السر", color = Color.Gray) },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray)
                    },
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    },
                    singleLine = true,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    textStyle = labelNormal(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colorScheme.secondary),
                )

                Button(
                    onClick = { viewModel.login(email, password) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colorScheme.third,
                        contentColor = Color.Black
                    )
                ) {
                    Text("تسجيل الدخول", style = labelNormal())
                }
            }

            if (dialogText != null) {
                Popup(dialogText) {
                    dialogText = null
                }
            }
        }
    }
}
