package com.example.parsexmlusingretrofitmac


import com.example.parsexmlusingretrofitmac.model.feedModel.Author
import com.example.parsexmlusingretrofitmac.model.feedModel.Entry
import org.simpleframework.xml.*
import java.io.Serializable


@Root(name = "feed", strict = false)
class AppFeed constructor() : Serializable   {
    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "updated")
    var updated: String? = null

    @field:Element(required = false, name = "author")
    var author: Author? = null

    @field:ElementList(inline = true, name = "entry")
    var entries: List<Entry>? = null


}





