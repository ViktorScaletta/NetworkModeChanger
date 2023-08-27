package com.gv.networkmodechanger

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.getSystemService
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

@Composable
fun NetworkTypeButton(
    networkType: NetworkType,
    telephonyManager: TelephonyManager,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            setNetworkType(
                telephonyManager = telephonyManager,
                networkType = networkType
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
        // A surface container using the 'background' color from the theme
        Column {
            networkTypes.forEach {
                NetworkTypeButton(
                    networkType = it,
                    telephonyManager = Activity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                )
            }
        }
    }
}

private fun setNetworkType(
    telephonyManager: TelephonyManager,
    networkType: NetworkType
) = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
        setNetworkTypeForAndroid13(
            telephonyManager = telephonyManager,
            networkType = networkType
        )
    }
    else -> {
        setNetworkTypeForAndroid12AndBelow(
            telephonyManager = telephonyManager,
            networkType = networkType
        )
    }
}

@SuppressLint("SoonBlockedPrivateApi")
private fun setNetworkTypeForAndroid13(
    telephonyManager: TelephonyManager,
    networkType: NetworkType
) {
    val getITelephonyMethod = telephonyManager.javaClass.getDeclaredMethod("getITelephony")
    getITelephonyMethod.isAccessible = true
    val telephonyService = getITelephonyMethod.invoke(telephonyManager)
    val getSubIdMethod =
        telephonyManager.javaClass.getDeclaredMethod("getSubId")
    getSubIdMethod.isAccessible = true
    val subId = getSubIdMethod.invoke(telephonyManager) as Int
    val setAllowedNetworkTypesForReasonMethod = telephonyService.javaClass.getDeclaredMethod(
        "setAllowedNetworkTypesForReason",
        Int::class.java,
        Int::class.java,
        Long::class.java
    )
    setAllowedNetworkTypesForReasonMethod.invoke(
        telephonyService,
        subId,
        0,
        networkType.value
    )
}

private fun setNetworkTypeForAndroid12AndBelow(
    telephonyManager: TelephonyManager,
    networkType: NetworkType
) {
    val setAllowedNetworkTypesForReasonMethod = telephonyManager.javaClass.getDeclaredMethod(
        "setPreferredNetworkTypeBitmask",
        Long::class.java
    )
    setAllowedNetworkTypesForReasonMethod.invoke(
        telephonyManager,
        networkType.value
    )
}