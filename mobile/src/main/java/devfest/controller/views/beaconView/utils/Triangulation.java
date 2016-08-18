package devfest.controller.views.beaconView.utils;


import devfest.controller.views.beaconView.models.Beacon;

public class Triangulation {

    private double x1;
    private double y1;
    private double radius1;

    private double x2;
    private double y2;
    private double radius2;

    public Triangulation(Beacon beacon1, Beacon beacon2) {
        x1 = beacon1.getxCoordinate();
        y1 = beacon1.getyCoordinate();
        radius1 = beacon1.getDeviceDistToBeacon();

        x2 = beacon2.getxCoordinate();
        y2 = beacon2.getyCoordinate();
        radius2 = beacon2.getDeviceDistToBeacon();
    }

//        x:=(1/2)*((y1-y2)*sqrt(-(-x1^2+2*x2*x1-x2^2+(-c+a-y1+y2)*(-c+a+y1-y2))*(-x1^2+2*x2*x1-x2^2
//    +(c+a-y1+y2)*(c+a+y1-y2))*(x1-x2)^2)
//            +(x1^3-x1^2*x2+(y2^2-2*y1*y2-c^2+y1^2+a^2-x2^2)*x1-x2*
//    (a^2-c^2-x2^2-y2^2+2*y1*y2-y1^2))*(x1-x2))/
//            ((x1-x2)*(x1^2-2*x2*x1+x2^2+(y1-y2)^2));
//
//    y := (-sqrt(-(-x1^2+2*x2*x1-x2^2+(-c+a-y1+y2)*(-c+a+y1-y2))*(-x1^2+2*x2*x1-x2^2+(c+a-y1+y2)
//    *(c+a+y1-y2))*(x1-x2)^2)
// +y1^3-y1^2*y2+(a^2+x1^2-c^2+x2^2-2*x2*x1-y2^2)*y1+y2^3+
//    (x2^2-2*x2*x1+c^2-a^2+x1^2)*y2)/(2*y1^2-4*y1*y2+2*y2^2+2*(x1-x2)^2);


    public double getX() {
        double part3 = (x1 - x2) * (Math.pow(x1, 2) - 2 * x2 * x1 + Math.pow(x2, 2) + Math.pow(y1 - y2, 2));

        return 1 / 2 * ((y1 - y2) * Math.sqrt(-(Math.pow(-x1, 2) + 2 * x2 * x1 - Math.pow(x2, 2)
                + (-radius1 + radius2 - y1 + y2) * (-radius1 + radius2 + y1 - y2))
                * (Math.pow(-x1, 2) + 2 * x2 * x1 - Math.pow(x2, 2)
                + (radius1 + radius2 - y1 + y2) * (radius1 + radius2 + y1 - y2)) * Math.pow(x1 - x2, 2))
                + (Math.pow(x1, 3) - Math.pow(x1, 2) * x2
                + (Math.pow(y2, 2) - 2 * y1 * y2 - Math.pow(radius1, 2) + Math.pow(y1, 2)
                + Math.pow(radius2, 2) - Math.pow(x2, 2)) * x1 - x2
                * (Math.pow(radius2, 2) - Math.pow(radius1, 2) - Math.pow(x2, 2)
                - Math.pow(y2, 2) + 2 * y1 * y2 - Math.pow(y1, 2))) * (x1 - x2)) / part3;
    }

    public double getY() {
        return -Math.sqrt((Math.pow(-x1, 2) + 2 * x2 * x1 - Math.pow(x2, 2)
                + (-radius1 + radius2 - y1 + y2) * (-radius1 + radius2 + y1 - y2))
                * (Math.pow(-x1, 2) + 2 * x2 * x1 - Math.pow(x2, 2)
                + (radius1 + radius2 - y1 + y2) * (radius1 + radius2 + y1 - y2)) * Math.pow(x1 - x2, 2))
                + Math.pow(y1, 3) - Math.pow(y1, 2) * y2 + (Math.pow(radius2, 2) + Math.pow(x1, 2)
                - Math.pow(radius1, 2) + Math.pow(x2, 2) - 2 * x2 * x1 - Math.pow(y2, 2)) * y1
                + Math.pow(y2, 3) + (Math.pow(x2, 2) - 2 * x2 * x1 + Math.pow(radius1, 2)
                - Math.pow(radius2, 2) + Math.pow(x1, 2)) * y2
                / (2 * Math.pow(y1, 2) - 4 * y1 * y2 + 2 * Math.pow(y2, 2) + 2 * Math.pow(x1 - x2, 2));
    }
}
