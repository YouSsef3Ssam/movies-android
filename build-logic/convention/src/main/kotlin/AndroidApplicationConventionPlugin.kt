import com.innovation.movies.task.extensions.configureAndroidApp
import com.innovation.movies.task.extensions.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            configureAndroidApp()
            configureKotlin()

            /** Add Dependencies */
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findBundle("androidx").get())
                add("implementation", libs.findLibrary("splash-screen").get())
                add("implementation", libs.findLibrary("app-startup").get())
                add("implementation", libs.findLibrary("timber").get())
            }
        }
    }
}