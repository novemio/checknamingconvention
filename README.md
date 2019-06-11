# Installation

Using  plugin application:
```sh
buildscript {
  repositories {
    maven {
    jcenter()
    maven { url 'https://www.jitpack.io' }
    }
  }
  dependencies {
	classpath 'com.novemio.gradle:checknamingconvention:0.1.0'
  }
}

apply plugin: 'check-naming-convention'

checkNamingConvention{
    configFile = "$projectDir/config/quality/checknaming/checname-config.json"
}
```