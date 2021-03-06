package org.socialforce.model;

import org.socialforce.geom.*;

/**
 * 表示可以移动的实体。
 *
 * @see Agent
 * Created by Ledenel on 2016/7/30.
 */
public interface Moveable extends InteractiveEntity {

    /**
     * 使用determineNext()方法计算出的结果。
     * 该方法会将时间往前推进一步。
     * 当act()成功执行，其还会将之前determineNext()方法计算出的结果清零。
     * 当无法获得该agent通过determineNext()方法计算所得的结果时，不会有移动。
     * 当agent到达目标（或者逃出）时，不会有移动。
     */
    void act();

    /**
     * 获取移动实体的速度。.
     *
     * @return currVelocity。 当前速度
     */
    Velocity getVelocity();

    /**
     * 将实体以一定大小的力推向目标点。
     *
     * @param affection 推时力的大小
     */
    void push(Affection affection);

    /**
     * 用大小为force的力推位于特定位置上的点。
     * 该方法还可以使实体旋转。
     *
     * @param force      推力大小
     * @param startPoint 力作用的位置。
     */
    void push(Force force, Point startPoint);
}
