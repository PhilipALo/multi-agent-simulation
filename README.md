#SocialForceSimulation
**本版本为过程版本，各功能仍在不断更新调整中，可能出现重构。**
**此处的文档可能是过期的或错误的，需要及时更新或更改。**
//markdown还是什么鬼我现在还在摸索，不知有没有部署，之后或许搞一下。
//html文档这件事情稍稍明白了。

项目分为5个package，分别为app、container、drawer、geom、model

1.app为应用层，功能是初始化一系列场景，供仿真使用。包括墙、出口、障碍物、智能体等。
2.container是克隆所需的库，主要用于在未来实现各种的加速功能。
3.drawer是用于实现场景可视化的层。
4.geom是几何层，主要实现各种形状的初始化及各种变化和计算功能。
5.model是社会力模型层，用于计算各实体之间相互力的作用，并在场景中实现。


项目目的在于实现多场景的社会力仿真，并对其运算效率进行优化，获得相关优化参数。
目前阶段的工作在于首先实现社会力的仿真，并初步部署可视化策略。