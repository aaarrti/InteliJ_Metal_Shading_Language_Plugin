package org.intellij.sdk.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lexer.FlexAdapter
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.intellij.sdk.language.parser.MetalParser
import org.intellij.sdk.language.psi.MetalFile
import org.intellij.sdk.language.psi.MetalTokenSets
import org.intellij.sdk.language.psi.MetalTypes

class MetalLexerAdapter : FlexAdapter(MetalLexer(null))

class MetalParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = MetalLexerAdapter()

    override fun getCommentTokens() = MetalTokenSets.COMMENTS
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createParser(project: Project) = MetalParser()

    override fun getFileNodeType() = FILE

    override fun createFile(viewProvider: FileViewProvider) = MetalFile(viewProvider)

    override fun createElement(node: ASTNode): PsiElement = MetalTypes.Factory.createElement(node)
    companion object {
        val FILE = IFileElementType(MetalLanguage.INSTANCE)
    }
}