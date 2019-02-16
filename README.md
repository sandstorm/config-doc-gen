# configuration documentation generator

This tool enables you to generate a Configuration Documentation (similar to a JavaDoc) from your source code.
For now, there is support for:

## Basic Settings
The annotation processor is configured via YAML file. The path (URL) can be specified via argument `de.sandstorm.configdocgen.settingsFile`.

Example: `-Ade.sandstorm.configdocgen.settingsFile=file:///home/user/config-doc.yml`

Example using a config in `src/main/resources` via gradle:
```
compileJava {
    options.compilerArgs += ["-Ade.sandstorm.configdocgen.settingsFile=file://" + sourceSets.main.resources.sourceDirectories.asPath + "/config-doc.yaml"]
}
```

Settings:


## Standalone
Generate configuration documentation using custom annotations in your code.

TODO: documentation of custom annotations!!!

## Spring Boot
Generate configuration documentation from Spring Boot sources.

### Usage with Spring Boot

build.gradle

```
dependencies {
    annotationProcessor "de.sandstorm.configdocgen:processor-spring:${configDocVersion}"
}
```

You need to replace the configDocVersion or set it via gradle properties!!!

# local dev

```
./gradlew junitPlatformTest
```

