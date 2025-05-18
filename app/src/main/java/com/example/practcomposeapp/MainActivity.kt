package com.example.practcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practcomposeapp.data.Contact
import com.example.practcomposeapp.ui.theme.ContactDetails
import com.example.practcomposeapp.ui.theme.PractComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           ContactDetailsPreviewSecond()
        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreview() {
        PractComposeAppTheme(contact = Contact(
            name = "Евгений",
            surname = "Андреевич",
            familyName = "Лукашин",
            phone = "+7 495 495 95 95",
            address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
            isFavorite = true,
            email = "lucashin@practicum.ru"
        ))
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreviewSecond() {
        PractComposeAppTheme(
            contact = Contact(
                name = "Василий",
                familyName = "Кузякин",
                phone = "+792321",
                address = "Ивановская область, дер. Крутово, д.4",
                imageRes = R.drawable.car,
            )
        )
    }
}

