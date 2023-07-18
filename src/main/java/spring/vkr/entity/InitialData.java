package spring.vkr.entity;

import javax.persistence.*;


@Entity
@Table(name = "dannye")
public class InitialData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "st1")
    private int st1;

    @Column(name = "st2")
    private int st2;

    @Column(name = "name")
    private String name;

    @Column(name = "Pn")
    private int Pn;

    @Column(name = "nc")
    private int nc;

    @Column(name = "U1")
    private int U1;

    @Column(name = "f")
    private float f;

    @Column(name = "KPD")
    private float KPD;

    @Column(name = "cos")
    private float cos;

    @Column(name = "h")
    private float h;

    @Column(name = "DB")
    private float DB;

    @Column(name = "Da")
    private float Da;

    @Column(name = "D1")
    private float D1;

    @Column(name = "l1")
    private float l1;

    @Column(name = "z1")
    private int z1;

    @Column(name = "b11")
    private float b11;

    @Column(name = "b12")
    private float b12;

    @Column(name = "h11")
    private float h11;

    @Column(name = "e11")
    private float e11;

    @Column(name = "m11")
    private float m11;

    @Column(name = "l2")
    private float l2;

    @Column(name = "O")
    private float O;

    @Column(name = "z2")
    private int z2;

    @Column(name = "bck")
    private float bck;

    @Column(name = "b21")
    private float b21;

    @Column(name = "b22")
    private float b22;

    @Column(name = "h21")
    private float h21;

    @Column(name = "e21")
    private float e21;

    @Column(name = "m21")
    private float m21;

    @Column(name = "Sp1")
    private int Sp1;

    @Column(name = "a1")
    private int a1;

    @Column(name = "dI")
    private float dI;

    @Column(name = "qef")
    private float qef;

    @Column(name = "Nef")
    private int Nef;

    @Column(name = "yp1")
    private int yp1;

    public InitialData(int id, int st1, int st2, String name, int pn, int nc, int u1, float f, float KPD, float cos, float h, float DB, float da, float d1, float l1, int z1, float b11, float b12, float h11, float e11, float m11, float l2, float o, int z2, float bck, float b21, float b22, float h21, float e21, float m21, int sp1, int a1, float dI, float qef, int nef, int yp1) {
        this.id = id;
        this.st1 = st1;
        this.st2 = st2;
        this.name = name;
        Pn = pn;
        this.nc = nc;
        U1 = u1;
        this.f = f;
        this.KPD = KPD;
        this.cos = cos;
        this.h = h;
        this.DB = DB;
        Da = da;
        D1 = d1;
        this.l1 = l1;
        this.z1 = z1;
        this.b11 = b11;
        this.b12 = b12;
        this.h11 = h11;
        this.e11 = e11;
        this.m11 = m11;
        this.l2 = l2;
        O = o;
        this.z2 = z2;
        this.bck = bck;
        this.b21 = b21;
        this.b22 = b22;
        this.h21 = h21;
        this.e21 = e21;
        this.m21 = m21;
        Sp1 = sp1;
        this.a1 = a1;
        this.dI = dI;
        this.qef = qef;
        Nef = nef;
        this.yp1 = yp1;
    }

    public float getdI() {
        return dI;
    }

    public void setdI(float dI) {
        this.dI = dI;
    }
    public int getSt1() {
        return st1;
    }

    public void setSt1(int st1) {
        this.st1 = st1;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public float getL1() {
        return l1;
    }

    public void setL1(float l1) {
        this.l1 = l1;
    }

    public int getZ1() {
        return z1;
    }

    public void setZ1(int z1) {
        this.z1 = z1;
    }

    public int getSt2() {
        return st2;
    }

    public void setSt2(int st2) {
        this.st2 = st2;
    }

        public float getB11() {
        return b11;
    }

    public void setB11(float b11) {
        this.b11 = b11;
    }

    public float getB12() {
        return b12;
    }

    public void setB12(float b12) {
        this.b12 = b12;
    }

    public float getH11() {
        return h11;
    }

    public void setH11(float h11) {
        this.h11 = h11;
    }

    public float getE11() {
        return e11;
    }

    public void setE11(float e11) {
        this.e11 = e11;
    }

    public float getM11() {
        return m11;
    }

    public void setM11(float m11) {
        this.m11 = m11;
    }

    public float getL2() {
        return l2;
    }

    public void setL2(float l2) {
        this.l2 = l2;
    }

    public float getO() {
        return O;
    }

    public void setO(float o) {
        O = o;
    }

    public int getZ2() {
        return z2;
    }

    public void setZ2(int z2) {
        this.z2 = z2;
    }

    public float getBck() {
        return bck;
    }

    public void setBck(float bck) {
        this.bck = bck;
    }

    public float getB21() {
        return b21;
    }

    public void setB21(float b21) {
        this.b21 = b21;
    }

    public float getB22() {
        return b22;
    }

    public void setB22(float b22) {
        this.b22 = b22;
    }

    public float getH21() {
        return h21;
    }

    public void setH21(float h21) {
        this.h21 = h21;
    }

    public float getE21() {
        return e21;
    }

    public void setE21(float e21) {
        this.e21 = e21;
    }

    public float getM21() {
        return m21;
    }

    public void setM21(float m21) {
        this.m21 = m21;
    }

    public int getSp1() {
        return Sp1;
    }

    public void setSp1(int sp1) {
        Sp1 = sp1;
    }

    public int getA1() {
        return a1;
    }

    public void setA1(int a1) {
        this.a1 = a1;
    }

    public float getQef() {
        return qef;
    }

    public void setQef(float qef) {
        this.qef = qef;
    }

    public int getNef() {
        return Nef;
    }

    public void setNef(int nef) {
        Nef = nef;
    }

    public int getYp1() {
        return yp1;
    }

    public void setYp1(int yp1) {
        this.yp1 = yp1;
    }

    public float getKPD() {
        return KPD;
    }

    public void setKPD(float KPD) {
        this.KPD = KPD;
    }

    public float getCos() {
        return cos;
    }

    public void setCos(float cos) {
        this.cos = cos;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getDB() {
        return DB;
    }

    public void setDB(float DB) {
        this.DB = DB;
    }

    public float getDa() {
        return Da;
    }

    public void setDa(float da) {
        Da = da;
    }

    public float getD1() {
        return D1;
    }

    public void setD1(float d1) {
        D1 = d1;
    }

    public InitialData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPn() {
        return Pn;
    }

    public void setPn(int pn) {
        Pn = pn;
    }

    public int getNc() {
        return nc;
    }

    public void setNc(int nc) {
        this.nc = nc;
    }

    public int getU1() {
        return U1;
    }

    public void setU1(int u1) {
        U1 = u1;
    }
}