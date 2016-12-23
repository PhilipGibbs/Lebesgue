import java.io.*;
import java.util.*;
import java.text.*;


public class Lebesgue {

   /* calculates area for best slant cover with pieces removed near E2 and C2 */

   static double hex[][];
   static double dod[][];          // the dodocehedron for cutting off C and E
   static double dod0[][];         // the regular dodecahedron
   static double dodm[][];         // the mirror image
   static double e2[][];           // region removed near E2;
   static double c2[][];           // region removed near c2;

   static DecimalFormat af = new DecimalFormat("#.############");
   static DecimalFormat af1 = new DecimalFormat("#.###");

   private static double Q[];
   private static double M[];

   static double delta;

   public static void main(String[] args) {

      double minarea = 1.0;
      double minangle = 0.0;

      for(double a=1.54; a<1.56; a+=0.00001) {
         double p[][] = cover( a );

         double area1 = (2.0*polygonArea(hex)+polygonArea( dod0 ))/3.0;  // pals area
         double das = (polygonArea( dod ) - polygonArea( dod0 ))/3.0;    // area added by slant 
         double area2 = area1+das;   // modifed pals area
         
         double area = polygonArea( p );

         area += segmentArea(p[1],p[2]);
         area += segmentArea(p[3],p[4]);
         area += segmentArea(p[7],p[8]);
         area += segmentArea(p[11],p[12]);
         area += segmentArea(p[12],p[13]);
         for(int i=15;i<p.length; i++) {
            area += segmentArea(p[i-1],p[i]);
         }

         double areae2 = polygonArea(e2)-segmentArea(p[1],p[2])-segmentArea(p[3],p[4]);
         double areac2 = polygonArea(c2)-segmentArea(p[7],p[8]);

         double ang1 = angle(Q,p[13],p[14]);

         if(area < minarea) {
            minarea = area;
            minangle = a;
         }
 
         System.out.println(af1.format(a)+"\t"+af.format(delta)+"\t"+af.format(area)+"\t"+af.format(ang1));

         double w[] = image(p[14]);


      }

      System.err.println("minimum area = "+minarea+" at angle "+minangle);

   }

   public static double xminussinx(double x) {

      double t = -x;
      double y = 0.0;
      double n = 3;

      while( y+t != y ) {
         t = -t*x*x/(n*(n-1));
         y += t;
         n += 2;
      }

      return y;
   }

   public static double[] image(double a[]) {

      double b[] = radialPoint(dod[2], dod[3], a);  // point on ED at distance one
      double c[] = radialPoint(dod[5], dod[4], a);  // point on CD at distance one
      double d[] = radialPoint(dod[1], dod[0], c);  // point on EF
      double e[] = triangulate(d,b);                // image point in B
      double m[] = midPoint(hex[1],hex[2]);         // midpoint of side ED
      double n[] = midPoint(hex[4],hex[5]);         // midpoint of side AB
      double f[] = reflect(m,n,e);                  // reflect in line mn

      return f;
   }

   public static double[] reflect(double m[], double n[], double x[]) {

      double c[] = nearestPoint(m,n,x);

      double y[] = {2.0*c[0]-x[0], 2.0*c[1]-x[1]};

      return y;
   }

