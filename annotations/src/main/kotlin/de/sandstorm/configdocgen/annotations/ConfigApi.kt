package de.sandstorm.configdocgen.annotations

@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.FIELD,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.SOURCE)
annotation class ConfigApi