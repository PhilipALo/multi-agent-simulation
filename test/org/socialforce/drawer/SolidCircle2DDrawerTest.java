package org.socialforce.drawer;

import org.junit.Test;
import org.socialforce.entity.impl.Circle2D;
import org.socialforce.entity.impl.Point2D;
import org.socialforce.test.util.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

/**
 * Created by Ledenel on 2016/8/12.
 */
public class SolidCircle2DDrawerTest extends AwtMathDrawerTest {
    @Test
    public void renderShape() throws Exception {
        Circle2D circle2D = new Circle2D();
        circle2D.setRadius(3);
        circle2D.moveTo(new Point2D(-1, -1));
        drawer.setCircle(circle2D);
        drawer.draw();
        ImageUtils.assertImageSimilar(image,
                ImageIO.read(SolidCircle2DDrawerTest.class.getResourceAsStream("SolidCircle2DDrawer_base.png")));
    }

    SolidCircle2DDrawer drawer;

    @Override
    protected void drawerInit(Graphics2D gra) {
        drawer = new SolidCircle2DDrawer(gra, null);
        drawer.setColor(0xFFFF0000);
    }
}