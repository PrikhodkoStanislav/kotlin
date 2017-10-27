
apply { plugin("kotlin") }

jvmTarget = "1.6"

dependencies {
    compile(project(":core"))
    compile(project(":compiler:util"))
    compile(project(":compiler:container"))
    compile(project(":compiler:resolution"))
    compile(projectDist(":kotlin-script-runtime"))
    compile(commonDep("io.javaslang","javaslang"))
    compile(ideaSdkDeps("gson"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

