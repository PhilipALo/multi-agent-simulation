package org.socialforce.app.Applications;

import org.socialforce.app.Interpreter;
import org.socialforce.app.SocialForceApplication;
import org.socialforce.app.impl.SimpleInterpreter;
import org.socialforce.geom.DistanceShape;
import org.socialforce.geom.Shape;
import org.socialforce.geom.impl.Box2D;
import org.socialforce.geom.impl.Circle2D;
import org.socialforce.geom.impl.Point2D;
import org.socialforce.geom.impl.Rectangle2D;
import org.socialforce.model.InteractiveEntity;
import org.socialforce.model.impl.Monitor;
import org.socialforce.scene.ParameterPool;
import org.socialforce.scene.Scene;
import org.socialforce.scene.SceneLoader;
import org.socialforce.scene.impl.*;
import org.socialforce.strategy.GoalStrategy;
import org.socialforce.strategy.PathFinder;
import org.socialforce.strategy.impl.AStarPathFinder;
import org.socialforce.strategy.impl.NearestGoalStrategy;

import java.io.InputStream;
import java.util.Iterator;

import static org.socialforce.scene.SceneLoader.genParameter;

/**
 * Created by Whatever on 2017/3/7.
 */
public class ApplicationForNarrowPattern extends SimpleApplication implements SocialForceApplication {
    DistanceShape template;
    double DoorWidth;
    /**
     * start the application immediately.
     */
    @Override
    public void start() {
        for (Iterator<Scene> iterator = scenes.iterator(); iterator.hasNext(); ) {
            Scene scene = iterator.next();
            PathFinder pathFinder = new AStarPathFinder(scene, template);
            GoalStrategy strategy = new NearestGoalStrategy(scene, pathFinder);
            strategy.pathDecision();
            while (!scene.getAllAgents().isEmpty()) {
                scene.stepNext();
            }
        }
    }

    /**
     * 需要根据parameter的map来生成一系列scene
     */
    @Override
    public void setUpScenes(){
        template = new Circle2D(new Point2D(0,0),0.486/2);
        DoorWidth = 1.36;
        setUpT1Scene();
        setUpT1Scene2();
        setUpT2Scene();
        setUpPassageScene();
        setUpPassageScene2();
        setUpOtherScene();
    }

    protected void setUpT1Scene(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("T1.s");
        Interpreter interpreter = new SimpleInterpreter();
        interpreter.loadFrom(is);
        SceneLoader loader = interpreter.setLoader();
        ParameterPool parameters = new SimpleParameterPool();
        parameters.addLast(genParameter(new SV_Exit(new Box2D[]{new Box2D(5-DoorWidth/2,-0.5,DoorWidth,2),new Box2D(5-DoorWidth/2,-4.5,DoorWidth,2),new Box2D(5-DoorWidth/2,0.5,DoorWidth,4)}),
                new SV_Exit(new Box2D[]{new Box2D(10-DoorWidth,-0.5,DoorWidth,2),new Box2D(10-DoorWidth,-4.5,DoorWidth,2),new Box2D(10-DoorWidth,0.5,DoorWidth,4)})));
        parameters.addLast(genParameter(new SV_RandomAgentGenerator(200,new Box2D(0,0,10,-10),template)));
        parameters.addLast(genParameter(new SV_Wall(new Box2D[]{new Box2D(0,-4,10,-3)}),new SV_Wall(new Shape[]{new Circle2D(new Point2D(5,-2),1)}),new SV_Wall(new Shape[]{new Box2D(4,-3,2,1)})
                ,new SV_Wall(new Shape[]{new Box2D(),new Box2D()}),new SV_Wall(new Shape[]{new Box2D(0,1,10,3)}),new SV_Wall(new Shape[]{})));        parameters.addLast(genParameter(new SV_RandomAgentGenerator(200,new Box2D(0,0,10,-10),template)));
        parameters.addLast(genParameter(new SV_SafetyRegion(new Box2D(1,6,8,1))));
        loader.readParameterSet(parameters);
        for (Scene s : loader.readScene(this)){
            scenes.add(s);
        }
    }

