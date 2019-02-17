# configuration documentation generator

This tool enables you to generate a Configuration Documentation (similar to a JavaDoc) from your source code.
For now, there is support for Spring Boot and Standalone.

## Main Features

- documentation parsing from java doc comments
- explicit or automatic recognition of configuration code
- code documentation validator (configurable compiler warning or error, e.g. fail build on missing doc comment)
- interactive React UI for configuration documentation browsing
- other configurable output format (JSON, NONE)
- easily usable with build tools (e.g. gradle)
- supports Lombok

The releases are available on the maven central repository.
```
repositories {
    mavenCentral()
}
```

Snapshot releases or local maven repositories:
```
repositories {
    maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
    mavenLocal()
}
```

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
```yaml
# The application name
# default: "No module name set!"
moduleName: 'Your application name'
# Processor settings
processor:
  # Compiler behavior for missing documentation comments
  # All javax.tools.Diagnostic.Kind enum constants are allowed:
  # - ERROR
  # - WARNING
  # - MANDATORY_WARNING
  # - NOTE
  # - OTHER
  # default: WARNING
  missingDocCommentDiagnostic: WARNING
writer:
  # The output writer type
  # All de.sandstorm.configdocgen.core.WriterType enum constants are allowed: 
  # - JSON
  # - REACT_UI
  # - NONE
  type: REACT_UI
  # The output file name
  # default depends on the writer type:
  # - JSON = "config-doc.json"
  # - REACT_UI = "config-doc-react-ui.html"
  # - NONE = no output 
  outputFileName: 'config-doc.html'
```

It may be a good idea to set the `processor.missingDocCommentDiagnostic` to `ERROR`. 
This enforces the developers to comment the applications public API.

## Standalone
Generate configuration documentation using custom annotations in your code.

TODO: documentation of custom annotations!!!

## Spring Boot
Generate configuration documentation from Spring Boot sources.

All `@ConfigurationProperties` classes are scanned as namespaces with fields as configuration properties.

### Usage with Spring Boot

add the following annotation processor dependency to the projects build.gradle:
```
ext.configDocVersion = "0.9.0-RC1"

dependencies {
    annotationProcessor "de.sandstorm.configdocgen:processor-spring:${configDocVersion}"
}
```

You may update the configDocVersion or set it via gradle properties.

# local dev

Requirements:
- a running Docker deamon
- JDK 11

Run tests:
```
./gradlew junitPlatformTest
```

## React UI (using docker):

enter the utility directory:
```
cd local-dev/react-ui/
```

Setup local development environment:
```
./setup.sh
```

Run UI dev server in watch mode:
```
./start.sh
```

Package the React UI resources to the core module.
```
./package-ui.sh
```

Enter bash (e.g. for `yarn add some-new-package`)
```
./bash.sh
```
