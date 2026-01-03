-keep class com.innovation.movies.task.**$$serializer { *; }

# 2️⃣Keep companion objects and serializer() methods
# (Prevents loss of the entry point to generated serializers)
-keepclassmembers class com.innovation.movies.task.** {
    public static **$Companion Companion;
    public static kotlinx.serialization.KSerializer serializer(...);
}

# 3️⃣ Keep only @Serializable-annotated model classes
# (Prevents stripping the metadata needed for serialization)
-keepclassmembers class com.innovation.movies.task.** {
    @kotlinx.serialization.Serializable <fields>;
    @kotlinx.serialization.Serializable <methods>;
}

# 4️⃣ Keep @Serializable classes and their members
-keep @kotlinx.serialization.Serializable class com.innovation.movies.task.** {
    *;
}

# 5️⃣ Keep members referenced by @SerialName annotations, even if they appear unused
-keepnames class com.innovation.movies.task.** {
    @kotlinx.serialization.SerialName <fields>;
    @kotlinx.serialization.SerialName <methods>;
}