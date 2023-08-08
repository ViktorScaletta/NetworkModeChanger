package com.gv.networkmodechanger

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gv.networkmodechanger.ui.theme.NetworkModeChangerTheme

class MainActivity : ComponentActivity() {

    private val telephonyManager by lazy {
        getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkModeChangerTheme {
                // A surface container using the 'background' color from the theme
                Column {
                    networkTypes.forEach {
                        NetworkTypeButton(networkType = it, telephonyManager = telephonyManager)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NetworkTypeButton(
    networkType: NetworkType,
    telephonyManager: TelephonyManager,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            /*val getITelephonyMethod = telephonyManager.javaClass.getDeclaredMethod("getITelephony")
            getITelephonyMethod.isAccessible = true
            val telephonyService = getITelephonyMethod.invoke(telephonyManager)
            *//*val getSubIdMethod =
                telephonyManager.javaClass.getDeclaredMethod("getSubId")
            val subId = getSubIdMethod.invoke(telephonyManager) as Int*//*
            val setAllowedNetworkTypesForReasonMethod =
                telephonyService.javaClass.getDeclaredMethod("setAllowedNetworkTypesForReason")
            setAllowedNetworkTypesForReasonMethod.invoke(
                telephonyManager,
                0,
                0,
                networkType.value
            )
*/
            telephonyManager.setAllowedNetworkTypesForReason(
                TelephonyManager.ALLOWED_NETWORK_TYPES_REASON_USER,
                networkType.value
            )
        },
        modifier = modifier
    ) {
        Text(networkType.name)
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkTypeButtonsPreview() {
    NetworkModeChangerTheme {
        //NetworkTypeButton(networkType = it, telephonyManager = telephonyManager)
    }
}