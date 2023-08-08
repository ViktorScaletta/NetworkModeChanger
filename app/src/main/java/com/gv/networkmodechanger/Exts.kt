package com.gv.networkmodechanger

import android.os.Build
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi

const val NETWORK_TYPE_BITMASK_GSM = (1 shl 16 - 1).toLong()
const val NETWORK_TYPE_BITMASK_GPRS = (1 shl 1 - 1).toLong()
const val NETWORK_TYPE_BITMASK_EDGE = (1 shl 2 - 1).toLong()
const val NETWORK_TYPE_BITMASK_HSUPA = (1 shl 9 - 1).toLong()
const val NETWORK_TYPE_BITMASK_HSDPA = (1 shl 8 - 1).toLong()
const val NETWORK_TYPE_BITMASK_HSPA = (1 shl 10 - 1).toLong()
const val NETWORK_TYPE_BITMASK_HSPAP = (1 shl 15 - 1).toLong()
const val NETWORK_TYPE_BITMASK_UMTS = (1 shl 3 - 1).toLong()
const val NETWORK_TYPE_BITMASK_TD_SCDMA = (1 shl 17 - 1).toLong()
const val  NETWORK_TYPE_BITMASK_LTE = (1 shl (13 - 1)).toLong()
const val NETWORK_TYPE_BITMASK_LTE_CA = (1 shl 19 - 1).toLong()

const val gsmBitmask = (NETWORK_TYPE_BITMASK_GSM
        or NETWORK_TYPE_BITMASK_GPRS
        or NETWORK_TYPE_BITMASK_EDGE)

const val wcdmaBitmask = (NETWORK_TYPE_BITMASK_HSUPA
        or NETWORK_TYPE_BITMASK_HSDPA
        or NETWORK_TYPE_BITMASK_HSPA
        or NETWORK_TYPE_BITMASK_HSPAP
        or NETWORK_TYPE_BITMASK_UMTS
        or NETWORK_TYPE_BITMASK_TD_SCDMA)

const val lteBitmask = (NETWORK_TYPE_BITMASK_LTE
        or NETWORK_TYPE_BITMASK_LTE_CA)

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