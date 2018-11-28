package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.annotations.ConfigApi
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier

fun isConfigApiAnnotationPresent(element: Element) = element.getAnnotation(ConfigApi::class.java) != null
fun isNonNullAnnotationPresent(element: Element) = element.annotationMirrors
        .map { it.annotationType.asElement().toString() }
        .any {
            listOf(
                    "lombok.NonNull",
                    "org.springframework.lang.NonNull",
                    "javax.validation.constraints.NotNull",
                    "javax.annotation.Nonnull"
            ).contains(it)
        }

fun findGetterForField(field: Element) =
        field.enclosingElement.enclosedElements.find { element ->
            element.kind == ElementKind.METHOD && fieldNameToGetterName(field.simpleName.toString()) == element.simpleName.toString()
        }

fun isNonPrivateGetterPresentForField(field: Element) = checkGetterForField(field, ::isNotPrivate)

fun isConfigApiAnnotationPresentOnGetter(field: Element) = checkGetterForField(field, ::isConfigApiAnnotationPresent)
fun isNonNullAnnotationPresentOnGetter(field: Element) = checkGetterForField(field, ::isNonNullAnnotationPresent)

fun checkGetterForField(field: Element, predicate: (Element) -> Boolean): Boolean {
    val getter = findGetterForField(field)
    return if (getter == null)
        false
    else
        predicate(getter)
}

fun isGetterForFieldPublic(field: Element) = checkGetterForField(field, ::isPublic)

fun isPrivate(element: Element) = element.modifiers.contains(Modifier.PRIVATE)
fun isNotPrivate(element: Element) = !isPrivate(element)
fun isPublic(element: Element) = element.modifiers.contains(Modifier.PUBLIC)

fun fieldNameToGetterName(fieldName: String) =
        "get${fieldName[0].toUpperCase()}${fieldName.substring(1)}"

fun isPrimitiveType(field: Element) = field.asType().kind.isPrimitive