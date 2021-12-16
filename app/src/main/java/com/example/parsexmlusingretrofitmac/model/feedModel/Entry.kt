package com.example.parsexmlusingretrofitmac.model.feedModel

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "entry", strict = false)
class Entry constructor() : Serializable {

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "name")
    var name: String? = null

    @field:ElementList( name = "image",inline = true)
    var imageUrl: List<Image>? = null

    @field:Element(name = "summary")
    var summary: String? = null

}