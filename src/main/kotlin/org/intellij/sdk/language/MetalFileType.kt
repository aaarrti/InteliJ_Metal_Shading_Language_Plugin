package org.intellij.sdk.language

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader

class MetalLanguage private constructor() : Language("Metal") {
    companion object {
        val INSTANCE: MetalLanguage = MetalLanguage()
    }
}

object MetalIcons {
    val FILE = IconLoader.getIcon("/META-INF/icon.svg", MetalIcons::class.java)
}

class MetalFileType private constructor() : LanguageFileType(MetalLanguage.INSTANCE) {
    override fun getName() = "Metal"


    override fun getDescription() = "Metal shading language file"

    override fun getDefaultExtension(): String = "metal"


    override fun getIcon() = MetalIcons.FILE

    companion object {
        val INSTANCE = MetalFileType()
    }
}