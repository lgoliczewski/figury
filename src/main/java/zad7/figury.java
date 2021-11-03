package zad7;

class BledneDane extends Exception {}
class BledneDaneCzworokata extends Exception {}

abstract class Figura {
    public abstract double Obwod();
    public abstract double Pole();
}

class Kolo extends Figura {
    private double promien;
    public double Obwod() {
        return 2*Math.PI*promien;
    }
    public double Pole() {
        return Math.PI*promien*promien;
    }
    Kolo(double promien) throws BledneDane {
        if(promien <= 0) throw new BledneDane();
        this.promien = promien;
    }
}

class Kwadrat extends Figura {
    private double bok;
    public double Obwod() {
        return 4*bok;
    }
    public double Pole() {
        return bok*bok;
    }
    Kwadrat(double bok) throws BledneDaneCzworokata {
        if(bok <= 0) throw new BledneDaneCzworokata();
        this.bok = bok;
    }
}

class Prostokat extends Figura {
    private double bok1;
    private double bok2;
    public double Obwod() {
        return 2*bok1+2*bok2;
    }
    public double Pole() {
        return bok1*bok2;
    }
    Prostokat(double bok1, double bok2) throws BledneDaneCzworokata {
        if(bok1 <= 0 || bok2 <= 0) throw new BledneDaneCzworokata();
        this.bok1 = bok1;
        this.bok2 = bok2;
    }
}

class Romb extends Figura {
    private double bok;
    private double kat;
    public double Obwod() {
        return 4*bok;
    }
    public double Pole() {
        return bok*bok*Math.sin(Math.toRadians(kat));
    }
    Romb(double bok, double kat) throws BledneDaneCzworokata {
        if(bok <= 0 || kat <= 0 || kat >= 180) throw new BledneDaneCzworokata();
        this.bok = bok;
        this.kat = kat;
    }
}

public class figury {

    public static void main(String[] args) {

        String a = args[0];
        int arg = 1;
        for (int j = 0; j < a.length(); j++) {
            if (a.charAt(j) == 'o') arg++;
            else if (a.charAt(j) == 'c') arg += 5;
        }
        if (arg == args.length) {
            Figura[] f = new Figura[a.length()];
            int i = 1;
            double x, y;
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) == 'o' || a.charAt(j) == 'c') {
                    try {
                        x = Double.parseDouble(args[i]);
                        if (a.charAt(j) == 'o') {
                            f[j] = new Kolo(x);
                            i++;
                            System.out.println("Kolo (promien = " + x + "):");
                        } else if (a.charAt(j) == 'c') {
                            if (args[i].equals(args[i + 1]) && args[i].equals(args[i + 2]) && args[i].equals(args[i + 3])) {
                                y = Double.parseDouble(args[i + 4]);
                                if (y == 90) {
                                    f[j] = new Kwadrat(x);
                                    System.out.println("Kwadrat (bok = " + x + "):");
                                } else {
                                    f[j] = new Romb(x, y);
                                    System.out.println("Romb (bok = " + x + ", kat = " + y + "):");
                                }
                            } else if (args[i].equals(args[i + 1]) && args[i + 2].equals(args[i + 3]) && args[i + 4].equals("90")) {
                                y = Double.parseDouble(args[i + 2]);
                                f[j] = new Prostokat(x, y);
                                System.out.println("Prostokat (bok1 = " + x + ", bok2 = " + y + "):");
                            } else
                                System.out.println(args[i] + "," + args[i + 1] + "," + args[i + 2] + "," + args[i + 3] + "," + args[i + 4] + " - z tych danych nie da sie utworzyc kwadratu, rombu ani prostokata");
                            i += 5;
                        }
                        if (f[j] != null) {
                            System.out.println("\tPole = " + f[j].Pole());
                            System.out.println("\tObwod = " + f[j].Obwod());
                        }


                    } catch (NumberFormatException ex) {
                        System.out.println("Nieprawidlowe dane");
                        if (a.charAt(j) == 'c') {
                            i += 5;
                        } else {
                            i++;
                        }
                    } catch (BledneDane e) {
                        System.out.println(args[i] + " - Liczba spoza zakresu (bok>0, promien>0)");
                        i++;
                    } catch (BledneDaneCzworokata e) {
                        System.out.println(args[i] + "," + args[i + 1] + "," + args[i + 2] + "," + args[i + 3] + "," + args[i + 4] + " - min. jedna z tych liczb jest poza zakresem (boki>0, 180>kat>0)");
                        i += 5;
                    }

                } else System.out.println(a.charAt(j) + " - ten znak nie odpowiada zadnej figurze geometrycznej");
            }

        } else {
            System.out.println("Nieopdowiednia liczba parametrow");
        }
    }
}
