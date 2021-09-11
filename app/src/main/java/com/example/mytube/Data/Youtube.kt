package com.example.mytube.Data

import android.widget.TextView
import java.io.Serializable

class Youtube(
    val title : String? = null,
    val content : String? = null,
    val video : String? = null,
    val thumbnail : String? = null
):Serializable