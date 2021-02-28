package com.bugiadev.talentomobile.domain

interface DomainMappable<R> {
    fun toDomain(): R
}