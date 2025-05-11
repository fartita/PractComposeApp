package com.example.practcomposeapp.ui.theme
import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.provider.Settings.Secure.getString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.practcomposeapp.data.Contact
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.practcomposeapp.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    primaryContainer = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    primaryContainer = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PractComposeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    contact: Contact,
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = { Column(){
            TopAppBar(title = {Text(text=stringResource(R.string.app_name))}, colors = TopAppBarDefaults.topAppBarColors(containerColor = colorScheme.primary, titleContentColor = Color.White))
            ContactDetails(contact)
        }}
    )
}



@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowImage(contact)
        if (contact.surname.isNullOrEmpty()) {
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = contact.name
            )
        } else {
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = "${contact.name} ${contact.surname}"
            )
        }
        FamilyShow(contact.isFavorite, contact.familyName)
        Spacer(modifier = Modifier.height(40.dp) )
        InfoRow(R.string.phone, contact.phone)
        InfoRow(R.string.address, contact.address)
        if (!contact.email.isNullOrEmpty()) InfoRow(R.string.email, contact.email)
    }
}

@Composable
fun FamilyShow(isFavorite: Boolean, familyName: String) {
    Row {
        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = familyName
        )
        if (isFavorite) Image(
            modifier = Modifier
                .padding(start = 15.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = android.R.drawable.star_big_on),
            contentDescription = null
        )
    }

}

@Composable
fun ShowImage(contact: Contact) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(100.dp)
    ) {
        if (contact.imageRes != null) {
            Image(
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center,
                painter = painterResource(contact.imageRes),
                contentDescription = null
            )
        } else RoundInitials(contact.name.take(1) + contact.familyName.take(1))
    }
}

@Composable
fun RoundInitials(initials: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            tint = Color.LightGray
        )
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = initials
        )
    }
}

@Composable
fun InfoRow(text: Int, content: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            modifier = Modifier.weight(1F),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            text = stringResource(text) + ":"
        )
        Text(
            modifier = Modifier
                .weight(1F)
                .padding(start = 8.dp),
            text = content
        )
    }
}
