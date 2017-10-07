/**
 * Created by lukas on 7.10.2017.
 */
public class Main {
    private static final double height = 1.7;       //1.7
    private static final double throwForce = 20;
    private static final double gravitation = 10;
    private static final double initialAngle = 50;
    private static final double finalAngle = 0;
    private static final double precision = 0.01;

    public static void main(String[] args) {
        double angle = initialAngle;
        double[] idealAngle = new double[2];        //idealAngle[0] = angle; idealAngle[1] = distance
        idealAngle[0] = initialAngle;
        idealAngle[1] = 0;
        while (angle >= finalAngle) {
            double distance = calculateDistance(angle);
            if (distance > idealAngle[1]) {
                idealAngle[0] = angle;
                idealAngle[1] = distance;
            }
            angle -= precision;
        }
        System.out.println("The ideal angle for throwing from " + height + " meters is " + idealAngle[0] + " degrees.");
    }

    private static double calculateDistance(double angle) {
        double radianAngle = Math.toRadians(angle);
        double verticalSpeed = Math.sin(radianAngle) * throwForce;
        double horizontalSpeed = Math.cos(radianAngle) * throwForce;

        double timeInAir = getTimeInAir(verticalSpeed);
        double distance = horizontalSpeed * timeInAir;

        //System.out.println("Height = " + height + "; angle = " + angle + "; distance = " + distance);

        return distance;
    }

    private static double getTimeInAir(double speed) {
        double timeIncreasingHeight = speed / gravitation;      //only works when the angle is >= 0
        double maxHeight = height + 0.5 * gravitation * Math.pow(timeIncreasingHeight, 2);
        double timeDecreasingHeight = Math.sqrt(maxHeight / (gravitation * 0.5));
        double time = timeIncreasingHeight + timeDecreasingHeight;

        return time;
    }
}
