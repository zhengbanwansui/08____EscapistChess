package Windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Win extends JFrame implements ActionListener {

    public BGJPanel rootPanel;    // 根面板
    private MVJPanel jpTop;        // 拖动标题栏
    private JButton small,out;     // 最小化最大化按钮
    public JPanel map;            // 地图位置范围
    private JButton btn;           // 开始游戏
    public String direction = "中";
    public boolean startGame = false; // 按开始游戏之后变成true

    public ArrayList<ArrayList<AutoBGJpanel>> jpUp = new ArrayList<>();         // 战争迷雾2d
    public ArrayList<ArrayList<AutoBGJpanel>> jpDown = new ArrayList<>();       // 生物物品2d

    public Win() {
        // 去掉上面的窗口栏
        this.setUndecorated(true);
        initInside();
        setSize(1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400,200);
    }

    private void initInside() {
        // rootPanel为根容器 绝对布局
        rootPanel = new BGJPanel();
        rootPanel.setLayout(null);
        // 可拖拽顶栏
        jpTop = new MVJPanel();
        jpTop.setDragable(this);
        jpTop.setBounds(0,0,1000,100);
        jpTop.setOpaque(false);
        jpTop.setLayout(null);
        // 最小化按钮
        small = new JButton(new ImageIcon(new FilePath().filePath("small.png")));
        small.setBounds(955,2,20,20);
        small.addActionListener(this);
        // 最大化按钮
        out   = new JButton(new ImageIcon(new FilePath().filePath("out.png")));
        out  .setBounds(978,2,20,20);
        out  .addActionListener(this);
        // 地图区域 6x6 每格70像素x70像素
        map = new JPanel();
        map.setBounds(480,130,420,420);
        map.setLayout(null);
        // 按钮
        btn = new JButton("开始游戏");
        btn.setBounds(100,130,120,30);
        btn.addActionListener(this);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int code = e.getKeyCode();
                // 上38 下40 左37 右39
                System.out.println("你按 KeyCode = " + code);
                switch(code){
                    case 38:
                        direction = "上";
                        break;
                    case 40:
                        direction = "下";
                        break;
                    case 37:
                        direction = "左";
                        break;
                    case 39:
                        direction = "右";
                        break;
                    default:
                        System.out.println("错误按键");
                }
            }
        });
        this.setFocusable(true);
        // 面板组合
        this.add(rootPanel);
        rootPanel.add(jpTop);
        rootPanel.add(map);
        rootPanel.add(btn);
        jpTop.add(small);
        jpTop.add(out);
    }

    public void drawUp(ArrayList<ArrayList<unit>> unitUp) {
        for(int i=0; i<6; i++){
            jpUp.add(new ArrayList<AutoBGJpanel>());
            for(int j=0; j<6; j++){
                AutoBGJpanel auto = new AutoBGJpanel(unitUp.get(i).get(j).name);
                auto.setBounds(i*70, j*70, 60, 60);
                auto.setVisible(unitUp.get(i).get(j).visible);
                map.add(auto);
                jpUp.get(i).add(auto);
            }
        }
    }

    public void drawDown(ArrayList<ArrayList<unit>> unitDown) {
        for(int i=0; i<6; i++){
            jpDown.add(new ArrayList<AutoBGJpanel>());
            for(int j=0; j<6; j++){
                AutoBGJpanel auto = new AutoBGJpanel(unitDown.get(i).get(j).name);
                auto.setBounds(10+i*70, 10+j*70, 60, 60);
                auto.setVisible(unitDown.get(i).get(j).visible);
                map.add(auto);
                jpDown.get(i).add(auto);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == small){
            this.setExtendedState(ICONIFIED);
        }
        if(e.getSource() == out  ){
            System.exit(0);
        }
        if(e.getSource() == btn){
            System.out.println("检测到btn按键了");
            // 初始化全部变量
            //2@^%*^TYUF&T*GFT(*GYF(^Y(D^*GYRFYUGH)(&*R(FTYUy一大堆代码
            // 初始化完毕重启游戏
            MapsetVisible(true);
            startGame = true;
            this.requestFocus();
        }
    }

    public void MapsetVisible(boolean b){
        map.setVisible(true);
    }
}