    protected void setUpT1Scene2(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("T1.s");
        Interpreter interpreter = new SimpleInterpreter();
        interpreter.loadFrom(is);
        SceneLoader loader = interpreter.setLoader();
        ParameterPool parameters = new SimpleParameterPool();
        parameters.addLast(genParameter(new SV_Exit(new Box2D[]{new Box2D(5-DoorWidth,-0.5,2*DoorWidth,2)})));
        parameters.addLast(genParameter(new SV_RandomAgentGenerator(200,new Box2D(0,0,10,-10),template)));
        parameters.addLast(genParameter(new SV_Wall(new Shape[]{new Rectangle2D(new Point2D(3,5),1,5,Math.PI/6),new Rectangle2D(new Point2D(7,5),1,5,-Math.PI/6)}),new SV_Wall(new Shape[]{new Rectangle2D(new Point2D(3,5),1,5,-Math.PI/6),new Rectangle2D(new Point2D(7,5),1,5,Math.PI/6),new Box2D(5-DoorWidth,0,DoorWidth/2,1),new Box2D(5+DoorWidth,0,-DoorWidth/2,1)}),new SV_Wall(new Shape[]{new Box2D(5-DoorWidth,0,DoorWidth/2,1),new Box2D(5+DoorWidth,0,-DoorWidth/2,1),new Rectangle2D(new Point2D(3,3),1,5,Math.PI/4),new Rectangle2D(new Point2D(7,3),1,5,-Math.PI/4),new Rectangle2D(new Point2D(3,6),1,5,-Math.PI/4),new Rectangle2D(new Point2D(7,6),1,5,Math.PI/4)})));
        parameters.addLast(genParameter(new SV_SafetyRegion(new Box2D(1,10,8,1))));
        loader.readParameterSet(parameters);
        for (Scene s : loader.readScene(this)){
            scenes.add(s);
        }
    }

    protected void setUpT2Scene(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("T2.s");
        Interpreter interpreter = new SimpleInterpreter();
        interpreter.loadFrom(is);
        SceneLoader loader = interpreter.setLoader();
        ParameterPool parameters = new SimpleParameterPool();
        parameters.addLast(genParameter(new SV_RandomAgentGenerator(400,new Box2D(0,0,10,-20),template)));

        parameters.addLast(genParameter(new SV_Exit(new Box2D[]{new Box2D(5-DoorWidth/2,-0.5,DoorWidth,2)}),new SV_Exit(new Box2D[]{new Box2D(10-DoorWidth,-0.5,DoorWidth,2)})));

        parameters.addLast(genParameter(new SV_Wall(new Shape[]{new Circle2D(new Point2D(3,-4),2),new Circle2D(new Point2D(7,-4),2)}),new SV_Wall(new Shape[]{}),new SV_Wall(new Shape[]{new Box2D(1,-10,1,9),new Box2D(3,-10,1,9),new Box2D(5,-10,1,9),new Box2D(7,-10,1,9),new Box2D(9,-10,1,9)})));

        parameters.addLast(genParameter(new SV_SafetyRegion(new Box2D(1,6,8,1))));
        loader.readParameterSet(parameters);
        for (Scene s : loader.readScene(this)){
            scenes.add(s);
        }
    }

    protected void setUpPassageScene(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("passage.s");
        Interpreter interpreter = new SimpleInterpreter();
        interpreter.loadFrom(is);
        SceneLoader loader = interpreter.setLoader();
        ParameterPool parameters = new SimpleParameterPool();
        parameters.addLast(genParameter(new SV_Exit(new Box2D[]{new Box2D(4,-2,2,5)})));
        parameters.addLast(genParameter(new SV_RandomAgentGenerator(100,new Box2D(0,0,10,-10),template)));
        parameters.addLast(genParameter(new SV_SafetyRegion(new Box2D(0,6,4,1))));
        loader.readParameterSet(parameters);
        for (Scene s : loader.readScene(this)){
            scenes.add(s);
        }
    }

    protected void setUpPassageScene2(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("passage.s");
        Interpreter interpreter = new SimpleInterpreter();
        interpreter.loadFrom(is);
        SceneLoader loader = interpreter.setLoader();
        ParameterPool parameters = new SimpleParameterPool();
        parameters.addLast(genParameter(new SV_Exit(new Box2D[]{new Box2D(4,-2,2,5)})));
        parameters.addLast(genParameter(new SV_RandomAgentGenerator(100,new Box2D(0,0,10,-10),template)));
        parameters.addLast(genParameter(new SV_SafetyRegion(new Box2D(0,6,4,1))));
        loader.readParameterSet(parameters);
        for (Scene s : loader.readScene(this)){
            scenes.add(s);
        }
    }

    protected void setUpOtherScene(){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("void.s");
        Interpreter interpreter = new SimpleInterpreter();
        interpreter.loadFrom(is);
        SceneLoader loader = interpreter.setLoader();
        ParameterPool parameters = new SimpleParameterPool();
        parameters.addLast(genParameter(new SV_Exit(new Box2D[]{new Box2D(4,-2,2,5)})));
        parameters.addLast(genParameter(new SV_RandomAgentGenerator(100,new Box2D(0,0,10,-10),template)));
        parameters.addLast(genParameter(new SV_SafetyRegion(new Box2D(1,6,8,1))));
        loader.readParameterSet(parameters);
        for (Scene s : loader.readScene(this)){
            scenes.add(s);
        }
    }
}

