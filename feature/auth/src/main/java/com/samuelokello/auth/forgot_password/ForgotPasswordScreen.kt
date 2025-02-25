package com.samuelokello.auth.forgot_password

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samuelokello.feature.auth.R

@Composable
fun ForgotPasswordScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    ForgotPasswordScreenContent(
        modifier = modifier,
        onClickForgotPassword = {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.no_password_request_link),
                    Toast.LENGTH_LONG,
                ).show()
        },
    )
}

@Composable
private fun ForgotPasswordScreenContent(
    modifier: Modifier = Modifier,
    onClickForgotPassword: () -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            Column(Modifier.padding(end = 16.dp)) {
                Text(
                    text =
                        stringResource(
                            R.string.please_enter_an_email_address,
                        ),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = { },
                label = {
                    Text(text = "Email")
                },
                keyboardOptions =
                    KeyboardOptions(
                        autoCorrectEnabled = true,
                        keyboardType = KeyboardType.Email,
                    ),
            )
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickForgotPassword,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                    text = "Continue",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
