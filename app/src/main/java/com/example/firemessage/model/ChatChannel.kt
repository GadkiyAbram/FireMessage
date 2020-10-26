package com.example.firemessage.model

data class ChatChannel(val userId: MutableList<String>) {
    constructor() : this(mutableListOf())
}