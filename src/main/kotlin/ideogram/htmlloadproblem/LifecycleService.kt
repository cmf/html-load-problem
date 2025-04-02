package ideogram.htmlloadproblem

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.components.serviceAsync
import com.intellij.openapi.project.Project
import kotlinx.coroutines.CoroutineScope

/**
 * @author Colin Fleming
 */
@Service(Service.Level.APP, Service.Level.PROJECT)
internal class LifecycleService(private val cs: CoroutineScope) {
  companion object {
    @JvmStatic
    fun applicationScope(): CoroutineScope {
      return service<LifecycleService>().cs
    }

    @JvmStatic
    suspend fun applicationScopeAsync(): CoroutineScope {
      return serviceAsync<LifecycleService>().cs
    }

    @JvmStatic
    fun projectScope(project: Project): CoroutineScope {
      return project.service<LifecycleService>().cs
    }

    @JvmStatic
    suspend fun projectScopeAsync(project: Project): CoroutineScope {
      return project.serviceAsync<LifecycleService>().cs
    }
  }
}
