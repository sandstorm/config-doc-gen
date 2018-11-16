package de.sandstorm.configdocgen.annotations

@Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.FIELD,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.PROPERTY_GETTER
)
@Retention(AnnotationRetention.SOURCE)
annotation class ConfigProperty