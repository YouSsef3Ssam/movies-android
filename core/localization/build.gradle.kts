import com.innovation.movies.task.utils.NameSpace

plugins {
    id(libs.plugins.innovation.android.library.get().pluginId)
}

android {
    namespace = NameSpace.Core.LOCALIZATION
}
