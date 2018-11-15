package de.sandstorm.configdocgen.processor.standalone

import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@SupportedAnnotationTypes(
        "de.sandstorm.configdocgen.annotations.ConfigNamespace",
        "de.sandstorm.configdocgen.annotations.ConfigProperty"
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class StandaloneProcessor : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        for (annotation in annotations!!) {
            println(annotation)
            val annotatedElements = roundEnv!!.getElementsAnnotatedWith(annotation)

            println(annotatedElements)
        }
        return false
    }

}