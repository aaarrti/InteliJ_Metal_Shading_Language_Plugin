package org.intellij.sdk.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry


interface MetalNamedElement : PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement? = node.psi
    override fun setName(name: String): PsiElement = node.psi
}


private fun <T> firstOrNull(array: Array<T>): T? {
    return if (array.isNotEmpty()) array[0] else null
}


abstract class MetalNamedFunctionDeclareImpl(node: ASTNode) : ASTWrapperPsiElement(node), MetalNamedElement {
    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)


    override fun getReference(): PsiReference? {
        val references = references
        return if (references.isNotEmpty()) references[0] else null
    }

}


abstract class MetalNamedVariableAccessImpl(node: ASTNode) : ASTWrapperPsiElement(node), MetalNamedElement {
    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = firstOrNull(references)


}


abstract class MetalNamedVariableAccessMemberImpl(node: ASTNode) : ASTWrapperPsiElement(node), MetalNamedElement {
    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)


    override fun getReference(): PsiReference? = firstOrNull(references)


}

abstract class MetalNamedVariableDeclareImpl(node: ASTNode) : ASTWrapperPsiElement(node), MetalNamedElement {
    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = firstOrNull(references)


}


abstract class MetalNamedVariableFuncAccessImpl(node: ASTNode) : ASTWrapperPsiElement(node), MetalNamedElement {
    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = firstOrNull(references)

}