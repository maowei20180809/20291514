package com.mw;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *  ���ÿ�����X��Y����
 */
class Node {
    private int x , y;
    public Node() {}
    public Node(int a , int b){
         x = a;
         y = b;
     }
    public Node(Node tmp) {
        x = tmp.getX(); 
        y = tmp.getY();
    }
    int getX() {
        return x;
    }
    int getY() {
        return y;
    }
    void setX(int a) {
        x = a;
    }
    void setY(int b) {
        y = b;
    }
}


public class Snack {

    static final int DIR [][] = {{0 , -1} , {0 , 1} , {-1 , 0} , {1 , 0}};
    private List<Node> lt = new ArrayList<Node>();
    private int curDir; //��ǰ�˶�����

    /**
     *  ��ʼ��
     *  �ߵĳ�ʼ���������ң�Right=3
     */
    public Snack() {
        curDir = 3;
        lt.add(new Node(350 , 250));
    }

    /**
     *  List�ĳ��ȱ�ʾ�ߵĳ���
     * @return
     */
    int length() {
        return lt.size();
    }

    int getDir() {
        return curDir;
    }

/**  
 * Graphics����ͼ��
 *  ����
 *  �ҵ�ÿ���±�ָ���X��Yֵ��������ߵ�ÿһ������λ��
 */
    void draw(Graphics g) 
    {
        g.setColor(Color.black);
        for(int i = 0; i < lt.size(); i++) {
            g.fillRect(lt.get(i).getX(), lt.get(i).getY(), 10, 10);
        }
    }

  /**
   *  �ж����Ƿ�ҧ���Լ�
   *  ����ߵ�ͷ����0��������λ���غϣ����ʾ��ҧ���Լ���
   * @return
   */
    boolean Dead() {
        for(int i = 1; i < lt.size(); i++) {
            if(lt.get(0).getX() == lt.get(i).getX() && lt.get(0).getY() == lt.get(i).getY()) 
                return true;
        }
        return false;
    }

    /**
     * ͷ���ƶ���λ��
     * @return tmp��ͷ����λ������
     */
    Node headMove()
    {
//      �����ϻ�����ʱ��curDirΪ0/1����ʱX�᲻�䣨�� snackWin.Size * DIR[curDir][0]��ֵΪ0����Y��仯
//      �����������ʱ��curDirΪ2/3����ʱY�᲻�䣨�� snackWin.Size * DIR[curDir][1]��ֵΪ0����X��仯
        int tx = lt.get(0).getX() + snackWin.Size * DIR[curDir][0];
        int ty = lt.get(0).getY() + snackWin.Size * DIR[curDir][1];

//     ��һ�����δ����ߵ�һ���ڵ㣬���εĳ���Ϊ10��������SizeҲΪ10
//     ��ͷ����λ�ô�����Ϸ����Ŀ��֤�������������ұߣ�֮������Ҫ����߳��֣���X�����Ϊ����ߵ�X����λ��
        if(tx > snackWin.GameLocX + snackWin.GameWidth - snackWin.Size) 
            tx = snackWin.GameLocX;
//     ��ͷ����λ��С����ߵ�X���֤꣬���������߲���������ߣ�֮������Ҫ���ұ߳��֣���X�����Ϊ���ұߵ�X����λ��
        if(tx < snackWin.GameLocX) 
            tx = snackWin.GameWidth + snackWin.GameLocX - snackWin.Size;
//     ����ͬ
        if(ty > snackWin.GameLocY + snackWin.GameHeight - snackWin.Size) 
            ty = snackWin.GameLocY;
        if(ty < snackWin.GameLocY) 
            ty = snackWin.GameHeight + snackWin.GameLocY - snackWin.Size;
        Node tmp = new Node(tx, ty);
        return tmp;
    }

    void move()
    {
//      ͷ����λ��
        Node head = headMove();
//    ����ӵĽڵ��λ��
        Node newNode = new Node();
        boolean eat = false;

//     abs()���ز����ľ���ֵ
//     ͨ���ж�ͷ����X/Y����λ�ú�ʳ���X/Y����λ�ã�����ͷ����С��10���ʾ���С��10�ʹ���Ե���
        if(Math.abs(head.getX() - snackWin.rx) < 10 && Math.abs(head.getY() - snackWin.ry) < 10) {
            eat = true;
            newNode = new Node(lt.get(lt.size() - 1));
            snackWin.rx = (int)(Math.random() * (snackWin.GameWidth - 10) + snackWin.GameLocX);
            snackWin.ry = (int)(Math.random() * (snackWin.GameHeight - 10) + snackWin.GameLocY);
        }

//      ��ǰһ�����λ�ø���һ�㣨���ƶ���
        for(int i = lt.size() - 1; i > 0; i--) 
            lt.set(i, lt.get(i - 1));
        lt.set(0, head);

        if(Dead()) {
//          �����Ի��򣬸�֪��Ϸ����
            JOptionPane.showMessageDialog(null, "Snake eating itself", "Message", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
//        ����Ե���ʳ���eatΪtrue����ʱ��ӽڵ㡢������һ���ٶȼ�һ
        if(eat) {
            lt.add(newNode);
            snackWin.score++;
            snackWin.speed++;
        }
    }

    /**
     *  �ı��ߵķ���
     * @param dir
     */
    void changeDir(int dir) {
        curDir = dir;
     }

}
