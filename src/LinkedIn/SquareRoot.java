package LinkedIn;

public class SquareRoot {
    final double T = 0.0001;
    public double mySqrt2(double x) {
        if(x<0){
            return -1;
        }
        if(x==0){
            return 0;
        }
        if(x<1.0){
            return fractionHelper(x);
        }
        return helper(x);
    }
    public double fractionHelper(double x){
        double start = 0.0;
        double end = 1.0;
        while(start<=end){
            double mid = start + ((end-start)/2.0);
            if(mid>x/mid){
                end = mid - (end-mid)/2.0;
            }else if(mid<x/mid){
                start = mid + (mid-start)/2.0;
            }else{
                return mid;
            }
            if(Math.abs(mid-T)<0.0001){
                return mid;
            }
        }
        return Math.abs(end-x)-Math.abs(start-x)>0.0?start:end;
    }

    public double helper(double x){
        double start = 1.0;
        double end = 100;
        while(start<=end){
            double mid = start + ((end-start)/2.0);
            if(mid>x/mid){
                end = mid - 1;
            }else if(mid<x/mid){
                start = mid + 1;
            }else{
                return mid;
            }
        }
        return Math.abs(end-x)-Math.abs(start-x)>0.0?start:end;
    }

    public double newton(double x){
        // read in the command-line argument
        double epsilon = 1.0e-15;  // relative error tolerance
        double t = x;              // estimate of the square root of c

        // repeatedly apply Newton update step until desired precision is achieved
        while (Math.abs(t - x/t) > epsilon*t) {
            t = (x/t + t) / 2.0;
        }

        // print out the estimate of the square root of c
//        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        SquareRoot sr = new SquareRoot();
        double x = 0.17;
        System.out.println(sr.fractionHelper(x));
        System.out.println(sr.newton(x));
    }
}

