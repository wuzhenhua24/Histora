# Histora - 历史模拟应用

## 📱 应用简介

Histora 是一款创新的历史模拟Android应用,允许用户探索历史事件并模拟"如果历史不同会怎样"的场景。通过改变历史事件中的关键因素,用户可以看到不同的历史走向和可能的结果。

## ✨ 主要功能

### 1. 🏛️ 历史探索
- **事件推荐**: 精选的重要历史事件,涵盖各个时期和领域
- **智能搜索**: 快速查找感兴趣的历史事件
- **事件详情**: 详细的历史背景、关键人物介绍
- **因果关系图**: 可视化展示历史事件的因果关系网络

### 2. 🔄 历史推演
- **修改历史因素**: 选择并修改历史事件中的关键决策点
- **多情景模拟**: 生成多个可能的历史走向
- **概率分析**: 显示每个情景发生的可能性
- **时间线展示**: 以时间线形式展示替代历史的发展

### 3. 📊 结果展示
- **详细报告**: 全面的历史推演结果分析
- **关键变化**: 突出显示与原始历史的主要差异
- **导出功能**: 保存和分享推演结果(开发中)

### 4. ⚙️ 个性化设置
- **多语言支持**: 中文/English (规划中)
- **讲解风格**: 学术风/故事风/简洁风
- **通知管理**: 自定义推送通知设置

## 🏗️ 技术架构

### 技术栈
- **语言**: Kotlin
- **最低SDK**: API 24 (Android 7.0)
- **目标SDK**: API 34 (Android 14)
- **UI框架**:
  - Material Design 3
  - Android ViewBinding
  - RecyclerView
- **异步处理**: Kotlin Coroutines
- **数据存储**: SharedPreferences

### 项目结构
```
com.histora.app/
├── MainActivity.kt              # 主界面
├── EventDetailActivity.kt       # 事件详情页
├── SimulationActivity.kt        # 历史推演页
├── SettingsActivity.kt          # 设置页
├── models/                      # 数据模型
│   ├── HistoricalEvent.kt      # 历史事件模型
│   ├── Simulation.kt           # 推演模型
│   └── ...
├── adapters/                    # RecyclerView适配器
│   ├── EventAdapter.kt
│   ├── ScenarioAdapter.kt
│   └── ...
├── fragments/                   # Fragment组件
│   ├── HomeFragment.kt
│   ├── EventsFragment.kt
│   └── ...
├── views/                       # 自定义视图
│   └── CausalityGraphView.kt   # 因果关系图
└── data/                        # 数据层
    └── SampleData.kt            # 示例数据
```

## 🚀 快速开始

### 环境要求
- Android Studio Hedgehog | 2023.1.1 或更高版本
- JDK 8 或更高版本
- Android SDK API 34
- Gradle 8.2+

### 构建步骤

1. **克隆项目**
```bash
git clone https://github.com/yourusername/Histora.git
cd Histora
```

2. **打开项目**
在Android Studio中打开项目

3. **同步Gradle**
等待Gradle同步完成并下载所有依赖

4. **运行应用**
- 连接Android设备或启动模拟器
- 点击 Run 按钮或使用快捷键 `Shift + F10`

## 📖 使用指南

### 基本流程

1. **浏览历史事件**
   - 打开应用后在主页查看推荐的历史事件
   - 使用搜索框快速查找特定事件

2. **查看事件详情**
   - 点击事件卡片进入详情页
   - 查看事件背景、关键人物和因果关系图
   - 点击因果关系图中的节点查看详细信息

3. **开始历史推演**
   - 在事件详情页点击"修改历史"按钮
   - 选择要修改的历史因素
   - 从下拉菜单中选择替代值
   - 点击"开始推演"

4. **查看推演结果**
   - 查看系统生成的多个情景
   - 每个情景包含可能性百分比、关键变化和时间线
   - 可以导出或分享推演结果

## 📚 示例事件

应用内置了多个历史事件示例:

1. **二战的转折点**
   - 可修改美国参战时间
   - 可修改苏德战争走向
   - 查看战争可能的不同结果

2. **秦始皇统一六国**
   - 探索商鞅变法失败的影响
   - 模拟不同统一策略的结果

3. **工业革命**
   - 如果工业革命延迟发生
   - 对世界发展的影响

4. **文艺复兴**
   - 探索文艺复兴运动的影响

## 🎨 UI设计特点

- **简洁现代**: Material Design 3设计语言
- **沉浸式体验**: 时间线和因果图可视化
- **交互友好**: 平滑的动画和直观的操作
- **卡片式布局**: 清晰的信息层级
- **配色方案**:
  - 主色调: #2C3E50 (深蓝灰)
  - 强调色: #E74C3C (红色)
  - 时间线原始: #3498DB (蓝色)
  - 时间线替代: #E74C3C (红色)

## 🔮 未来规划

### 短期目标
- [ ] 集成AI模型进行更智能的历史推演
- [ ] 添加更多历史事件数据
- [ ] 实现PDF导出功能
- [ ] 完善分享功能
- [ ] 添加用户收藏功能

### 长期目标
- [ ] 多语言国际化支持
- [ ] 用户生成内容(UGC)
- [ ] 社区讨论功能
- [ ] 语音讲解功能
- [ ] 增强现实(AR)历史场景
- [ ] 在线同步和云存储

## 🤝 贡献指南

欢迎贡献代码、报告问题或提出建议!

1. Fork 本项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 👥 作者

- 项目创建者: [Your Name]
- 邮箱: your.email@example.com

## 🙏 致谢

- 感谢所有历史事件数据的贡献者
- Material Design团队提供的优秀设计规范
- Android开发社区的支持

## 📞 联系我们

- 问题反馈: [GitHub Issues](https://github.com/yourusername/Histora/issues)
- 邮箱: support@histora.app
- 官网: https://histora.app (规划中)

---

**Histora** - 探索历史,改变未来 🚀
