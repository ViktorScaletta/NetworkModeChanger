package com.gv.networkmodechanger

import android.os.Build
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_1xRTT
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_CDMA
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_EDGE
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_EHRPD
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_EVDO_0
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_EVDO_A
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_EVDO_B
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_GPRS
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_GSM
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_HSDPA
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_HSPA
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_HSPAP
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_HSUPA
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_IWLAN
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_LTE
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_LTE_CA
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_TD_SCDMA
import android.telephony.TelephonyManager.NETWORK_TYPE_BITMASK_UMTS
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
const val gsmBitmask = (NETWORK_TYPE_BITMASK_GSM
        or NETWORK_TYPE_BITMASK_GPRS
        or NETWORK_TYPE_BITMASK_EDGE)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
const val wcdmaBitmask = (NETWORK_TYPE_BITMASK_HSUPA
        or NETWORK_TYPE_BITMASK_HSDPA
        or NETWORK_TYPE_BITMASK_HSPA
        or NETWORK_TYPE_BITMASK_HSPAP
        or NETWORK_TYPE_BITMASK_UMTS
        or NETWORK_TYPE_BITMASK_TD_SCDMA)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
const val lteBitmask = (NETWORK_TYPE_BITMASK_LTE
        or NETWORK_TYPE_BITMASK_LTE_CA)

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
var networkTypes = listOf(
    NetworkType("2G", gsmBitmask),
    NetworkType("3G", wcdmaBitmask),
    NetworkType("LTE", lteBitmask),
    NetworkType("2G/3G", gsmBitmask or wcdmaBitmask),
    NetworkType("2G/3G/LTE", gsmBitmask or wcdmaBitmask or lteBitmask)
)

data class NetworkType(
    val name: String,
    val value: Long
)