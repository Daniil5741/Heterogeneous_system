import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.io.*;
import java.util.*;

public class Main {

    FastScanner in;
    PrintWriter out;


    class Number {
        public long getNumerator() {
            return numerator;
        }

        long Nod(long a, long b) {
            while (a != 0 && b != 0) {
                if (a > b)
                    a = a % b;
                else
                    b = b % a;
            }
            return (a + b);
        }

        public void setNumerator(long numerator) {
            this.numerator = numerator;
        }

        public long getDenominator() {
            return denominator;
        }

        public void setDenominator(long denominator) {
            this.denominator = denominator;

        }

        void diminish() {
            long c = Nod(Math.abs(numerator), Math.abs(denominator));
            this.numerator = numerator / c;
            this.denominator = denominator / c;
            if(this.numerator<0 &&this.denominator<0){
                this.numerator=Math.abs(this.numerator);
                this.denominator=Math.abs(this.denominator);
            }
            if(this.numerator>0 &&this.denominator<0){
                this.numerator=(-1)*this.numerator;
                this.denominator=Math.abs(this.denominator);
            }
        }

        long numerator = 0;
        long denominator = 0;

        Number(long numerator, long denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    Number add(Number a, Number b) {
        Number c = new Number(0, 1);
        c.setNumerator(a.numerator * b.denominator + a.denominator * b.numerator);
        c.setDenominator(a.denominator * b.denominator);
        c.diminish();
        return c;
    }

    Number reduce(Number a, Number b) {
        Number c = new Number(0, 1);
        c.setNumerator(a.numerator * b.denominator - a.denominator * b.numerator);
        c.setDenominator(a.denominator * b.denominator);
        c.diminish();
        return c;
    }

    Number multiply(Number a, Number b) {
        Number c = new Number(0, 1);
        c.setNumerator(a.numerator * b.numerator);
        c.setDenominator(a.denominator * b.denominator);
        c.diminish();
        return c;
    }

    Number division(Number a, Number b) {
        Number c = new Number(0, 1);
        c.setNumerator(a.numerator * b.denominator);
        c.setDenominator(a.denominator * b.numerator);
        c.diminish();
        return c;
    }

    Number[][] x;
    Number[] b;
    Number ans[];
    int n;

    void zeroing(int i) {

    }

    public void solve() throws IOException {
        n = in.nextInt();
        x = new Number[n][n + 1];
        b = new Number[n];
        ans = new Number[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                    x[i][j] = new Number(in.nextInt(), 1);
            }
        }
        for (int i = 0; i < n; i++) {
            Number cur = new Number(x[i][i].numerator,x[i][i].denominator);
            long change_numerator = cur.numerator;
            cur.setNumerator(cur.denominator);
            cur.setDenominator(change_numerator);
            for (int j = i; j < n + 1; j++) {
                Number multiple_number = multiply(cur, x[i][j]);
               // out.println("умножение всей строки "+multiple_number.numerator+"/"+multiple_number.denominator);
                x[i][j] = multiple_number;
            }
            Number use = x[i][i];
            for (int j = i + 1; j < n; j++) {
                Number multiple_number = x[j][i];

                for (int k = i; k < n + 1; k++) {
                    Number multiple = multiply(x[i][k], multiple_number);
                 //   out.println();
                 //   out.println("multiple " + x[i][k].numerator +"/"+x[i][k].denominator+ " " + multiple_number.numerator +"/"+multiple_number.denominator+" ");
                    Number result = reduce(x[j][k], multiple);
                //    out.println("reduce " + x[j][k].numerator +"/"+x[j][k].denominator+ " " + multiple.numerator+"/"+multiple.denominator);
               //     out.println();
                    x[j][k] = result;
                 //   out.println("result "+result.numerator+"/"+result.denominator+" ");
                }
            }
            for (int j = i - 1; j >= 0; j--) {
                Number multiple_number = x[j][i];

                for (int k = i; k < n + 1; k++) {
                    Number multiple = multiply(x[i][k], multiple_number);
              //      out.println();
                 //   out.println("multiple " + x[i][k].numerator +"/"+x[i][k].denominator+ " " + multiple_number.numerator +"/"+multiple_number.denominator+" ");
                    Number result = reduce(x[j][k], multiple);
                  //  out.println("reduce " + x[j][k].numerator +"/"+x[j][k].denominator+ " " + multiple.numerator+"/"+multiple.denominator);
                //    out.println();
                    x[j][k] = result;
                }
            }
            for (int q = 0; q < n; q++) {
                for (int j = 0; j < n + 1; j++) {
                    out.print(x[q][j].numerator + "/"+ x[q][i].denominator+" ");
                }
                out.println();
            }
            out.println();
        }

    }


    class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        boolean hasNext() throws IOException {
            return br.ready() || (st != null && st.hasMoreTokens());
        }

        int nextInt() throws IOException {
            try {
                int c=Integer.parseInt(next());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                 Frame theFrame = new JFrame("Решение неоднородной системы");
                ((JFrame) theFrame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                BorderLayout layout = new BorderLayout();
                JPanel backround = new JPanel(layout);
                backround.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JTextArea text = new JTextArea("Данные не читаемые - пожалуйста введите верные данные ");
                backround.add(BorderLayout.CENTER, text);
                ((JFrame) theFrame).getContentPane().add(backround);
                theFrame.setBounds(200, 250, 500, 300);
                theFrame.pack();
                theFrame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }

        boolean hasNextLine() throws IOException {
            return br.ready();
        }

    }
    JFrame theFrame;
    private void run() throws IOException {
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showOpenDialog(theFrame);
        FileInputStream fileInputStream=null;
        FileInputStream fileOutputStream=null;
        try {
            fileInputStream = new FileInputStream(jFileChooser.getSelectedFile());
          //  fileOutputStream =new FileOutputStream(jFileChooser.getSelectedFile());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        in = //new FastScanner(System.in);
                new FastScanner(fileInputStream);
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("output.txt"));
        solve();
      //  theFrame.pack();
       // theFrame.setVisible(true);
        theFrame.dispose();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}