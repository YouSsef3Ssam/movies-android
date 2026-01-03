package com.innovation.movies.task.extensions

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermission
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

internal fun Project.configureGitHookScriptsTask() {
    tasks.register("addScriptsToGitHooks") {
        group = "Linting Scripts"
        description = "Adds a pre-commit & pre-push Git hook script on build"

        doLast {
            println("⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈")
            println("✅ Adding Pre Commit And Pre Push Git Hook Script.")

            println("⚈ ⚈ ⚈ Running Add Pre Commit Git Hook Script on Build ⚈ ⚈ ⚈")
            copyGitScriptFileToHookDirectory(fileName = "pre-commit")
            println("✅ Added Pre Commit Git Hook Script.")

            println("⚈ ⚈ ⚈ Running Add Pre Push Git Hook Script on Build ⚈ ⚈ ⚈")
            copyGitScriptFileToHookDirectory(fileName = "pre-push")
            println("✅ Added Pre Push Git Hook Script.")
            println("⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈ ⚈")
        }
    }
}

private fun Task.copyGitScriptFileToHookDirectory(fileName: String) {
    val hooksDir = File(project.rootProject.rootDir, ".git/hooks")
    val preCommitScript = hooksDir.resolve(fileName)

    // Create hooks directory if it doesn't exist
    if (!hooksDir.exists()) hooksDir.mkdirs()

    val scriptInputStream = getResourceFileAsStream("scripts/$fileName")

    // Copy the pre-commit script from the plugin resources to the hooks directory
    preCommitScript.outputStream().use { output ->
        scriptInputStream.copyTo(output)
    }

    // Ensure the script is executable
    if (!preCommitScript.setExecutable(true)) {
        // If setting the permission fails, try another approach
        val path = Paths.get(preCommitScript.absolutePath)
        val permissions = HashSet<PosixFilePermission>()
        permissions.add(PosixFilePermission.OWNER_READ)
        permissions.add(PosixFilePermission.OWNER_WRITE)
        permissions.add(PosixFilePermission.OWNER_EXECUTE)
        Files.setPosixFilePermissions(path, permissions)
    }

    println("$fileName Git hook installed.")
}

internal fun Project.configureKtlint() {
    configure<KtlintExtension> {
        debug.set(true)
        android.set(true)
        ignoreFailures.set(false)
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

internal fun Project.configureDetekt() {
    val scriptInputStream = getResourceFileAsStream("config/detekt/detekt.yml")

    extensions.configure<DetektExtension> {
        config.setFrom(
            convertStreamToFile(
                scriptInputStream,
                Files.createTempFile("detekt", ".yml").toFile()
            )
        )
    }

    tasks.withType(Detekt::class.java).configureEach {
        reports {
            html.required.set(true) // Create HTML report
            xml.required.set(false)  // Disable XML report
            txt.required.set(false)  // Disable TXT report
        }
    }
}

private fun getResourceFileAsStream(filePath: String): InputStream =
    Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)
        ?: throw RuntimeException("Failed to load $filePath from resources")

private fun convertStreamToFile(inputStream: InputStream, file: File): File {
    inputStream.use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
    return file
}