   public static double[][] cover(double slant) {

     int n=20000;

     hex = hexagon();
     dod = dodecagon(slant);    // the dodocehedron for cutting off C and E
     dod0 = dodecagon(0.0);     // the regular dodecahedron
     dodm = dodecagon(-slant);  // the mirror image

     double cov[][] = new double[15+n][2];

     cov[0] = hex[0];                                  // point F1 

     // area removed near E2


     cov[1] = dodm[1];                                 // point on edge F1E1 nearest C3
     cov[2] = radialPoint(dod0[2],dod0[1],dod[6]);     // point at distance one from C3 on regular dodecahedron
     double X[] = meet(dod[0], dod[11], dodm[0], dodm[11]);  // meeting point near F
     double di = dist(X,dod[6]);
     double Y[] = radialPoint(dod[7], dod[6], X);
     cov[3] = triangulate(Y,dod[8]);                   // point at distance one from C3 and B3
     cov[4] = nearestPoint(dod[1],dod[2],dod[8]);      // point on edge E2E3 nearest B3

     e2 = new double[5][2];
     e2[0] = dod[1];
     e2[1] = cov[4];
     e2[2] = cov[3];
     e2[3] = cov[2];
     e2[4] = cov[1];

     cov[5] = dod[2];                                  // point E3
     cov[6] = hex[2];                                  // point D1

     // area removed near C2

     double G[] = radialPoint(hex[2],hex[3],dod[0]);   // point G on D1C1 at distance one from F3
     cov[7] = G;
     cov[8] = nearestPoint(dod[5],dod[6],dod[0]);      // point K on C2C3 nearest F3

     c2 = new double[3][2];
     c2[0] = dod[5];
     c2[1] = cov[8];
     c2[2] = cov[7];


     cov[9] = dod[6];                                  // point C3
     cov[10] = hex[4];                                 // point B1

     // area removed near A1

     cov[11] = dodm[9];                                // point on edge B1A1 nearest E3
     Q = dodm[5];                                      // point Q one edge D1C1 nearest A3
     cov[12] = triangulate(dod[2],Q);                  // point X at distance one from E3 and Q
     M = midPoint(hex[1],hex[2]);                      // midpoint of E1D1
     cov[14] = triangulate(M,dod0[5]);                 // midpoint Y at distance one from M and point on dodec0
     double ang1 = angle(Q,cov[12],cov[14]);
     if(ang1 >= 90.0) {
        cov[13] = cov[12];
     } else {
        cov[13] = right(Q,cov[14]);
     }

     //System.err.println(dist(cov[14],cov[13])+" "+dist(cov[14],cov[12]));

     double l1[] = radialPoint(dodm[9],dodm[10],G);
     double l2[] = image(cov[13]);
     for(int i=1; i<n; i++) {
        double s = ((double)i)/((double)(n-1));
        cov[14+i] = image(interp(cov[13],cov[14],s));
     }

     //if(dist(l1,cov[16]) < dist(l2,cov[16])) cov[16]=l2;
     //System.err.println(dist(l2,cov[16])+" "+dist(l1,cov[16]));

     cov[14+n] = nearestPoint(hex[5],hex[0],G);          // point on A1F1 nearest G

     delta = dist(l2,cov[14+n])-dist(l1,cov[14+n]);
        
     return cov;

   }

   public static double[][] hexagon() {
    
      // returns a regular hexagon
 
      double hex[][] = new double[6][2];                // six (x,y) points

      hex[0] = edgePoint(30.0);                         // first point F1 is on left edge of hexagon    

      // the remaining five points are constructed by rotations of 60 degrees

      for(int i=1; i<6; i++) {
         hex[i] = rotate(hex[i-1], 60.0);
      }

      return hex;
   }

   public static double[][] dodecagon(double slant) {
    
      // returns a twelve sided polygon formed by cutting off corners from a regular
      // hexagon with cuts tangent to an incribed curcle of unit diameter at a slanted angle
 
      double dod[][] = new double[12][2];               // twelve (x,y) points

      dod[0] = edgePoint(15.0-slant*0.5);               // first point F3 is on left edge of hexagon    

      dod[1] = edgePoint(-15.0-slant*0.5);              // second point E2 is also on left edge of hexagon

      // the remaining ten points are constructed by rotations of 60 degrees

      for(int i=2; i<12; i++) {
         dod[i] = rotate(dod[i-2], 60.0);
      }

      return dod;
   }

   public static double[] edgePoint(double angle) {

      // a point on the left edge of a hexagon at an angle from the centre

      // angle in radians

      double angler = angle*Math.PI/180.0;
     
      double e[] = {-0.5, 0.5*Math.tan(angler)};

      return e;
   }

   public static double[] triangulate( double a[], double b[] ) {

      // finds a point of distance 1 from a and b

      double d = dist(a, b);

      if(d >= 1.0) {
         System.err.println("points too far apart to trianglulate");
         return a;
      }

      double dx = (b[0] - a[0])/d;
      double dy = (b[1] - a[1])/d;
      double mx = (a[0] + b[0])*0.5;
      double my = (a[1] + b[1])*0.5;
      double l = Math.sqrt(1.0-d*d*0.25);

      double t[] = {mx - dy*l, my + dx*l}; 

      return t;
   }

   public static double[] nearestPoint( double a[], double b[], double c[] ) {

      // find the point on line through a and b, which is nearest to c
      // a point on the segment is given by a(1-s) + bs for parameter s
      // diastance from c is (a-b)^2 s^2 - 2(a-b).(a-c) s + (a-c)^2 

      double ab[] = {a[0]-b[0],a[1]-b[1]};
      double ac[] = {a[0]-c[0],a[1]-c[1]};

      double A = dot(ab,ab);
      double B = dot(ab,ac);
      double C = dot(ac,ac)-1.0;

      // find minimum of As^2 - 2Bs + C = 0

      double s = B/A;

      double d[] = {a[0]*(1.0-s) + b[0]*s, a[1]*(1.0-s) + b[1]*s};

      return d;
   }

