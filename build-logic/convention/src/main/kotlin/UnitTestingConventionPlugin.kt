import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class UnitTestingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("de.mannodermaus.android-junit5")
            }

            /** Add Dependencies */
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", libs.findBundle("testing-unit").get())
                add("testRuntimeOnly", libs.findLibrary("junit5-launcher").get())
                add("testImplementation", libs.findLibrary("konsist").get())
            }
        }
    }
}