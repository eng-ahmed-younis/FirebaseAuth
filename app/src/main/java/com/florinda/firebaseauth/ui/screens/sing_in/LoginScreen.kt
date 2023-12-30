
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.florinda.firebaseauth.R
import com.florinda.firebaseauth.ui.screens.sing_in.LoginViewModel
import com.florinda.firebaseauth.ui.screens.sing_in.SignInState
import com.florinda.firebaseauth.ui.theme.AppBlackColor
import com.florinda.firebaseauth.ui.theme.AppBlueColor
import com.florinda.firebaseauth.ui.theme.AppFocusColor
import com.florinda.firebaseauth.ui.theme.AppUnFocusedColor
import com.florinda.firebaseauth.ui.theme.BackgroundColor
import com.florinda.firebaseauth.ui.theme.FacebookButtonColor
import com.florinda.firebaseauth.ui.theme.GoogleButtonColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state : Flow<SignInState>
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    val onSignInButtonClicked: (String, String) -> Unit = {email, password ->
        Log.i("LoginScreen", "Email: $email, Password: $password")
    }
    val onGoogleSignInButtonClicked: () -> Unit = {}
    val onFacebookSignInButtonClicked: () -> Unit = {}
    val onSignUpButtonClicked: () -> Unit = {}
    val onForgortPasswordButtonClicked: () -> Unit = {}

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Login ring",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .offset(x = 10.dp, y = (-5).dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Sample Logo",
                modifier = Modifier.padding(vertical = 24.dp)
            )

            Text(
                text = stringResource(id = R.string.welcome_back),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = 28.sp,
                color = AppBlackColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.sign_in_continue),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                color = AppBlackColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("enter email") },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppFocusColor,
                    unfocusedBorderColor = AppUnFocusedColor
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = AppFocusColor
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(24.dp))
            val visibilityIcon = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("passwoed") },
                placeholder = { Text("enter password") },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppFocusColor,
                    unfocusedBorderColor = AppUnFocusedColor
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Email Icon",
                        tint = AppFocusColor
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = visibilityIcon, description)
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it }
                    )
                    Text(
                        text = "remember me",
                        fontSize = 14.sp,
                        color = AppFocusColor
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "forget",
                    fontSize = 14.sp,
                    color = AppBlueColor,
                    modifier = Modifier
                        .clickable { onForgortPasswordButtonClicked()}
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onSignInButtonClicked(email.text, password.text) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppBlueColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(
                    text = "sign in",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(Modifier.weight(0.5f))
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "continue",
                    style = TextStyle(
                        color = AppFocusColor,
                        fontSize = 12.sp,
                    )
                )
                Spacer(Modifier.width(12.dp))
                Divider(Modifier.weight(0.5f))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onGoogleSignInButtonClicked() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GoogleButtonColor),
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .weight(0.5f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "Google icon",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Google",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { onFacebookSignInButtonClicked() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FacebookButtonColor),
                    modifier = Modifier
                        .height(45.dp)
                        .fillMaxWidth()
                        .weight(0.5f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "Facebook icon",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Facebook",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "do_not_have_account",
                    color = AppBlackColor,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "sign up",
                    color = AppBlueColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onSignUpButtonClicked() }
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
   // LoginScreen(flowOf(SignInState(false,null,null))
}