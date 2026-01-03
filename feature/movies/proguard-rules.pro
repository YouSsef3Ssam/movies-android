-repackageclasses 'com.innovation.movies.task.feature.movies'
-dontwarn java.lang.invoke.StringConcatFactory

-keep class com.innovation.movies.task.feature.movies.common.di.MoviesModuleKt { *; }

-keep class com.innovation.movies.task.feature.movies.presentation.ui.movies.MoviesRouteKt { *; }
-keep class com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesListNavigationActionCallbacks { *; }
-keep class com.innovation.movies.task.feature.movies.presentation.ui.movies.manipulator.MoviesListNavigationActionCallbacks$* { *; }

-keep class com.innovation.movies.task.feature.movies.presentation.ui.movieDetails.MovieDetailsRouteKt { *; }