package com.histora.app.models

import java.io.Serializable

/**
 * 历史事件数据模型
 */
data class HistoricalEvent(
    val id: String,
    val title: String,
    val description: String,
    val background: String,
    val date: String,
    val category: String,
    val imageUrl: String? = null,
    val keyFigures: List<KeyFigure> = emptyList(),
    val causalNodes: List<CausalNode> = emptyList(),
    val modifiableFactors: List<ModifiableFactor> = emptyList()
) : Serializable

/**
 * 关键人物
 */
data class KeyFigure(
    val name: String,
    val role: String,
    val description: String,
    val imageUrl: String? = null
) : Serializable

/**
 * 因果关系节点
 */
data class CausalNode(
    val id: String,
    val title: String,
    val description: String,
    val type: NodeType,
    val connectedNodes: List<String> = emptyList(),
    val x: Float = 0f,
    val y: Float = 0f
) : Serializable

enum class NodeType {
    CAUSE,      // 原因
    EVENT,      // 事件
    RESULT,     // 结果
    PERSON,     // 人物
    DECISION    // 决策
}

/**
 * 可修改因素
 */
data class ModifiableFactor(
    val id: String,
    val title: String,
    val description: String,
    val originalValue: String,
    val alternativeValues: List<AlternativeValue>
) : Serializable

data class AlternativeValue(
    val value: String,
    val description: String
) : Serializable
