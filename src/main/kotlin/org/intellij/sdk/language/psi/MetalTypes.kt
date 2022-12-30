package org.intellij.sdk.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.intellij.sdk.language.MetalFileType
import org.intellij.sdk.language.MetalLanguage
import org.jetbrains.annotations.NonNls

class MetalTokenType(@NonNls debugName: String) : IElementType(debugName, MetalLanguage.INSTANCE) {
    override fun toString() = "SimpleTokenType." + super.toString()

}


class MetalElementType(@NonNls debugName: String) : IElementType(debugName, MetalLanguage.INSTANCE)


object MetalTokenSets {
    val COMMENTS = TokenSet.create(MetalTypes.COMMENT)
}


class MetalFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MetalLanguage.INSTANCE) {
    override fun getFileType() = MetalFileType.INSTANCE
    override fun toString() = "Metal File"
}