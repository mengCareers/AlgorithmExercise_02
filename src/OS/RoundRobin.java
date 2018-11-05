package OS;

import java.util.Arrays;

public class RoundRobin {
    public static void roundRobin(String p[], int a[],
                                  int b[], int n) {
        int res = 0, resc = 0;
        String sequenceStorage = "";
        int res_b[] = Arrays.copyOf(b, b.length);
        int res_a[] = Arrays.copyOf(a, a.length);
        int criticalTime = 0;
        int w[] = new int[p.length];
        int comp[] = new int[p.length];

        while (true) {
            boolean flag = true;
            for (int i = 0; i < p.length; i++) {
                if (res_a[i] <= criticalTime) {
                    if (res_a[i] <= n) {
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > n) {
                                criticalTime += n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                sequenceStorage += "->" + p[i];
                            } else {
                                criticalTime += res_b[i];
                                comp[i] = criticalTime - a[i];
                                w[i] = criticalTime - b[i] - a[i];
                                res_b[i] = 0;
                                sequenceStorage += "->" + p[i];
                            }
                        }
                    } else if (res_a[i] > n) {

                        // is any have less arrival time
                        // the coming process then execute them
                        for (int j = 0; j < p.length; j++) {

                            // compare
                            if (res_a[j] < res_a[i]) {
                                if (res_b[j] > 0) {
                                    flag = false;
                                    if (res_b[j] > n) {
                                        criticalTime += n;
                                        res_b[j] = res_b[j] - n;
                                        res_a[j] = res_a[j] + n;
                                        sequenceStorage += "->" + p[j];
                                    } else {
                                        criticalTime += res_b[j];
                                        comp[j] = criticalTime - a[j];
                                        w[j] = criticalTime - b[j] - a[j];
                                        res_b[j] = 0;
                                        sequenceStorage += "->" + p[j];
                                    }
                                }
                            }
                        }

                        // now the previous porcess according to
                        // ith is process
                        if (res_b[i] > 0) {
                            flag = false;

                            // Check for greaters
                            if (res_b[i] > n) {
                                criticalTime += n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                sequenceStorage += "->" + p[i];
                            } else {
                                criticalTime += res_b[i];
                                comp[i] = criticalTime - a[i];
                                w[i] = criticalTime - b[i] - a[i];
                                res_b[i] = 0;
                                sequenceStorage += "->" + p[i];
                            }
                        }
                    }
                } else if (res_a[i] > criticalTime) {
                    criticalTime++;
                    i--;
                }
            }
            if (flag) {
                break;
            }
        }

        System.out.println("name  ctime  wtime");
        for (int i = 0; i < p.length; i++) {
            System.out.println(" " + p[i] + "    " + comp[i]
                    + "    " + w[i]);

            res = res + w[i];
            resc = resc + comp[i];
        }

        System.out.println("Average waiting time is "
                + (float) res / p.length);
        System.out.println("Average compilation  time is "
                + (float) resc / p.length);
        System.out.println("Sequence is like that " + sequenceStorage);
    }

}
