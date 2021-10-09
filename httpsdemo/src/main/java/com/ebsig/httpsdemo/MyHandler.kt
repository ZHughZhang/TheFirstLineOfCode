package com.ebsig.httpsdemo

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

/**
 *   @Auther:  asad
 *   @Date:  2021/10/08 15:08
 *   @Description:  TODO
 *   @ClassName:  com.ebsig.httpsdemo
 *   @PackageName:  MyHandler
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class MyHandler:DefaultHandler() {
    override fun startDocument() {
        super.startDocument()
    }

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
    }

    override fun endDocument() {
        super.endDocument()
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
    }
}