   public static double[] radialPoint( double a[], double b[], double c[] ) {

      // find the point on line through a and b, near b which is at distance one from c
      // a point on the segment is given by a(1-s) + bs for parameter s
      // diastance squared from c is (a-b)^2 s^2 - 2(a-b).(a-c) s + (a-c)^2 = 1

      double ab[] = {a[0]-b[0],a[1]-b[1]};
      double ac[] = {a[0]-c[0],a[1]-c[1]};

      double A = dot(ab,ab);
      double B = dot(ab,ac);
      double C = dot(ac,ac)-1.0;

      // solve As^2 - 2Bs + C = 0

      double s = (B + Math.sqrt(B*B - A*C))/A;

      double d[] = {a[0]*(1.0-s) + b[0]*s, a[1]*(1.0-s) + b[1]*s};

      return d;
   }

   public static double[] midPoint( double a[], double b[] ) {

      double m[] = {0.5*(a[0]+b[0]), 0.5*(a[1]+b[1])};

      return m;
   }

   public static double[] rotate( double a[], double angle ) {

      // rotate a point an angle about the origin

      double angler = angle*Math.PI/180.0;   // angle in radians

      double cos = Math.cos(angler);
      double sin = Math.sin(angler);

      double b[] = {cos*a[0] - sin*a[1], sin*a[0] + cos*a[1]};

      return b;
   }

   public static double polygonArea( double p[][] ) {

      // compute the area of a polygon

      int n = p.length;       // number of points

      double area = 0.0;

      for(int i=0; i<n; i++) {
         int j = (i+1)%n;         // plus one mod n
         area += 0.5*cross(p[i],p[j]);
      }          

      return area;
   }

   public static double segmentArea( double a[], double b[] ) {

      // area of a segment of a circle of radius one on the chord from a to b

      double d = dist(a,b);

      double theta = 2.0*Math.asin(0.5*d);

      double area = 0.5*(xminussinx(theta));

      return area;
   }   

   public static double cross( double a[], double b[] ) {

      // 2d cross product

      double c = a[0]*b[1] - a[1]*b[0];
  
      return c;
   }

   public static double dot( double a[], double b[] ) {

      // 2d dot product

      double d = a[0]*b[0] + a[1]*b[1];
  
      return d;
   }

   public static double dist( double a[], double b[] ) {

      // distance from a to b

      double dx = a[0]-b[0];
      double dy = a[1]-b[1];

      double d = Math.sqrt(dx*dx + dy*dy);

      return d;
   }

   public static double[] meet( double a[], double b[], double c[], double d[]) {

      // find the point where the line segment ab meets the line segment cd

      double ab[] = diff(a,b);
      double cd[] = diff(c,d);
      double db[] = diff(d,b);
      double s = cross(db,cd)/cross(ab,cd);
      double m[] = interp(a,b,s);

      return m;
   }

   public static double[] interp( double a[], double b[], double s ) {

      double m[] = {s*a[0]+(1.0-s)*b[0], s*a[1]+(1.0-s)*b[1]};

      return m;
   }

   public static double[] diff( double a[], double b[]) {

      double d[] = {a[0] - b[0], a[1] - b[1]};
      
      return d;
   }

   public static double angle( double a[], double b[], double c[]) {

      double ba0 = b[0] - a[0];
      double ba1 = b[1] - a[1];
      double bc0 = b[0] - c[0];
      double bc1 = b[1] - c[1];

      double badotbc = ba0*bc0 + ba1*bc1;
      double cosabc = badotbc/(dist(a,b)*dist(b,c));

      double abc = Math.acos(cosabc)*180.0/Math.PI;

      return abc;
   }

   public static double[] right(double a[], double b[]) {

      // finds a point at distance one from a that makes a right angle with b

      double dab = dist(a,b);
      double dxb = Math.sqrt(dab*dab-1.0);
      double sn = (dxb*dxb)/(dab*dab);
      double cs = dxb/(dab*dab);

      double ab[] = diff(a,b);
      double x[] = {b[0]+ab[0]*sn-ab[1]*cs, b[1]+ab[0]*cs+ab[1]*sn};

      return x;
   }

}