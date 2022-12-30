package org.intellij.sdk.language


import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import org.intellij.sdk.language.psi.MetalTypes


class MetalSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = MetalSyntaxHighlighter()
}

class MetalSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = MetalLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> = arrayOf(
        when (tokenType) {
            MetalTypes.COMMENT -> DefaultLanguageHighlighterColors.BLOCK_COMMENT
            MetalTypes.ARITHMETIC_OP -> DefaultLanguageHighlighterColors.OPERATION_SIGN
            MetalTypes.IDENTIFIER_TYPE -> DefaultLanguageHighlighterColors.KEYWORD
            MetalTypes.C_BRACKET_L, MetalTypes.C_BRACKET_R, MetalTypes.S_BRACKET_L, MetalTypes.S_BRACKET_R -> DefaultLanguageHighlighterColors.BRACKETS
            MetalTypes.SEMICOLON -> DefaultLanguageHighlighterColors.SEMICOLON
            MetalTypes.DOT -> DefaultLanguageHighlighterColors.DOT
            MetalTypes.PAREN_L, MetalTypes.PAREN_R -> DefaultLanguageHighlighterColors.PARENTHESES
            MetalTypes.VARIABLE_DEFINITION, MetalTypes.VAR_NAME_ACCESS_MEMBER, MetalTypes.VAR_NAME_ACCESS_FUNC,
            MetalTypes.VAR_NAME_ACCESS_VAR, MetalTypes.VAR_NAME_ORIGIN_VARIABLE, MetalTypes.VAR_NAME_ORIGIN_FUNC -> DefaultLanguageHighlighterColors.LOCAL_VARIABLE
            MetalTypes.INIT_VAL, MetalTypes.FLOAT_CONSTANT -> DefaultLanguageHighlighterColors.NUMBER
            MetalTypes.COMMA -> DefaultLanguageHighlighterColors.COMMA
            else -> DefaultLanguageHighlighterColors.IDENTIFIER
        }
    )


}