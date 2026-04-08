package com.erableto.mywikidexapp.ui

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun AboutScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center).padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val appPackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                val appPackageName = appPackageInfo.packageName

                val iconBitmap = remember {
                    val icon = context.packageManager.getApplicationIcon(appPackageName)
                    icon.toBitmap()
                }

                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .aspectRatio(1f)
                        .clickable {
                            //
                        },
                    painter = BitmapPainter(iconBitmap.asImageBitmap()),
                    contentDescription = "Logo de la app"
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column() {
                    val appName = context.applicationInfo.loadLabel(context.packageManager).toString()
                    val appVersionName = appPackageInfo.versionName
                    val appVersionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        appPackageInfo.longVersionCode
                    } else {
                        appPackageInfo.versionCode.toLong()
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "$appName $appVersionName [$appVersionCode]",
                        fontSize = 32.sp
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = appPackageName
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val images = listOf(
                    R.drawable.erableto_1,
                    R.drawable.erableto_2,
                    R.drawable.erableto_3,
                    R.drawable.erableto_4
                )

                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f)
                        .clickable {
                            //
                        },
                    painter = painterResource(images.random()),
                    contentDescription = "Erableto"
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Creada con ❤️ por Erableto",
                    fontSize = 32.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    MyWikiDexAppTheme() {
        AboutScreen()
    }
}