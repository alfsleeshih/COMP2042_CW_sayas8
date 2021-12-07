package brick;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * This class is a child class of the abstract parent class, Brick.
 * 
 * @author Shih Alf Slee
 * @category Software Maintenance
 * @version 2.0
 * @since 0.1
 *
 */


public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * This is a constructor method of class ClayBrick, it initializes the clay brick.
     * 
     * @param point  the upper left coordination of the clay brick
     * @param size  the dimension of the clay brick
     * 
     * @see brick.Brick.Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength)
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }


}
