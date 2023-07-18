package spring.vkr.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.vkr.dao.DAO;
import spring.vkr.entity.InitialData;
import spring.vkr.entity.Result;
import java.lang.Math;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private DAO DAO;

    @RequestMapping("/")
    public String main(Model model) {

        InitialData initialData = new InitialData();
        model.addAttribute("data", initialData);

        return "main";
    }

    @RequestMapping("/goToMain")
    public String goToMain(Model model) {

        InitialData initialData = new InitialData();
        model.addAttribute("data", initialData);

        return "main";
    }
    //добавление записи в БД будет через sql insert строку, указав поля(переменные)без id.
    // id в форме hidden-формат

    @RequestMapping("/showOldInitialData")
    public String showOldInitialData(Model model) {

        List<InitialData> allInitialData = DAO.showOldInitialData();
        model.addAttribute("allInitialData", allInitialData);

        return "old_initial_data";
    }

    @RequestMapping(value = "/a", params = "createNewData", method = RequestMethod.POST)
    public String createNewData(@ModelAttribute("data") InitialData initialData) {

        DAO.createNewData(initialData);

        return "main";
    }

    @RequestMapping("/useOldData")
    public String useOldData(@RequestParam("dataId") int id, Model model) {

        InitialData initialData = DAO.getOldData(id);
        model.addAttribute("data", initialData);

        return "main";
    }

    @RequestMapping("/deleteOldData")
    public String deleteOldData(@RequestParam("dataId") int id) {

        DAO.deleteOldData(id);

        return "redirect:/showOldInitialData";

    }

    @RequestMapping(value = "/a", params = "sendDataToCalculation", method = RequestMethod.POST)
    public ModelAndView doCalulation(@ModelAttribute("data") InitialData initialData) {

        ModelAndView modelAndView = new ModelAndView("result");

        Result result = new Result();

        final int m = 3;//число фаз
        final float PI = 3.14F; //пи
        final float p75 = 0.00000002439F; //удельное сопротивление t=75
        final float p115 = 0.0000000454F; //удельное сопротивление t=115
        final float kB = 1.11F; //кф формы поля
        final float Mo = 0.000001257F; //мю 0
        final float kFe = 0.97F; //кф заполнения пакета стали для 0,5мм
        final float hk = 0.003F; //высота клина
        final float kpp1 = 0.91F;//коэфффициент, учитывающий демпфирующую реакцию токов
        final float p1050 = 2.5F;//удельные потери в стали при  50 Гц и 1 Тл

        double[][] Ke2 = new double[][]{{0.971, 0.98, 0.981, 0.982, 0.985, 0.99}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] Ke4 = new double[][]{{0.945, 0.964, 0.96, 0.96, 0.978, 0.988}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] Ke6 = new double[][]{{0.913, 0.944, 0.956, 0.965, 0.968, 0.983}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] Ke8 = new double[][]{{0.86, 0.91, 0.93, 0.947, 0.95, 0.974}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] H2013 = new double[][]{{0.001, 141, 300, 780, 3000, 7000, 13000, 22000, 90000}, {0.001, 1.2, 1.4, 1.52, 1.68, 1.8, 2, 2.1, 2.4}};
        double[][] kd1 = new double[][]{{1, 2, 3, 4, 5, 6}, {0.01, 0.0285, 0.0141, 0.0089, 0.0065, 0.0052}};
        //double[][] fii = new double[][]{{0.001, 0.1, 0.4, 1.2, 2.05, 2.8}, {0, 0.8, 1.6, 2.4, 3.2, 4}};
        double[][] A12 = new double[][]{{110, 148, 164, 240, 158, 238}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] A14 = new double[][]{{63, 93, 100, 110, 109, 150}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] A16 = new double[][]{{38, 80, 90, 100, 99.5, 108}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] A18 = new double[][]{{30, 62, 80, 83, 89, 113}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] AB24 = new double[][]{{14, 20, 24, 27, 21, 41}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] AB6 = new double[][]{{13, 15, 21, 25, 18, 40}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] AB8 = new double[][]{{11, 11, 19, 17.5, 15, 24}, {0.08, 0.15, 0.2, 0.273, 0.3, 0.7}};
        double[][] Ppx = new double[][]{{0.028, 0.15, 0.32, 0.44, 0.52, 0.59}, {0.04, 0.08, 0.16, 0.24, 0.32, 0.4}};

        int n = 5; //количество элементов в таблице
        float M9[] = new float[n];
        float Sn9[] = new float[n];
        double Ioo9[] = new double[n];
        float cos09[] = new float[n];
        float KPDp9[] = new float[n];

        float KI = 0, k = 0.25F, Pd9, PB9, I2II9, I1A9, I1R9, Pcu19, Pcu29, Psum9, P19;
        int i = 0, j = 0, w = 0, u = 0;
        double ke = 0, A1 = 0, AB = 0, ST2 = 0, Pp = 0, Rn9, Zn9;

        int Pn =initialData.getPn();
        int nc =initialData.getNc();
        int U1 =initialData.getU1();
        float f =initialData.getF();
        int p =60 * (int)f /nc;
        p = 2;
        float KPD =initialData.getKPD();
        float cos =initialData.getCos();
        float h =initialData.getH();
        float DB =initialData.getDB();
        float Da =initialData.getDa();
        float D1 =initialData.getD1();
        float l1 =initialData.getL1();
        int z1 =initialData.getZ1();
        float b11 =initialData.getB11();
        float b12 =initialData.getB12();
        float h11 =initialData.getH11();
        float e11 =initialData.getE11();
        float m11 =initialData.getM11();
        float b21 =initialData.getB21();
        float b22 =initialData.getB22();
        float h21 =initialData.getH21();
        float e21 =initialData.getE21();
        float m21 =initialData.getM21();
        float dI =initialData.getdI();
        float O =initialData.getO();
        int z2 =initialData.getZ2();
        float bck =initialData.getBck();
        int Sp1 =initialData.getSp1();
        int a1 =initialData.getA1();
        float qef =initialData.getQef();
        int Nef =initialData.getNef();
        int yp1 =initialData.getYp1();

        while (Da >= Ke2[1][i]) {
            i++;
        }
        while (h >= Ppx[1][u]) {
            u++;
        }
        ST2 = Math.log10((Ppx[0][u]) / (Ppx[0][u - 1])) / (Math.log10((Ppx[1][u]) / (Ppx[1][u - 1])));
        Pp = Ppx[0][u - 1] * Math.pow((h / Ppx[1][u - 1]), ST2);

        if (2 * p == 2) {
            ST2 = Math.log10((Ke2[0][i]) / (Ke2[0][i - 1])) / (Math.log10((Ke2[1][i]) / (Ke2[1][i - 1])));
            ke = Ke2[0][i - 1] * Math.pow((Da / Ke2[1][i - 1]), ST2);
            ST2 = Math.log10((A12[0][i]) / (A12[0][i - 1])) / (Math.log10((A12[1][i]) / (A12[1][i - 1])));
            A1 = A12[0][i - 1] * Math.pow((Da / A12[1][i - 1]), ST2);
            ST2 = Math.log10((AB24[0][i]) / (AB24[0][i - 1])) / (Math.log10((AB24[1][i]) / (AB24[1][i - 1])));
            AB = AB24[0][i - 1] * Math.pow((Da / AB24[1][i - 1]), ST2);
            KI = 0.22F;
        }
        if (2 * p == 4) {
            ST2 = Math.log10((Ke4[0][i]) / (Ke4[0][i - 1])) / (Math.log10((Ke4[1][i]) / (Ke4[1][i - 1])));
            ke = Ke4[0][i - 1] * Math.pow((Da / Ke4[1][i - 1]), ST2);
            ST2 = Math.log10((A14[0][i]) / (A14[0][i - 1])) / (Math.log10((A14[1][i]) / (A14[1][i - 1])));
            A1 = A14[0][i - 1] * Math.pow((Da / A14[1][i - 1]), ST2);
            ST2 = Math.log10((AB24[0][i]) / (AB24[0][i - 1])) / (Math.log10((AB24[1][i]) / (AB24[1][i - 1])));
            AB = AB24[0][i - 1] * Math.pow((Da / AB24[1][i - 1]), ST2);
            KI = 0.2F;
        }
        if (2 * p == 6) {
            ST2 = Math.log10((Ke6[0][i]) / (Ke6[0][i - 1])) / (Math.log10((Ke6[1][i]) / (Ke6[1][i - 1])));
            ke = Ke6[0][i - 1] * Math.pow((Da / Ke6[1][i - 1]), ST2);
            ST2 = Math.log10((A16[0][i]) / (A16[0][i - 1])) / (Math.log10((A16[1][i]) / (A16[1][i - 1])));
            A1 = A16[0][i - 1] * Math.pow((Da / A16[1][i - 1]), ST2);
            ST2 = Math.log10((AB6[0][i]) / (AB6[0][i - 1])) / (Math.log10((AB6[1][i]) / (AB6[1][i - 1])));
            AB = AB6[0][i - 1] * Math.pow((Da / AB6[1][i - 1]), ST2);
            KI = 0.19F;
        }
        if (2 * p == 8) {
            ST2 = Math.log10((Ke8[0][i]) / (Ke8[0][i - 1])) / (Math.log10((Ke8[1][i]) / (Ke8[1][i - 1])));
            ke = Ke8[0][i - 1] * Math.pow((Da / Ke8[1][i - 1]), ST2);
            ST2 = Math.log10((A18[0][i]) / (A18[0][i - 1])) / (Math.log10((A18[1][i]) / (A18[1][i - 1])));
            A1 = A18[0][i - 1] * Math.pow((Da / A18[1][i - 1]), ST2);
            ST2 = Math.log10((AB8[0][i]) / (AB8[0][i - 1])) / (Math.log10((AB8[1][i]) / (AB8[1][i - 1])));
            AB = AB8[0][i - 1] * Math.pow((Da / AB8[1][i - 1]), ST2);
            KI = 0.18F;
        }
        //п.2
        float l = l1 ;
        float t = PI * D1 / (2 * p);
        float q1 = z1 / (2 * p * m);
        while ((int) q1 != kd1[0][j]) {
            j++;
        }
        float tz1 = PI * D1 / z1;
        float D2 = D1 - 2 * O;
        float tz2 = PI * D2 / z2;
        float I1H = Pn / (m * U1 * KPD * cos);
        float w1 = Sp1 * p * q1 / a1;
        float bb = z1 / (2 * p * yp1);
        double ky1 = Math.sin(bb * PI / 2);
        double kp1 = 0.5 / (q1 * Math.sin(PI / 6 / q1));
        float kob = (float) ky1 * (float) kp1;
        float FF = (float) ke * U1 / (4 * kB * w1 * kob * f);
        float Bo = FF * p / (l * D1);
        float ko1 = 1 + m11 / (tz1 - m11 + 5 * O * tz1 / m11);
        float ko2 = 1 + m21 / (tz2 - m21 + 5 * O * tz2 / m21);
        float ko = ko1 * ko2;
        float Fo = O * ko * Bo / Mo;
        float hp1 = h11 + e11;
        float bz1 = PI * (D1 + 2 * hp1) / z1 - b12;
        float Bz1 = Bo * tz1 / (bz1 * kFe);
        i = 0;
        while (Bz1 >= H2013[1][i]) {
            i++;
        }
        ST2 = Math.log10((H2013[0][i]) / (H2013[0][i - 1])) / (Math.log10((H2013[1][i]) / (H2013[1][i - 1])));
        double Hz1 = H2013[0][i - 1] * Math.pow((Bz1 / H2013[1][i - 1]), ST2);
        float Fz1 = (float) Hz1 * hp1;
        float hp2 = h21 + e21;
        float bz2 = PI * (D2 - 2 * hp2) / z2 - b22;
        float Bz2 = Bo * tz2 / (bz2 * kFe);
        i = 0;
        while (Bz2 >= H2013[1][i]) {
            i++;
        }
        ST2 = Math.log10((H2013[0][i]) / (H2013[0][i - 1])) / (Math.log10((H2013[1][i]) / (H2013[1][i - 1])));
        double Hz2 = H2013[0][i - 1] * Math.pow((Bz2 / H2013[1][i - 1]), ST2);
        float Fz2 = (float) Hz2 * hp2;
        float ha1 = (Da - D1 - 2 * hp1) / 2;
        float Ba1 = FF / (2 * ha1 * l * kFe);
        i = 0;
        while (Ba1 >= H2013[1][i]) {
            i++;
        }
        ST2 = Math.log10((H2013[0][i]) / (H2013[0][i - 1])) / (Math.log10((H2013[1][i]) / (H2013[1][i - 1])));
        double Ha1 = H2013[0][i - 1] * Math.pow((Ba1 / H2013[1][i - 1]), ST2);
        float La1 = PI * (Da - ha1) / (4 * p);
        float Fa1 = La1 * (float) Ha1;
        float ha2 = (D2 - DB - 2 * hp2) / 2;
        float Ba2 = FF / (2 * ha2 * l * kFe);
        i = 0;
        while (Ba2 >= H2013[1][i]) {
            i++;
        }
        ST2 = Math.log10((H2013[0][i]) / (H2013[0][i - 1])) / (Math.log10((H2013[1][i]) / (H2013[1][i - 1])));
        double Ha2 = H2013[0][i - 1] * Math.pow((Ba2 / H2013[1][i - 1]), ST2);
        float La2 = PI * (DB + ha2) / (4 * p);
        if (2 * p == 2) {
            La2 = 2 * ha2;}
        float Fa2 = La2 * (float) Ha2;
        float Fm = Fo + Fz1 + Fz2 + Fa1 + Fa2;
        float km = Fm / Fo;
        float Im = Fm * 2.22F * p / (m * w1 * kob);
        float Imo = Im / I1H;
        float Qp1I = (b11+b12)/2*(hp1-e11-((b12-m11)/2));
        float Qu = 0.00025F*(2*hp1+b11+b12);
        float Qp1II = Qp1I - Qu;
        float kpZ = Nef*Sp1*dI*dI/Qp1II;

        //п.3
        float tcp = PI * (D1 + hp1) / z1;
        float bcp = tcp * yp1;
        float ll1 = (1.16F + 0.14F * p) * bcp + 0.015F;
        float lcp1 = 2 * (ll1 + l);
        float lB1 = (0.19F + 0.1F * p) * bcp + 0.01F;
        float r1 = p75 * w1 * lcp1 / (a1 * Nef * qef);
        float r1o = r1 * I1H / U1;
        float h1 = 0.9F * (h11 - hk);
        float h2 = 0.1F * (h11 - hk);
        float Jp1 = h1 / (3 * b12) + (h2 / b12 + 3 * hk / (b12 + 2 * m11) + e11 / m11);
        float Jl1 = 0.34F * q1 / l * (ll1 - 0.64F * t);
        float kw1 = 1 - 0.033F * m11 * m11 / (O * tz1);
        float Jd1 = 0.9F * tz1 * q1 * q1 * kob * kob * kw1 * (float) kd1[1][j] * (float) kpp1 / (O * ko);
        float J1 = Jp1 + Jd1 + Jl1;
        float x1 = 1.58F * f * l * w1 * w1 * J1 / (100000 * q1 * p);
        float x1o = x1 * I1H / U1;
        float E = (float) ke * U1;
        float xm = E / Im;
        float xmo = xm * I1H / U1;
        float T1 = x1 / xm;
        float p1 = r1 / (x1 + xm);
        //п.4
        float hc = (b21 - b22) *z2/(PI*2);
        float qc = PI / 8 * (b21 * b21 + b22 * b22) + (b21 + b22) * hc / 2;
        float rc = p115 * l / qc;
        float qkl = 0.4F * z2 * qc / (2 * p);
        float hkl = 1.25F * hp2;
        float lkl = qkl / hkl;
        float Dkl = D2 - hkl;
        double Kp2 = 2 * Math.sin(PI * p / z2);
        float rkl = p115 * 2 * PI * Dkl / (z2 * qkl * (float) Kp2 * (float) Kp2);
        float Kp1 = 4 * m * w1 * w1 * kob * kob / z2;
        float r2I = Kp1 * (rc + rkl);
        float r2Io = r2I * I1H / U1;
        float jjjj = (1 - PI * b21 * b21 / (8 * qc)) * (1 - PI * b21 * b21 / (8 * qc));
        float Jp2 = (h21 / 3 / b21 * jjjj + 0.66F - m21 / 2 / b21) + e21 / m21;
        float Jd2 = tz2 / (12 * O * ko);
        double Jl2 = 2.3 * Dkl * Math.log10(4.7 * Dkl / (hkl + 2 * lkl)) / (z2 * l * Kp2 * Kp2);
        float J2 = Jp2 + Jd2 + (float) Jl2;
        float x2 = 7.9F * f * l * J2 * 0.000001F;
        float x2I = Kp1 * x2;
        float x2Io = x2I * I1H / U1 ;
        //п.5
        float Iop = U1 / (xm * (1 + T1) * (1 + p1 * p1));
        float Pcm1 = m * Iop * Iop * r1 * (1 + p1 * p1);
        float Gc1 = 7.8F * PI * (Da - ha1) * ha1 * l * kFe * 1000;
        double Pc1 = 1.7 * p1050 * Math.pow((f / 50), (1 / 0.6667)) * Ba1 * Ba1 * Gc1;
        float Gz1 = 7.8F * z1 * bz1 * hp1 * l * kFe * 1000;
        double Pz1 = 1.7F * p1050 * Math.pow((f / 50), (1 / 0.6667)) * Bz1 * Bz1 * Gz1;
        double Pcsum = Pz1 * (1 + 2 * Math.sqrt(tz1 / 10 * Math.pow((ko - 1), 3))) + Pc1;
        float Po = Pcm1 + (float) Pcsum;
        float kmx = 1.3F * (1 - Da);
        double Pmxsum = kmx * Math.pow((nc / 10), 2) * Math.pow(Da, 4);
        float Ioa = (Po + (float) Pmxsum) / (m * U1);
        double Io = Math.sqrt(Ioa * Ioa + Iop * Iop);
        float coso = Ioa / (float) Io;
        float r1I = r1;
        float x1I = x1 * (1 + T1) * (1 + r1 * p1 / x1);
        float r2II = r2I * (1 + T1) * (1 + T1) * (1 +  p1 * p1);
        float x2II = x2I * (1 + T1) * (1 + T1) * (1 +  p1 * p1);
        float rk = r1I + r2II;
        float xk = x1I + x2II;
        double Zk = Math.sqrt(rk * rk + xk * xk);
        float Pd = 0.005F * Pn / KPD;
        float PB = Pn + (float) Pmxsum + Pd;
        double Rn = m * Math.pow(U1, 2) / 2 / PB - rk + Math.sqrt(Math.pow(m * Math.pow(U1, 2) / 2 / PB - rk, 2) - Zk * Zk);
        double Zn = Math.sqrt((Rn + rk) * (Rn + rk) + xk * xk);
        float Sn = 1 / (1 + (float) Rn / r2II);
        float IcA = Po / (m * U1);
        float I2II = U1 / (float) Zn;
        float I1A = IcA + I2II * (((float) Rn + rk) / (float) Zn + xk * 2 * p1 / (float) Zn);
        float I1R = Iop + I2II * (xk / (float) Zn - ((float) Rn + rk) * 2 * p1 / (float) Zn);
        double Ioo = Math.sqrt(I1A * I1A + I1R * I1R);
        float cos0 = I1A / (float) Ioo;
        float Pcu1 = m * (float) Ioo * (float) Ioo * r1;
        float Pcu2 = m * I2II * I2II * r2II;
        float Psum = Pcu1 + Pcu2 + (float) Pcsum + (float) Pmxsum + Pd;
        float P1 = Pn + Psum;
        float KPDp = (1 - Psum / P1) * 100;
        float P11 = m * I1A * U1;
        float P22 = m * I1H * U1 * cos * KPD;
        // Цикл расчета рабочих характеристик
        for (i = 0; i < n; i++) {
            M9[i] = k * Pn * 60.0F / (2.0F * PI * nc);
            Pd9 = 0.005F * k * Pn / KPD;
            PB9 = k * Pn + (float) Pmxsum + Pd9;
            Rn9 = m * Math.pow(U1, 2) / 2 / PB9 - rk + Math.sqrt(Math.pow(m * Math.pow(U1, 2) / 2 / PB9 - rk, 2) - Zk * Zk);
            Zn9 = Math.sqrt(Math.pow((Rn9 + rk), 2) + xk * xk);
            Sn9[i] = 1 / (1 + (float) Rn9 / r2II);
            I2II9 = U1 / (float) Zn9;
            I1A9 = IcA + I2II9 * (((float) Rn9 + rk) / (float) Zn9 + xk * 2 * p1 / (float) Zn9);
            I1R9 = Iop + I2II9 * (xk / (float) Zn9 - ((float) Rn9 + rk) * 2 * p1 / (float) Zn9);
            Ioo9[i] = Math.sqrt(I1A9 * I1A9 + I1R9 * I1R9);
            cos09[i] = I1A9 / (float) Ioo9[i];
            Pcu19 = m * (float) Ioo9[i] * (float) Ioo9[i] * r1;
            Pcu29 = m * I2II9 * I2II9 * r2II;
            Psum9 = Pcu19 + Pcu29 + (float) Pcsum + (float) Pmxsum + Pd9;
            P19 = Pn + Psum9;
            KPDp9[i] = (1 - Psum9 / P19) * 100;
            k += 0.25F;
        }
        //п.7
        double Zbec = Math.sqrt(r1 * r1 + xk * xk);
        float Rm = (float) Zbec + r1I;
        double Zm = Math.sqrt(2 * Zbec * Rm);
        float KMM = m * U1 * U1 * (1 - Sn) / (2 * Rm * P22);
        //п.8
        //double E115 = 64 * hc * Math.sqrt(f / 50);
        //while (E115 >= fii[1][w]) {
        //    w++;
        // }
        //double ST3 = Math.log10((fii[0][w]) / (fii[0][w - 1])) / (Math.log10((fii[1][w]) / (fii[1][w - 1])));
        //double fi = fii[0][w - 1] * Math.pow((E115 / fii[1][w - 1]), ST3);
        //float hr = hc / (1 + (float) fi);
        //float qr = (PI * b21 * b21 / 8) + (b21 + (b21 + b22) * 0.5F) / 2 * (hr - b21 / 2);
        //float kBT = qc / qr;
        float Jp1pep = 3 * hk / (b12 + 2 * m11) + e11 / m11 + Jd1;
        float Jp2pep = e21 / m21 + Jd2;
        float xpep = x1I * Jp1pep / J1 + x2II * Jp2pep / J2;
        float xpoct = x1I * (J1 - Jp1pep) / J1 + x2II * (J2 - Jp2pep) / J2;
        double Ip2II1 = U1 / Math.sqrt(rk * rk + Math.pow((xpoct + 0.25 * xpep), 2));
        double Ip2II21 = 1240 * O * a1 * xpep * (xpoct + 0.25 * xpep);
        double Ip2II22 = Sp1 * (rk * rk + Math.pow((xpoct + 0.25F * xpep), 2));
        double Ip2II2 = Ip2II21 / Ip2II22;
        float Ip2II = (float) Ip2II1 - (float) Ip2II2;
        float ZKk = U1 / Ip2II;
        double xKk = Math.sqrt(ZKk * ZKk - rk * rk);
        float Ipa1 = I1A + Ip2II * (rk / ZKk + (float) xKk * 2 * p1 / ZKk);
        float Ipp1 = I1R + Ip2II * ((float) xKk / ZKk - rk * 2 * p1 / ZKk);
        double Ip = Math.sqrt(Ipa1 * Ipa1 + Ipp1 * Ipp1);
        float KpyckI = (float) Ip / I1H;
        float Mp = m * Ip2II * Ip2II * r2II * (1 - Sn) / P22;
        //п.9
        float Pep1I = 1.07F * Pcu1 * 2 * l / lcp1;
        float Pel1I = 1.07F * Pcu1 * 2 * ll1 / lcp1;
        float OpoB = KI * (Pep1I + (float) Pcsum) / (PI * D1 * l * (float) A1);
        float Pp1 = 2 * hp1 + b11 + b12;
        float Ouzp1 = Pep1I / z1 / Pp1 / tz1 * (0.001538F + (b11 + b12) / 19.2F);
        float Ouzl1 = Pel1I / 2 / z1 / Pp1 / ll1 * (0.0001538F + hp1 / 14.4F);
        float OpoBl1 = KI * Pel1I / (2 * PI * D1 * lB1 * (float) A1);
        float O1I = ((OpoB + Ouzp1) * 2 * l + (Ouzl1 + OpoBl1) * 2 * ll1) / lcp1;
        float PBsumI = Psum - (1 - KI) * (Pep1I + (float) Pcsum);
        float Skop = (PI * Da + 8 * (float) Pp) * (l + 2 * lB1);
        float OB = PBsumI / Skop / (float) AB;
        float O1 = O1I + OB;

        result.setRes(O1);
        result.setR_name(initialData.getName());
        result.setR_Pn(Pn);
        result.setR_U1(U1);
        result.setR_f(f);
        result.setR_Im(Im);
        result.setR_Imo(Imo);
        result.setR_kpZ(kpZ);
        result.setR_r1(r1);
        result.setR_r1o(r1o);
        result.setR_x1(x1);
        result.setR_x1o(x1o);
        result.setR_xm(xm);
        result.setR_xmo(xmo);
        result.setR_r2I(r2I);
        result.setR_r2Io(r2Io);
        result.setR_x2I(x2I);
        result.setR_x2Io(x2Io);
        result.setR_KMM(KMM);
        result.setR_KpyckI(KpyckI);
        result.setR_Mp(Mp);
        result.setR_O1(O1);
        result.setR_PnK0(0.25F*Pn);
        result.setR_PnK1(0.5F*Pn);
        result.setR_PnK2(0.75F*Pn);
        result.setR_PnK3(Pn);
        result.setR_PnK4(1.25F*Pn);

        result.setR_M90(M9[0]);
        result.setR_M91(M9[1]);
        result.setR_M92(M9[2]);
        result.setR_M93(M9[3]);
        result.setR_M94(M9[4]);

        result.setR_Sn90(Sn9[0]);
        result.setR_Sn91(Sn9[1]);
        result.setR_Sn92(Sn9[2]);
        result.setR_Sn93(Sn9[3]);
        result.setR_Sn94(Sn9[4]);
        result.setR_Ioo90((float)Ioo9[0]);
        result.setR_Ioo91((float)Ioo9[1]);
        result.setR_Ioo92((float)Ioo9[2]);
        result.setR_Ioo93((float)Ioo9[3]);
        result.setR_Ioo94((float)Ioo9[4]);
        result.setR_cos090(cos09[0]);
        result.setR_cos091(cos09[1]);
        result.setR_cos092(cos09[2]);
        result.setR_cos093(cos09[3]);
        result.setR_cos094(cos09[4]);
        result.setR_KPDp90(KPDp9[0]);
        result.setR_KPDp91(KPDp9[1]);
        result.setR_KPDp92(KPDp9[2]);
        result.setR_KPDp93(KPDp9[3]);
        result.setR_KPDp94(KPDp9[4]);

        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
