package com.ebsig.httpsdemo

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

/**
 *   @Auther:  asad
 *   @Date:  2021/10/08 15:11
 *   @Description:  TODO
 *   @ClassName:  com.ebsig.httpsdemo
 *   @PackageName:  ContentHandler:DefaultHandler()
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class ContentHandler: DefaultHandler() {


    private val TAG: String = ContentHandler::class.java.canonicalName
    private var nodeName:String? = ""
    private lateinit var id:StringBuilder
    private lateinit var name:StringBuilder
    private lateinit var version:StringBuilder

    override fun startDocument() {
        super.startDocument()
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)
            nodeName = localName

        Log.e(TAG, "startElement: uri $uri" )
        Log.e(TAG, "startElement: localName $localName" )
        Log.e(TAG, "startElement: qName $qName" )
        Log.e(TAG, "startElement: attributes $attributes" )
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        when (nodeName) {
            "id"-> id.append(ch,start,length)
            "name"->name.append(ch,start,length)
            "version"->version.append(ch,start,length)
        }
    }


    override fun endDocument() {
        super.endDocument()
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        if ("app" == localName) {
            Log.e(TAG, "endElement: id is ${id.toString().trim()}" )
            Log.e(TAG, "endElement: name is ${name.toString().trim()}" )
            Log.e(TAG, "endElement: version is ${version.toString().trim()}" )
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }
}