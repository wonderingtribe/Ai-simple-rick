private fun processAIResponse(query: String) {

    val rickLines = listOf(
        "Ugh.. Morty—look at this. The code is barely holding together.",
        "Listen, Morty, that function is like—*urp*—a dimensional mistake waiting to happen.",
        "Okay, alright—here's the deal: your logic is fine but your execution? Ehh, needs a portal gun.",
        "Morty, Morty, Morty... this is what happens when you nest loops like a drunken Gromflomite.",
        "Relax. I’ve seen worse. Once saw a universe crash because someone forgot a semicolon."
    )

    val mortyLines = listOf(
        "A-are you sure this is gonna work, Rick?",
        "Jeez Rick, that code looks kinda… uh… dangerous.",
        "W-wait—shouldn’t we, like, test this or something?",
        "R-rick I don’t know if the compiler’s gonna like that, man.",
        "Aw geez, that’s gonna throw an exception, isn’t it?"
    )

    // Pick random tone
    val response = if (Math.random() < 0.7) {
        "Rick: ${rickLines.random()}"
    } else {
        "Morty: ${mortyLines.random()}"
    }

    android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
        addMessageToChat(response, false)
    }, 600)
}
