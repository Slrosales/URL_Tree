import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;


public class Interfaz extends javax.swing.JFrame {

    public static Tree tree = new Tree();
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 50;

    public Interfaz() {
        initComponents();

        this.setResizable(false);

    }

    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("Fondo.png"));
        Image image = icon.getImage();
        jDesktopPane1 = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        Si = new javax.swing.JPanel();
        URL = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 102, 0));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 600));

        Si.setBackground(new java.awt.Color(153, 153, 255));
        Si.setPreferredSize(new java.awt.Dimension(9999, 9999));
        Si.setVerifyInputWhenFocusTarget(false);
        Si.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                SiAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout SiLayout = new javax.swing.GroupLayout(Si);
        Si.setLayout(SiLayout);
        SiLayout.setHorizontalGroup(
                SiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 9999, Short.MAX_VALUE)
        );
        SiLayout.setVerticalGroup(
                SiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 9999, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(Si);

        URL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                URLActionPerformed(evt);
            }
        });

        jButton1.setText("Dibujar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_dibujar(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                                .addGap(147, 147, 147)
                                                .addComponent(URL, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(168, 168, 168)
                                                .addComponent(jButton1)))
                                .addContainerGap(53, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
                jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(URL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1))
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(URL, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SiAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_SiAncestorMoved
        start();
    }//GEN-LAST:event_SiAncestorMoved

    private void URLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_URLActionPerformed

    }//GEN-LAST:event_URLActionPerformed

    private void B_dibujar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_dibujar
        Scanner read = new Scanner(System.in);

        String url = URL.getText();
        while (urlValidator(url) != true) {
            url = read.nextLine();
        }

        if (statusCode(url) == 200) {

            Document doc = getHTML(url);
            Document docs = Jsoup.parse(doc.toString());

            webCrawler(docs.getElementsByTag("html").get(0), null);
            int Height = tree.Height(tree.getRoot());
            int maxNodes = tree.maxLevelNodes(tree.getRoot(), 0);
            int width = width(maxNodes, 35, 1);
            int height = height(Height, 1);

            tree.coordinates(tree.getRoot(), width, height);
            tree.print(tree.getRoot());
            tree.preOrder(tree.getRoot());

        }
        start();

    }//GEN-LAST:event_B_dibujar

    public void setObjArbol(Tree objArbol) {
        this.tree = objArbol;

        Graphics g = Si.getGraphics();
        if (tree.getRoot() != null) {
            pintar(g, tree.getRoot(), null);
        }
    }

    private void pintar(Graphics g, Node n, Point ptRoot) {
        int x = n.getX();
        int y = n.getY();

        g.drawOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);
        g.drawString(n.getElement() + "", x - 6, y + 4);
        if (ptRoot != null) {
            g.drawLine(ptRoot.x, ptRoot.y + RADIO, x, y - RADIO);
        }
        if (n != null) {

            for (Node act : n.getChild()) {

                pintar(g, act, new Point(x, y));

            }
        }
    }
    private void dibujarLinea(Graphics g, int x1, int y1, int x2, int y2) {

        double d = Math.sqrt(ANCHO * ANCHO + (x2 - x1) * (x2 - x1));
        int xx1 = (int) (x1 - RADIO * (x1 - x2) / d);
        int yy1 = (int) (y1 - RADIO * (y1 - y2) / d);
        int xx2 = (int) (x2 + RADIO * (x1 - x2) / d);
        int yy2 = (int) (y2 + RADIO * (y1 - y2) / d);

        g.drawLine(xx1, yy1, xx2, yy2);
    }

    public void start() {
        this.setObjArbol(tree);
    }

    public static void main(String[] args) throws IOException {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Interfaz prueba = new Interfaz();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                prueba.setVisible(true);

            }
        });
    }



    public static int width(int maxNodes, int xd,int xy){
        return xd  * maxNodes;
    }
    public static int height(int height,int xy){
        return height * xy;
    }

    public static boolean urlValidator(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (URISyntaxException exception) {
            return false;
        }
        catch (MalformedURLException exception) {
            return false;
        }
    }

    public static void webCrawler (Element element, String father){
        if (element != null) {
            tree.insert(element.nodeName(), father);
        } else {
            return;
        }

        if (element.childrenSize() > 0){
            //if (element.firstElementChild() != null && element.nodeName() != null)
              //  System.out.println(element.firstElementChild().nodeName()+ " " +element.nodeName());
            webCrawler(element.firstElementChild(), element.nodeName());
        }
        //if (element.nextElementSibling() != null && element.nodeName() != null)
          //  System.out.println("xd    "+element.nextElementSibling().nodeName()+ " " +element.nodeName());
        webCrawler(element.nextElementSibling(), father);
    }


    public static int statusCode(String url){
        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000000).ignoreHttpErrors(true).execute();
        } catch (IOException ex){
            System.out.println("Error");
        }

        return response.statusCode();
    }

    public static Document getHTML(String url){
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000000).get();
            return doc;
        } catch (IOException ex){
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Si;
    private javax.swing.JTextField URL;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}