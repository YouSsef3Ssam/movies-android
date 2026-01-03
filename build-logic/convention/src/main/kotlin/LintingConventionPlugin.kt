import com.innovation.movies.task.extensions.configureDetekt
import com.innovation.movies.task.extensions.configureGitHookScriptsTask
import com.innovation.movies.task.extensions.configureKtlint
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class LintingConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("org.jlleitschuh.gradle.ktlint")
                apply("io.gitlab.arturbosch.detekt")
            }
            configureGitHookScriptsTask()
            configureKtlint()
            configureDetekt()
        }
    }
}