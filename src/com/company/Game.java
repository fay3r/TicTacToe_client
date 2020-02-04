package com.company;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import javax.swing.*;

public class Game extends JPanel implements MouseListener  {

    private boolean isInside = false;
    private boolean circle = false;
    private boolean first = false;

    private GameStatus status = GameStatus.Null;
    private Board level = new Board();
    final private network net = new network();

    Game()
    {
        addMouseListener(this);
    }

    void runGame(){
        try
        {
            network.reset();

            if(!first)
            {
                if(circle) {
                    network.bot('X');

                }
                else {
                    network.bot('O');
                }

                this.first = true;
            }

            level = network.result();

        }
        catch(Exception ignored)
        {
            status = GameStatus.Exception;
        }

        status = GameStatus.Active;

    }


    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {

        isInside = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Point point = e.getPoint();

        if(isInside)
        {
            int x = point.x / 100;
            int y = point.y / 100;

            if(x < 0 || x > 2)
            {
                x = -1;
            }

            if(y < 0 || y > 3)
            {
                y = -1;
            }

            try
            {
                if(first && x >-1 && y >-1 && level.getField(x, y) == null && !level.isFull())
                {
                    first = false;

                    if(circle)
                    {
                        network.user(new User(x+1, y+1, 'O'));

                        level = network.result();

                        if(!level.isFull()) {
                            network.bot('X');
                        }
                        else
                        {
                            status = GameStatus.Full;
                        }

                    }
                    else {
                        network.user(new User(x+1, y+1, 'X'));

                        level = network.result();

                        if(!level.isFull()) {
                            network.bot('O');
                        }
                        else
                        {
                            status = GameStatus.Full;
                        }

                    }


                    level = network.result();

                    first = true;
                }
            }
            catch(Exception exp)
            {
                status = GameStatus.Exception;
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    void updateGame()
    {

        if((level.check('X') && !circle) || (level.check('O') && circle))
        {
            System.err.println("Player win");
            status = GameStatus.Player;
        }
        else if((level.check('O') && !circle) || (level.check('X') && circle))
        {
            System.err.println("Computer win");
            status = GameStatus.Computer;
        }
        else if(level.isFull())
        {
            System.err.println("Level is full");
            status = GameStatus.Full;
        }

    }

    @Override
    public void paint (Graphics g){

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        g2.draw(new Line2D.Float(0, 100, 300, 100));

        g2.draw(new Line2D.Float(0, 200, 300, 200));

        g2.draw(new Line2D.Float(100, 0, 100, 300));

        g2.draw(new Line2D.Float(200, 0, 200, 300));

        for( int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (level.getField(i, j) != null) {

                    if (level.getField(i, j).equals('X')) {

                        if (circle) {
                            g2.setColor(Color.RED);
                        } else {
                            g2.setColor(Color.GREEN);
                        }

                        g2.draw(new Line2D.Float((i * 100 + 5), (j * 100 + 5), (i * 100 + 95), (j * 100 + 95)));
                        g2.draw(new Line2D.Float((i * 100 + 95), (j * 100 + 5), (i * 100 + 5), (j * 100 + 95)));
                    } else if (level.getField(i, j).equals('O')) {

                        if (circle) {
                            g2.setColor(Color.GREEN);
                        } else {
                            g2.setColor(Color.RED);
                        }

                        g2.drawOval(((i * 100) + 50) - (45), ((j * 100) + 50) - (45), 90, 90);
                    }
                }
            }
        }

    }

    public GameStatus getStatus()
    {
        return status;
    }

}
