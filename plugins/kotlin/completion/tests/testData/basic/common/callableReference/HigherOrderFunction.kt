fun xfoo(p: (String, Char) -> Unit){}

fun test() {
    ::xfo<caret>
}

// EXIST: { lookupString:"xfoo", itemText: "xfoo", tailText: "(p: (String, Char) -> Unit) (<root>)", typeText:"Unit", icon: "nodes/function.svg"}
// NOTHING_ELSE
