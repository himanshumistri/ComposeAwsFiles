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
            description = "App is based on Compose Desktop UI"
            copyright = "© 2023 Himanshu Mistri. All rights reserved."
            vendor = "https://github.com/himanshumistri"
            buildTypes.release.proguard {
                obfuscate.set(true)
                configurationFiles.from(project.file("proguard-rules.pro"))
            }
            //With Below line , Mac App Start to be installed
            println("Path is ${project.layout.projectDirectory.dir("resources")}")
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))

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
                upgradeUuid = "1bcded87-b937-4be1-8ed0-560a9ee6aad9"
                msiPackageVersion = "1.0.0"

            }
            linux {
                iconFile.set(project.file("jetpack_icon.png"))
            }

        }
    }
}
