package com.histora.app.data

import com.histora.app.models.*

/**
 * 示例历史事件数据
 */
object SampleData {

    fun getRecommendedEvents(): List<HistoricalEvent> {
        return listOf(
            getWWIIEvent(),
            getQinUnificationEvent(),
            getIndustrialRevolutionEvent(),
            getRenaissanceEvent()
        )
    }

    private fun getWWIIEvent(): HistoricalEvent {
        return HistoricalEvent(
            id = "ww2_turning_point",
            title = "二战的转折点",
            description = "探索改变第二次世界大战走向的关键决策",
            background = "第二次世界大战是人类历史上规模最大、伤亡最重的战争。多个关键决策和战役决定了战争的走向和最终结果。",
            date = "1939-1945",
            category = "世界大战",
            keyFigures = listOf(
                KeyFigure(
                    name = "温斯顿·丘吉尔",
                    role = "英国首相",
                    description = "二战期间领导英国抵抗纳粹德国"
                ),
                KeyFigure(
                    name = "富兰克林·罗斯福",
                    role = "美国总统",
                    description = "领导美国参战并建立反法西斯同盟"
                ),
                KeyFigure(
                    name = "阿道夫·希特勒",
                    role = "纳粹德国元首",
                    description = "发动第二次世界大战的主要战犯"
                )
            ),
            causalNodes = listOf(
                CausalNode(
                    id = "node1",
                    title = "德国入侵波兰",
                    description = "1939年9月1日,德国入侵波兰,标志着二战爆发",
                    type = NodeType.EVENT,
                    connectedNodes = listOf("node2", "node3"),
                    x = 100f,
                    y = 100f
                ),
                CausalNode(
                    id = "node2",
                    title = "英法对德宣战",
                    description = "英法履行对波兰的保护承诺",
                    type = NodeType.RESULT,
                    connectedNodes = listOf("node4"),
                    x = 300f,
                    y = 100f
                ),
                CausalNode(
                    id = "node3",
                    title = "苏德互不侵犯条约",
                    description = "德苏秘密协议瓜分波兰",
                    type = NodeType.CAUSE,
                    connectedNodes = listOf("node1"),
                    x = 100f,
                    y = 50f
                ),
                CausalNode(
                    id = "node4",
                    title = "诺曼底登陆",
                    description = "1944年盟军开辟欧洲第二战场",
                    type = NodeType.EVENT,
                    connectedNodes = listOf("node5"),
                    x = 500f,
                    y = 100f
                ),
                CausalNode(
                    id = "node5",
                    title = "纳粹德国投降",
                    description = "1945年5月德国无条件投降",
                    type = NodeType.RESULT,
                    x = 700f,
                    y = 100f
                )
            ),
            modifiableFactors = listOf(
                ModifiableFactor(
                    id = "factor1",
                    title = "美国参战时间",
                    description = "美国在珍珠港事件后参战",
                    originalValue = "1941年12月",
                    alternativeValues = listOf(
                        AlternativeValue(
                            value = "1939年(战争开始)",
                            description = "美国在战争初期就加入盟军"
                        ),
                        AlternativeValue(
                            value = "不参战",
                            description = "美国保持中立,不参与战争"
                        )
                    )
                ),
                ModifiableFactor(
                    id = "factor2",
                    title = "苏德战争走向",
                    description = "德国入侵苏联的巴巴罗萨计划",
                    originalValue = "1941年6月入侵",
                    alternativeValues = listOf(
                        AlternativeValue(
                            value = "德国不入侵苏联",
                            description = "德国集中力量对付西线"
                        ),
                        AlternativeValue(
                            value = "提前一年入侵",
                            description = "1940年就对苏开战"
                        )
                    )
                )
            )
        )
    }

    private fun getQinUnificationEvent(): HistoricalEvent {
        return HistoricalEvent(
            id = "qin_unification",
            title = "秦始皇统一六国",
            description = "探索如果秦国未能统一中国会如何",
            background = "公元前221年,秦始皇完成了统一六国的伟业,建立了中国历史上第一个统一的中央集权封建王朝。",
            date = "公元前221年",
            category = "中国古代史",
            keyFigures = listOf(
                KeyFigure(
                    name = "秦始皇嬴政",
                    role = "秦国君主",
                    description = "完成统一大业,建立秦朝"
                ),
                KeyFigure(
                    name = "李斯",
                    role = "秦国丞相",
                    description = "协助秦始皇推行改革和统一政策"
                )
            ),
            causalNodes = listOf(
                CausalNode(
                    id = "qin1",
                    title = "商鞅变法",
                    description = "奠定秦国强盛基础",
                    type = NodeType.CAUSE,
                    connectedNodes = listOf("qin2"),
                    x = 100f,
                    y = 100f
                ),
                CausalNode(
                    id = "qin2",
                    title = "远交近攻策略",
                    description = "范雎提出的外交战略",
                    type = NodeType.DECISION,
                    connectedNodes = listOf("qin3"),
                    x = 300f,
                    y = 100f
                ),
                CausalNode(
                    id = "qin3",
                    title = "统一六国",
                    description = "秦灭六国,建立统一王朝",
                    type = NodeType.RESULT,
                    x = 500f,
                    y = 100f
                )
            ),
            modifiableFactors = listOf(
                ModifiableFactor(
                    id = "qin_factor1",
                    title = "商鞅变法结果",
                    description = "商鞅变法强化了秦国",
                    originalValue = "成功实施",
                    alternativeValues = listOf(
                        AlternativeValue(
                            value = "变法失败",
                            description = "保守势力阻止变法"
                        ),
                        AlternativeValue(
                            value = "部分实施",
                            description = "仅实施部分改革措施"
                        )
                    )
                )
            )
        )
    }

