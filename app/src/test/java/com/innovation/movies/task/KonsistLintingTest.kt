package com.innovation.movies.task

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withPublicModifier
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withAnnotationOf
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withNameStartingWith
import com.lemonappdev.konsist.api.ext.list.withType
import com.lemonappdev.konsist.api.ext.list.withoutEnumConstants
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@SuppressWarnings("kotlin:S1192", "TooManyFunctions")
open class KonsistLintingTest {
    @Test
    @DisplayName("Every 'ViewModel' MutableStateFlow property is private modifier")
    fun everyViewModelMutableStateFlowPropertyIsPrivateModifier() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("ViewModel")
            .properties()
            .withPublicModifier()
            .withType { it.name == "kotlinx.coroutines.flow.MutableStateFlow" }
            .assertTrue { it.hasPrivateModifier }
    }

    @Test
    @DisplayName("No empty files allowed")
    fun noEmptyFiles() {
        Konsist
            .scopeFromProject()
            .files
            .isNotEmpty()
    }

    @Test
    @DisplayName("Files ends with 'Extension' must exist in 'extensions' package")
    fun filesEndingWithExtension() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Extension")
            .assertTrue { it.resideInPath("..extensions..") }
    }

    @Test
    @DisplayName("Files ends with 'Extensions' must exist in 'extensions' package")
    fun extensions() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Extensions")
            .assertTrue { it.resideInPath("..extensions..") }
    }

    @Test
    @DisplayName("Companion object is last declaration in the class")
    fun companionObjectIsLastDeclaration() {
        Konsist
            .scopeFromProject()
            .classes()
            .assertTrue {
                val companionObject =
                    it.objects(includeNested = false).lastOrNull { obj ->
                        obj.hasModifier(KoModifier.COMPANION)
                    }

                if (companionObject != null) {
                    it
                        .declarations(includeNested = false, includeLocal = false)
                        .last() == companionObject
                } else {
                    true
                }
            }
    }

    @Test
    @DisplayName("No class should use Java util logging")
    fun noClassUseLogging() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.hasImport { import -> import.name == "java.util.logging.." } }
    }

    @Test
    @DisplayName("Package name must match file path")
    fun packageMustMatchFilePath() {
        Konsist
            .scopeFromProject()
            .packages
            .assertTrue { it.hasMatchingPath }
    }

    @Test
    @DisplayName("No wildcard imports allowed")
    fun noWildcardImports() {
        Konsist
            .scopeFromProject()
            .imports
            .assertFalse { it.isWildcard }
    }

    @Test
    @DisplayName("'Repository' classes should reside in 'repository' package")
    fun repositoryClassesShouldResideInRepositoryPackage() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Repository")
            .assertTrue { it.resideInPackage("..repository..") }
    }

    @Test
    @DisplayName("'Data Sources' classes should reside in 'source' package")
    fun dataSourcesClassesShouldResideInSourcePackage() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Source")
            .withoutName("FakeMoviesPagingSource")
            .assertTrue { it.resideInPackage("..source..") }
    }

    @Test
    @DisplayName("'Dtos' classes should reside in 'remote.dto' or 'local.dto' package")
    fun dtoClassesShouldResideInRemoteDtoPackage() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Dto")
            .assertTrue {
                it.resideInPackage("..remote.dto..") or it.resideInPackage("..local.dto..")
            }
    }

    @Test
    @DisplayName("'Domain' classes should reside in 'domain.model' package")
    fun domainClassesShouldResideInDomainModelPackage() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameStartingWith("Domain")
            .assertTrue { it.resideInPackage("..domain.model..") }
    }

    @Test
    @DisplayName("'Mappers' files should reside in 'mapper' package")
    fun mapperFilesShouldResideInMapperPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Mapper")
            .assertTrue { it.resideInPath("..mapper..") }
    }

    @Test
    @DisplayName("'Constants' objects should reside in 'common' package")
    fun constantsObjectsShouldResideInDomainPackage() {
        Konsist
            .scopeFromProject()
            .objects()
            .withNameEndingWith("Constants")
            .assertTrue { it.resideInPackage("..common..") }
    }

    @Test
    @DisplayName("'EndPoints' objects should reside in 'common' package")
    fun endPointsObjectsShouldResideInDomainPackage() {
        Konsist
            .scopeFromProject()
            .objects()
            .withNameEndingWith("EndPoints")
            .assertTrue { it.resideInPackage("..common..") }
    }

    @Test
    @DisplayName("'Koin Module' classes should reside in 'common.di' package")
    fun koinModulesClassesShouldResideInCommonDiPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Module")
            .assertTrue { it.resideInPath("..common.di..") }
    }

    @Test
    @DisplayName("'Events' classes should reside in 'presentation manipulator' package")
    fun eventsClassesShouldResideInPresentationManipulatorDiPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Events")
            .assertTrue { it.resideInPath("..presentation..manipulator..") }
    }

    @Test
    @DisplayName("'State' classes should reside in 'presentation manipulator' package")
    fun stateClassesShouldResideInPresentationManipulatorPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("State")
            .withoutName("ConnectionState")
            .withoutName("ConnectivityState")
            .withoutName("PhoneNumberState")
            .withoutName("AppState")
            .withoutName("MessageState")
            .assertTrue { it.resideInPath("..presentation..manipulator..") }
    }

    @Test
    @DisplayName("'ViewModel' classes should reside in 'presentation manipulator' package")
    fun viewModelClassesShouldResideInPresentationManipulatorDiPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("ViewModel")
            .assertTrue { it.resideInPath("..presentation..manipulator..") }
    }

    @Test
    @DisplayName("'UI' classes should reside in 'presentation.data' package")
    fun uiClassesShouldResideInPresentationDataPackage() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameStartingWith("Ui")
            .assertTrue { it.resideInPackage("..presentation..data..") }
    }

    @Test
    @DisplayName("'Route' files should reside in 'presentation.ui' package")
    fun routeFilesShouldResideInPresentationUiPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Route")
            .assertTrue { it.resideInPath("..presentation..ui..") }
    }

    @Test
    @DisplayName("'Screen' files should reside in 'presentation.ui' package")
    fun screenFilesShouldResideInPresentationUiPackage() {
        Konsist
            .scopeFromProject()
            .files
            .withNameEndingWith("Screen")
            .withoutName("PreviewScreen")
            .assertTrue { it.resideInPath("..presentation..ui..") }
    }

    @Test
    @DisplayName("No class should use Android util logging")
    fun noClassShouldUseAndroidLogging() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.hasImport { import -> import.name == "android.util.Log" } }
    }

    @Test
    @DisplayName("All Models Are Serializable")
    fun allModelsAreSerializable() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("Dto")
            .withoutEnumConstants()
            .assertTrue {
                it.hasAnnotationOf(Serializable::class)
            }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    @DisplayName("classes annotated with 'Serializable' have all properties annotated with 'SerialName'")
    fun classesAnnotatedWithSerializableAllPropertiesAnnotatedWithSerialName() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAnnotationOf(Serializable::class)
            .withNameEndingWith("Dto")
            .properties()
            .assertTrue {
                it.hasAnnotationOf(SerialName::class, JsonNames::class)
            }
    }
}
