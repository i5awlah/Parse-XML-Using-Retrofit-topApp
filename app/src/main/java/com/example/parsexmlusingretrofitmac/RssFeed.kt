package com.example.parsexmlusingretrofitmac


import org.simpleframework.xml.*
import java.io.Serializable


@Root(name = "feed", strict = false)
class RssFeed constructor() : Serializable   {
    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "updated")
    var updated: String? = null

    @field:Element(required = false, name = "author")
    var author: Author? = null

    @field:ElementList(inline = true, name = "entry")
    var entries: List<Entry>? = null


}

@Root(name = "entry", strict = false)
class Entry constructor() : Serializable {

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "name")
    var name: String? = null

    @field:Element(name = "summary")
    var summary: String? = null

}

@Root(name = "author", strict = false)
class Author constructor(): Serializable {
    @field:Element(name = "name")
    var name: String? = null

    @field:Element(name = "uri")
    var uri: String? = null
    override fun toString(): String {
        return "Author{" +
                "name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                '}'
    }
}