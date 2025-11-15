package com.histora.app.models

import java.io.Serializable
import java.util.Date

/**
 * 历史推演模拟
 */
data class Simulation(
    val id: String,
    val eventId: String,
    val eventTitle: String,
    val modifications: List<Modification>,
    val scenarios: List<Scenario>,
    val createdAt: Date = Date()
) : Serializable

/**
 * 修改项
 */
data class Modification(
    val factorId: String,
    val factorTitle: String,
    val originalValue: String,
    val newValue: String,
    val description: String
) : Serializable

/**
 * 情景/场景
 */
data class Scenario(
    val id: String,
    val title: String,
    val description: String,
    val timeline: Timeline,
    val probability: Float? = null,
    val keyChanges: List<String> = emptyList()
) : Serializable

/**
 * 时间线
 */
data class Timeline(
    val events: List<TimelineEvent>,
    val branches: List<TimelineBranch> = emptyList()
) : Serializable

/**
 * 时间线事件
 */
data class TimelineEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val type: TimelineEventType,
    val isAlternative: Boolean = false
) : Serializable

enum class TimelineEventType {
    ORIGINAL,      // 原始历史
    MODIFIED,      // 修改后
    CONSEQUENCE    // 连锁后果
}

/**
 * 时间线分支
 */
data class TimelineBranch(
    val fromEventId: String,
    val toEventId: String,
    val description: String
) : Serializable
