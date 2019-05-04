package com.mw;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class snackWin extends JPanel implements ActionListener, KeyListener {

//     �����ִ�����
    static final int Up = 0 , Down = 1 , Left = 2 , Right = 3;
//    GameWidth/GameHeight ����Ϸ����Ŀ���
//    GameLocX / GameLocY����Ϸ��������Ͻ�λ�õ�����
    static final int GameLocX = 50, GameLocY = 50 , GameWidth = 700 , GameHeight = 500 , Size = 10;//Size:ÿ���ƶ���λ�ô�С�Լ����ӵĳ���
 //    rx,ry��ʳ�������
    static int rx , ry , score = 0 , speed = 5; 
    boolean startFlag = false;
    JButton startButton , stopButton , quitButton;
    Snack snack;

    public snackWin() {
        snack = new Snack();
//        �������ʳ���λ��
        rx = (int)(Math.random() * (GameWidth - 10) + GameLocX);
        ry = (int)(Math.random() * (GameHeight - 10) + GameLocY);

        startButton = new JButton("��ʼ");
        stopButton = new JButton("��ͣ");
        quitButton = new JButton("�˳�");

        setLayout(new FlowLayout(FlowLayout.LEFT));
        // ���������������ʼ����ͣ���˳�
        this.add(startButton);
        this.add(stopButton);
        this.add(quitButton);
        // ������������ļ����¼�
           startButton.addActionListener(this);
           stopButton.addActionListener(this);
           quitButton.addActionListener(this);
           this.addKeyListener(this);
           new Thread(new snackThread()).start();  
       }

       public void paint(Graphics g)
       {
           super.paint(g);   //û�лὫbuttonˢ��
//           ���û�����ɫ
           g.setColor(Color.white);
//          �ð�ɫ ���һ����������
           g.fillRect(GameLocX, GameLocY, GameWidth, GameHeight);  
           g.setColor(Color.black);
//           drawRect���ڻ����Σ�X��YΪ���Ͻǵ�λ�ã�width��heightΪ���εĿ�͸�
//            �ú�ɫ��һ������
           g.drawRect(GameLocX, GameLocY, GameWidth, GameHeight); 
//           �����ַ���
           g.drawString("Score: " + score + "        Speed: " + speed + "      �ٶ����Ϊ100" , 250, 25);
//         ����ɫ���һ������Ϊ10�ľ��� ��ʾ ʳ��
         g.setColor(Color.green);
         g.fillRect(rx, ry, 10, 10); 
//         ����
         snack.draw(g);
     }
       public void actionPerformed(ActionEvent e) {
//         ����ʼ��ť�����ʱ����ʼ��ť�������ٱ����
           if(e.getSource() == startButton) {
               startFlag = true;
               startButton.setEnabled(false);
               stopButton.setEnabled(true);
           }
           if(e.getSource() == stopButton) {
               startFlag = false;
               startButton.setEnabled(true);
               stopButton.setEnabled(false);
           }
//           �˳�����
           if(e.getSource() == quitButton) {
               System.exit(0);
           }
           this.requestFocus();
       }

       /**
        * �����̱�����ʱ����
        */
       public void keyPressed(KeyEvent e) {
    	   //����û���¿�ʼ��ťʱ������������
           if(!startFlag) 
               return ;

           switch(e.getKeyCode()) {
//            ����ߵĳ���Ϊ1�����ʾ�տ�ʼ��ʲô���򶼿��ԣ�
//            ���ߵĳ��Ȳ�Ϊ1�����ʾ�����˶�����ʱ���ܲ������򷴷����˶�
              case KeyEvent.VK_UP:
                  if(snack.length() != 1 && snack.getDir() == Down) break;
                  snack.changeDir(Up);
                  break;
             case KeyEvent.VK_DOWN:
                 if(snack.length() != 1 && snack.getDir() == Up) break;
                 snack.changeDir(Down);
                 break;
             case KeyEvent.VK_LEFT:
                 if(snack.length() != 1 && snack.getDir() == Right) break;
                 snack.changeDir(Left);
                 break;
             case KeyEvent.VK_RIGHT:
                 if(snack.length() != 1 && snack.getDir() == Left) break;
                 snack.changeDir(Right);
                 break;
           }
      }

      public void keyReleased(KeyEvent e) {}
      public void keyTyped(KeyEvent e) {}

      class snackThread implements Runnable
      {
          public void run() {
              while(true) {
                  try {
//                    ͨ���߳�����ʱ������ߵ��ٶ�
                      Thread.sleep(100 - speed >= 0 ? 100 - speed : 0);
                      repaint(); // ����paint()���ػ������ˢ�½��棩
                      if(startFlag) {
                          snack.move();
                      }
                  }
                  catch(InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
  }