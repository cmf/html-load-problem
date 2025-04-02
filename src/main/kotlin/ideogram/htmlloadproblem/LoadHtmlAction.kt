package ideogram.htmlloadproblem

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.util.Key
import ideogram.htmlloadproblem.HTMLEditorProvider
import ideogram.htmlloadproblem.HTMLFileEditor

/**
 * @author Colin Fleming
 */
class LoadHtmlAction : AnAction() {
  val key = Key.create<String>("HTML_TEST_KEY")
  val keyValue = "this"

  override fun actionPerformed(e: AnActionEvent) {
    e.project?.let { project ->
      val html = """
        <html>
        <body>
        Timestamp: ${System.currentTimeMillis()}
        </body
        </html>
      """.trimIndent()
      val existing =
        FileEditorManager.getInstance(project).allEditors.find { it.getUserData(key) == keyValue } as? HTMLFileEditor
      if (existing != null) {
        existing.loadHtml(html)
      } else {
        val editor = HTMLEditorProvider.openEditor(project, "test", HTMLEditorProvider.Request.html(html))
        editor?.putUserData(key, keyValue)
      }
    }
  }
}
