package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.annotations.ConfigApi
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.Modifier
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Types

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
            element.kind == ElementKind.METHOD &&
                    field.simpleName.toString().let(
                            if (isBooleanType(field))
                                ::fieldNameToBooleanGetterName
                            else
                                ::fieldNameToGetterName
                    ) == element.simpleName.toString()
        }

fun findSetterForField(field: Element) =
        field.enclosingElement.enclosedElements.find { element ->
            element.kind == ElementKind.METHOD &&
                    fieldNameToSetterName(field.simpleName.toString()) == element.simpleName.toString() &&
                    (element as ExecutableElement).parameters.size == 1 &&
                    element.parameters[0].asType() == field.asType()
        }

fun isNonPrivateGetterPresentForField(field: Element) = checkGetterForField(field, ::isNotPrivate)

fun isPrimitiveConfigType(field: Element) = when {
    field.asType().kind.isPrimitive -> true
    listOf(
            "java.lang.String",
            "java.lang.Byte",
            "java.lang.Short",
            "java.lang.Integer",
            "java.lang.Long",
            "java.lang.Float",
            "java.lang.Double",
            "java.lang.Character",
            "java.lang.Boolean"
    ).contains(field.asType().toString()) -> true
    else -> false
}

fun isUnsupportedGenericType(field: Element) = field.asType().toString().let { typeName ->
    when {
        // whitelist
        listOf(
                "java.util.Date"
        ).contains(typeName) -> false
        // blacklist
        listOf(
                "java.lang.Object"
        ).contains(typeName) -> true
        typeName.startsWith("java.util.") -> true
        else -> false
    }
}

fun isMapType(field: Element) = field.asType().toString().matches(Regex("java\\.util\\.Map<.*?,.*?>"))
fun isCollectionType(field: Element, typeUtil: Types) = anySupertypeMatches(field.asType(), typeUtil) { it -> it.matches(Regex("java\\.util\\.Collection<.*?>")) }
fun anySupertypeMatches(type: TypeMirror, typeUtil: Types, superTypeMatcher: (String) -> Boolean): Boolean = when {
    superTypeMatcher(type.toString()) -> true
    type.toString() == "java.lang.Object" -> false
    else -> typeUtil.directSupertypes(type).any { anySupertypeMatches(it, typeUtil, superTypeMatcher) }
}

fun isBooleanType(field: Element) = field.asType().toString() == "boolean"
fun isStringType(field: Element) = field.asType().toString() == "java.lang.String"

fun getMapKeyTypeParameter(mapField: Element): TypeMirror = (mapField.asType() as DeclaredType).typeArguments[0]
fun getMapValueTypeParameter(mapField: Element): TypeMirror = (mapField.asType() as DeclaredType).typeArguments[1]
fun getCollectionTypeParameter(collectionField: Element): TypeMirror = (collectionField.asType() as DeclaredType).typeArguments[0]

fun isConfigApiAnnotationPresentOnGetter(field: Element) = checkGetterForField(field, ::isConfigApiAnnotationPresent)
fun isNonNullAnnotationPresentOnGetter(field: Element) = checkGetterForField(field, ::isNonNullAnnotationPresent)

fun checkGetterForField(field: Element, predicate: (Element) -> Boolean): Boolean {
    val getter = findGetterForField(field)
    return if (getter == null)
        false
    else
        predicate(getter)
}

fun checkSetterForField(field: Element, predicate: (Element) -> Boolean): Boolean {
    val setter = findSetterForField(field)
    return if (setter == null)
        false
    else
        predicate(setter)
}

fun isGetterForFieldPublic(field: Element) = checkGetterForField(field, ::isPublic)
fun isSetterForFieldPublic(field: Element) = checkSetterForField(field, ::isPublic)

fun isPrivate(element: Element) = element.modifiers.contains(Modifier.PRIVATE)
fun isNotPrivate(element: Element) = !isPrivate(element)
fun isPublic(element: Element) = element.modifiers.contains(Modifier.PUBLIC)

fun isField(element: Element) = element.kind == ElementKind.FIELD
fun fieldNameToGetterName(fieldName: String) =
        "get${fieldName[0].toUpperCase()}${fieldName.substring(1)}"

fun fieldNameToBooleanGetterName(fieldName: String) =
        "is${fieldName[0].toUpperCase()}${fieldName.substring(1)}"

fun fieldNameToSetterName(fieldName: String) =
        "set${fieldName[0].toUpperCase()}${fieldName.substring(1)}"

fun isPrimitiveType(field: Element) = field.asType().kind.isPrimitive