    private fun getIndustrialRevolutionEvent(): HistoricalEvent {
        return HistoricalEvent(
            id = "industrial_revolution",
            title = "工业革命",
            description = "如果工业革命没有发生或延迟发生",
            background = "18世纪中叶开始的工业革命从根本上改变了人类社会的生产方式和生活方式。",
            date = "1760-1840",
            category = "近代史",
            keyFigures = listOf(
                KeyFigure(
                    name = "詹姆斯·瓦特",
                    role = "发明家",
                    description = "改良蒸汽机,推动工业革命"
                )
            ),
            causalNodes = listOf(
                CausalNode(
                    id = "ir1",
                    title = "蒸汽机发明",
                    description = "瓦特改良蒸汽机",
                    type = NodeType.EVENT,
                    connectedNodes = listOf("ir2"),
                    x = 100f,
                    y = 100f
                ),
                CausalNode(
                    id = "ir2",
                    title = "机器大生产",
                    description = "工厂制度取代手工业",
                    type = NodeType.RESULT,
                    x = 300f,
                    y = 100f
                )
            ),
            modifiableFactors = listOf()
        )
    }

    private fun getRenaissanceEvent(): HistoricalEvent {
        return HistoricalEvent(
            id = "renaissance",
            title = "文艺复兴",
            description = "探索欧洲文艺复兴运动的影响",
            background = "14-17世纪,欧洲发生了一场思想文化运动,推动了人文主义思想的传播。",
            date = "1300-1600",
            category = "文化史",
            keyFigures = listOf(
                KeyFigure(
                    name = "达芬奇",
                    role = "艺术家、科学家",
                    description = "文艺复兴时期的代表人物"
                )
            ),
            causalNodes = listOf(),
            modifiableFactors = listOf()
        )
    }

    fun getSampleSimulation(eventId: String): Simulation {
        return Simulation(
            id = "sim_${System.currentTimeMillis()}",
            eventId = eventId,
            eventTitle = "二战的转折点",
            modifications = listOf(
                Modification(
                    factorId = "factor1",
                    factorTitle = "美国参战时间",
                    originalValue = "1941年12月",
                    newValue = "1939年(战争开始)",
                    description = "美国在战争初期就加入盟军"
                )
            ),
            scenarios = listOf(
                Scenario(
                    id = "scenario1",
                    title = "早期结束战争",
                    description = "美国早期参战使盟军实力大增,战争可能提前2-3年结束",
                    timeline = Timeline(
                        events = listOf(
                            TimelineEvent(
                                id = "te1",
                                title = "美国参战",
                                description = "美国在1939年加入盟军",
                                date = "1939年9月",
                                type = TimelineEventType.MODIFIED
                            ),
                            TimelineEvent(
                                id = "te2",
                                title = "盟军实力增强",
                                description = "美国的工业和军事力量大幅增强盟军实力",
                                date = "1940年",
                                type = TimelineEventType.CONSEQUENCE
                            ),
                            TimelineEvent(
                                id = "te3",
                                title = "德国提前投降",
                                description = "在盟军压倒性优势下,德国于1943年投降",
                                date = "1943年",
                                type = TimelineEventType.CONSEQUENCE
                            )
                        )
                    ),
                    probability = 0.65f,
                    keyChanges = listOf(
                        "战争时长缩短2-3年",
                        "欧洲破坏程度降低",
                        "战后格局可能不同"
                    )
                ),
                Scenario(
                    id = "scenario2",
                    title = "太平洋战场变化",
                    description = "美国专注欧洲战场,太平洋战争进程改变",
                    timeline = Timeline(
                        events = listOf(
                            TimelineEvent(
                                id = "te4",
                                title = "珍珠港事件不发生",
                                description = "美国已参战,日本不敢偷袭珍珠港",
                                date = "1941年",
                                type = TimelineEventType.CONSEQUENCE
                            )
                        )
                    ),
                    probability = 0.45f,
                    keyChanges = listOf(
                        "太平洋战争格局改变",
                        "日本策略调整"
                    )
                )
            )
        )
    }
}
