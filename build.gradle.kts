
import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.himanshu.aws"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}
val slf4j ="2.0.7"
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("aws.sdk.kotlin:s3:0.18.0-beta")
                runtimeOnly(files("libs/log4j-api-2.20.0.jar", "libs/log4j-core-2.20.0.jar","libs/log4j-slf4j-impl-2.20.0.jar"))
                //runtimeOnly files('libs/a.jar', 'libs/b.jar')
                //implementation("org.slf4j:slf4j-api:$slf4j")
                //implementation("org.slf4j:slf4j-reload4j:$slf4j")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ComposeAwsFiles"
            packageVersion = "1.0.0"

            //With Below line , Mac App Start to be installed
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))
            buildTypes.release.proguard {
                obfuscate.set(true)
                configurationFiles.from(project.file("proguard-rules.pro"))
            }
            description = "ComposeAwsFiles Descrption"
            copyright = "© 2023 Himanshu Mistri. All rights reserved."
            vendor = "Himanshu Mistri"


            macOS {
                iconFile.set(project.file("jetpack_icon.icns"))
                dmgPackageVersion = packageVersion
                pkgPackageVersion = packageVersion
            }
            windows {
                iconFile.set(project.file("jetpack_icon.ico"))
                //console = true
                //perUserInstall = true
                menuGroup = "start-menu-group"
                upgradeUuid = "6cd8eaca-cff8-473c-8796-9171d8f997e5"
                msiPackageVersion = project.properties["AppVersion"].toString()

            }
            linux {
                iconFile.set(project.file("jetpack_icon.png"))
            }
        }
    }
}
