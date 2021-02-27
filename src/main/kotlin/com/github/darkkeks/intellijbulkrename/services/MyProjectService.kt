package com.github.darkkeks.intellijbulkrename.services

import com.github.darkkeks.intellijbulkrename.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
