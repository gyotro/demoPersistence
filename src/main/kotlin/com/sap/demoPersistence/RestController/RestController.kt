package com.sap.demoPersistence.RestController

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persistence")
class RestController {

    @GetMapping("/checkContainer")
    fun testContainers() : String
    {
        return "Container Up and Running"
    }

}