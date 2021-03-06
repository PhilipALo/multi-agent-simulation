package org.socialforce.strategy;

import org.socialforce.geom.Point;
import org.socialforce.model.Agent;

/**接受场景，寻路器和目标点集合
 * 实现为场景中的每一个Agent按既定策略规划目标点
 * 并调用Pathfinder生成路径，赋予Agent
 * @see PathFinder
 * @see Agent
 * Created by sunjh1999 on 2016/12/14.
 */
public interface GoalStrategy {
    /**
     * 为Agent选择出口，规划路径
     */
    void pathDecision();
}

