package com.example.parsexmlusingretrofitmac.model.feedModel

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "author", strict = false)
class Author constructor(): Serializable {
    @field:Element(name = "name")
    var name: String? = null

    @field:Element(name = "uri")
    var uri: String? = null
}