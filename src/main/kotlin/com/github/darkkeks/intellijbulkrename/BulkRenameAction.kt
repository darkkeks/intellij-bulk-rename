package com.github.darkkeks.intellijbulkrename

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.refactoring.RefactoringFactory
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextArea

class BulkRenameAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.getRequiredData(CommonDataKeys.PROJECT)
        val dialog = BulkRenameDialog(project)

        if (dialog.showAndGet()) {
            val text = dialog.field.text
            for (line in text.lines()) {
                val (className, fromMethod, toMethod) = line.split(",")
                if (!renameMethod(project, className, fromMethod, toMethod)) {
                    return
                }
            }
            Messages.showMessageDialog(project, "Renamed successfully", "Bulk Rename", null)
        }
    }

    private fun renameMethod(project: Project, className: String, fromMethod: String, toMethod: String): Boolean {
        val clazz =
            JavaPsiFacade.getInstance(project).findClass(className, GlobalSearchScope.allScope(project))
        if (clazz == null) {
            Messages.showMessageDialog(project, "Cant find class $className", "Bulk Rename Failed", null)
            return false
        }

        val method = clazz.allMethods
            .find { it.name == fromMethod }

        if (method != null) {
            val superMethods = method.findDeepestSuperMethods()
            val toRename = superMethods.firstOrNull() ?: method

            val refactoring = RefactoringFactory.getInstance(project)
                .createRename(toRename, toMethod)
            refactoring.isSearchInComments = false
            refactoring.isSearchInNonJavaFiles = false
            refactoring.setInteractive(null)
            refactoring.run()
        }

        return true
    }

    class BulkRenameDialog(project: Project) : DialogWrapper(project, false) {
        val field = JTextArea("Input here")

        override fun createCenterPanel(): JComponent {
            val dialogPanel = JPanel(BorderLayout())
            field.preferredSize = Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT)
            dialogPanel.add(field, BorderLayout.CENTER)
            return dialogPanel
        }

        init {
            init()
            title = "Bulk Rename"
        }

        companion object {
            const val DEFAULT_WIDTH = 300
            const val DEFAULT_HEIGHT = 200
        }
    }
}
