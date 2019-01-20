package com.mobile.wattoeat.wattoeat.options

enum class Type {
    RESTAURANT,
    HOME
}

data class Option
@JvmOverloads constructor(
        var name: String = "",
        var timesSelected: Int = 0,
        var image: String = "",
        var type: Enum<Type> = Type.HOME,
        var added_by: String = ""
)